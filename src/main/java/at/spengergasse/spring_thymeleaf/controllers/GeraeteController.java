package at.spengergasse.spring_thymeleaf.controllers;

import at.spengergasse.spring_thymeleaf.entities.Geraete;
import at.spengergasse.spring_thymeleaf.repositories.GeraeteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/geraete")
public class GeraeteController {
    private final GeraeteRepository geraeteRepository;

    public GeraeteController(GeraeteRepository geraeteRepository) {
        this.geraeteRepository = geraeteRepository;
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("geraete", geraeteRepository.findAll());
        return "gerlist";
    }

    @GetMapping("/add")
    public String addGeraete(Model model) {
        model.addAttribute("geraete", new Geraete());
        return "add_geraete";
    }

    @PostMapping("/add")
    public String saveGeraete(@ModelAttribute Geraete geraete) {
        geraeteRepository.save(geraete);
        return "redirect:/geraete/list";
    }

}
