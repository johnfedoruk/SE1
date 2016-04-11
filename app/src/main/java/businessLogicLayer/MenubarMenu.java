package businessLogicLayer;

import android.app.Activity;
import android.content.Intent;

import net.javacrypt.se1.R;

/**
 * Created by pure__000 on 2016-04-11.
 */
public class MenubarMenu {

    public static boolean handleMenu(Activity passersClass, int id) {
        if (id == R.id.add_bird) {
            Intent intent = new Intent(passersClass, AddBird.class);
            passersClass.startActivity(intent);
            return true;
        }
        if (id == R.id.add_experiment) {
            Intent intent = new Intent(passersClass, AddExperiment.class);
            passersClass.startActivity(intent);
            return true;
        }
        if (id == R.id.search_bird) {
            Intent intent = new Intent(passersClass, SearchBird.class);
            passersClass.startActivity(intent);
            return true;
        }
        if (id == R.id.search_experiment) {
            Intent intent = new Intent(passersClass, SearchExperiment.class);
            passersClass.startActivity(intent);
            return true;
        }
        if (id == R.id.quit_or_logout) {
            Intent intent = new Intent(passersClass, QuitScreen.class);
            passersClass.startActivity(intent);
            return true;
        }
        if (id == R.id.importBirds) {
            Intent intent = new Intent(passersClass, ImportBirds.class);
            passersClass.startActivity(intent);
            return true;
        }
        if (id == R.id.Login) {
            Intent intent = new Intent(passersClass, LoginScreen.class);
            passersClass.startActivity(intent);
            return true;
        }
        if (id ==R.id.home) {
            Intent intent = new Intent(passersClass, MainActivity.class);
            passersClass.startActivity(intent);
            return true;
        }

        return false;
    }
}
