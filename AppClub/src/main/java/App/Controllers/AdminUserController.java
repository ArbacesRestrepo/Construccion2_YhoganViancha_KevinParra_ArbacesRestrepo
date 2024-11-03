package App.Controllers;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import App.Controllers.Request.UserRequest;
import App.Controllers.Validator.PersonValidator;
import App.Controllers.Validator.UserValidator;
import App.Dto.PersonDto;
import App.Dto.UserDto;
import App.Service.UserService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Getter
@Setter
@NoArgsConstructor
@Controller
public class AdminUserController implements ControllerInterface {
    private static final String MENU = "Ingrese la opcion que desea \n "
            + "1. CREAR usuario \n "
            + "2. Cambiar password a usuario \n "
            + "3. BORRAR un usuario \n "
            + "9. Volver a menú principal \n";
    
    @Autowired
    private UserService userService;

    @Autowired
    private PersonValidator personValidator;

    @Autowired
    private UserValidator userValidator;
    
    @Override
    public void session() throws Exception {
    }
    
    @PostMapping("/CreateUser")
    private ResponseEntity createUser( @RequestBody UserRequest request ) throws Exception{
        try{
            long personId = this.personValidator.validDocument( request.getDocument() );
            String userName = request.getUserName();
            this.userValidator.validUserName( userName );
            String password = request.getPassword();
            String role = request.getRole();
            
            PersonDto personDto = new PersonDto();
            personDto.setDocument( personId );

            UserDto userDto = new UserDto();
            userDto.setUserName( userName );
            userDto.setPassword( password );
            userDto.setRole( role );
            this.userService.createUser( personDto, userDto );
            return new ResponseEntity<>( "Se creo el usuario exitosamente", HttpStatus.OK );
        }
        catch( Exception e ){
            return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST );
        }
    }
    
}
