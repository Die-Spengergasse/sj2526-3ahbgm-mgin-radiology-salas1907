package at.spengergasse.spring_thymeleaf.repositories;

import at.spengergasse.spring_thymeleaf.entities.Geraete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeraeteRepository extends JpaRepository<Geraete,Integer> {
}
