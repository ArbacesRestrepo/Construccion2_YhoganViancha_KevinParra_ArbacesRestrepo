package App.Controllers;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import App.Controllers.Request.InvoiceDetailRequest;
import App.Controllers.Request.InvoiceRequest;
import App.Controllers.Validator.InvoiceDetailValidator;
import App.Controllers.Validator.PersonValidator;
import App.Dto.InvoiceDetailDto;
import App.Dto.PersonDto;

import App.Service.InvoiceService;
import java.util.ArrayList;
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
public class InvoiceController implements ControllerInterface{
    @Autowired
    private InvoiceService invoiceService;
    
    @Autowired
    private PersonValidator personValidator;
    
    @Autowired
    private InvoiceDetailValidator invoiceDetailValidator;
    
    @Override
    public void session() throws Exception {
    }
    
    @PostMapping("/CreateInvoice")
    private ResponseEntity createInvoice( @RequestBody InvoiceRequest request ) throws Exception{
        try{
            long document = this.personValidator.validDocument( request.getDocument() );
            PersonDto personDto = new PersonDto();
            personDto.setDocument( document );
            ArrayList items = request.getInvoiceDetails();
            ArrayList<InvoiceDetailDto> listInvoiceDetailDto = new ArrayList<InvoiceDetailDto>() ;
            for ( int i = 0; i < items.size(); i++ ){
                InvoiceDetailDto invoiceDetailDto = new InvoiceDetailDto();
                InvoiceDetailRequest item = (InvoiceDetailRequest) items.get(i) ;
                this.invoiceDetailValidator.validDescription( item.getDescription() );
                invoiceDetailDto.setDescription( item.getDescription() );
                invoiceDetailDto.setAmount( this.invoiceDetailValidator.validAmount( item.getAmount() ) );
                listInvoiceDetailDto.add( invoiceDetailDto );
            }
            
            this.invoiceService.createInvoice( personDto, listInvoiceDetailDto );

            return new ResponseEntity<>( "Se registró la factura exitosamente", HttpStatus.OK );
        }
        catch( Exception e ){
            for(StackTraceElement st : e.getStackTrace()){
                System.out.println(st.toString());
            }
            return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST );
        }
    }
}
