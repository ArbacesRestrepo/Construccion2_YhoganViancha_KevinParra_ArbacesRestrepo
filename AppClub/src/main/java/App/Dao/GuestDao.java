package App.Dao;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import App.Helper.Helper;
import App.Dao.Interfaces.GuestDaoInterface;
import App.Dao.Repository.GuestRepository;

import App.Dto.UserDto;
import App.Dto.GuestDto;
import App.Model.Guest;
import App.Model.User;

public class GuestDao implements GuestDaoInterface{
    GuestRepository guestRepository;

    @Override
    public boolean existsByUserId( UserDto userDto ) throws Exception {
        User user = Helper.parse( userDto );
        return guestRepository.existsByUserId( user );
    }

    @Override
    public void createGuest(GuestDto guestDto) throws Exception {
        Guest guest = Helper.parse( guestDto );
        guestRepository.save( guest );
    }

    @Override
    public void updateGuestStatus( GuestDto guestDto ) throws Exception {
        Guest guest = Helper.parse( guestDto );
        guestRepository.save( guest );
    }

    @Override
    public void deleteGuest( GuestDto guestDto ) throws Exception {
        Guest guest = Helper.parse( guestDto );
        guestRepository.deleteById( guest.getId() );
    }

    @Override
    public GuestDto findByUserId( UserDto userDto ) throws Exception {        
        User user = Helper.parse( userDto );
        Guest guest = guestRepository.findByUserId( user );
        return Helper.parse( guest );
    }
}
