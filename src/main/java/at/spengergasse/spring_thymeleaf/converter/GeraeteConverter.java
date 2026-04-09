package at.spengergasse.spring_thymeleaf.converter;

import at.spengergasse.spring_thymeleaf.entities.Geraete;
import at.spengergasse.spring_thymeleaf.repositories.GeraeteRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GeraeteConverter implements Converter<String, Geraete> {
    private final GeraeteRepository repo;
    public GeraeteConverter(GeraeteRepository repo) { this.repo = repo; }

    @Override
    public Geraete convert(String id) {
        return repo.findById(Integer.parseInt(id)).orElse(null);
    }
}