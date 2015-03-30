package com.tvfreakz.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "PROG_EP_PERF")
public class ProgEpPerfAssociation implements Serializable {

    public static class Builder {
        private Long programmeId;
        private Long episodeId;
        private Long performerId;
        private Programme programme;
        private Episode episode;
        private Performer performer;

        public ProgEpPerfAssociation build() {
            return new ProgEpPerfAssociation(programmeId, episodeId,
                    performerId, programme, episode, performer);
        }

        public Builder withEpisode(Episode episode) {
            this.episode = episode;
            return this;
        }

        public Builder withEpisodeId(Long episodeId) {
            this.episodeId = episodeId;
            return this;
        }

        public Builder withPerformer(Performer performer) {
            this.performer = performer;
            return this;
        }

        public Builder withPerformerId(Long performerId) {
            this.performerId = performerId;
            return this;
        }

        public Builder withProgramme(Programme programme) {
            this.programme = programme;
            return this;
        }

        public Builder withProgrammeId(Long programmeId) {
            this.programmeId = programmeId;
            return this;
        }

    }

    private static final long serialVersionUID = 1L;

    @Id
    private Long programmeId;

    @Id
    private Long episodeId;

    @Id
    private Long performerId;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "PROGRAMMEID")
    private Programme programme;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "EPISODEID")
    private Episode episode;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "PERFORMERID")
    private Performer performer;

    public ProgEpPerfAssociation(Long programmeId, Long episodeId,
            Long performerId, Programme programme, Episode episode,
            Performer performer) {
        this.programmeId = programmeId;
        this.episodeId = episodeId;
        this.performerId = performerId;
        this.programme = programme;
        this.episode = episode;
        this.performer = performer;
    }

    public Episode getEpisode() {
        return episode;
    }

    public Long getEpisodeId() {
        return episodeId;
    }

    public Performer getPerformer() {
        return performer;
    }

    public Long getPerformerId() {
        return performerId;
    }

    public Programme getProgramme() {
        return programme;
    }

    public Long getProgrammeId() {
        return programmeId;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }

    public void setEpisodeId(Long episodeId) {
        this.episodeId = episodeId;
    }

    public void setPerformer(Performer performer) {
        this.performer = performer;
    }

    public void setPerformerId(Long performerId) {
        this.performerId = performerId;
    }

    public void setProgramme(Programme programme) {
        this.programme = programme;
    }

    public void setProgrammeId(Long programmeId) {
        this.programmeId = programmeId;
    }

}
