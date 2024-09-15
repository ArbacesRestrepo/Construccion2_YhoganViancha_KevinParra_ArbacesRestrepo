package App.Dao.Repository;

/**
 * @author Arbaces Restrepo, Jhogan Viancha, Kevin Parra
 */

import App.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long>{
    public boolean existsByPersonDocument(long document);
    public Person findByPersonId(long id);
    public Person findByPersonDocument(long document);
}
