/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Immutable;

/**
 * Channel entity - represents a TV Channel
 * @author pstatham
 *
 */
@Entity
@Immutable
@Table( name = "CHANNELS" )
public class Channel {
  
  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy="increment")
  private Long channelId;

  private String channelName;

  public Channel() {}

  /**
   * Returns the Channel ID
   * @return channelId
   */
  public Long getChannelId() {
    return channelId;
  }

  private void setChannelId(Long channelId) {
    this.channelId = channelId;
  }
  
  /**
   * Returns the channel name
   * @return channelName
   */
  public String getChannelName() {
    return channelName;
  }
  
  private void setChannelName(String channelName) {
    this.channelName = channelName;
  }


}
