package src.presentationLayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import net.javacrypt.se1.R;

/**
 * Created by Kevin on 15/02/2016.
 *
 */
public class SplashScreen extends Activity{
    //Display Length for splash screen
    private final int SPLASH_DISPLAY_TIME = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* John: did not work without an absoloute path to MainActivity */
                Intent i = new Intent(SplashScreen.this, src.businessLogicLayer.MainActivity.class);
                startActivity(i);

                finish();
            }
        }, SPLASH_DISPLAY_TIME);
    }


}
