/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.util;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.http.MediaType;

import com.tvfreakz.model.entity.Channel;
import com.tvfreakz.model.entity.ChannelProgramme;
import com.tvfreakz.model.entity.Director;
import com.tvfreakz.model.entity.Episode;
import com.tvfreakz.model.entity.Genre;
import com.tvfreakz.model.entity.Performer;
import com.tvfreakz.model.entity.ProgEpPerfAssociation;
import com.tvfreakz.model.entity.Programme;

public final class TestUtil {
    
    public static final Date TODAY = new LocalDate().toDateTimeAtStartOfDay()
            .toDate();

    public static final Date TWO_WEEKS = new LocalDate()
            .toDateTimeAtStartOfDay().plusWeeks(2).toDate();
    public static final Date NOW = new LocalTime().toDateTimeToday()
            .withSecondOfMinute(0).withMillisOfSecond(0).toDate();

    public static final Date TWO_HOURS = new LocalTime().plusHours(2)
            .toDateTimeToday().withSecondOfMinute(0).withMillisOfSecond(0)
            .toDate();
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    public static final String DATE_TIME_FORMAT = "yyyyMMddHHmm";

    public static final Director[] DIRECTORS = createDirectorTestData();

    public static final Channel[] CHANNELS = createChannelTestData();

    public static final Genre[] GENRES = createGenreTestData();

    public static final Programme[] PROGRAMMES = createProgrammeTestData();

    public static final Episode[] EPISODES = createEpisodeTestData();

    public static final ChannelProgramme[] CHANNEL_PROGRAMMES = createChannelProgrammeTestData();

    public static final Performer[] PERFORMERS = createPerformerTestData();

    public static final ProgEpPerfAssociation[] PROG_EP_PERF_ASSOCIATIONS = createProgEpPerfAssociations();

    static {
        setupAssociations(PROG_EP_PERF_ASSOCIATIONS);
    }

    private static ChannelProgramme[] createChannelProgrammeTestData() {
        Channel bbc1 = CHANNELS[0];
        Channel bbc2 = CHANNELS[1];
        Channel bbc3 = CHANNELS[2];

        Programme aliens = PROGRAMMES[1];

        Programme alien = PROGRAMMES[0];

        Programme bladeRunner = PROGRAMMES[3];

        Programme alien3 = PROGRAMMES[2];

        Episode unknown = EPISODES[0];

        ChannelProgramme chanprog1 = new ChannelProgramme.Builder()
                .withChannelProgrammeId(1L)
                .withChannel(bbc1)
                .withChoice(false)
                .withDeafSigned(false)
                .withDuration(60)
                .withEndTime(
                        new LocalTime(12, 30, 0).toDateTimeToday().toDate())
                .withNewSeries(false)
                .withPremiere(false)
                .withRepeat(false)
                .withStarRating(5)
                .withStartTime(
                        new LocalTime(11, 30, 0).toDateTimeToday().toDate())
                .withSubtitles(false).withProgramme(aliens)
                .withEpisode(unknown).build();

        ChannelProgramme chanprog2 = new ChannelProgramme.Builder()
                .withChannelProgrammeId(2L)
                .withChannel(bbc1)
                .withChoice(false)
                .withDeafSigned(false)
                .withDuration(60)
                .withEndTime(
                        new LocalTime(23, 30, 0).toDateTimeToday().minusDays(1)
                                .toDate())
                .withNewSeries(false)
                .withPremiere(false)
                .withRepeat(false)
                .withStarRating(5)
                .withStartTime(
                        new LocalTime(22, 30, 0).toDateTimeToday().minusDays(1)
                                .toDate()).withSubtitles(false)
                .withProgramme(alien).withEpisode(unknown).build();

        ChannelProgramme chanprog3 = new ChannelProgramme.Builder()
                .withChannelProgrammeId(3L)
                .withChannel(bbc1)
                .withChoice(false)
                .withDeafSigned(false)
                .withDuration(60)
                .withEndTime(
                        new LocalTime(23, 30, 0).toDateTimeToday().plusDays(1)
                                .toDate())
                .withNewSeries(false)
                .withPremiere(false)
                .withRepeat(false)
                .withStarRating(5)
                .withStartTime(
                        new LocalTime(22, 30, 0).toDateTimeToday().plusDays(1)
                                .toDate()).withSubtitles(false)
                .withProgramme(bladeRunner).withEpisode(unknown).build();

        ChannelProgramme chanprog4 = new ChannelProgramme.Builder()
                .withChannelProgrammeId(4L)
                .withChannel(bbc2)
                .withChoice(false)
                .withDeafSigned(false)
                .withDuration(60)
                .withEndTime(
                        new LocalTime(23, 30, 0).toDateTimeToday().minusDays(1)
                                .toDate())
                .withNewSeries(false)
                .withPremiere(false)
                .withRepeat(false)
                .withStarRating(5)
                .withStartTime(
                        new LocalTime(22, 30, 0).toDateTimeToday().minusDays(1)
                                .toDate()).withSubtitles(false)
                .withProgramme(alien3).withEpisode(unknown).build();

        ChannelProgramme chanprog5 = new ChannelProgramme.Builder()
                .withChannelProgrammeId(5L)
                .withChannel(bbc1)
                .withChoice(false)
                .withDeafSigned(false)
                .withDuration(60)
                .withEndTime(
                        new LocalTime(23, 30, 0).toDateTimeToday().plusDays(2)
                                .toDate())
                .withNewSeries(false)
                .withPremiere(false)
                .withRepeat(false)
                .withStarRating(5)
                .withStartTime(
                        new LocalTime(22, 30, 0).toDateTimeToday().plusDays(2)
                                .toDate()).withSubtitles(false)
                .withProgramme(alien3).withEpisode(unknown).build();

        ChannelProgramme chanprog6 = new ChannelProgramme.Builder()
                .withChannelProgrammeId(6L)
                .withChannel(bbc3)
                .withChoice(false)
                .withDeafSigned(false)
                .withDuration(60)
                .withEndTime(
                        new LocalTime(23, 30, 0).toDateTimeToday().plusDays(3)
                                .toDate())
                .withNewSeries(false)
                .withPremiere(false)
                .withRepeat(false)
                .withStarRating(5)
                .withStartTime(
                        new LocalTime(22, 30, 0).toDateTimeToday().plusDays(3)
                                .toDate()).withSubtitles(false)
                .withProgramme(alien3).withEpisode(unknown).build();

        return new ChannelProgramme[] { chanprog2, chanprog1, chanprog3,
                chanprog4, chanprog5, chanprog6 };
    }

