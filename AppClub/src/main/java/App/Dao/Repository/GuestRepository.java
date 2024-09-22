package App.Dao.Repository;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import App.Model.User;
import App.Model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long>{
    public boolean existsByUserId( User user );
    public Guest findById( long id );
    public Guest findByUserId( User user );    
}
