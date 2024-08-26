package App.Service;

/**
 * @author Arbaces Restrepo, Jhogan Viancha
 */


import App.Dao.UserDao;
import App.Dto.UserDto;
import App.Dao.Interfaces.UserDaoInteface;
import App.Service.Intefaces.LoginServiceInterface;

public class LoginService implements LoginServiceInterface{
    private UserDaoInteface userDao;
    
    public static UserDto user;

    public LoginService() {
        this.userDao = new UserDao();
    }
    
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
