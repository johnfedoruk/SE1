package businessLogicLayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.javacrypt.se1.R;

/**
 * Created by pure__000 on 2016-04-09.
 */
public class QuitScreen extends AppCompatActivity
{

    Button quitButton, logOutButton;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quit_screen);
        View view = this.getCurrentFocus();

        quitButton = (Button)findViewById(R.id.QuitButton );

        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });

        logOutButton = (Button)findViewById(R.id.LogOutButton);

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LoginScreen.name == "") {
                    Toast.makeText(QuitScreen.this, "You are not logged in", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(QuitScreen.this, LoginScreen.name + " logged out", Toast.LENGTH_SHORT).show();
                    LoginScreen.name = "";
                    Intent intent = new Intent(QuitScreen.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return MenubarMenu.handleMenu(this, id);
    }

}
