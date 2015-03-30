/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.tvfreakz.model.entity.Performer;
import com.tvfreakz.model.entity.ProgEpPerfAssociation;
import com.tvfreakz.model.entity.Programme;

public class PerformerBuilderTest {

    private Performer expected;
    private Long performerId = 1L;
    private String performerName = "Sigourney Weaver";
    private Programme aliens = new Programme();
    private Set<ProgEpPerfAssociation> performerAssociations = new HashSet<>(
            Arrays.asList(new ProgEpPerfAssociation[] { new ProgEpPerfAssociation.Builder()
                    .withProgramme(aliens).build() }));

    @Before
    public void setup() {
        expected = new Performer(performerId, performerName,
                performerAssociations);
    }

    @Test
    public void testPerformerBuilderBuildObjectsShouldBeEqual() {
        Performer actual = new Performer.Builder().withPerformerId(performerId)
                .withPeformerName(performerName)
                .withPerformerAssociations(performerAssociations).build();

        assertEquals(expected, actual);
    }

    @Test
    public void testPerformerBuilderBuildObjectsShouldNotBeEqual() {
        Performer actual = new Performer.Builder().withPerformerId(performerId)
                .build();

        assertNotEquals(expected, actual);
    }

}
