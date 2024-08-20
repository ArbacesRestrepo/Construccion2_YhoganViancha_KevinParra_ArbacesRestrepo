package App.Dto;

/**
 * @author Arbaces Restrepo, Jhogan Viancha
 */

import App.Controllers.Utils;
import App.Controllers.Validator.UserValidator;
import App.Dto.Interfaces.UserDtoInterface;

public class UserDto implements UserDtoInterface {
    private long id;
    private PersonDto personId;
    private String userName;
    private String password;
    private String role;

    private UserValidator userValidator;
    
    public UserDto() {}

    @Override
    public void getUserDto() throws Exception {
        personId.getPersonDto();

        System.out.println("Ingrese el nombre de usuario");
        String userNameDto = Utils.getReader().nextLine();
        userValidator.validUserName( userNameDto );
        this.userName = userNameDto;

        System.out.println("Ingrese el password de usuario");
        String userPasswordDto = Utils.getReader().nextLine();
        userValidator.validPassword( userPasswordDto );
        this.password = userPasswordDto;

        System.out.println("Ingrese el nombre de roll del usuario");
        String userRoleDto = Utils.getReader().nextLine();
        userValidator.validRole( userRoleDto );
        this.role = userRoleDto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PersonDto getPersonid() {
        return personId;
    }

    public void setPersonid(PersonDto personid) {
        this.personId = personid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
