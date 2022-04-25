package se.sti.javasti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.sti.javasti.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(String name);

}
