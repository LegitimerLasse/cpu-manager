package com.nubedian.cpuapi.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cpu")
public class Cpu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "socket_id", nullable = false)
    private Socket socket;

    @Column(name = "clock_speed_ghz", nullable = false)
    private BigDecimal clockSpeedGhz;

    @Column(nullable = false)
    private Integer cores;

    @Column(nullable = false)
    private Integer threads;

    @Column(name = "tdp_watt")
    private Integer tdpWatt;

    @Column(name = "price_eur", nullable = false)
    private BigDecimal priceEur;

    @Version
    private Integer version;

    public Long getId() { return id; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public Socket getSocket() { return socket; }
    public void setSocket(Socket socket) { this.socket = socket; }
    public BigDecimal getClockSpeedGhz() { return clockSpeedGhz; }
    public void setClockSpeedGhz(BigDecimal clockSpeedGhz) { this.clockSpeedGhz = clockSpeedGhz; }
    public Integer getCores() { return cores; }
    public void setCores(Integer cores) { this.cores = cores; }
    public Integer getThreads() { return threads; }
    public void setThreads(Integer threads) { this.threads = threads; }
    public Integer getTdpWatt() { return tdpWatt; }
    public void setTdpWatt(Integer tdpWatt) { this.tdpWatt = tdpWatt; }
    public BigDecimal getPriceEur() { return priceEur; }
    public void setPriceEur(BigDecimal priceEur) { this.priceEur = priceEur; }
    public Integer getVersion() { return version; }
}
