package App.Controllers;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import App.Controllers.Validator.UserValidator;
import App.Dto.UserDto;
import App.Service.LoginService;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Setter
@Getter
@Controller
public class LoginController implements ControllerInterface {
    private static final String MENU = "ingrese la opcion que desea: \n "
            + "1. para iniciar sesion. \n "
            + "9. para detener la ejecucion.";
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private LoginService serviceLogin;
    private Map<String, ControllerInterface> roles;
    
    @Override
    public void session() throws Exception {
    }
}
