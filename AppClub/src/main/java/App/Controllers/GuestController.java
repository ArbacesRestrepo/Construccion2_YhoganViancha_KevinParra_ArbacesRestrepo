package App.Controllers;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import App.Dao.GuestDao;
import App.Dao.PersonDao;
import App.Dto.GuestDto;
import App.Dto.PersonDto;

import App.Service.LoginService;
import App.Service.UserService;
import App.Service.GuestService;
import App.Service.InvoiceService;


public class GuestController implements ControllerInterface{
    private static final String MENU = "Ingrese la opcion que desea \n 1. Solicitar consumo \n 2. Ver historial de consumos \n 3. Cambio a SOCIO \n 4. Cambiar el PASSWORD \n 9. Para cerrar sesion \n";

    private final PersonDao personDao = new PersonDao();
    private final GuestDao guestDao = new GuestDao();
    
    private final UserService userService = new UserService();
    private final InvoiceService invoiceService = new InvoiceService();
    private final GuestService guestService = new GuestService();
    
    
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
                PersonDto personDto = this.personDao.findByUserId( LoginService.user );
                this.invoiceService.historyGuestInvoice( personDto );
                return true;
            }
            case "3": {
                this.guestService.changeGuestToPartner( LoginService.user );
                return true;
            }
            case "4": {
                this.userService.changePasswordUser( LoginService.user );
                return true;
            }
            case "9": {
                System.out.println("Se ha cerrado sesion");
                return false;
            }
            default: {
                System.out.println("ingrese una opcion valida");
                return true;
            }
        }
    }

}
