/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.util;

import java.nio.charset.Charset;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.http.MediaType;

import com.tvfreakz.model.entity.Channel;
import com.tvfreakz.model.entity.ChannelProgramme;
import com.tvfreakz.model.entity.Director;
import com.tvfreakz.model.entity.Genre;
import com.tvfreakz.model.entity.Programme;

public class TestUtil {
  
  public static final Date TODAY = new LocalDate().toDateTimeAtStartOfDay().toDate();
  public static final Date TWO_WEEKS = new LocalDate().toDateTimeAtStartOfDay().plusWeeks(2).toDate();

  public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
      MediaType.APPLICATION_JSON.getSubtype(),                        
      Charset.forName("utf8")                     
      );
  
  public static final ChannelProgramme[] CHANNEL_PROGRAMMES = createChannelProgrammeTestData();

  private static ChannelProgramme[] createChannelProgrammeTestData() {
    Director ridleyScott = new Director();
    ridleyScott.setDirectorId(1L);
    ridleyScott.setDirectorName("Ridley Scott");

    Director jamesCameron = new Director();
    jamesCameron.setDirectorId(2L);
    jamesCameron.setDirectorName("James Cameron");

    Genre genre = new Genre();
    genre.setGenreId(1L);
    genre.setGenreName("Scifi");

    Channel bbc1 = new Channel();
    bbc1.setChannelId(1L);
    bbc1.setChannelName("BBC 1");
    Programme prog1 = new Programme();
    prog1.setProgrammeId(1L);
    prog1.setBlackAndWhite(false);
    prog1.setCertificate("18");
    prog1.setDescription("Fast paced action thriller");
    prog1.setDirector(jamesCameron);
    prog1.setFilm(true);
    prog1.setGenre(genre);
    prog1.setPerformers(null);
    prog1.setProgTitle("Aliens");
    prog1.setWideScreen(false);
    prog1.setYear(new LocalDate(1986,1,1).toDate());

    Programme prog2 = new Programme();
    prog2.setProgrammeId(2L);
    prog2.setBlackAndWhite(false);
    prog2.setCertificate("18");
    prog2.setDescription("Fast paced action thriller");
    prog2.setDirector(ridleyScott);
    prog2.setFilm(true);
    prog2.setGenre(genre);
    prog2.setPerformers(null);
    prog2.setProgTitle("Alien");
    prog2.setWideScreen(false);
    prog2.setYear(new LocalDate(1979,1,1).toDate());

    Programme prog3 = new Programme();
    prog3.setProgrammeId(3L);
    prog3.setBlackAndWhite(false);
    prog3.setCertificate("18");
    prog3.setDescription("A blade runner must pursue and try to terminate four replicants who stole a ship in space and have returned to Earth to find their creator");
    prog3.setDirector(ridleyScott);
    prog3.setFilm(true);
    prog3.setGenre(genre);
    prog3.setPerformers(null);
    prog3.setProgTitle("Blade Runner");
    prog3.setWideScreen(false);
    prog3.setYear(new LocalDate(1982,1,1).toDate());

    ChannelProgramme chanprog1 = new ChannelProgramme();
    chanprog1.setChannelProgrammeId(1L);
    chanprog1.setChannel(bbc1);
    chanprog1.setChoice(false);
    chanprog1.setDeafSigned(false);
    chanprog1.setDuration(60);
    chanprog1.setEndTime(new LocalTime(12,30,0).toDateTimeToday().toDate());
    chanprog1.setEpisode(null);
    chanprog1.setNewSeries(false);
    chanprog1.setPremiere(false);
    chanprog1.setProgDate(new DateTime().toDate());
    chanprog1.setRepeat(false);
    chanprog1.setStarRating(5);
    chanprog1.setStartTime(new LocalTime(11,30,0).toDateTimeToday().toDate());
    chanprog1.setSubtitles(false);
    chanprog1.setProgramme(prog1);

    ChannelProgramme chanprog2 = new ChannelProgramme();
    chanprog2.setChannelProgrammeId(2L);
    chanprog2.setChannel(bbc1);
    chanprog2.setChoice(false);
    chanprog2.setDeafSigned(false);
    chanprog2.setDuration(60);
    chanprog2.setEndTime(new LocalTime(23,30,0).toDateTimeToday().toDate());
    chanprog2.setEpisode(null);
    chanprog2.setNewSeries(false);
    chanprog2.setPremiere(false);
    chanprog2.setProgDate(new DateTime().minusDays(1).toDate());
    chanprog2.setRepeat(false);
    chanprog2.setStarRating(5);
    chanprog2.setStartTime(new LocalTime(22,30,0).toDateTimeToday().toDate());
    chanprog2.setSubtitles(false);
    chanprog2.setProgramme(prog2);

    ChannelProgramme chanprog3 = new ChannelProgramme();
    chanprog3.setChannelProgrammeId(3L);
    chanprog3.setChannel(bbc1);
    chanprog3.setChoice(false);
    chanprog3.setDeafSigned(false);
    chanprog3.setDuration(60);
    chanprog3.setEndTime(new LocalTime(23,30,0).toDateTimeToday().toDate());
    chanprog3.setEpisode(null);
    chanprog3.setNewSeries(false);
    chanprog3.setPremiere(false);
    chanprog3.setProgDate(new DateTime().plusDays(1).toDate());
    chanprog3.setRepeat(false);
    chanprog3.setStarRating(5);
    chanprog3.setStartTime(new LocalTime(22,30,0).toDateTimeToday().toDate());
    chanprog3.setSubtitles(false);
    chanprog3.setProgramme(prog3);

    return new ChannelProgramme[]{chanprog2, chanprog1, chanprog3};
  }

}
