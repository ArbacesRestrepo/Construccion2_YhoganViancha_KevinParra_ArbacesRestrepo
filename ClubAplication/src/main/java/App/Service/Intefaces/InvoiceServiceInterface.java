package App.Service.Intefaces;

/**
 * @author Arbaces Restrepo, Jhogan Viancha
 */

import App.Dto.PartnerDto;
import App.Dto.GuestDto;

public interface InvoiceServiceInterface {
    public void createInvoice( ) throws Exception;
    public void createInvoice( PartnerDto partnerDto ) throws Exception;
    public void createInvoice( GuestDto guestDto ) throws Exception;
    
    public void historyInvoice( ) throws Exception;
    public void historyInvoice( PartnerDto partnerDto ) throws Exception;
    public void historyInvoice( GuestDto guestDto ) throws Exception;
}
