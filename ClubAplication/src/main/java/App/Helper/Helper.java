package App.Helper;

/**
 * @author Arbaces Restrepo, Jhogan Viancha
 */


import App.Dto.UserDto;
import App.Dto.PersonDto;
import App.Model.Person;
import App.Model.User;

public abstract class Helper {
    
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

}
