package App.Service;

/**
 * @author Arbaces Restrepo, Jhogan Vianch, Kevin Parra
 */

import App.Service.Intefaces.LoginServiceInterface;

import App.Dao.UserDao;
import App.Dto.UserDto;

public class LoginService implements LoginServiceInterface{
    private final UserDao userDao = new UserDao();
    
    public static UserDto user;

    @Override
    public void login(UserDto userDto) throws Exception {
        UserDto validateDto = this.userDao.findByUserName(userDto);
        if (validateDto == null) {
            throw new Exception("no existe usuario registrado");
        }
        if (!userDto.getPassword().equals(validateDto.getPassword())) {
            throw new Exception("usuario o contraseña incorrecto");
        }
        userDto.setRole(validateDto.getRole());
        user = validateDto;
    }

    @Override
    public void logout() {
        user = null;
        System.out.println("se ha cerrado sesion");
    }
    
}
