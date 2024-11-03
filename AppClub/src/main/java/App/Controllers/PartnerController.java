package App.Controllers;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import App.Dao.PartnerDao;
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
    private PartnerDao partnerDao;
    
    @Autowired
    private UserService userService;
    @Autowired
    private PartnerService partnerService;
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private GuestService guestService;
    
    
    @Override
    public void session() throws Exception {
    }
}
