package App.Service.Intefaces;

/**
 * @author Arbaces Restrepo, Jhogan Viancha
 */


import App.Dto.UserDto;

public interface AdminService {
    public void createUser(UserDto userDto) throws Exception;

    public void createPartner(UserDto userDto) throws Exception;    
}
