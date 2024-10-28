package App.Service.Intefaces;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import App.Dto.PersonDto;
import App.Dto.UserDto;

public interface UserServiceInterface {
    public void createUser( PersonDto personDto, UserDto userDto ) throws Exception;
    public void changeUserPassword( UserDto userDto ) throws Exception;
    public void changeUserRole( UserDto userDto ) throws Exception;    
    public void deleteUser( UserDto userDto ) throws Exception;        
}
