package App.Service;

import java.sql.SQLException;

import App.Dao.PersonDao;
import App.Dao.UserDao;
import App.Dao.PartnerDao;

import App.Dto.PartnerDto;

/**
 * @author Arbaces Restrepo, Jhogan Viancha
 */

import App.Dto.PersonDto;
import App.Dto.UserDto;
import App.Service.Intefaces.UserServiceInterface;

public class UserService implements UserServiceInterface {
    private final PersonService personService = new PersonService();

    private final PersonDao personDao = new PersonDao();
    private final UserDao userDao = new UserDao();

    @Override
    public void createUser( ) throws Exception {
        UserDto userDto;
        PersonDto personDto = personService.createPerson( );
        
        userDto = this.userDao.findByPersonId( personDto ) ;
        
        if ( userDto != null ){
            throw new Exception("El usuario para: " + personDto.getName() + " es: " + userDto.getUserName() );
        }
        
        userDto = new UserDto();
        userDto.setPersonid( personDto );
        
        userDto.getUserNameDto();
        userDto.getUserTypeDto();
        userDto.getUserPasswordDto();
                
        if ( this.userDao.existsByUserName( userDto ) ) {
            throw new Exception("Ya existe un usuario con ese user name");
        }
        
        try {
            this.userDao.createUser( userDto );
        } catch (SQLException e) {
            throw new Exception( e.getMessage() );
        }
    }

    @Override
    public void changePasswordUser( ) throws Exception {
        UserDto userDto;
        PersonDto personDto = new PersonDto();
        personDto.getPersonDocumentDto();
        personDto = this.personDao.findByDocument( personDto );
        if ( personDto == null ){
            throw new Exception("No se encontró el documento" );            
        }
        
        userDto = this.userDao.findByPersonId( personDto ) ;
        if ( userDto == null ){
            throw new Exception("La persona no tiene usuario" );
        }
        
        System.out.println("Cambiar password al usuario: " + userDto.getUserName() );
        userDto.getUserPasswordDto();
        
        this.userDao.updatePasswordUser( userDto );
    }

    @Override
    public void deleteUser( ) throws Exception {
        UserDto userDto;
        PersonDto personDto = new PersonDto();
        personDto.getPersonDocumentDto();
        
        personDto = this.personDao.findByDocument( personDto );
                
        userDto = this.userDao.findByPersonId( personDto ) ;
        
        if ( userDto == null ){
            throw new Exception("La persona no tiene usuario" );
        }
        
        PartnerDao partnerDao = new PartnerDao();
        PartnerDto partnerDto = partnerDao.existsByUserId( userDto );
        
        if ( partnerDto != null ){
            throw new Exception("El usuario es socio" );            
        }
        
        System.out.println("Borrar usuario: " + userDto.getUserName() );
        
        this.userDao.deleteUser( userDto );        
    }
    
}
