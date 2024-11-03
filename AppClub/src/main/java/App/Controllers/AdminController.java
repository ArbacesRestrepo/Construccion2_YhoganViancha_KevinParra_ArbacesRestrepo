package App.Controllers;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import App.Controllers.Validator.PersonValidator;
import App.Service.PersonService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Getter
@Setter
@NoArgsConstructor
@RestController

public class AdminController implements ControllerInterface{
    private static final String MENU = "Ingrese la opcion que desea \n "
            + "1. PERSONAS \n "
            + "2. USUARIOS \n "
            + "3. SOCIOS \n "
            + "4. INVITADOS \n "
            + "5. PROCESOS \n "
            + "9. Para cerrar sesion \n";
    
    @Autowired
    public ControllerInterface adminPersonController;
    @Autowired
    public ControllerInterface adminUserController;
    @Autowired
    public ControllerInterface adminPartnerController;
    @Autowired
    public ControllerInterface adminGuestController;
    @Autowired
    public ControllerInterface adminProcessesController;

    @Autowired
    private PersonValidator personValidator;

    @Override
    public void session() throws Exception {
    }
    
    @GetMapping("/")
    public String Active(){
        return "Está corriendo";
    }    
}
