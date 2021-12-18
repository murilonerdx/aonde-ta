package com.murilonerdx.aondeta.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @JsonFormat(pattern="dd-MM-YYYY")
    private LocalDate momentEvent;

    private String whatWasStolen;

    @ManyToMany(mappedBy="reports")
    private List<Local> locals;

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
}
