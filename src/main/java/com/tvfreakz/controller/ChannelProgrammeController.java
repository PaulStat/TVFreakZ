/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tvfreakz.exception.ChannelNotFoundException;
import com.tvfreakz.exception.ChannelProgrammeNotFoundException;
import com.tvfreakz.exception.DirectorNotFoundException;
import com.tvfreakz.exception.InvalidDateFormatException;
import com.tvfreakz.exception.PerformerNotFoundException;
import com.tvfreakz.model.dto.ChannelDTO;
import com.tvfreakz.model.dto.ChannelProgrammeDTO;
import com.tvfreakz.model.dto.DirectorDTO;
import com.tvfreakz.model.dto.EpisodeDTO;
import com.tvfreakz.model.dto.ProgrammeDTO;
import com.tvfreakz.model.dto.ProgrammeSearchDTO;
import com.tvfreakz.model.entity.ChannelProgramme;
import com.tvfreakz.service.ChannelProgrammeService;
import com.tvfreakz.service.ChannelService;
import com.tvfreakz.service.DirectorService;
import com.tvfreakz.service.PerformerService;

@SessionAttributes("filter")
@Controller
public class ChannelProgrammeController {

    private ChannelProgrammeService channelProgrammeService;
    private DirectorService directorService;
    private PerformerService performerService;
    private ChannelService channelService;

    @Autowired
    public void setChannelProgrammeService(
            ChannelProgrammeService channelProgrammeService) {
        this.channelProgrammeService = channelProgrammeService;
    }

    @Autowired
    public void setDirectorService(DirectorService directorService) {
        this.directorService = directorService;
    }

    @Autowired
    public void setPerformerService(PerformerService performerService) {
        this.performerService = performerService;
    }

    @Autowired
    public void setChannelService(ChannelService channelService) {
        this.channelService = channelService;
    }

    @ResponseBody
    @RequestMapping(value = "/api/all", method = RequestMethod.GET)
    public List<ChannelProgrammeDTO> findScheduledProgrammes() {
        List<ChannelProgramme> chanprogs = channelProgrammeService
                .findScheduledProgrammes();
        return createDTOList(chanprogs);
    }

    @ResponseBody
    @RequestMapping(value = "/api/all/{from}/{to}", method = RequestMethod.GET)
    public List<ChannelProgrammeDTO> findScheduledProgrammesForPeriod(
            @PathVariable("from") String from, @PathVariable("to") String to)
            throws InvalidDateFormatException {
        // First check if the date request fields are valid
        if (!(DateTimeParameterValidator.validateDateTimeString(from) || DateTimeParameterValidator
                .validateDateTimeString(to))) {
            throw new InvalidDateFormatException();
        }
        List<ChannelProgramme> chanprogs = channelProgrammeService
                .findScheduledProgrammesForPeriod(from, to);
        return createDTOList(chanprogs);
    }

    @ResponseBody
    @RequestMapping(value = "/api/programme/{channelProgrammeId}", method = RequestMethod.GET)
    public ChannelProgrammeDTO findScheduledProgramme(
            @PathVariable("channelProgrammeId") Long channelProgrammeId)
            throws ChannelProgrammeNotFoundException {
        ChannelProgramme chanProg = channelProgrammeService
                .findScheduledProgramme(channelProgrammeId);
        return createDTO(chanProg);
    }

    @ResponseBody
    @RequestMapping(value = "/api/programmeshowings/{channelProgrammeId}", method = RequestMethod.GET)
    public List<ChannelProgrammeDTO> findScheduledProgrammeShowings(
            @PathVariable("channelProgrammeId") Long channelProgrammeId)
            throws ChannelProgrammeNotFoundException {
        List<ChannelProgramme> chanprogs = channelProgrammeService
                .findScheduledProgrammeShowings(channelProgrammeId);
        return createDTOList(chanprogs);
    }