    private static ProgEpPerfAssociation[] createProgEpPerfAssociations() {
        Performer sigourneyWeaver = PERFORMERS[0];
        Performer johnHurt = PERFORMERS[1];
        Performer harrisonFord = PERFORMERS[2];

        Programme aliens = PROGRAMMES[1];
        Programme alien = PROGRAMMES[0];
        Programme bladeRunner = PROGRAMMES[3];
        Programme alien3 = PROGRAMMES[2];

        Episode unknown = EPISODES[0];

        ProgEpPerfAssociation assoc1 = new ProgEpPerfAssociation.Builder()
                .withEpisode(unknown).withEpisodeId(unknown.getEpisodeId())
                .withPerformer(sigourneyWeaver)
                .withPerformerId(sigourneyWeaver.getPerformerId())
                .withProgramme(alien).withProgrammeId(alien.getProgrammeId())
                .build();

        ProgEpPerfAssociation assoc2 = new ProgEpPerfAssociation.Builder()
                .withEpisode(unknown).withEpisodeId(unknown.getEpisodeId())
                .withPerformer(sigourneyWeaver)
                .withPerformerId(sigourneyWeaver.getPerformerId())
                .withProgramme(aliens).withProgrammeId(aliens.getProgrammeId())
                .build();

        ProgEpPerfAssociation assoc3 = new ProgEpPerfAssociation.Builder()
                .withEpisode(unknown).withEpisodeId(unknown.getEpisodeId())
                .withPerformer(sigourneyWeaver)
                .withPerformerId(sigourneyWeaver.getPerformerId())
                .withProgramme(alien3).withProgrammeId(alien3.getProgrammeId())
                .build();

        ProgEpPerfAssociation assoc4 = new ProgEpPerfAssociation.Builder()
                .withEpisode(unknown).withEpisodeId(unknown.getEpisodeId())
                .withPerformer(johnHurt)
                .withPerformerId(johnHurt.getPerformerId())
                .withProgramme(alien).withProgrammeId(alien.getProgrammeId())
                .build();

        ProgEpPerfAssociation assoc5 = new ProgEpPerfAssociation.Builder()
                .withEpisode(unknown).withEpisodeId(unknown.getEpisodeId())
                .withPerformer(harrisonFord)
                .withPerformerId(harrisonFord.getPerformerId())
                .withProgramme(bladeRunner)
                .withProgrammeId(bladeRunner.getProgrammeId()).build();

        return new ProgEpPerfAssociation[] { assoc1, assoc2, assoc3, assoc4,
                assoc5 };
    }

