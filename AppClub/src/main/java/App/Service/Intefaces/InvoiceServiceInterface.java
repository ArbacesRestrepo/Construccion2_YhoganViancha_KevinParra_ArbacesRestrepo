package App.Service.Intefaces;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import App.Dto.PartnerDto;
import App.Dto.GuestDto;
import App.Dto.PersonDto;

public interface InvoiceServiceInterface {
    public void createInvoice( ) throws Exception;
    public void createPartnerInvoice( PartnerDto partnerDto ) throws Exception;
    public void createGuestInvoice( GuestDto guestDto ) throws Exception;
    
    public void historyInvoice( ) throws Exception;
    public void historyPartnerInvoice( PartnerDto partnerDto ) throws Exception;
    public void historyGuestInvoice( PersonDto personDto ) throws Exception;
}
