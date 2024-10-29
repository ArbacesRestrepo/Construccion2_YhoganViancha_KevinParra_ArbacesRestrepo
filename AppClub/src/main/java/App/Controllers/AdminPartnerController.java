package App.Controllers;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import App.Service.PartnerService;
import App.Service.LoginService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Getter
@Setter
@NoArgsConstructor
@Controller
public class AdminPartnerController implements ControllerInterface {
    private static final String MENU = "Ingrese la opcion que desea \n "
            + "1. Crear socio \n "
            + "2. Actualizar inversión de un SOCIO \n "
            + "3. Borrar socio \n "
            + "4. Autorizar cambios a VIP \n "
            + "9. Volver a menú principal  \n";
    
    @Autowired
    private final PartnerService partnerService = new PartnerService();

    @Override
    public void session() throws Exception {
    }


}
