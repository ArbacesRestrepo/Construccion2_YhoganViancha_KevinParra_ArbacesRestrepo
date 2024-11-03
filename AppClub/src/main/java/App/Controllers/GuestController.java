package App.Controllers;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import App.Dao.GuestDao;
import App.Dao.PersonDao;
import App.Dto.GuestDto;

import App.Service.LoginService;
import App.Service.UserService;
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
public class GuestController implements ControllerInterface{
    private static final String MENU = "Ingrese la opcion que desea \n "
            + "1. Solicitar consumo  \n "
            + "2. Cambio a SOCIO \n "
            + "3. Cambiar el PASSWORD \n "
            + "9. Para cerrar sesion \n";
    
    @Autowired
    private PersonDao personDao;
    @Autowired
    private GuestDao guestDao;
    
    @Autowired
    private UserService userService;
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private GuestService guestService;
    
    
    @Override
    public void session() throws Exception {
        boolean session = true;
        while (session) {
            session = menu();
        }
    }
    
    private boolean menu() {
        try {
            System.out.println("bienvenido " + LoginService.user.getUserName());
            System.out.print(MENU);
            String option = Utils.getReader().nextLine();
            return options(option);

        } catch ( Exception e ) {
            System.out.println(e.getMessage());
            return true;
        }
    }

    private boolean options(String option) throws Exception{
        switch (option) {
            case "1": {
                GuestDto guestDto = this.guestDao.findByUserId( LoginService.user );
                this.invoiceService.createGuestInvoice( guestDto );
                return true;
            }

            case "2": {
                this.guestService.changeGuestToPartner( LoginService.user );
                return true;
            }
            case "3": {
                this.userService.changeUserPassword( LoginService.user );
                return true;
            }
            case "9": {
                System.out.println("Se ha cerrado sesion");
                return false;
            }
            default: {
                System.out.println("Ingrese una opcion valida");
                return true;
            }
        }
    }

}
