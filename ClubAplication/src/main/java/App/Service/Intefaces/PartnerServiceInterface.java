package App.Service.Intefaces;

/**
 * @author Arbaces Restrepo, Jhogan Viancha
 */

import App.Dto.UserDto;

public interface PartnerServiceInterface {
    public void createPartner( ) throws Exception;
    public void updateAmountPartner( ) throws Exception;
    public void deletePartner( ) throws Exception;    
    public void deletePartner( UserDto userDto ) throws Exception;    
}
