package App.Service.Intefaces;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import App.Dto.UserDto;
import App.Dto.PartnerDto;
import App.Dto.PersonDto;

public interface PartnerServiceInterface {
    public void createPartner( PersonDto personDto, PartnerDto partnerDto ) throws Exception;
    public void updatePartnerAmount( PersonDto personDto, PartnerDto partnerDto ) throws Exception;
    public void updatePartnerType( PersonDto personDto ) throws Exception;
    public void deletePartner( PersonDto personDto ) throws Exception;
    public void changePartnersToVIP( PersonDto personDto ) throws Exception;    
}
