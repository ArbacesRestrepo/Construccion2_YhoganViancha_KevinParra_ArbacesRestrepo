package App.Controllers;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import java.util.Scanner;

public abstract class Utils {
    private static Scanner reader = new Scanner(System.in);

    public static Scanner getReader() {
        return reader;
    }
}
