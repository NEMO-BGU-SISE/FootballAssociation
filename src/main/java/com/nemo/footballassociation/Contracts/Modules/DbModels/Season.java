package com.nemo.footballassociation.Contracts.Modules.DbModels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Season {
    @Id
    private int Id;
    private LocalDate startDate;
    private LocalDate endDate;

    public Season() {
    }

    public Season(int id, LocalDate startDate, LocalDate endDate) {
        Id = id;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
