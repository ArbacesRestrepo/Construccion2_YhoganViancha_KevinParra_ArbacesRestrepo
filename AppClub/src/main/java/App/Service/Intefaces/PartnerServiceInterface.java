package App.Service.Intefaces;

/**
 * @author Arbaces Restrepo, Jhogan Vianch, Kevin Parra
 */

import App.Dto.UserDto;
import App.Dto.PartnerDto;

public interface PartnerServiceInterface {
    public void createPartner( ) throws Exception;
    public void updateAmountPartner( ) throws Exception;
    public void updateTypePartner( PartnerDto partnerDto ) throws Exception;
    public void deletePartner( ) throws Exception;
    public void deletePartner( UserDto userDto ) throws Exception;    
    public void changePartnersToVIP(  ) throws Exception;    
}
