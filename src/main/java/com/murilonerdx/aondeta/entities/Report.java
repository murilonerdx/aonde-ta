package com.murilonerdx.aondeta.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import com.murilonerdx.aondeta.dto.ReportDTO;
import com.murilonerdx.aondeta.entities.Profile;
import org.springframework.hateoas.RepresentationModel;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="TB_REPORT")
public class Report extends RepresentationModel<Report> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @JsonFormat(pattern="dd-MM-YYYY")
    private LocalDate momentEvent;

    private String whatWasStolen;

    @ManyToMany(mappedBy="reporteds", cascade = CascadeType.ALL)
    private List<Local> locals = new ArrayList<>();
    
    @ManyToOne()
    private Profile profile;

    public Report(Long id, String name, String description, LocalDate momentEvent, String whatWasStolen) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.momentEvent = momentEvent;
        this.whatWasStolen = whatWasStolen;
    }

    public Report() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getMomentEvent() {
        return momentEvent;
    }

    public void setMomentEvent(LocalDate momentEvent) {
        this.momentEvent = momentEvent;
    }

    public String getWhatWasStolen() {
        return whatWasStolen;
    }

    public void setWhatWasStolen(String whatWasStolen) {
        this.whatWasStolen = whatWasStolen;
    }

    public List<Local> getLocals() {
        return locals;
    }

    public void setLocals(List<Local> locals) {
        this.locals = locals;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
