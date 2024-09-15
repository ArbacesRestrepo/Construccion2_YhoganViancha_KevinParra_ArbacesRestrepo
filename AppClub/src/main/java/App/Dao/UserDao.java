package App.Dao;

/**
 * @author Arbaces Restrepo, Jhogan Viancha, Kevin Parra
 */

import App.Dao.Interfaces.UserDaoInteface;
import App.Dao.Repository.UserRepository;
import App.Dto.GuestDto;
import App.Dto.PartnerDto;

import App.Dto.PersonDto;
import App.Model.User;
import App.Dto.UserDto;
import App.Helper.Helper;
import App.Model.Person;

public class UserDao implements UserDaoInteface {
    UserRepository userRepository;

    @Override
    public UserDto findByUserName( UserDto userDto ) throws Exception {
        User user = userRepository.findByUserName(userDto.getUserName());
        return Helper.parse(user);
    }

    @Override
    public UserDto findByPersonId( PersonDto personDto ) throws Exception {
        Person person = Helper.parse(personDto);
        User user = userRepository.findByPersonnId( person );
        return Helper.parse(user);
    }

    @Override
    public UserDto findByUserId( PartnerDto partnerDto ) throws Exception {
        User user = userRepository.findById( partnerDto.getUserId() );
        return Helper.parse(user);
    }

    @Override
    public UserDto findByGuestUserId( GuestDto guestDto ) throws Exception {
        User user = userRepository.findById( guestDto.getUserId() );
        return Helper.parse(user);
    }

    @Override
    public boolean existsByUserName(UserDto userDto) throws Exception {
        return userRepository.existsByUserName( userDto.getUserName() );
    }

    @Override
    public void createUser(UserDto userDto) throws Exception {
        User user = Helper.parse(userDto);
        userRepository.save( user );
    }
    
    @Override
    public void updatePasswordUser(UserDto userDto) throws Exception {
        User user = Helper.parse(userDto);
        userRepository.save( user );
    }

    @Override
    public void updateRoleUser(UserDto userDto) throws Exception {
        User user = Helper.parse(userDto);
        userRepository.save( user );
    }

    @Override
    public void deleteUser(UserDto userDto) throws Exception {
        User user = Helper.parse(userDto);
        userRepository.deleteById( user.getId() );
    }
}
