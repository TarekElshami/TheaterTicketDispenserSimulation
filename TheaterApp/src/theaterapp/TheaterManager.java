package theaterapp;

import static theaterapp.ScreenMode.optionsMode;

/**
 *
 * @author t.elshami.2021
 */

public class TheaterManager {

    /**
     *
     */
    public void run() {
        DispenserManager dm = new DispenserManager();
        Theater t = new Theater();
        WelcomeScreen w = new WelcomeScreen(t, dm, "Welcome to Alfil Theater",optionsMode);
        dm.showScreen(w);
    }
}
