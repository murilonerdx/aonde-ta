package com.murilonerdx.aondeta.entities;

import com.murilonerdx.aondeta.entities.enums.StatusLocal;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Local {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private Double latitude;
    private Double longitude;
    
    @Enumerated(EnumType.STRING)
    private StatusLocal status;

    @ManyToMany
    @JoinTable(name="local_report",
            joinColumns={@JoinColumn(name="local_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="report_id", referencedColumnName="id")})
    List<Report> reports = new ArrayList<>();


    public Local(Long id, String address, Double latitude, Double longitude, StatusLocal status) {
        this.id = id;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
    }

    public Local() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public StatusLocal getStatus() {
        return status;
    }

    public void setStatus(StatusLocal status) {
        this.status = status;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }
}
