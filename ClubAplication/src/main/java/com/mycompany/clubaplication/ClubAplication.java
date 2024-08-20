package com.mycompany.clubaplication;

/**
 * @author Arbaces Restrepo, Jhogan Viancha
 */

import App.Controllers.ControllerInterface;
import App.Controllers.LoginController;

public class ClubAplication {
    public static void main(String[] args) throws Exception {
        ControllerInterface controller = new LoginController();
        try {
                controller.session();
                //MYSQLConnection.getConnection();
        } catch (Exception e) {
                System.out.println(e.getMessage());
        }
    }
}
