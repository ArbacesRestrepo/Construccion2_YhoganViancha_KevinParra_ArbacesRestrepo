package App.Service.Intefaces;

/**
 * @author Arbaces Restrepo, Jhogan Vianch, Kevin Parra
 */

import App.Dto.UserDto;

public interface UserServiceInterface {
    public void createUser( ) throws Exception;
    public UserDto createUserGuest( ) throws Exception;
    public void changePasswordUser( ) throws Exception;    
    public void changePasswordUser( UserDto userDto ) throws Exception;    
    public void changeRoleUser( UserDto userDto ) throws Exception;    
    public void deleteUser( ) throws Exception;        
}
