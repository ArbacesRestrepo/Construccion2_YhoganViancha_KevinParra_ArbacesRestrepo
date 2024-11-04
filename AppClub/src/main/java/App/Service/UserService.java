package App.Service;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import App.Service.Intefaces.UserServiceInterface;

import App.Dao.PersonDao;
import App.Dao.UserDao;
import App.Dao.PartnerDao;

import App.Dto.PersonDto;
import App.Dto.UserDto;
import App.Dto.PartnerDto;
import App.Helper.Helper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@Service
public class UserService implements UserServiceInterface {
    @Autowired
    private PersonService personService;

    @Autowired
    private PersonDao personDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private PartnerDao partnerDao;

    @Override
    public void createUser( PersonDto personDto, UserDto userDto ) throws Exception {
        if ( !this.personDao.existsByDocument( personDto ) ){
            throw new Exception("No existe ninguna persona con el documento: " + String.valueOf( personDto.getDocument() ) );
        }
        
        personDto = this.personDao.findByDocument( personDto );
                
        if ( this.userDao.existsByUserName( userDto ) ){
            throw new Exception("El usuario para: " + personDto.getName() + " es: " + userDto.getUserName() );
        }
        
        userDto.setPersonId( Helper.parse( personDto ) );

        if ( this.userDao.existsByUserName( userDto ) ) {
            throw new Exception("Ya existe un usuario con el user name: " + userDto.getUserName() );
        }
        
        this.userDao.createUser( userDto );
    }

    @Override
    public void changeUserPassword( PersonDto personDto, UserDto userDto, UserDto oldUserDto ) throws Exception {
        if ( !this.personDao.existsByDocument( personDto ) ){
            throw new Exception("No existe ninguna persona con el documento: " + String.valueOf( personDto.getDocument() ) );
        }
        
        personDto = this.personDao.findByDocument( personDto );

        UserDto userDtoLocal = this.userDao.findByPersonId( personDto );
        
        if ( userDtoLocal == null ){
            throw new Exception( "No existe el usuario" );
        }

        if ( !userDtoLocal.getPassword().equals( oldUserDto.getPassword() ) ){
            throw new Exception( "Password incorrecto" );            
        }

        userDtoLocal.setPassword( userDto.getPassword() );
        
        this.userDao.updateUser( userDtoLocal );
    }
    
    @Override
    public void deleteUser( PersonDto personDto ) throws Exception {
        if ( !this.personDao.existsByDocument( personDto ) ){
            throw new Exception("No existe la persona con el documento: " + String.valueOf( personDto.getDocument() ) );
        }
        
        personDto = this.personDao.findByDocument( personDto );
        
        UserDto userDtoLocal = this.userDao.findByPersonId( personDto );        
        if ( userDtoLocal == null ){
            throw new Exception("La persona no tiene usuario" );
        }
        
        PartnerDto partnerDto = this.partnerDao.findByUserId( userDtoLocal );
        
        if ( partnerDto != null ){
            throw new Exception("El usuario es socio" );            
        }
        
        this.userDao.deleteUser( userDtoLocal );        
    }
}
