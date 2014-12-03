/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.model.entity;

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
  
  public static class Builder {
    private Long channelId;
    private String channelName;
    
    public Channel build() {
      return new Channel(channelId, channelName);
    }
    
    public Builder withChannelId(Long channelId) {
      this.channelId = channelId;
      return this;
    }
    
    public Builder withChannelName(String channelName) {
      this.channelName = channelName;
      return this;
    }
  }
  
  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy="increment")
  private Long channelId;

  private String channelName;

  public Channel() {}

  public Channel(Long channelId, String channelName) {
    this.channelId = channelId;
    this.channelName = channelName;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Channel other = (Channel) obj;
    if (channelId == null) {
      if (other.channelId != null)
        return false;
    } else if (!channelId.equals(other.channelId))
      return false;
    if (channelName == null) {
      if (other.channelName != null)
        return false;
    } else if (!channelName.equals(other.channelName))
      return false;
    return true;
  }

  /**
   * Returns the Channel ID
   * @return channelId
   */
  public Long getChannelId() {
    return channelId;
  }

  /**
   * Returns the channel name
   * @return channelName
   */
  public String getChannelName() {
    return channelName;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((channelId == null) ? 0 : channelId.hashCode());
    result = prime * result + ((channelName == null) ? 0 : channelName.hashCode());
    return result;
  }
  
  public void setChannelId(Long channelId) {
    this.channelId = channelId;
  }
  
  public void setChannelName(String channelName) {
    this.channelName = channelName;
  }


}