    private static void setupAssociations(ProgEpPerfAssociation[] associations) {
        Performer sigourneyWeaver = PERFORMERS[0];
        Performer johnHurt = PERFORMERS[1];
        Performer harrisonFord = PERFORMERS[2];

        Programme aliens = PROGRAMMES[1];
        Programme alien = PROGRAMMES[0];
        Programme bladeRunner = PROGRAMMES[3];
        Programme alien3 = PROGRAMMES[2];

        Episode unknown = EPISODES[0];

        Set<ProgEpPerfAssociation> swAssociations = new HashSet<>(
                Arrays.asList(new ProgEpPerfAssociation[] {
                        PROG_EP_PERF_ASSOCIATIONS[0],
                        PROG_EP_PERF_ASSOCIATIONS[1],
                        PROG_EP_PERF_ASSOCIATIONS[2] }));
        sigourneyWeaver.setPerformerAssociations(swAssociations);

        Set<ProgEpPerfAssociation> jhAssociations = new HashSet<>(
                Arrays.asList(new ProgEpPerfAssociation[] { PROG_EP_PERF_ASSOCIATIONS[3] }));
        johnHurt.setPerformerAssociations(jhAssociations);

        Set<ProgEpPerfAssociation> hfAssociations = new HashSet<>(
                Arrays.asList(new ProgEpPerfAssociation[] { PROG_EP_PERF_ASSOCIATIONS[4] }));
        harrisonFord.setPerformerAssociations(hfAssociations);

        Set<ProgEpPerfAssociation> aliensAssociations = new HashSet<>(
                Arrays.asList(new ProgEpPerfAssociation[] {                        
                        PROG_EP_PERF_ASSOCIATIONS[1] }));
        aliens.setProgrammeAssociations(aliensAssociations);

        Set<ProgEpPerfAssociation> alienAssociations = new HashSet<>(
                Arrays.asList(new ProgEpPerfAssociation[] {
                        PROG_EP_PERF_ASSOCIATIONS[0],
                        PROG_EP_PERF_ASSOCIATIONS[3] }));
        alien.setProgrammeAssociations(alienAssociations);
        
        Set<ProgEpPerfAssociation> alien3Associations = new HashSet<>(
                Arrays.asList(new ProgEpPerfAssociation[] {
                        PROG_EP_PERF_ASSOCIATIONS[2] }));
        alien3.setProgrammeAssociations(alien3Associations);
        
        Set<ProgEpPerfAssociation> bladeRunnerAssociations = new HashSet<>(
                Arrays.asList(new ProgEpPerfAssociation[] {
                        PROG_EP_PERF_ASSOCIATIONS[4] }));
        bladeRunner.setProgrammeAssociations(bladeRunnerAssociations);
        
        Set<ProgEpPerfAssociation> unknownEpAssociations = new HashSet<>(
                Arrays.asList(PROG_EP_PERF_ASSOCIATIONS));
        unknown.setEpisodeAssociations(unknownEpAssociations);
    }

    private static Channel[] createChannelTestData() {
        Channel bbc1 = new Channel.Builder().withChannelId(1L)
                .withChannelName("BBC 1").build();

        Channel bbc2 = new Channel.Builder().withChannelId(2L)
                .withChannelName("BBC 2").build();

        Channel bbc3 = new Channel.Builder().withChannelId(3L)
                .withChannelName("BBC 3").build();

        return new Channel[] { bbc1, bbc2, bbc3 };
    }

