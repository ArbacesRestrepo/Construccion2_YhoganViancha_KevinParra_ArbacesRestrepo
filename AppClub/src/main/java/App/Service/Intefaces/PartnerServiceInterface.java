package App.Service.Intefaces;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import App.Dto.UserDto;
import App.Dto.PartnerDto;

public interface PartnerServiceInterface {
    public void createPartner( ) throws Exception;
    public void updateAmountPartner( ) throws Exception;
    public void updatePartnerType( PartnerDto partnerDto ) throws Exception;
    public void deletePartner( ) throws Exception;
    public void deletePartner( UserDto userDto ) throws Exception;    
    public void changePartnersToVIP(  ) throws Exception;    
}
