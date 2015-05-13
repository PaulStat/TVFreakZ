import csv
import urllib
import os
import shutil
import sys
import MySQLdb
import time
import logging
import urllib
import urllib2
import platform
import cStringIO
import logging.config

from time import gmtime, strftime
from datetime import datetime, timedelta

__metaclass__ = type

# Radio times data is occasionally bad i.e. incorrect number
# of fields or large amounts of blank lines, in order to detect  
# this we need access to the number of fields in a row, so we
# have a ChanDictReader which gives access to lf and lr.
class ChanDictReader(csv.DictReader):
    def __init__(self, f, fieldnames=None, restkey=None, restval=None,
                 dialect="excel", *args, **kwds):
        csv.DictReader.__init__(self, f, fieldnames, restkey, restval,
                 dialect, *args, **kwds)
        self.lf = 0
        self.lr = 0

    def next(self):
        if self.line_num == 0:
            # Used only for its side effect.
            self.fieldnames
        row = self.reader.next()
        self.line_num = self.reader.line_num

        # unlike the basic reader, we prefer not to return blanks,
        # because we will typically wind up with a dict full of None
        # values
        while row == []:
            row = self.reader.next()
        d = dict(zip(self.fieldnames, row))
        self.lf = len(self.fieldnames)
        self.lr = len(row)
        if self.lf < self.lr:
            d[self.restkey] = row[self.lf:]
        elif self.lf > self.lr:
            for key in self.fieldnames[self.lr:]:
                d[key] = self.restval
        return d