    private static Director[] createDirectorTestData() {
        Director ridleyScott = new Director.Builder().withDirectorId(1L)
                .withDirectorName("Ridley Scott").build();

        Director jamesCameron = new Director.Builder().withDirectorId(2L)
                .withDirectorName("James Cameron").build();

        Director jeanPierreJeunet = new Director.Builder().withDirectorId(3L)
                .withDirectorName("Jean-Pierre Jeunet").build();

        Director unknown = new Director.Builder().withDirectorId(4L).build();

        return new Director[] { ridleyScott, jamesCameron, jeanPierreJeunet,
                unknown };
    }

    private static Episode[] createEpisodeTestData() {
        Director unknown = DIRECTORS[3];
        Episode emptyEpisode = new Episode.Builder().withEpisodeId(1L)
                .withDirector(unknown).withDescription("").build();
        return new Episode[] { emptyEpisode };
    }

    private static Genre[] createGenreTestData() {
        Genre arts = new Genre.Builder().withGenreId(1L).withGenreName("Arts")
                .build();

        Genre childrens = new Genre.Builder().withGenreId(2L)
                .withGenreName("Childrens").build();

        Genre comedy = new Genre.Builder().withGenreId(3L)
                .withGenreName("Comedy").build();

        Genre currentAffairs = new Genre.Builder().withGenreId(4L)
                .withGenreName("Current affairs").build();

        Genre documentary = new Genre.Builder().withGenreId(5L)
                .withGenreName("Documentary").build();

        Genre sciFi = new Genre.Builder().withGenreId(6L)
                .withGenreName("Scifi").build();

        return new Genre[] { arts, childrens, comedy, currentAffairs,
                documentary, sciFi };
    }

    private static Performer[] createPerformerTestData() {
        Performer sigourneyWeaver = new Performer.Builder().withPerformerId(1L)
                .withPeformerName("Sigourney Weaver").build();

        Performer johnHurt = new Performer.Builder().withPerformerId(2L)
                .withPeformerName("John Hurt").build();

        Performer harrisonFord = new Performer.Builder().withPerformerId(3L)
                .withPeformerName("Harrison Ford").build();

        return new Performer[] { sigourneyWeaver, johnHurt, harrisonFord };
    }

    private static Programme[] createProgrammeTestData() {
        Director ridleyScott = DIRECTORS[0];
        Director jamesCameron = DIRECTORS[1];
        Director jeanPierreJeunet = DIRECTORS[2];

        Genre sciFi = GENRES[5];

        Programme alien = new Programme.Builder().withProgrammeId(1L)
                .withBlackAndWhite(false).withCertificate("18")
                .withDescription("Fast paced action thriller")
                .withDirector(ridleyScott).withFilm(true).withGenre(sciFi)
                .withProgTitle("Alien").withWideScreen(false)
                .withYear(new LocalDate(1979, 1, 1).toDate()).build();

        Programme aliens = new Programme.Builder().withProgrammeId(2L)
                .withBlackAndWhite(false).withCertificate("18")
                .withDescription("Fast paced action thriller")
                .withDirector(jamesCameron).withFilm(true).withGenre(sciFi)
                .withProgTitle("Aliens").withWideScreen(false)
                .withYear(new LocalDate(1986, 1, 1).toDate()).build();

        Programme alien3 = new Programme.Builder().withProgrammeId(3L)
                .withBlackAndWhite(false).withCertificate("18")
                .withDescription("Fast paced action thriller")
                .withDirector(jeanPierreJeunet).withFilm(true).withGenre(sciFi)
                .withProgTitle("Alien 3").withWideScreen(false)
                .withYear(new LocalDate(1992, 1, 1).toDate()).build();

        Programme bladeRunner = new Programme.Builder()
                .withProgrammeId(4L)
                .withBlackAndWhite(false)
                .withCertificate("18")
                .withDescription(
                        "A blade runner must pursue and try to terminate four replicants who stole a ship in space and have returned to Earth to find their creator")
                .withDirector(ridleyScott).withFilm(true).withGenre(sciFi)
                .withProgTitle("Blade Runner").withWideScreen(false)
                .withYear(new LocalDate(1982, 1, 1).toDate()).build();

        return new Programme[] { alien, aliens, alien3, bladeRunner };
    }

    public static String createStringWithLength(int length) {
        StringBuilder builder = new StringBuilder();

        for (int index = 0; index < length; index++) {
            builder.append("a");
        }

        return builder.toString();
    }

    private TestUtil() {
        
    }

}
