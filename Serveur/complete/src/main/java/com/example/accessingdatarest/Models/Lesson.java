package com.example.accessingdatarest.Models;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String moniteur;
    private String Titre;
    private Date date;
    private String level;
    private String starttime;
    private String endtime;
    @ElementCollection
    private List<String> student;
    @ElementCollection
    private Set<Horse> horses;
    private String nbreleve;

    protected Lesson() {
    }

    public Lesson(String Titre, String moniteur, String level, Date date, String starttime, String endtime,
            List<String> student, Set<Horse> horses, String nbreleve) {
        this.moniteur = moniteur;
        this.Titre = Titre;
        this.date = date;
        this.starttime = starttime;
        this.endtime = endtime;
        this.student = student;
        this.horses = horses;
        this.level = level;
        this.nbreleve = nbreleve;
    }

    public String getMoniteur() {
        return moniteur;
    }

    public void setMoniteur(String moniteur) {
        this.moniteur = moniteur;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStartTime() {
        return starttime;
    }

    public void setStartTime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndTime() {
        return endtime;
    }

    public void setEndTime(String endtime) {
        this.endtime = endtime;
    }

    public List<String> getStudent() {
        return student;
    }

    public void setStudent(List<String> student) {
        this.student = student;
    }

    public Set<Horse> getHorses() {
        return horses;
    }

    public void setHorses(Set<Horse> horses) {
        this.horses.add((Horse) horses);
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public String getNbreleve() {
        return nbreleve;
    }

    public void setNbreleve(String nbreleve) {
        this.nbreleve = nbreleve;
    }

}
