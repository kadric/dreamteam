package edu.cth.dzenkyair.frontend.view;

import edu.cth.dzenkyair.backend.core.Airport;
import edu.cth.dzenkyair.backend.core.FlightModel;
import edu.cth.dzenkyair.backend.core.Line;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 * 
 *
 * @author Dženan
 */
@Named
@ViewScoped
public class ListAirportsBB implements Serializable {
    
    @Inject
    private FlightModel flightModel;
    
    private Long fromId;
    private Long lineId;
    private Date date;
    private int nPassengers;
    private String error;
  
    
     public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }
  
    public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
         
        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");
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
    
    public Collection<Integer> getNPassengersList() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        return list;
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
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public int getNPassengers() {
        return nPassengers;
    }
    public void setNPassengers(int nPassengers) {
        this.nPassengers = nPassengers;
    }
    
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    
  }
