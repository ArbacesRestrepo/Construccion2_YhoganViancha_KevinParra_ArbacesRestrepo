package App.Controllers;

/**
 * @author Arbaces Restrepo, Jhogan Viancha
 */

import App.Service.Service;


public class AdminController implements ControllerInterface{
    private static final String MENU = "ingrese la opcion que desea \n 1. para CREAR Socio \n 2. para CREAR Vendedor \n 3. para cerrar sesion \n";

    @Override
    public void session() throws Exception {
        boolean session = true;
        while (session) {
            session = menu();
        }
    }
    
    private boolean menu() {
        try {
            System.out.println("bienvenido " + Service.user.getUserName());
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
                return true;
            }
            case "2": {
                return true;
            }
            case "3": {
                System.out.println("se ha cerrado sesion");
                return false;
            }
            default: {
                System.out.println("ingrese una opcion valida");
                return true;
            }
        }
    }

}
