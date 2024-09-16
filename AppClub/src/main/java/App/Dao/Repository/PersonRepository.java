package App.Dao.Repository;

/**
 * @author Arbaces Restrepo, Jhogan Viancha, Kevin Parra
 */

import App.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long>{
    public boolean existsByDocument( long document );
    public Person findById( long id );
    public Person findByDocument( long document );
}
