package App.Service.Intefaces;

/**
 * @author Arbaces Restrepo, Jhogan Viancha
 */

import App.Dto.UserDto;

public interface LoginService {
    public void login(UserDto userDto) throws Exception;
    public void logout();    
}
