package edu.cth.dzenkyair.frontend.view;

import edu.cth.dzenkyair.backend.core.Airport;
import edu.cth.dzenkyair.backend.core.FlightModel;
import edu.cth.dzenkyair.backend.core.Line;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * 
 *
 * @author Dženan
 */
@Named
@RequestScoped
public class ListAirportsBB implements Serializable {
    
    @Inject
    private FlightModel flightModel;
    
    private Long fromId;
    private Long lineId;
    private int year;
    private int month;
    private int day;
    private String error;
    
    protected ListAirportsBB() {
        // Must have for CDI
    }

    public Collection<Airport> getAllAirport() {
        return flightModel.getAirportList().findAll();
    }
    
    public Collection<Line> getLines() {
        if(fromId == null)
            return new ArrayList<Line>();
        Airport airport = flightModel.getAirportList().find(fromId);
        return flightModel.getLineList().getByFromAirport(airport);
    }
    
    public Long getFromId() {
        return fromId;
    }

    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }
    
    public Long getLineId() {
        return lineId;
    }

    public void setLineId(Long lineId) {
        this.lineId = lineId;
    }
    
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
    
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
    
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
}
