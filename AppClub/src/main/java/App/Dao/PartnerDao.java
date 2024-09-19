package App.Dao;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import java.util.ArrayList;

import App.Helper.Helper;
import App.Dto.UserDto;
import App.Dto.PartnerDto;
import App.Dto.GuestDto;
import App.Dao.Interfaces.PartnerDaoInterface;
import App.Dao.Repository.PartnerRepository;
import App.Dto.InvoiceDto;
import App.Model.User;
import App.Model.Partner;

public class PartnerDao implements PartnerDaoInterface{
    PartnerRepository partnerRepository;

    @Override
    public boolean existsByUserId(UserDto userDto) throws Exception {
        User user = Helper.parse( userDto );
        return partnerRepository.existsByUserId( user );
    }

    @Override
    public void createPartner(PartnerDto partnerDto) throws Exception {
        Partner partner = Helper.parse( partnerDto );
        partnerRepository.save( partner );
    }

    @Override
    public void updateAmountPartner(PartnerDto partnerDto) throws Exception {
        Partner partner = Helper.parse( partnerDto );
        partnerRepository.save( partner );
    }

    @Override
    public void updateTypePartner(PartnerDto partnerDto) throws Exception {
        Partner partner = Helper.parse( partnerDto );
        partnerRepository.save( partner );
    }

    @Override
    public void deletePartner(PartnerDto partnerDto) throws Exception {
        Partner partner = Helper.parse( partnerDto );
        partnerRepository.deleteById( partner.getId() );
    }

    @Override
    public PartnerDto findByUserId( UserDto userDto ) throws Exception {
        User user = Helper.parse( userDto );
        Partner partner = partnerRepository.findByUserId( user );
        return Helper.parse( partner );
    }

    @Override
    public PartnerDto findByGuestPartnerId( GuestDto guestDto ) throws Exception {
        Partner partner = partnerRepository.findById( guestDto.getPartnerId().getId() );
        return Helper.parse( partner );
    }

    @Override
    public PartnerDto findByPartnerId( InvoiceDto invoiceDto ) throws Exception {
        Partner partner = partnerRepository.findById( invoiceDto.getPartnerId().getId() );
        return Helper.parse( partner );
    }

    @Override
    public long numberPartnersVIP(  ) throws Exception {
        return partnerRepository.countByType( "VIP" );
    }

    @Override
    public long numberPartnersRequestVIP(  ) throws Exception {
        return partnerRepository.countByType( "PIDE CAMBIO A VIP" );
    }
    
    @Override
    public ArrayList<PartnerDto> listPartnerRequestVIP( ) throws Exception{
        ArrayList<Partner> listVIP = partnerRepository.findByType( "VIP" );
        ArrayList<PartnerDto> listPartners = new ArrayList<PartnerDto>();
        for (int i=0; i<listVIP.size(); i++){
            listPartners.add( Helper.parse( listVIP.get(i) ) );            
        }        
        return listPartners;
    }
    
}