    @RequestMapping(value = "/api/filter", method = RequestMethod.POST)
    public String filterChannelProgrammes(
            @Valid @ModelAttribute("filter") ProgrammeSearchDTO programmeSearchDTO,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "api/filter";
        }
        model.addAttribute("filter", programmeSearchDTO);
        return "redirect:/filterResults";
    }

    @ResponseBody
    @RequestMapping(value = "/filterResults", method = RequestMethod.GET)
    public List<ChannelProgrammeDTO> getFilteredChannelProgrammes(
            @ModelAttribute("filter") ProgrammeSearchDTO programmeSearcDTO) {
        List<ChannelProgramme> chanprogs = channelProgrammeService
                .filterChannelProgrammes(programmeSearcDTO);
        return createDTOList(chanprogs);
    }

    @ResponseBody
    @RequestMapping(value = "/api/channelshowings/{channelId}/{from}/{to}", method = RequestMethod.GET)
    public List<ChannelProgrammeDTO> findScheduledChannelProgrammesForPeriod(
            @PathVariable("channelId") Long channelId,
            @PathVariable("from") String from, @PathVariable("to") String to)
            throws ChannelNotFoundException, InvalidDateFormatException {
        // First check if the date request fields are valid
        if (!(DateTimeParameterValidator.validateDateTimeString(from) || DateTimeParameterValidator
                .validateDateTimeString(to))) {
            throw new InvalidDateFormatException();
        }
        // Now check the channel exists via the ChannelService, if it doesn't
        // a ChannelNotFoundException is thrown
        channelService.findByChannelId(channelId);
        List<ChannelProgramme> chanprogs = channelProgrammeService
                .findScheduledChannelProgrammesForPeriod(channelId, from, to);
        return createDTOList(chanprogs);
    }

    @ResponseBody
    @RequestMapping(value = "/api/directorshowings/{directorId}", method = RequestMethod.GET)
    public List<ChannelProgrammeDTO> findScheduledDirectorProgrammes(
            @PathVariable("directorId") Long directorId)
            throws DirectorNotFoundException {
        // First check that a director with the specified id exists
        // if it does not then the DirectorService will throw a
        // DirectorNotFoundException
        directorService.findByDirectorId(directorId);
        Date today = new LocalDate().toDateTimeAtStartOfDay().toDate();
        Date twoWeeks = new LocalDate().toDateTimeAtStartOfDay().plusWeeks(2)
                .toDate();
        List<ChannelProgramme> chanprogs = channelProgrammeService
                .findScheduledDirectorProgrammes(directorId, today, twoWeeks);
        return createDTOList(chanprogs);
    }

    @ResponseBody
    @RequestMapping(value = "/api/performershowings/{performerId}", method = RequestMethod.GET)
    public List<ChannelProgrammeDTO> findScheduledPerformerProgrammes(
            @PathVariable("performerId") Long performerId)
            throws PerformerNotFoundException {
        // First check that a performer with the specified id exists
        // if it does not then the PerformerService will throw a
        // DirectorNotFoundException
        performerService.findByPerformerId(performerId);
        Date today = new LocalDate().toDateTimeAtStartOfDay().toDate();
        Date twoWeeks = new LocalDate().toDateTimeAtStartOfDay().plusWeeks(2)
                .toDate();
        List<ChannelProgramme> chanprogs = channelProgrammeService
                .findScheduledPerformerProgrammes(performerId, today, twoWeeks);
        return createDTOList(chanprogs);
    }

    private ChannelProgrammeDTO createDTO(ChannelProgramme chanprog) {
        ChannelProgrammeDTO channelProgrammeDTO = new ChannelProgrammeDTO();

        ChannelDTO channelDTO = new ChannelDTO();
        channelDTO.setChannelId(chanprog.getChannel().getChannelId());
        channelDTO.setChannelName(chanprog.getChannel().getChannelName());

        EpisodeDTO episodeDTO = null;
        if (chanprog.getEpisode() != null) {
            episodeDTO = new EpisodeDTO();
            DirectorDTO directorDTO = new DirectorDTO();
            directorDTO.setDirectorId(chanprog.getEpisode().getDirector()
                    .getDirectorId());
            directorDTO.setDirectorName(chanprog.getEpisode().getDirector()
                    .getDirectorName());
            episodeDTO.setEpisodeId(chanprog.getEpisode().getEpisodeId());
            episodeDTO.setDescription(chanprog.getEpisode().getDescription());
            episodeDTO.setDirector(directorDTO);
            episodeDTO.setEpisode(chanprog.getEpisode().getEpisode());
            episodeDTO.setEpisodeAssociations(chanprog.getEpisode()
                    .getEpisodeAssociations());
        }

        ProgrammeDTO programmeDTO = new ProgrammeDTO();
        programmeDTO
                .setBlackAndWhite(chanprog.getProgramme().isBlackAndWhite());
        programmeDTO.setCertificate(chanprog.getProgramme().getCertificate());
        programmeDTO.setDescription(chanprog.getProgramme().getDescription());
        programmeDTO.setDirector(chanprog.getProgramme().getDirector());
        programmeDTO.setFilm(chanprog.getProgramme().isFilm());
        programmeDTO.setGenre(chanprog.getProgramme().getGenre());
        programmeDTO.setProgrammeAssociations(chanprog.getProgramme()
                .getProgrammeAssociations());
        programmeDTO.setProgrammeId(chanprog.getProgramme().getProgrammeId());
        programmeDTO.setProgTitle(chanprog.getProgramme().getProgTitle());
        programmeDTO.setWideScreen(chanprog.getProgramme().isWideScreen());
        programmeDTO.setYear(chanprog.getProgramme().getYear());

        channelProgrammeDTO.setChannel(channelDTO);
        channelProgrammeDTO.setChannelProgrammeId(chanprog
                .getChannelProgrammeId());
        channelProgrammeDTO.setChoice(chanprog.isChoice());
        channelProgrammeDTO.setDeafSigned(chanprog.isDeafSigned());
        channelProgrammeDTO.setDuration(chanprog.getDuration());
        channelProgrammeDTO.setEndTime(chanprog.getEndTime());
        channelProgrammeDTO.setEpisode(episodeDTO);
        channelProgrammeDTO.setNewSeries(chanprog.isNewSeries());
        channelProgrammeDTO.setPremiere(chanprog.isPremiere());
        channelProgrammeDTO.setProgramme(programmeDTO);
        channelProgrammeDTO.setRepeat(chanprog.isRepeat());
        channelProgrammeDTO.setStarRating(chanprog.getStarRating());
        channelProgrammeDTO.setStartTime(chanprog.getStartTime());
        channelProgrammeDTO.setSubtitles(chanprog.isSubtitles());
        return channelProgrammeDTO;
    }

    private List<ChannelProgrammeDTO> createDTOList(
            List<ChannelProgramme> chanprogs) {
        List<ChannelProgrammeDTO> chanprogDTOS = new ArrayList<>();
        for (ChannelProgramme chanprog : chanprogs) {
            chanprogDTOS.add(createDTO(chanprog));
        }
        return chanprogDTOS;
    }

}
