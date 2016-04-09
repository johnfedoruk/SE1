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
 * Created by pure__000 on 2016-04-07.
 */
public class LoginScreen extends AppCompatActivity implements View.OnFocusChangeListener {

    public static String name = "";

    private EditText txtName, txtPassword;

    private Button loginBtn;
    private Button cancelBtn;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        View view = this.getCurrentFocus();

        loginBtn = (Button) findViewById(R.id.loginButton);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtName = (EditText)findViewById(R.id.nameField);
                txtPassword = (EditText)findViewById(R.id.passwordField);

                if(name == "") {
                    if (checkValidation()) {

                        name = txtName.getText().toString();
                        Toast.makeText(LoginScreen.this, "Logged in as " + name, Toast.LENGTH_LONG).show();

                        Intent myIntent = new Intent(LoginScreen.this, MainActivity.class);
                        startActivity(myIntent);
                    } else {
                        Toast.makeText(LoginScreen.this, "Invalid input", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(LoginScreen.this, "You are already logged in as " + name, Toast.LENGTH_LONG).show();
                }
            }
        });

        cancelBtn = (Button) findViewById(R.id.cancelButton);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(LoginScreen.this, MainActivity.class);
                startActivity(myIntent);
            }
        });
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        if(!hasFocus){
            hideSoftKeyboard(this);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.add_bird) {
            Intent intent = new Intent(this,AddBird.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.add_experiment) {
            Intent intent = new Intent(this,AddExperiment.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.search_bird) {
            Intent intent = new Intent(this,SearchBird.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.search_experiment) {
            Intent intent = new Intent(this,SearchExperiment.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.quit_or_logout)
        {
            Intent intent = new Intent(this, QuitScreen.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private boolean checkValidation() {
        boolean ret = true;

        if (!InputValidation.hasText(txtName))
            ret = false;
        if (!InputValidation.hasText(txtPassword))
            ret = false;

        return ret;
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }
}
