/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java8;

import java.io.Serializable;

/**
 *
 * @author yassin
 */
public class Beacon implements Serializable{
    
    private Long id;
    private String icao_code;
    private String landind_track;
    private String outer_beacon;
    private String middle_beacon;
    private String inner_beacon;
    private String airport_name;

    public Beacon(){
        
    }
    
    public Beacon(Long id, String icao_code, String landind_track, String outer_beacon, String middle_beacon, String inner_beacon, String airport_name) {
        this.id = id;
        this.icao_code = icao_code;
        this.landind_track = landind_track;
        this.outer_beacon = outer_beacon;
        this.middle_beacon = middle_beacon;
        this.inner_beacon = inner_beacon;
        this.airport_name = airport_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIcao_code() {
        return icao_code;
    }

    public void setIcao_code(String icao_code) {
        this.icao_code = icao_code;
    }

    public String getLandind_track() {
        return landind_track;
    }

    public void setLandind_track(String landind_track) {
        this.landind_track = landind_track;
    }

    public String getOuter_beacon() {
        return outer_beacon;
    }

    public void setOuter_beacon(String outer_beacon) {
        this.outer_beacon = outer_beacon;
    }

    public String getMiddle_beacon() {
        return middle_beacon;
    }

    public void setMiddle_beacon(String middle_beacon) {
        this.middle_beacon = middle_beacon;
    }

    public String getInner_beacon() {
        return inner_beacon;
    }

    public void setInner_beacon(String inner_beacon) {
        this.inner_beacon = inner_beacon;
    }

    public String getAirport_name() {
        return airport_name;
    }

    public void setAirport_name(String airport_name) {
        this.airport_name = airport_name;
    }
    
    
    
}
