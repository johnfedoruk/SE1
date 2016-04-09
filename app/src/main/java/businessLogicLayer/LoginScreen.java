package businessLogicLayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
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

    private EditText txtName, txtPassword;

    private Button loginBtn;
    private Button cancelBtn;

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

                if (checkValidation()) {

                    Toast.makeText(LoginScreen.this, "Logged in as " + txtName, Toast.LENGTH_LONG).show();
                    String name = txtName.getText().toString();

                    Intent myIntent = new Intent(LoginScreen.this, MainActivity.class);
                    startActivity(myIntent);
                } else {
                    Toast.makeText(LoginScreen.this, "Invalid input", Toast.LENGTH_LONG).show();
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
