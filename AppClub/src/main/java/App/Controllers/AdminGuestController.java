package App.Controllers;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import App.Service.GuestService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Getter
@Setter
@NoArgsConstructor
@Controller
public class AdminGuestController implements ControllerInterface {
    private static final String MENU = "Ingrese la opcion que desea \n "
            + "1. Crear invitado \n "
            + "2. Borrar invitado \n "
            + "9. Volver a menú principal  \n";
    
    @Autowired
    private final GuestService guestService = new GuestService();

    @Override
    public void session() throws Exception {
    }

}
