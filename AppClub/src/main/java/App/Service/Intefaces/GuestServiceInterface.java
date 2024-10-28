package App.Service.Intefaces;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import App.Dto.PersonDto;
import App.Dto.UserDto;

public interface GuestServiceInterface {
    public void createGuest( UserDto userDto, PersonDto personDto ) throws Exception;
    public void deleteGuest( PersonDto personDto ) throws Exception;    
    public void changeGuestToPartner( UserDto userDto ) throws Exception;    
}
