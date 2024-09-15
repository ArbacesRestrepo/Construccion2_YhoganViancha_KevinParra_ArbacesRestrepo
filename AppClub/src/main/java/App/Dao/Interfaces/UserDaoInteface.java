package App.Dao.Interfaces;

/**
 * @author Arbaces Restrepo, Jhogan Viancha, Kevin Parra
 */

import App.Dto.PersonDto;
import App.Dto.UserDto;
import App.Dto.PartnerDto;
import App.Dto.GuestDto;

public interface UserDaoInteface {
    public UserDto findByUserName( UserDto userDto ) throws Exception;
    public UserDto findByPersonId( PersonDto personDto ) throws Exception;
    public UserDto findByUserId( PartnerDto partnerDto ) throws Exception;
    public UserDto findByGuestUserId( GuestDto guestDto ) throws Exception;
    public boolean existsByUserName( UserDto userDto ) throws Exception;
    public void createUser( UserDto userDto ) throws Exception;    
    public void updatePasswordUser( UserDto userDto ) throws Exception;    
    public void updateRoleUser( UserDto userDto ) throws Exception;    
    public void deleteUser( UserDto userDto ) throws Exception;    
}
