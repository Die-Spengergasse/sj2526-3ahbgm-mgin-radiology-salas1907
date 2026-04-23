package at.spengergasse.spring_thymeleaf.controllers;

import at.spengergasse.spring_thymeleaf.entities.Geraete;
import at.spengergasse.spring_thymeleaf.entities.Patient;
import at.spengergasse.spring_thymeleaf.entities.Reservierungen;
import at.spengergasse.spring_thymeleaf.repositories.GeraeteRepository;
import at.spengergasse.spring_thymeleaf.repositories.PatientRepository;
import at.spengergasse.spring_thymeleaf.repositories.ReservierungenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/reservierungen")
public class ReservierungController {
    private final ReservierungenRepository reservierungenRepository;
    private final PatientRepository patientRepository;
    private final GeraeteRepository geraeteRepository;

    public ReservierungController(ReservierungenRepository reservierungenRepository,
                                  PatientRepository patientRepository,
                                  GeraeteRepository geraeteRepository) {
        this.reservierungenRepository = reservierungenRepository;
        this.patientRepository = patientRepository;
        this.geraeteRepository = geraeteRepository;
    }
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("reservierungens", reservierungenRepository.findAll());
        var liste = reservierungenRepository.findAll();
        System.out.println("Anzahl: " + liste.size());
        model.addAttribute("reservierungen", liste);
        return "reslist";
    }

    @GetMapping("/add")
    public String add(Model model, @RequestParam(required = false) String error) {
        model.addAttribute("reservierungen", new Reservierungen());
        model.addAttribute("patienten", patientRepository.findAll());
        model.addAttribute("geraete", geraeteRepository.findAll());
        if (error != null) {
            model.addAttribute("fehlermeldung", error);
        }
        return "add_reservierungen";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Reservierungen reservierungen) {
        List<Reservierungen> alleReservierungen = reservierungenRepository.findAll();
        LocalDateTime reservierungsszeit = reservierungen.getDatum();
        Geraete reservierungsGeraet = reservierungen.getGeraete();
        Patient reservierungsPatient = reservierungen.getPatient();
        for (Reservierungen r : alleReservierungen) {
            Geraete reservierteGereate = r.getGeraete();
            Patient reserviertePatient = r.getPatient();
            if(reservierungsszeit.isBefore(r.getDatum().plusMinutes(15)) &&
                    reservierungsszeit.isAfter(r.getDatum().minusMinutes(15)) &&
                    reservierungsGeraet.getId() == reservierteGereate.getId() &&
                    reservierungsPatient.getId() == reserviertePatient.getId()) {
                return "redirect:/reservierungen/add?error=Zeitraum+ist+bereits+belegt";
            }
        }
        reservierungenRepository.save(reservierungen);
        return "redirect:/reservierungen/list";
    }

    @DeleteMapping("/delete")
    public String deleteReservierung(@RequestParam List<Integer> ids) {
        for (Integer id : ids) {
            reservierungenRepository.deleteById(id);
        }
        return "redirect:/reservierungen/list";
    }
}
