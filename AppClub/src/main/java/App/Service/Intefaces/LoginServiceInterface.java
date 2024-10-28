package App.Service.Intefaces;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import App.Dto.UserDto;

public interface LoginServiceInterface {
    public void login(UserDto userDto) throws Exception;
    public void logout();    
}
