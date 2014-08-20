package com.tvfreakz.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tvfreakz.model.entity.ChannelProgramme;

@Repository("channelProgrammeRepository")
public interface ChannelProgrammeRepository extends JpaRepository<ChannelProgramme, Long> {

  List<ChannelProgramme> findByProgDateBetweenOrderByProgDateAscStartTimeAsc(Date today, Date twoWeeks);

}
