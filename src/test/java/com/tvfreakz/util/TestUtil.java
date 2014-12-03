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
import com.tvfreakz.model.entity.Performer;
import com.tvfreakz.model.entity.Programme;

public class TestUtil {

  public static final Date TODAY = new LocalDate().toDateTimeAtStartOfDay().toDate();
  public static final Date TWO_WEEKS = new LocalDate().toDateTimeAtStartOfDay().plusWeeks(2).toDate();

  public static final Date NOW = new LocalTime().toDateTimeToday().withSecondOfMinute(0).withMillisOfSecond(0).toDate();
  public static final Date TWO_HOURS = new LocalTime().plusHours(2).toDateTimeToday().withSecondOfMinute(0).withMillisOfSecond(0).toDate();

  public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
      MediaType.APPLICATION_JSON.getSubtype(),                        
      Charset.forName("utf8")                     
      );

  public static final String DATE_TIME_FORMAT = "yyyyMMddHHmm";

  public static final Director[] DIRECTORS = createDirectorTestData();

  public static final Channel[] CHANNELS = createChannelTestData();
  
  public static final Genre[] GENRES = createGenreTestData();
  
  public static final Programme[] PROGRAMMES = createProgrammeTestData();

  public static final ChannelProgramme[] CHANNEL_PROGRAMMES = createChannelProgrammeTestData();  

  public static final Performer[] PERFORMERS = createPerformerTestData();  

  public static String createStringWithLength(int length) {
    StringBuilder builder = new StringBuilder();

    for (int index = 0; index < length; index++) {
      builder.append("a");
    }

    return builder.toString();
  }

  private static Director[] createDirectorTestData() {
    Director ridleyScott = new Director.Builder().withDirectorId(1L).withDirectorName("Ridley Scott").build();

    Director jamesCameron = new Director.Builder().withDirectorId(2L).withDirectorName("James Cameron").build();

    Director jeanPierreJeunet = new Director.Builder().withDirectorId(3L).withDirectorName("Jean-Pierre Jeunet").build();
    
    return new Director[]{ridleyScott, jamesCameron, jeanPierreJeunet};    
  }

  private static Channel[] createChannelTestData() {
    Channel bbc1 = new Channel.Builder().withChannelId(1L).withChannelName("BBC 1").build();

    Channel bbc2 = new Channel.Builder().withChannelId(2L).withChannelName("BBC 2").build();

    Channel bbc3 = new Channel.Builder().withChannelId(3L).withChannelName("BBC 3").build();

    return new Channel[]{bbc1, bbc2, bbc3};
  }

  private static Performer[] createPerformerTestData() {
    Performer sigourneyWeaver = new Performer.Builder().withPerformerId(1L).withPeformerName("Sigourney Weaver").build();

    Performer johnHurt = new Performer.Builder().withPerformerId(2L).withPeformerName("John Hurt").build();

    Performer harrisonFord = new Performer.Builder().withPerformerId(3L).withPeformerName("Harrison Ford").build();

    return new Performer[]{sigourneyWeaver, johnHurt, harrisonFord};
  }

  private static Genre[] createGenreTestData() {
    Genre arts = new Genre.Builder().withGenreId(1L).withGenreName("Arts").build();

    Genre childrens = new Genre.Builder().withGenreId(2L).withGenreName("Childrens").build();

    Genre comedy = new Genre.Builder().withGenreId(3L).withGenreName("Comedy").build();

    Genre currentAffairs = new Genre.Builder().withGenreId(4L).withGenreName("Current affairs").build();

    Genre documentary = new Genre.Builder().withGenreId(5L).withGenreName("Documentary").build();
    
    Genre sciFi = new Genre.Builder().withGenreId(6L).withGenreName("Scifi").build();

    return new Genre[]{arts, childrens, comedy, currentAffairs, documentary, sciFi};
  }
  
  private static Programme[] createProgrammeTestData() {
    Director ridleyScott = DIRECTORS[0];
    Director jamesCameron = DIRECTORS[1];
    Director jeanPierreJeunet = DIRECTORS[2];
    
    Genre sciFi = GENRES[5];
    
    Programme alien = new Programme.Builder().withProgrammeId(1L).withBlackAndWhite(false)
        .withCertificate("18").withDescription("Fast paced action thriller").withDirector(ridleyScott)
        .withFilm(true).withGenre(sciFi).withProgTitle("Alien").withWideScreen(false)
        .withYear(new LocalDate(1979,1,1).toDate()).build();
    
    Programme aliens = new Programme.Builder().withProgrammeId(2L).withBlackAndWhite(false)
        .withCertificate("18").withDescription("Fast paced action thriller").withDirector(jamesCameron)
        .withFilm(true).withGenre(sciFi).withProgTitle("Aliens").withWideScreen(false)
        .withYear(new LocalDate(1986,1,1).toDate()).build();
    
    Programme alien3 = new Programme.Builder().withProgrammeId(3L).withBlackAndWhite(false)
        .withCertificate("18").withDescription("Fast paced action thriller").withDirector(jeanPierreJeunet)
        .withFilm(true).withGenre(sciFi).withProgTitle("Alien 3").withWideScreen(false)
        .withYear(new LocalDate(1992,1,1).toDate()).build();
    
    Programme bladeRunner = new Programme.Builder().withProgrammeId(4L).withBlackAndWhite(false)
        .withCertificate("18").withDescription("A blade runner must pursue and try to terminate four replicants who stole a ship in space and have returned to Earth to find their creator").withDirector(ridleyScott)
        .withFilm(true).withGenre(sciFi).withProgTitle("Blade Runner").withWideScreen(false)
        .withYear(new LocalDate(1982,1,1).toDate()).build();
    
    return new Programme[]{alien, aliens, alien3, bladeRunner};
  }

  private static ChannelProgramme[] createChannelProgrammeTestData() {
    Channel bbc1 = CHANNELS[0];    
    Channel bbc2 = CHANNELS[1];
    Channel bbc3 = CHANNELS[2];

    Programme aliens = PROGRAMMES[1];

    Programme alien = PROGRAMMES[0];

    Programme bladeRunner = PROGRAMMES[3];

    Programme alien3 = PROGRAMMES[2];

    ChannelProgramme chanprog1 = new ChannelProgramme.Builder().withChannelProgrammeId(1L).withChannel(bbc1).withChoice(false).withDeafSigned(false)
        .withDuration(60).withEndTime(new LocalTime(12,30,0).toDateTimeToday().toDate()).withNewSeries(false).withPremiere(false)
        .withProgDate(new DateTime().toDate()).withRepeat(false).withStarRating(5).withStartTime(new LocalTime(11,30,0).toDateTimeToday().toDate())
        .withSubtitles(false).withProgramme(aliens).build();

    ChannelProgramme chanprog2 = new ChannelProgramme.Builder().withChannelProgrammeId(2L).withChannel(bbc1).withChoice(false).withDeafSigned(false)
        .withDuration(60).withEndTime(new LocalTime(23,30,0).toDateTimeToday().toDate()).withNewSeries(false).withPremiere(false)
        .withProgDate(new DateTime().minusDays(1).toDate()).withRepeat(false).withStarRating(5).withStartTime(new LocalTime(22,30,0).toDateTimeToday().toDate())
        .withSubtitles(false).withProgramme(alien).build();    

    ChannelProgramme chanprog3 = new ChannelProgramme.Builder().withChannelProgrammeId(3L).withChannel(bbc1).withChoice(false).withDeafSigned(false)
        .withDuration(60).withEndTime(new LocalTime(23,30,0).toDateTimeToday().toDate()).withNewSeries(false).withPremiere(false)
        .withProgDate(new DateTime().plusDays(1).toDate()).withRepeat(false).withStarRating(5).withStartTime(new LocalTime(22,30,0).toDateTimeToday().toDate())
        .withSubtitles(false).withProgramme(bladeRunner).build();

    ChannelProgramme chanprog4 = new ChannelProgramme.Builder().withChannelProgrammeId(4L).withChannel(bbc2).withChoice(false).withDeafSigned(false)
        .withDuration(60).withEndTime(new LocalTime(23,30,0).toDateTimeToday().toDate()).withNewSeries(false).withPremiere(false)
        .withProgDate(new DateTime().minusDays(1).toDate()).withRepeat(false).withStarRating(5).withStartTime(new LocalTime(22,30,0).toDateTimeToday().toDate())
        .withSubtitles(false).withProgramme(alien3).build();

    ChannelProgramme chanprog5 = new ChannelProgramme.Builder().withChannelProgrammeId(5L).withChannel(bbc1).withChoice(false).withDeafSigned(false)
        .withDuration(60).withEndTime(new LocalTime(23,30,0).toDateTimeToday().toDate()).withNewSeries(false).withPremiere(false)
        .withProgDate(new DateTime().plusDays(2).toDate()).withRepeat(false).withStarRating(5).withStartTime(new LocalTime(22,30,0).toDateTimeToday().toDate())
        .withSubtitles(false).withProgramme(alien3).build();

    ChannelProgramme chanprog6 = new ChannelProgramme.Builder().withChannelProgrammeId(6L).withChannel(bbc3).withChoice(false).withDeafSigned(false)
        .withDuration(60).withEndTime(new LocalTime(23,30,0).toDateTimeToday().toDate()).withNewSeries(false).withPremiere(false)
        .withProgDate(new DateTime().plusDays(3).toDate()).withRepeat(false).withStarRating(5).withStartTime(new LocalTime(22,30,0).toDateTimeToday().toDate())
        .withSubtitles(false).withProgramme(alien3).build();

    return new ChannelProgramme[]{chanprog2, chanprog1, chanprog3, chanprog4, chanprog5, chanprog6};
  }

}
