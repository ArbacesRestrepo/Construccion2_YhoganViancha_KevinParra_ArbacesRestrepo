package App.Controllers;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import App.Controllers.Request.GuestRequest;
import App.Controllers.Validator.GuestValidator;
import App.Controllers.Validator.PersonValidator;
import App.Dto.PersonDto;
import App.Service.GuestService;
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
public class GuestController implements ControllerInterface {
    @Autowired
    private PersonValidator personValidator;

    @Autowired
    private GuestValidator guestValidator;

    @Autowired
    private final GuestService guestService = new GuestService();

    @Override
    public void session() throws Exception {
    }

    @PostMapping("/CreateGuest")
    private ResponseEntity createGuest( @RequestBody GuestRequest request ) throws Exception{
        try{
            long document = this.personValidator.validDocument( request.getDocument() );
            long documentInvite = this.personValidator.validDocument( request.getDocumentInvite() );
            
            PersonDto personDto = new PersonDto();
            personDto.setDocument( document );

            PersonDto personInviteDto = new PersonDto();
            personInviteDto.setDocument( documentInvite );
            
            this.guestService.createGuest( personDto, personInviteDto );
            return new ResponseEntity<>( "Se creo el invitado exitosamente", HttpStatus.OK );
        }
        catch( Exception e ){
            return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST );
        }
    }

    @PostMapping("/DeleteGuest")
    private ResponseEntity deleteGuest( @RequestBody GuestRequest request ) throws Exception{
        try{
            long document = this.personValidator.validDocument( request.getDocument() );
            
            PersonDto personDto = new PersonDto();
            personDto.setDocument( document );
            
            this.guestService.deleteGuest( personDto );
            return new ResponseEntity<>( "Se eliminó invitado exitosamente", HttpStatus.OK );
        }
        catch( Exception e ){
            return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST );
        }
    }

    @PostMapping("/ChangeGuestToPartner")
    private ResponseEntity changeGuestToPartner( @RequestBody GuestRequest request ) throws Exception{
        try{
            long document = this.personValidator.validDocument( request.getDocument() );
            
            PersonDto personDto = new PersonDto();
            personDto.setDocument( document );
            
            this.guestService.changeGuestToPartner( personDto );
            return new ResponseEntity<>( "Se cambió invitado a socio exitosamente", HttpStatus.OK );
        }
        catch( Exception e ){
            return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST );
        }
    }
}
