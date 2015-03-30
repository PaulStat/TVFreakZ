/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.model.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Type;

/**
 * Programme entity
 * 
 * @author pstatham
 *
 */
@Entity
@Immutable
@Table(name = "PROGRAMME")
public class Programme {

    public static class Builder {
        private Long programmeId;
        private Genre genre;
        private Director director;
        private String progTitle;
        private Date year;
        private boolean film;
        private boolean wideScreen;
        private boolean blackAndWhite;
        private String certificate;
        private String description;
        private Set<ProgEpPerfAssociation> programmeAssociations;

        public Programme build() {
            return new Programme(programmeId, progTitle, genre, director, year,
                    film, wideScreen, blackAndWhite, certificate, description,
                    programmeAssociations);
        }

        public Builder withBlackAndWhite(boolean blackAndWhite) {
            this.blackAndWhite = blackAndWhite;
            return this;
        }

        public Builder withCertificate(String certificate) {
            this.certificate = certificate;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withDirector(Director director) {
            this.director = director;
            return this;
        }

        public Builder withFilm(boolean film) {
            this.film = film;
            return this;
        }

        public Builder withGenre(Genre genre) {
            this.genre = genre;
            return this;
        }

        public Builder withProgrammeAssociations(
                Set<ProgEpPerfAssociation> programmeAssociations) {
            this.programmeAssociations = programmeAssociations;
            return this;
        }

        public Builder withProgrammeId(Long programmeId) {
            this.programmeId = programmeId;
            return this;
        }

        public Builder withProgTitle(String progTitle) {
            this.progTitle = progTitle;
            return this;
        }

        public Builder withWideScreen(boolean wideScreen) {
            this.wideScreen = wideScreen;
            return this;
        }

        public Builder withYear(Date year) {
            this.year = year;
            return this;
        }
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long programmeId;

    @Type(type = "com.tvfreakz.model.Genre")
    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "GENREID")
    private Genre genre;

    @Type(type = "com.tvfreakz.model.Director")
    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "DIRECTORID")
    private Director director;

    private String progTitle;

    @Column(name = "YR")
    @Temporal(TemporalType.DATE)
    private Date year;

    @Type(type = "org.hibernate.type.BooleanType")
    private boolean film;

    @Type(type = "org.hibernate.type.BooleanType")
    private boolean wideScreen;

    @Column(name = "BNW")
    @Type(type = "org.hibernate.type.BooleanType")
    private boolean blackAndWhite;

    private String certificate;

    private String description;

    @OneToMany(mappedBy = "programme")
    private Set<ProgEpPerfAssociation> programmeAssociations;

    public Programme() {
    }

    public Programme(Long programmeId, String progTitle, Genre genre,
            Director director, Date year, boolean film, boolean wideScreen,
            boolean blackAndWhite, String certificate, String description,
            Set<ProgEpPerfAssociation> programmeAssociations) {
        this.programmeId = programmeId;
        this.progTitle = progTitle;
        this.genre = genre;
        this.director = director;
        this.year = year;
        this.film = film;
        this.wideScreen = wideScreen;
        this.blackAndWhite = blackAndWhite;
        this.certificate = certificate;
        this.description = description;
        this.programmeAssociations = programmeAssociations;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Programme other = (Programme) obj;
        if (blackAndWhite != other.blackAndWhite)
            return false;
        if (certificate == null) {
            if (other.certificate != null)
                return false;
        } else if (!certificate.equals(other.certificate))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (director == null) {
            if (other.director != null)
                return false;
        } else if (!director.equals(other.director))
            return false;
        if (film != other.film)
            return false;
        if (genre == null) {
            if (other.genre != null)
                return false;
        } else if (!genre.equals(other.genre))
            return false;
        if (progTitle == null) {
            if (other.progTitle != null)
                return false;
        } else if (!progTitle.equals(other.progTitle))
            return false;
        if (programmeId == null) {
            if (other.programmeId != null)
                return false;
        } else if (!programmeId.equals(other.programmeId))
            return false;
        if (wideScreen != other.wideScreen)
            return false;
        if (year == null) {
            if (other.year != null)
                return false;
        } else if (!year.equals(other.year))
            return false;
        return true;
    }

    /**
     * @return the certificate
     */
    public String getCertificate() {
        return certificate;
    }

    public String getDescription() {
        return description;
    }

    public Director getDirector() {
        return director;
    }

    public Genre getGenre() {
        return genre;
    }

    @JsonIgnore
    public Set<ProgEpPerfAssociation> getProgrammeAssociations() {
        return programmeAssociations;
    }

    /**
     * @return the programmeId
     */
    public Long getProgrammeId() {
        return programmeId;
    }

    /**
     * @return the progTitle
     */
    public String getProgTitle() {
        return progTitle;
    }

    /**
     * @return the year
     */
    public Date getYear() {
        return year;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (blackAndWhite ? 1231 : 1237);
        result = prime * result
                + ((certificate == null) ? 0 : certificate.hashCode());
        result = prime * result
                + ((description == null) ? 0 : description.hashCode());
        result = prime * result
                + ((director == null) ? 0 : director.hashCode());
        result = prime * result + (film ? 1231 : 1237);
        result = prime * result + ((genre == null) ? 0 : genre.hashCode());
        result = prime * result
                + ((progTitle == null) ? 0 : progTitle.hashCode());
        result = prime * result
                + ((programmeId == null) ? 0 : programmeId.hashCode());
        result = prime * result + (wideScreen ? 1231 : 1237);
        result = prime * result + ((year == null) ? 0 : year.hashCode());
        return result;
    }

    /**
     * @return the blackAndWhite
     */
    public boolean isBlackAndWhite() {
        return blackAndWhite;
    }

    /**
     * @return the film
     */
    public boolean isFilm() {
        return film;
    }

    /**
     * @return the wideScreen
     */
    public boolean isWideScreen() {
        return wideScreen;
    }

    /**
     * @param blackAndWhite
     *            the blackAndWhite to set
     */
    public void setBlackAndWhite(boolean blackAndWhite) {
        this.blackAndWhite = blackAndWhite;
    }

    /**
     * @param certificate
     *            the certificate to set
     */
    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @param director
     *            the director to set
     */
    public void setDirector(Director director) {
        this.director = director;
    }

    /**
     * @param film
     *            the film to set
     */
    public void setFilm(boolean film) {
        this.film = film;
    }

    /**
     * @param genre
     *            the genre to set
     */
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setProgrammeAssociations(
            Set<ProgEpPerfAssociation> programmeAssociations) {
        this.programmeAssociations = programmeAssociations;
    }

    /**
     * @param programmeId
     *            the programmeId to set
     */
    public void setProgrammeId(Long programmeId) {
        this.programmeId = programmeId;
    }

    /**
     * @param progTitle
     *            the progTitle to set
     */
    public void setProgTitle(String progTitle) {
        this.progTitle = progTitle;
    }

    /**
     * @param wideScreen
     *            the wideScreen to set
     */
    public void setWideScreen(boolean wideScreen) {
        this.wideScreen = wideScreen;
    }

    /**
     * @param year
     *            the year to set
     */
    public void setYear(Date year) {
        this.year = year;
    }

}
