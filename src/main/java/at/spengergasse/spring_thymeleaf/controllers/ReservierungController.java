package at.spengergasse.spring_thymeleaf.controllers;

import at.spengergasse.spring_thymeleaf.entities.Patient;
import at.spengergasse.spring_thymeleaf.entities.Reservierungen;
import at.spengergasse.spring_thymeleaf.repositories.GeraeteRepository;
import at.spengergasse.spring_thymeleaf.repositories.PatientRepository;
import at.spengergasse.spring_thymeleaf.repositories.ReservierungenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String add(Model model) {
        model.addAttribute("reservierungen", new Reservierungen());
        model.addAttribute("patienten", patientRepository.findAll());
        model.addAttribute("geraete", geraeteRepository.findAll());
        return "add_reservierungen";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Reservierungen reservierungen) {
        reservierungenRepository.save(reservierungen);
        return "redirect:/reservierungen/list";
    }
}
