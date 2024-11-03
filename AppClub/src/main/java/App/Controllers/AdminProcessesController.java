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
public class AdminProcessesController implements ControllerInterface {
    private static final String MENU = "Ingrese la opcion que desea \n "
            + "1. Historial de facturación \n "
            + "2. Autorizar cambios a VIP \n "
            + "9. Volver a menú principal \n";
    
    @Autowired
    private PartnerService partnerService;
    @Autowired
    private InvoiceService invoiceService;
    
    @Autowired
    private PersonValidator personValidator ;

    @Override
    public void session() throws Exception {
    }

    private boolean menu() {
        try {
            System.out.println("bienvenido " + LoginService.user.getUserName());
            System.out.print(MENU);
            String option = Utils.getReader().nextLine();
            return options(option);

        } catch ( Exception e ) {
            System.out.println(e.getMessage());
            return true;
        }
    }

    private boolean options(String option) throws Exception{
        switch (option) {
            case "1": {
                this.invoiceService.historyInvoice();
                return true;
            }
            default: {
                System.out.println("Ingrese una opcion valida");
                return true;
            }
        }
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
