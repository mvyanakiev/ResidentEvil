package residentevil.domain.models.view;

import residentevil.domain.entities.Creator;
import residentevil.domain.entities.Magnitude;
import residentevil.domain.entities.Mutation;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class VirusListViewModel {

    private String id;
    private String name;
    private Magnitude magnitude;
    private LocalDate releasedOn;

    public VirusListViewModel() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Magnitude getMagnitude() {
        return this.magnitude;
    }

    public void setMagnitude(Magnitude magnitude) {
        this.magnitude = magnitude;
    }

    public Date getReleasedOn() {
        Date date = Date.from(this.releasedOn.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return date;
    }

    public void setReleasedOn(LocalDate releasedOn) {
        this.releasedOn = releasedOn;
    }
}


