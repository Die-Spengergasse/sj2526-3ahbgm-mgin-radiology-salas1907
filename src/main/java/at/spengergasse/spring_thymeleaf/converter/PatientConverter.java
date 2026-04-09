package at.spengergasse.spring_thymeleaf.converter;

import at.spengergasse.spring_thymeleaf.entities.Patient;
import at.spengergasse.spring_thymeleaf.repositories.PatientRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PatientConverter implements Converter<String, Patient> {
    private final PatientRepository repo;
    public PatientConverter(PatientRepository repo) { this.repo = repo; }

    @Override
    public Patient convert(String id) {
        return repo.findById(Integer.parseInt(id)).orElse(null);
    }
}