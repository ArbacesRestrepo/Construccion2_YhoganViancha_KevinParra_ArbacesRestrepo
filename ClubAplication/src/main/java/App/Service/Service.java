package App.Service;

/**
 * @author Arbaces Restrepo, Jhogan Viancha
 */


import java.sql.SQLException;

import App.Service.Intefaces.LoginService;
import App.Dao.Interfaces.PersonDao;
import App.Dao.UserDaoImplementation;
import App.Dao.Interfaces.UserDao;
import App.Dto.PersonDto;
import App.Dto.UserDto;

public class Service implements LoginService{
    private UserDao userDao;
    private PersonDao personDao;
    
    public static UserDto user;

    public Service() {
        this.userDao = new UserDaoImplementation();
    }
    
    @Override
    public void login(UserDto userDto) throws Exception {
        UserDto validateDto = userDao.findByUserName(userDto);
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
    
    private void createPerson(PersonDto personDto) throws Exception {
        if (this.personDao.existsByDocument(personDto)) {
            throw new Exception("ya existe una persona con ese documento");
        }
        this.personDao.createPerson(personDto);
    }

    private void createUser(UserDto userDto) throws Exception {
        this.createPerson(userDto.getPersonid());
        PersonDto personDto = personDao.findByDocument(userDto.getPersonid());
        userDto.setPersonid(personDto);
        if (this.userDao.existsByUserName(userDto)) {
            this.personDao.deletePerson(userDto.getPersonid());
            throw new Exception("ya existe un usuario con ese user name");
        }
        try {
            this.userDao.createUser(userDto);
        } catch (SQLException e) {
            this.personDao.deletePerson(userDto.getPersonid());
        }
    }
}
