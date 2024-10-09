package App.Service.Intefaces;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import App.Dto.UserDto;

public interface UserServiceInterface {
    public void createUser( ) throws Exception;
    public UserDto createGuestUser( ) throws Exception;
    public void changeUserPassword( ) throws Exception;    
    public void changeUserPassword( UserDto userDto ) throws Exception;    
    public void changeUserRole( UserDto userDto ) throws Exception;    
    public void deleteUser( ) throws Exception;        
}
