package App.Service;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import App.Dao.PersonDao;
import App.Dao.UserDao;
import App.Dto.PersonDto;
import App.Dto.UserDto;
import App.Service.Intefaces.PersonServiceInterface;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@Service
public class PersonService implements PersonServiceInterface {
    @Autowired
    private final PersonDao personDao = new PersonDao();
    @Autowired
    private final UserDao userDao = new UserDao();
    
    @Override
    public PersonDto createPerson( PersonDto personDto ) throws Exception {
        if ( this.personDao.existsByDocument( personDto ) ) {
            personDto = this.personDao.findByDocument( personDto );
            throw new Exception( "Ya existe: " + personDto.getName() );
        }
        
        this.personDao.createPerson( personDto );        
        return personDto;
    }

    @Override
    public PersonDto updatePerson( PersonDto personDto ) throws Exception {
        PersonDto personDtoLocal = this.personDao.findByDocument( personDto );
                
        if ( !this.personDao.existsByDocument( personDto ) ) {
            throw new Exception( "No se encentra el número identificación");
        }
        
        personDtoLocal.setName( personDto.getName() );
        personDtoLocal.setCellphone( personDto.getCellphone() );
        
        this.personDao.updatePerson( personDtoLocal );

        return personDtoLocal;        
    }

    @Override
    public void deletePerson( PersonDto personDto ) throws Exception {
        if ( !this.personDao.existsByDocument( personDto ) ) {
            throw new Exception( "No se encentra el número identificación: " + String.valueOf( personDto.getDocument() ) );
        }
        personDto = this.personDao.findByDocument( personDto );
                
        UserDto userDto = this.userDao.findByPersonId( personDto );
        if ( userDto != null ){
            throw new Exception("La persona tiene usuario" );
        }
        
        this.personDao.deletePerson( personDto );        
    }
}
