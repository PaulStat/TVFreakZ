/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.repository;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.xml.FlatDtdDataSet;

public class DatabaseExportSample {
  
  public static void main(String[] args) throws Exception {  
      
      Connection jdbcConnection = DriverManager.getConnection("jdbc:mysql://172.28.84.209:3306/tvlistings", "test", "test");
      IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

      // write DTD file
      FlatDtdDataSet.write(connection.createDataSet(), new FileOutputStream("c:\\temp\\test.dtd"));
  }

}
