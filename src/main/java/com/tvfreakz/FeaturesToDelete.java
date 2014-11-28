/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FeaturesToDelete {

  private static final String FEATURES           = "C:\\digirati\\Features.txt";
  private static final String FEATURES_TO_DELETE = "C:\\digirati\\FeaturesToDelete.txt";
  private static final String NEW_FEATURES       = "C:\\digirati\\NewFeatures.txt";

  public static void main(String[] args) {
    long startTime = System.nanoTime();

    createNewFeatures();

    long endTime = System.nanoTime();

    System.out.println("Time to complete: " + (endTime - startTime));
  }

  /**
   * This method creates the new features file, which is the contents
   * of the original features files, minus the keys that appear in
   * the FeaturesToDelete file
   */
  private static void createNewFeatures() {
    Path features = Paths.get(FEATURES);
    Path featuresToDelete = Paths.get(FEATURES_TO_DELETE);

    try(PrintWriter writerFeaturesNew = new PrintWriter(NEW_FEATURES, "UTF-8")) {
      String featuresContents = new String(Files.readAllBytes(features));

      String featuresDeleteContents = new String(Files.readAllBytes(featuresToDelete));
      Set<String> deleteKeys = new HashSet<>(Arrays.asList(featuresDeleteContents.split("\r\n")));
      
      String[] lines = featuresContents.split("\r\n");
      for(int i=0; i<lines.length; i++) {
        String[] keyValue = lines[i].split(",", 2);
        String key = keyValue[0];
        if(!deleteKeys.contains(key)) {
          writerFeaturesNew.println(key +"," + keyValue[1].trim());          
        }
      }      

    } catch (IOException e1) {
      e1.printStackTrace();
      System.exit(1);
    }    
  }  

}
