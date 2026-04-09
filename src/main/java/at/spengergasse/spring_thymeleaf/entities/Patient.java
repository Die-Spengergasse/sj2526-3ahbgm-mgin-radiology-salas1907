package at.spengergasse.spring_thymeleaf.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="p_patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="p_svnr")
    private Integer id;

    @Column(name="p_vorname")
    private String vorname;

    @Column(name="p_nachname")
    private String nachname;

    @Column(name="p_gebdat")
    private LocalDate birth;

    @Column(name="p_geschlecht")
    private String geschlecht;

    @OneToMany(mappedBy = "patient")
    private List<Reservierungen> reservierungen = new ArrayList<>();


    public Patient() {}

    public Patient(String vorname, String nachname, LocalDate birth) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.birth = birth;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
    }

    public List<Reservierungen> getReservierungen() {
        return reservierungen;
    }

    public void setReservierungen(List<Reservierungen> reservierungen) {
        this.reservierungen = reservierungen;
    }
}
