package se.sti.javasti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.sti.javasti.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
