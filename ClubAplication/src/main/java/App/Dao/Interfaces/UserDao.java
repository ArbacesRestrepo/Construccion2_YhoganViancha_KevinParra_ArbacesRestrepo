package App.Dao.Interfaces;

/**
 * @author Arbaces Restrepo, Jhogan Viancha
 */

import App.Dto.UserDto;

public interface UserDao {
    public UserDto findByUserName(UserDto userDto) throws Exception;

    public boolean existsByUserName(UserDto userDto) throws Exception;

    public void createUser(UserDto userDto) throws Exception;    
}
