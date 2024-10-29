package App.Controllers;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import App.Dao.PartnerDao;
import App.Dto.PartnerDto;

import App.Service.LoginService;
import App.Service.UserService;
import App.Service.PartnerService;
import App.Service.GuestService;
import App.Service.InvoiceService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Getter
@Setter
@NoArgsConstructor
@Controller
public class PartnerController implements ControllerInterface{
    private static final String MENU = "Ingrese la opcion que desea \n "
            + "1. Solicitar consumo \n "
            + "2. Ver historial de consumos \n "
            + "3. Crear Invitado \n "
            + "4. Borrar Invitado \n "
            + "5. Cambio a VIP \n "
            + "6. Cambiar el PASSWORD \n "
            + "9. Para cerrar sesion \n";

    @Autowired
    private final PartnerDao partnerDao = new PartnerDao();
    
    @Autowired
    private final UserService userService = new UserService();
    @Autowired
    private final PartnerService partnerService = new PartnerService();
    @Autowired
    private final InvoiceService invoiceService = new InvoiceService();
    @Autowired
    private final GuestService guestService = new GuestService();
    
    
    @Override
    public void session() throws Exception {
    }
    

}
