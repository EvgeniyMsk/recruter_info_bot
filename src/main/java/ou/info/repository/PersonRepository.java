package ou.info.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ou.info.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByNameAndBirth(String name, String birth);
}
