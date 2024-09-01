package App.Controllers;

/**
 * @author Arbaces Restrepo, Jhogan Viancha
 */

import App.Dao.PartnerDao;
import App.Dto.PartnerDto;

import App.Service.LoginService;
import App.Service.UserService;
import App.Service.PartnerService;
import App.Service.GuestService;
import App.Service.InvoiceService;


public class PartnerController implements ControllerInterface{
    private static final String MENU = "Ingrese la opcion que desea \n 1. Solicitar consumo \n 2. Ver historial de consumos \n 3. Crear Invitado \n 4. Cambio a VIP \n 5. Cambiar el PASSWORD \n 9. Para cerrar sesion \n";

    private final PartnerDao partnerDao = new PartnerDao();
    
    private final UserService userService = new UserService();
    private final PartnerService partnerService = new PartnerService();
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
                PartnerDto partnerDto = this.partnerDao.findByUserId( LoginService.user );
                this.invoiceService.createPartnerInvoice( partnerDto );
                return true;
            }
            case "2": {
                PartnerDto partnerDto = this.partnerDao.findByUserId( LoginService.user );
                this.invoiceService.historyPartnerInvoice( partnerDto );
                return true;
            }
            case "3": {
                this.guestService.createGuest( LoginService.user );
                return true;
            }
            case "4": {
                PartnerDto partnerDto = this.partnerDao.findByUserId( LoginService.user );
                this.partnerService.updateTypePartner( partnerDto );
                return true;
            }
            case "5": {
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
