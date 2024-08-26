package App.Helper;

/**
 * @author Arbaces Restrepo, Jhogan Viancha
 */


import App.Dto.UserDto;
import App.Dto.PersonDto;
import App.Dto.PartnerDto;

import App.Model.Person;
import App.Model.User;
import App.Model.Partner;

public abstract class Helper {
    public static PersonDto parse(Person person) {
        PersonDto personDto = new PersonDto();
        personDto.setId( person.getPersonId() );
        personDto.setDocument( person.getDocument() );
        personDto.setName( person.getPersonName() );
        personDto.setCellPhone( person.getCellPhone());
        return personDto;
    }

    public static Person parse(PersonDto personDto) {
        Person person = new Person();
        person.setPersonId(personDto.getId());
        person.setDocument(personDto.getDocument());
        person.setPersonName(personDto.getName());
        person.setCellPhone(personDto.getCellPhone());
        return person;
    }

    
    public static UserDto parse(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getUserId());
        userDto.setPassword(user.getPassword());
        userDto.setPersonid(parse(user.getPerson()));
        userDto.setRole(user.getRole());
        userDto.setUserName(user.getUserName());
        return userDto;
    }

    public static User parse(UserDto userDto) {
        User user = new User();
        user.setUserId(userDto.getId());
        user.setPassword(userDto.getPassword());
        user.setPerson(parse(userDto.getPersonid()));
        user.setRole(userDto.getRole());
        user.setUserName(userDto.getUserName());
        return user;
    }
    
    public static Partner parse( PartnerDto partnerDto) {
        Partner partner = new Partner();
        partner.setId( partnerDto.getId() );
        partner.setUserId( partnerDto.getUserId() );
        partner.setType( partnerDto.getType() );
        partner.setAmount( partnerDto.getAmount() );
        partner.setCreationDate( partnerDto.getCreationDate() );
        return partner;
    }
    
    public static PartnerDto parse( Partner partner ){
        PartnerDto partnerDto = new PartnerDto();
        partnerDto.setId( partner.getId() );
        partnerDto.setUserId( partner.getUserId() );
        partnerDto.setType( partner.getType() );
        partnerDto.setAmount( partner.getAmount() );
        partnerDto.setCreationDate( partner.getCreationDate() );
        return partnerDto;
    }

}
