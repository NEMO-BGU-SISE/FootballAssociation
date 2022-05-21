package com.nemo.footballassociation.Contracts.Modules.DbModels;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Season {
    @Id
    private int id;
    private LocalDate start_date;
    private LocalDate end_date;

    public Season() {
    }

    public Season(int id, LocalDate start_date, LocalDate end_date) {
        this.id = id;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate startDate) {
        this.start_date = startDate;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate endDate) {
        this.end_date = endDate;
    }
}
