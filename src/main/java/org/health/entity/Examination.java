package org.health.entity;

import org.health.dao.IGettingID;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Сущность осмотр пациента
 */
@Entity
@Table(name = "examinations")
public class Examination implements IGettingID {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String conclusion;
    private LocalDate localDate;

    public Examination() {
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
}
