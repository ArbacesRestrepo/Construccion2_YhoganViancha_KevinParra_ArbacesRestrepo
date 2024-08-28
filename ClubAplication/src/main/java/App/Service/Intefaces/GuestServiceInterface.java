package App.Service.Intefaces;

/**
 * @author Arbaces Restrepo, Jhogan Viancha
 */

import App.Dto.UserDto;

public interface GuestServiceInterface {
    public void createGuest( ) throws Exception;
    public void createGuest( UserDto userDto ) throws Exception;
    public void updateGuest( ) throws Exception;
    public void deleteGuest( ) throws Exception;    
}
