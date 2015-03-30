/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import com.tvfreakz.model.entity.Channel;

public class ChannelBuilderTest {

    private Channel expected;
    private Long channelId = 1L;
    private String channelName = "BBC1";

    @Before
    public void setup() {
        expected = new Channel(channelId, channelName);
    }

    @Test
    public void testChannelBuilderBuildObjectsShouldBeEqual() {
        Channel actual = new Channel.Builder().withChannelId(channelId)
                .withChannelName(channelName).build();

        assertEquals(expected, actual);
    }

    @Test
    public void testChannelBuilderBuildObjectsShouldNotBeEqual() {
        Channel actual = new Channel.Builder().withChannelId(channelId).build();

        assertNotEquals(expected, actual);
    }

}
