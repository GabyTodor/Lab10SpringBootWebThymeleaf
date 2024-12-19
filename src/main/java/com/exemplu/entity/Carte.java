package com.exemplu.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name="carti")
public class Carte {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titlul;
    private String autorul;

    public Carte(int id, String titlul, String autorul) {
        this.id = id;
        this.titlul = titlul;
        this.autorul = autorul;
    }
    public Carte(String titlul, String autorul) {
        this.titlul = titlul;
        this.autorul = autorul;
    }
    public Carte(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitlul() {
        return titlul;
    }

    public void setTitlul(String titlul) {
        this.titlul = titlul;
    }

    public String getAutorul() {
        return autorul;
    }

    public void setAutorul(String autorul) {
        this.autorul = autorul;
    }

    @Override
    public String toString() {
        return "Carte{" +
                "id=" + id +
                ", titlul='" + titlul + '\'' +
                ", autorul='" + autorul + '\'' +
                '}';
    }
}
