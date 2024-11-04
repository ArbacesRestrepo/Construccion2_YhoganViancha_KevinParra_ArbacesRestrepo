package App.Service.Intefaces;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import App.Dto.PersonDto;
import App.Dto.UserDto;

public interface UserServiceInterface {
    public void createUser( PersonDto personDto, UserDto userDto ) throws Exception;
    public void changeUserPassword( PersonDto personDto, UserDto userDto, UserDto oldUserDto ) throws Exception;
    public void deleteUser( PersonDto personDto ) throws Exception;        
}
