package App.Service.Intefaces;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import App.Dto.UserDto;

public interface GuestServiceInterface {
    public void createGuest( ) throws Exception;
    public void createGuest( UserDto userDto ) throws Exception;
    public void updateGuest( ) throws Exception;
    public void deleteGuest( ) throws Exception;    
    public void changeGuestToPartner( UserDto userDto ) throws Exception;    
}