#Main class for integrating with different processes and invoking them.
class Processing:

   def __init__(self):
      self._dataURL  = "http://xmltv.radiotimes.com/xmltv/"
      self._inputDir = "./input/"
      self._logsDir = "./logs/"
      self._channelsData = {}
      self._performers = []
      self._progFiles = []
      self._progPerformers = {}
      self._channelPrograms = []
      logging.config.fileConfig(self._logsDir+'logging.conf')
      self._logger = logging.getLogger('email')
      self._dbase = MySQLdb.connect(user="test",passwd="test",db="tvlistings")
      self._cursor = self._dbase.cursor()

   def setupEnvironment(self):
      try:
          # Create temporary tables for loading buffered data
          self._cursor.execute("""CREATE TEMPORARY TABLE TVTEMPTABLE (
                                  PROGTITLE VARCHAR(200), SUBTITLE VARCHAR(200),
                                  EPISODE VARCHAR(200), YR YEAR, DIRECTOR VARCHAR(200),
                                  PERFORMERS TEXT, PREMIERE BOOL, FILM BOOL, RPEAT BOOL,
                                  SUBTITLES BOOL, WIDESCREEN BOOL, NEWSERIES BOOL, DEAFSIGNED BOOL,
                                  BNW BOOL, STARRATING TINYINT, CERTIFICATE VARCHAR(5), GENRENAME VARCHAR(70),
                                  DESCRIPTION TEXT, CHOICE BOOL, STARTTIME DATETIME, ENDTIME DATETIME,
                                  DURATION INT, CHANNELID INT NOT NULL, INDEX(PROGTITLE),
                                  INDEX(CHANNELID), INDEX(DIRECTOR), INDEX(GENRENAME), INDEX(SUBTITLE,EPISODE),
                                  INDEX(SUBTITLE), INDEX(EPISODE))""")

          self._cursor.execute("""CREATE TEMPORARY TABLE CHANNELTEMP (
                                  CHANNELID INT NOT NULL, CHANNELNAME VARCHAR(100), INDEX(CHANNELNAME), PRIMARY KEY(CHANNELID))""")

          #Create permanent tables
          self._cursor.execute("""CREATE TABLE IF NOT EXISTS CHANNELS (
                                      CHANNELID INT NOT NULL, CHANNELNAME VARCHAR(100), INDEX(CHANNELNAME), PRIMARY KEY(CHANNELID)
                                  ) ENGINE=INNODB""")

          self._cursor.execute("""CREATE TABLE IF NOT EXISTS GENRE (
                                      GENREID INT NOT NULL AUTO_INCREMENT, GENRENAME VARCHAR(70), PRIMARY KEY(GENREID), INDEX(GENRENAME)
                                  ) ENGINE=INNODB""")

          self._cursor.execute("""CREATE TABLE IF NOT EXISTS DIRECTOR (
                                      DIRECTORID INT NOT NULL AUTO_INCREMENT, DIRECTOR VARCHAR(70), PRIMARY KEY(DIRECTORID), INDEX(DIRECTOR)
                                  ) ENGINE=INNODB""")

          self._cursor.execute("""CREATE TABLE IF NOT EXISTS PERFORMER (
                                      PERFORMERID INT NOT NULL AUTO_INCREMENT, PERFORMER VARCHAR(70), PRIMARY KEY(PERFORMERID), UNIQUE(PERFORMER), INDEX(PERFORMER)
                                  ) ENGINE=INNODB""")

          self._cursor.execute("""CREATE TABLE IF NOT EXISTS PROGRAMME (
                                      PROGRAMMEID INT NOT NULL AUTO_INCREMENT, GENREID INT NOT NULL, DIRECTORID INT NOT NULL, PROGTITLE VARCHAR(200), DESCRIPTION TEXT, YR YEAR,
                                      FILM BOOL, WIDESCREEN BOOL, BNW BOOL, CERTIFICATE VARCHAR(5),
                                      PRIMARY KEY(PROGRAMMEID), INDEX (GENREID), INDEX(DIRECTORID), INDEX(PROGTITLE), FOREIGN KEY (GENREID) REFERENCES GENRE(GENREID),
                                      FOREIGN KEY (DIRECTORID) REFERENCES DIRECTOR(DIRECTORID)
                                  ) ENGINE=INNODB""")

          self._cursor.execute("""CREATE TABLE IF NOT EXISTS EPISODE (
                                      EPISODEID INT NOT NULL AUTO_INCREMENT, PROGRAMMEID INT NOT NULL, DIRECTORID INT NOT NULL, SUBTITLE VARCHAR(200),
                                      EPISODE VARCHAR(200), DESCRIPTION TEXT, PRIMARY KEY(EPISODEID), INDEX (PROGRAMMEID), INDEX (DIRECTORID), INDEX(SUBTITLE,EPISODE),
                                      INDEX(SUBTITLE), INDEX(EPISODE), FOREIGN KEY (PROGRAMMEID) REFERENCES PROGRAMME(PROGRAMMEID),
                                      FOREIGN KEY (DIRECTORID) REFERENCES DIRECTOR(DIRECTORID)
                                  ) ENGINE=INNODB""")

          self._cursor.execute("""CREATE TABLE IF NOT EXISTS PROG_EP_PERF (
                                      PROGRAMMEID INT NOT NULL, EPISODEID INT NOT NULL, PERFORMERID INT NOT NULL,
                                      FOREIGN KEY (PROGRAMMEID) REFERENCES PROGRAMME(PROGRAMMEID),
                                      FOREIGN KEY (EPISODEID) REFERENCES EPISODE(EPISODEID), FOREIGN KEY (PERFORMERID) REFERENCES PERFORMER(PERFORMERID),
                                      PRIMARY KEY (PROGRAMMEID, EPISODEID, PERFORMERID)
                                  ) ENGINE=INNODB""")

          self._cursor.execute("""CREATE TABLE IF NOT EXISTS CHANNELPROGRAMME (
                                      CHANNELPROGRAMMEID INT NOT NULL AUTO_INCREMENT, CHANNELID INT NOT NULL,
                                      PROGRAMMEID INT NOT NULL, EPISODEID INT NOT NULL, RPEAT BOOL, NEWSERIES BOOL,
                                      PREMIERE BOOL, CHOICE BOOL, SUBTITLES BOOL, DEAFSIGNED BOOL, STARRATING TINYINT,
                                      STARTTIME DATETIME, ENDTIME DATETIME, DURATION INT, PRIMARY KEY(CHANNELPROGRAMMEID),
                                      INDEX (CHANNELID), FOREIGN KEY (CHANNELID) REFERENCES CHANNELS(CHANNELID), INDEX (PROGRAMMEID),
                                      FOREIGN KEY (PROGRAMMEID) REFERENCES PROGRAMME(PROGRAMMEID), INDEX (EPISODEID),
                                      FOREIGN KEY (EPISODEID) REFERENCES EPISODE(EPISODEID)
                                  ) ENGINE=INNODB""")
      except MySQLdb.Error, e:
          processing._logger.error('Error during environment setup: ' +str(e))
          sys.exit(1)

   def bufferFiles(self):
      try:
         # First buffer the list of channels
         channelData = "channels.dat"
         response = urllib2.urlopen(self._dataURL+channelData)
         content = response.read()
         buf = cStringIO.StringIO(content)
         
         # Open the channel buffer
         fields = ["CHANNELID", "CHANNELNAME"]
         lineReader   = csv.DictReader(buf, delimiter="|",fieldnames=fields)

         # Read the header lines
         lineReader.next()
         lineReader.next()

         self._progFiles = [row for row in lineReader]

         for i in range(lineReader.line_num-2):
            tempDict = self._progFiles[i]
            channelID = tempDict["CHANNELID"]
            progData = channelID + '.dat'
            response = urllib2.urlopen(self._dataURL+progData)
            content = response.read()
            self._channelsData[channelID] = content
      except (IOError, csv.Error, OSError), e:
          processing._logger.error('Error during download files: ' +str(e))
          sys.exit(1)

   def clearTable(self):
      try:
         self._cursor.execute("""TRUNCATE TABLE CHANNELPROGRAMME""")
      except MySQLdb.Error, e:
         processing._logger.error('Error during clear table: ' +str(e))
         sys.exit(1)

   def loadProgData(self,datFile):
      try:
         fields = ["PROGTITLE", "SUBTITLE", "EPISODE", "YEAR", "DIRECTOR", "PERFORMERS",
                   "PREMIERE", "FILM", "RPEAT", "SUBTITLES", "WIDESCREEN", "NEWSERIES",
                   "DEAFSIGNED", "BNW", "STARRATING", "CERTIFICATE", "GENRE", "DESCRIPTION",
                   "CHOICE", "DATE", "STARTTIME", "ENDTIME", "DURATION"]
         delim    = '~'
         
         buf = cStringIO.StringIO(self._channelsData[datFile[:-4]])
         lineReader   = ChanDictReader(buf, delimiter=delim,fieldnames=fields)

         # Read the header lines
         lineReader.next()
         lineReader.next()

         for row in lineReader:
            if lineReader.lf == lineReader.lr:
               row["CHANNELID"] = datFile[:-4]
               progPerformersKey = row["PROGTITLE"] +"~" + row["SUBTITLE"] +"~" +row["EPISODE"]
               if row["PERFORMERS"] != '':
                  self._progPerformers[progPerformersKey] = row["PERFORMERS"]
                  pList = row["PERFORMERS"].split("|")
                  for p in pList:
                     cp = p.split("*")
                     if cp[1] not in self._performers:
                        self._performers.append(str(cp[1]))
               self._channelPrograms.append(row)
      except (IOError, csv.Error), e:
         processing._logger.error('Error during load prog data: ' +str(e))
         sys.exit(1)


   def loadDBData(self):
       try:
          #Load channel name data
          columns = []
          sqlString = """INSERT INTO CHANNELTEMP (
                             CHANNELID,CHANNELNAME)
                         VALUES(%s,%s)"""
          columns = [(row["CHANNELID"],row["CHANNELNAME"]) for row in self._progFiles]
          self._cursor.executemany(sqlString,columns)

          #Load channel programme data
          columns = []
          sqlString = """INSERT INTO TVTEMPTABLE (
                             PROGTITLE, SUBTITLE, EPISODE,
                             YR, DIRECTOR, PERFORMERS,
                             PREMIERE, FILM, RPEAT, SUBTITLES,
                             WIDESCREEN, NEWSERIES, DEAFSIGNED,
                             BNW, STARRATING, CERTIFICATE, GENRENAME,
                             DESCRIPTION, CHOICE, 
                             STARTTIME, ENDTIME, DURATION, CHANNELID)
                         VALUES (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)"""
          
          columns = [(row["PROGTITLE"],row["SUBTITLE"],row["EPISODE"],self.formatVal(row["YEAR"]),row["DIRECTOR"],
                      row["PERFORMERS"],self.formatBool(row["PREMIERE"]),self.formatBool(row["FILM"]),
                      self.formatBool(row["RPEAT"]),self.formatBool(row["SUBTITLES"]),
                      self.formatBool(row["WIDESCREEN"]),self.formatBool(row["NEWSERIES"]),
                      self.formatBool(row["DEAFSIGNED"]),self.formatBool(row["BNW"]),self.formatVal(row["STARRATING"]),
                      row["CERTIFICATE"],row["GENRE"],row["DESCRIPTION"],self.formatBool(row["CHOICE"]),
                      self.formatDate(row["DATE"], row["STARTTIME"]),self.checkEndDate(self.formatDate(row["DATE"], row["STARTTIME"]), self.formatDate(row["DATE"], row["ENDTIME"])),row["DURATION"],row["CHANNELID"]) for row in self._channelPrograms ]
          self.batchExecute(sqlString, columns, 25)
       except MySQLdb.Error, e:
          processing._logger.error('Error during load prog data: ' +str(e))
          sys.exit(1)

   def updateChannels(self):
       try:
          sqlString = """INSERT INTO CHANNELS(CHANNELID,CHANNELNAME)
                         SELECT
                             T.CHANNELID, T.CHANNELNAME
                         FROM
                             CHANNELTEMP T
                             LEFT JOIN CHANNELS C ON C.CHANNELID=T.CHANNELID
                         WHERE C.CHANNELNAME IS NULL"""
          self._cursor.execute(sqlString)
          self._dbase.commit()
       except MySQLdb.Error, e:
          processing._logger.error('Error during update channels: ' +str(e))
          sys.exit(1)

   def updateGenres(self):
       try:
          sqlString = """INSERT INTO GENRE (GENRENAME)
                         SELECT DISTINCT
                             T.GENRENAME
                         FROM
                             TVTEMPTABLE T
                             LEFT JOIN GENRE G ON G.GENRENAME=T.GENRENAME
                         WHERE
                             G.GENRENAME IS NULL"""
          self._cursor.execute(sqlString)
          self._dbase.commit()
       except MySQLdb.Error, e:
          processing._logger.error('Error during update genres: ' +str(e))
          sys.exit(1)

   def updateDirectors(self):
       try:
          sqlString = """INSERT INTO DIRECTOR (DIRECTOR)
                         SELECT DISTINCT
                             T.DIRECTOR
                         FROM
                             TVTEMPTABLE T
                             LEFT JOIN DIRECTOR D ON D.DIRECTOR=T.DIRECTOR
                         WHERE
                             D.DIRECTOR IS NULL"""
          self._cursor.execute(sqlString)
          self._dbase.commit()
       except MySQLdb.Error, e:
          processing._logger.error('Error during update directors: ' +str(e))
          sys.exit(1)
   
   def updatePerformers(self):
       try:
          selectSQL = "SELECT * FROM PERFORMER P WHERE P.PERFORMER = %s"
          sqlString = """INSERT IGNORE INTO PERFORMER (PERFORMER)
                         VALUES (%s)"""
          for entry in self._performers:
             self._cursor.execute(sqlString, (entry,))
             result = self._cursor.fetchone()
             if result is None:
                self._cursor.execute(sqlString, (entry,))
          self._dbase.commit()
       except MySQLdb.Error, e:
          processing._logger.error('Error during update directors: ' +str(e))
          sys.exit(1)

   def updateProgrammes(self):
       try:
          sqlString = """INSERT INTO PROGRAMME (
                             PROGTITLE, GENREID, DIRECTORID, YR, FILM, WIDESCREEN, BNW,
                             CERTIFICATE, DESCRIPTION)
                         SELECT
                             T.PROGTITLE, MAX(G.GENREID), MAX(D.DIRECTORID),
                             MAX(T.YR), MAX(T.FILM), MAX(T.WIDESCREEN),
                             MAX(T.BNW), MAX(T.CERTIFICATE), MAX(T.DESCRIPTION)
                         FROM
                             TVTEMPTABLE T
                             INNER JOIN GENRE G ON G.GENRENAME=T.GENRENAME
                             INNER JOIN DIRECTOR D ON D.DIRECTOR=T.DIRECTOR
                             LEFT JOIN PROGRAMME P ON P.PROGTITLE=T.PROGTITLE
                         WHERE P.PROGTITLE IS NULL
                         GROUP BY T.PROGTITLE"""
          self._cursor.execute(sqlString)
          self._dbase.commit()
       except MySQLdb.Error, e:
          processing._logger.error('Error during update programmes: ' +str(e))
          sys.exit(1)

   def updateEpisodes(self):
       try:
          sqlString = """INSERT INTO EPISODE (
                             SUBTITLE, EPISODE, PROGRAMMEID, DIRECTORID, DESCRIPTION)
                         SELECT
                             T.SUBTITLE, T.EPISODE, MAX(P.PROGRAMMEID), MAX(D.DIRECTORID),
                             MAX(T.DESCRIPTION)
                         FROM
                             TVTEMPTABLE T
                             INNER JOIN PROGRAMME P ON P.PROGTITLE=T.PROGTITLE
                             INNER JOIN DIRECTOR D ON D.DIRECTOR=T.DIRECTOR
                             LEFT JOIN EPISODE E ON E.SUBTITLE=T.SUBTITLE AND E.EPISODE=T.EPISODE
                         WHERE E.SUBTITLE IS NULL AND E.EPISODE IS NULL
                         GROUP BY T.SUBTITLE,T.EPISODE"""
          self._cursor.execute(sqlString)
          self._dbase.commit()
       except MySQLdb.Error, e:
          processing._logger.error('Error during update episodes: ' +str(e))
          sys.exit(1)

   def updateChanProgs(self):
      try:
          sqlString = """INSERT INTO CHANNELPROGRAMME (
                             CHANNELID, PROGRAMMEID, EPISODEID,
                             RPEAT, NEWSERIES, PREMIERE, CHOICE, SUBTITLES,
                             DEAFSIGNED, STARRATING, STARTTIME, ENDTIME,
                             DURATION)
                         SELECT
                             T.CHANNELID, P.PROGRAMMEID, E.EPISODEID, T.RPEAT, T.NEWSERIES,
                             T.PREMIERE, T.CHOICE, T.SUBTITLES, T.DEAFSIGNED, T.STARRATING,
                             T.STARTTIME, T.ENDTIME, T.DURATION
                         FROM
                             TVTEMPTABLE T
                             INNER JOIN PROGRAMME P ON P.PROGTITLE=T.PROGTITLE
                             INNER JOIN CHANNELS CH ON CH.CHANNELID=T.CHANNELID
                             INNER JOIN EPISODE E ON E.SUBTITLE=T.SUBTITLE AND E.EPISODE=T.EPISODE"""
          self._cursor.execute(sqlString)
          self._dbase.commit()
      except MySQLdb.Error, e:
          processing._logger.error('Error during update channel programmes: ' +str(e))
          sys.exit(1)
   
   def updateProgPerformers(self):
      try:
          data = []
          for k,v in self._progPerformers.iteritems():
              ksplit = k.split("~")
              prog = ksplit[0]
              sub  = ksplit[1]
              ep   = ksplit[2]
              performers = v.split("|")
              for performer in performers:
                  actor = performer.split("*")[1]
                  data.append((prog, actor, ep, sub))
          sqlString = """INSERT IGNORE INTO PROG_EP_PERF (
                            PROGRAMMEID, EPISODEID, PERFORMERID)
                         SELECT
                            P.PROGRAMMEID, E.EPISODEID, PF.PERFORMERID
                         FROM
                            PROGRAMME P, EPISODE E, PERFORMER PF
                         WHERE
                            P.PROGTITLE = %s AND
                            PF.PERFORMER = %s AND
                            E.EPISODE = %s AND
                            E.SUBTITLE = %s """
          self.batchExecute(sqlString, data, 25)
          self._dbase.commit()
      except MySQLdb.Error, e:
          processing._logger.error('Error during update channel programmes: ' +str(e))
          sys.exit(1)

   def batchExecute(self, sqlString, data, batchSize):
       end_index = len(data) - 1
       start = 0
       end = batchSize - 1
       
       while end < end_index:
           self._cursor.executemany(sqlString,data[start:end])
           start = end + 1
           if(end_index - start >= batchSize):
               end += batchSize
           else:
               end = end_index
   
   def checkEndDate(self, startDate, endDate):
       sd = datetime.strptime(startDate, "%Y-%m-%d %H:%M:%S")
       ed = datetime.strptime(endDate, "%Y-%m-%d %H:%M:%S")
       if ed > sd:
           return str(ed)
       else:
           return str(ed + timedelta(days=1))
   
   def formatDate(self, date, time):
      return date[6:10] +"-" +date[3:5] + "-" +date[0:2] + " " +time + ":00"

   def formatBool(self,val):
      return "1" if val=="true" else "0"

   def formatVal(self,val):
       return val if val != "null" and val else "0"

   def closeDB(self):
      try:
         #Code to close connection to MySQL database
         self._cursor.close()
         self._dbase.close()
      except MySQLdb.Error, e:
          processing._logger.error('Error during close database: ' +str(e))
          sys.exit(1)

