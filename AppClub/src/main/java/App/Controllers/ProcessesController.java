package App.Controllers;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import App.Controllers.Request.PersonRequest;
import App.Controllers.Validator.PersonValidator;
import App.Dto.PersonDto;
import App.Service.InvoiceService;
import App.Service.LoginService;
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
public class ProcessesController implements ControllerInterface {    
    @Autowired
    private PartnerService partnerService;
    @Autowired
    private InvoiceService invoiceService;
    
    @Autowired
    private PersonValidator personValidator ;

    @Override
    public void session() throws Exception {
    }
    
    @PostMapping("/UpgradePartner")
    private ResponseEntity upgradePartner( @RequestBody PersonRequest request ) throws Exception{
        try{
            long document = personValidator.validDocument( request.getDocument() );
            String name = request.getName();
            personValidator.validName( name );
            long cellPhone = personValidator.validCellPhone( request.getCellPhone() );
            PersonDto personDto = new PersonDto();
            personDto.setDocument( document );
            personDto.setName( name );
            personDto.setCellphone( cellPhone );
            this.partnerService.changePartnersToVIP( personDto );
            return new ResponseEntity<>( "Se procesó la actualización exitosamente", HttpStatus.OK );
        }
        catch( Exception e ){
            return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST );
        }
        
    }
    
}
