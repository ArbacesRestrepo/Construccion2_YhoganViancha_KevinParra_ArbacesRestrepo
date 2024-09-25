package App.Controllers.Validator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

@Getter
@Setter
@NoArgsConstructor
@Component
public class UserValidator extends CommonsValidator{	
    public void validUserName(String userName) throws Exception {
        super.isValidString("el nombre de usuario ", userName);
    }
    
    public void validPassword(String password) throws Exception {
        super.isValidString("la contraseña de usuario ", password);
    }
    
    public void validRole(String role) throws Exception {
        super.isValidString("el rol de usuario ", role);
    }
}