if __name__ == '__main__':
   processing = Processing()
   processing.setupEnvironment()
   tStart = time.time()
   totTime = tStart
   processing.bufferFiles()
   print "Time to buffer files = " + str((time.time() - tStart))

   if len(processing._progFiles)>0:
      processing.clearTable()
   else:
      processing._cursor.execute("""DELETE FROM CHANNELPROGRAMME WHERE STARTTIME < CURDATE()""")
      processing.closeDB()
      processing._logger.error('NO CHANNEL DATA, EXITING PROCESS')
      sys.exit(1)

   tStart = time.time()

   for i in range(len(processing._progFiles)):
      tempDict = processing._progFiles[i]
      channelFile = tempDict["CHANNELID"]
      processing.loadProgData(channelFile+'.dat')

   print "Time to buffer data = " + str((time.time() - tStart))

   tStart = time.time()
   processing.loadDBData()
   print "Time to load temp data = " + str((time.time() - tStart))

   tStart = time.time()
   processing.updateChannels()
   print "Time to update channels = " + str((time.time() - tStart))

   tStart = time.time()
   processing.updateGenres()
   print "Time to update genres = " + str((time.time() - tStart))

   tStart = time.time()
   processing.updateDirectors()
   print "Time to update directors = " + str((time.time() - tStart))
   
   tStart = time.time()
   processing.updatePerformers()
   print "Time to update performers = " + str((time.time() - tStart))

   tStart = time.time()
   processing.updateProgrammes()
   print "Time to update programmes = " + str((time.time() - tStart))

   tStart = time.time()
   processing.updateEpisodes()
   print "Time to update episodes = " + str((time.time() - tStart))

   tStart = time.time()
   processing.updateChanProgs()
   print "Time to update chanprogs = " + str((time.time() - tStart))
   
   tStart = time.time()
   processing.updateProgPerformers()
   print "Time to update progperformers = " + str((time.time() - tStart))
   print "TOTAL TIME = " + str((time.time() - totTime))

   processing.closeDB()

   sys.exit(0)
