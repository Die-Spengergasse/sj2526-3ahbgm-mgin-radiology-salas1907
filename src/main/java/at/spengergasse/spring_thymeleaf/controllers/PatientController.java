package at.spengergasse.spring_thymeleaf.controllers;

import at.spengergasse.spring_thymeleaf.entities.Patient;
import at.spengergasse.spring_thymeleaf.repositories.PatientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientController {
    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping("/list")
    public String patients(Model model) {
        model.addAttribute("patients", patientRepository.findAll());
        return "patlist";
    }

    @GetMapping("/add")
    public String addPatient(Model model, @RequestParam(required = false) String error) {
        model.addAttribute("patient", new Patient());
        if (error != null) {
            model.addAttribute("fehlermeldung_patient", error);
        }
        return "add_patient";
    }

    @PostMapping("/add")
    public String addPatient(@ModelAttribute("patient") Patient patient) {
        LocalDate now = LocalDate.now();
        if(patient.getBirth().isAfter(now)) {
            return "redirect:/patient/add?error=Geburtstdatum+liegt+in+der+Zukunft";
        }
        if(!(patient.getSvnr().length() == 10)){
            return "redirect:/patient/add?error=SVNR+muss+zehnstellig+sein";
        }
        if(!korrekteSvnr(patient)){
            return "redirect:/patient/add?error=SVNR+ist+nicht+gültig";
        }

        patientRepository.save(patient);
        return  "redirect:/patient/list";
    }

    @DeleteMapping("/delete")
    public String deletePatient(@RequestParam List<Integer> ids) {
        for (Integer id : ids) {
            patientRepository.deleteById(id);
        }
        return "redirect:/patient/list";
    }

    public boolean korrekteSvnr(Patient patient) {
        String versicherungsnummer=patient.getSvnr();
        int pruefziffer = 0;
        int[] gewichtung = {3,7,9,0,5,8,4,2,1,6};

        for(int i=0;i<gewichtung.length;i++){
            pruefziffer += Character.getNumericValue(versicherungsnummer.charAt(i)) * gewichtung[i];
        }
        pruefziffer = pruefziffer % 11;


        return pruefziffer == Character.getNumericValue(versicherungsnummer.charAt(3));

    }
}
