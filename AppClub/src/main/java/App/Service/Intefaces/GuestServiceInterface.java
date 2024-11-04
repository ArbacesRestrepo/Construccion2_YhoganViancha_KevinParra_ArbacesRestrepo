package App.Service.Intefaces;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import App.Dto.PersonDto;

public interface GuestServiceInterface {
    public void createGuest( PersonDto personDto, PersonDto personInviteDto ) throws Exception;
    public void deleteGuest( PersonDto personDto ) throws Exception;    
    public void changeGuestToPartner( PersonDto personDto ) throws Exception;    
}
