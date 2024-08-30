package App.Dao.Interfaces;

/**
 * @author Arbaces Restrepo, Jhogan Viancha
 */


import App.Dto.UserDto;
import App.Dto.PartnerDto;
import App.Dto.GuestDto;
import App.Dto.InvoiceDto;

public interface PartnerDaoInterface {
    public boolean existsByUserId( UserDto userDto ) throws Exception;
    public void createPartner( PartnerDto partnerDto ) throws Exception;
    public void updateAmountPartner( PartnerDto partnerDto ) throws Exception;
    public void updateTypePartner( PartnerDto partnerDto ) throws Exception;
    public void deletePartner( PartnerDto partnerDto ) throws Exception;
    public PartnerDto findByUserId( UserDto userDto ) throws Exception;
    public PartnerDto findByPartnerId( InvoiceDto invoiceDto ) throws Exception;
    public PartnerDto findByGuestPartnerId( GuestDto guestDto ) throws Exception;
    public long numberPertnersVIP( ) throws Exception;
}
