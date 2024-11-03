package App.Service.Intefaces;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import App.Dto.PartnerDto;
import App.Dto.GuestDto;
import App.Dto.InvoiceDetailDto;
import App.Dto.PersonDto;
import java.util.ArrayList;

public interface InvoiceServiceInterface {
    public void createInvoice( ) throws Exception;
    public void createInvoice( PersonDto personDto, ArrayList<InvoiceDetailDto> listInvoiceDetailDto ) throws Exception;
    public void createPartnerInvoice( PartnerDto partnerDto ) throws Exception;
    public void createGuestInvoice( GuestDto guestDto ) throws Exception;
    
    public void historyInvoice( ) throws Exception;
    public void historyPartnerInvoice( PartnerDto partnerDto ) throws Exception;
}
