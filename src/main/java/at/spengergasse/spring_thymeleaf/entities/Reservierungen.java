package at.spengergasse.spring_thymeleaf.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="r_reservierungen")
public class Reservierungen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "r_id")
    private Integer id;

    @Column(name = "r_datum")
    private LocalDateTime datum;

    @ManyToOne
    @JoinColumn(name = "p_patient_p_svnr")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "g_geraete_g_id")
    private Geraete geraete;

    public Reservierungen() {}

    public Reservierungen(LocalDateTime datum, Patient patient, Geraete geraete) {
        this.datum = datum;
        this.patient = patient;
        this.geraete = geraete;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Geraete getGeraete() {
        return geraete;
    }

    public void setGeraete(Geraete geraete) {
        this.geraete = geraete;
    }
}
