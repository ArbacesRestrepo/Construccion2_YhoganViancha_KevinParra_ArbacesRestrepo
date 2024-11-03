package App.Controllers;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import App.Controllers.Request.DeletePersonRequest;
import App.Controllers.Request.PersonRequest;
import App.Controllers.Validator.PersonValidator;
import App.Dto.PersonDto;
import App.Service.PersonService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Getter
@Setter
@NoArgsConstructor
@RestController
public class AdminPersonController implements ControllerInterface{
    private static final String MENU = "Ingrese la opcion que desea \n "
            + "1. Crear persona \n "
            + "2. Actualizar persona \n "
            + "3. Borrar persona \n "
            + "9. Volver a menú principal  \n";
    
    @Autowired
    private PersonService personService;

    @Autowired
    private PersonValidator personValidator ;
    
    @Override
    public void session() throws Exception {
    }

    @PostMapping("/CreatePerson")
    private ResponseEntity createPerson( @RequestBody PersonRequest request ) throws Exception{
        try{
            long document = personValidator.validDocument( request.getDocument() );
            String name = request.getName();
            personValidator.validName( name );
            long cellPhone = personValidator.validCellPhone( request.getCellPhone() );
            PersonDto personDto = new PersonDto();
            personDto.setDocument( document );
            personDto.setName( name );
            personDto.setCellphone( cellPhone );
            this.personService.createPerson( personDto );
            return new ResponseEntity<>( "Se creo la persona exitosamente", HttpStatus.OK );
        }
        catch( Exception e ){
            return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST );
        }
    }
    
    @PostMapping("/UpdatePerson")
    private ResponseEntity updatePerson( @RequestBody PersonRequest request ) throws Exception{
        try{
            long document = personValidator.validDocument( request.getDocument() );
            String name = request.getName();
            personValidator.validName( name );
            long cellPhone = personValidator.validCellPhone( request.getCellPhone() );
            PersonDto personDto = new PersonDto();
            personDto.setDocument( document );
            personDto.setName( name );
            personDto.setCellphone( cellPhone );
            this.personService.updatePerson( personDto );
            return new ResponseEntity<>( "Se actualizó la persona exitosamente", HttpStatus.OK );
        }
        catch( Exception e ){
            return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST );
        }
    }

    @PostMapping("/DeletePerson")
    private ResponseEntity deletePerson( @RequestBody DeletePersonRequest request ) throws Exception{
        try{
            long document = personValidator.validDocument( request.getDocument() );
            PersonDto personDto = new PersonDto();
            personDto.setDocument( document );
            this.personService.deletePerson( personDto );
            return new ResponseEntity<>( "Se eliminó la persona exitosamente", HttpStatus.OK );
        }
        catch( Exception e ){
            return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST );
        }
    }

}
