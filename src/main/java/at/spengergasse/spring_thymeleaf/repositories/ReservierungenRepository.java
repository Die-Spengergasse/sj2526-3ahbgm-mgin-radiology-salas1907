package at.spengergasse.spring_thymeleaf.repositories;

import at.spengergasse.spring_thymeleaf.entities.Reservierungen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservierungenRepository extends JpaRepository<Reservierungen,Integer> {
}
