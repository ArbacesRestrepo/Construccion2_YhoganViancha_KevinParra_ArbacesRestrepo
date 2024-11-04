package App.Controllers;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import App.Controllers.Request.PartnerRequest;
import App.Controllers.Validator.PartnerValidator;
import App.Controllers.Validator.PersonValidator;
import App.Dto.PartnerDto;
import App.Dto.PersonDto;
import App.Service.PartnerService;
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
public class PartnerController implements ControllerInterface {
    @Autowired
    private PersonValidator personValidator ;

    @Autowired
    private PartnerValidator partnerValidator ;

    @Autowired
    private PartnerService partnerService;

    @Override
    public void session() throws Exception {
    }

    @PostMapping("/CreatePartner")
    private ResponseEntity createPartner( @RequestBody PartnerRequest request ) throws Exception{
        try{
            long document = this.personValidator.validDocument( request.getDocument() );
            
            double amount = this.partnerValidator.validAmount( request.getAmount() );

            String type = request.getType();
            this.partnerValidator.validType( type );

            PersonDto personDto = new PersonDto();
            personDto.setDocument( document );
            
            PartnerDto partnerDto = new PartnerDto();
            partnerDto.setAmount( amount );
            partnerDto.setType( type );

            this.partnerService.createPartner( personDto, partnerDto );
            return new ResponseEntity<>( "Se creo el socio exitosamente", HttpStatus.OK );
        }
        catch( Exception e ){
            return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST );
        }
    }

    @PostMapping("/UpdatePartnerAmount")
    private ResponseEntity updatePartnerAmount( @RequestBody PartnerRequest request ) throws Exception{
        try{
            long document = this.personValidator.validDocument( request.getDocument() );
            
            double amount = this.partnerValidator.validAmount( request.getAmount() );

            PersonDto personDto = new PersonDto();
            personDto.setDocument( document );
            
            PartnerDto partnerDto = new PartnerDto();
            partnerDto.setAmount( amount );

            this.partnerService.updatePartnerAmount( personDto, partnerDto );
            return new ResponseEntity<>( "Se actualizó el monto del socio exitosamente", HttpStatus.OK );
        }
        catch( Exception e ){
            return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST );
        }
    }

    @PostMapping("/UpdatePartnerType")
    private ResponseEntity updatePartnerType( @RequestBody PartnerRequest request ) throws Exception{
        try{
            long document = this.personValidator.validDocument( request.getDocument() );
            
            PersonDto personDto = new PersonDto();
            personDto.setDocument( document );
            
            this.partnerService.updatePartnerType( personDto );
            return new ResponseEntity<>( "El socio solicitó cambio a VIP exitosamente", HttpStatus.OK );
        }
        catch( Exception e ){
            return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST );
        }
    }

    @PostMapping("/DeletePartner")
    private ResponseEntity deletePartner( @RequestBody PartnerRequest request ) throws Exception{
        try{
            long document = this.personValidator.validDocument( request.getDocument() );
            
            PersonDto personDto = new PersonDto();
            personDto.setDocument( document );
            
            this.partnerService.deletePartner( personDto );
            return new ResponseEntity<>( "Se eliminó el socio exitosamente", HttpStatus.OK );
        }
        catch( Exception e ){
            return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST );
        }
    }
}
