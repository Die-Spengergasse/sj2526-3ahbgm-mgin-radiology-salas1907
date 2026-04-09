package at.spengergasse.spring_thymeleaf.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "g_geraete")
public class Geraete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "g_id", nullable = false)
    private Integer id;

    @Column(name="g_artGeraet")
    private String artDesgeraets;

    @Column(name="g_Standort")
    private String standort;

    @OneToMany(mappedBy = "geraete")
    private List<Reservierungen> reservierungen = new ArrayList<>();



    public Geraete() {}

    public Geraete(String standort, String artDesgeraets, Integer id) {
        this.standort = standort;
        this.artDesgeraets = artDesgeraets;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArtDesgeraets() {
        return artDesgeraets;
    }

    public void setArtDesgeraets(String artDesgeraets) {
        this.artDesgeraets = artDesgeraets;
    }

    public String getStandort() {
        return standort;
    }

    public void setStandort(String standort) {
        this.standort = standort;
    }

    public List<Reservierungen> getReservierungen() {
        return reservierungen;
    }

    public void setReservierungen(List<Reservierungen> reservierungen) {
        this.reservierungen = reservierungen;
    }

    //TODO [Reverse Engineering] generate columns from DB
}