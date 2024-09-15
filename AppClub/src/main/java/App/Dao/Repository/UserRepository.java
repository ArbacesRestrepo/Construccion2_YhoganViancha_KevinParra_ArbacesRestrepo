package App.Dao.Repository;

/**
 * @author Arbaces Restrepo, Jhogan Viancha, Kevin Parra
 */

import App.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>{
    public boolean existsByUserName(String userName);
    public User findByUserId(long id);
    public User findByUserName(String name);    
    public User findByPersonId(long id);    
}
