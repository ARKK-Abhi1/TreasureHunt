package in.leaf.abhi.treasurehunt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.content.Intent;
import java.util.Scanner;

public class LoginActivity extends AppCompatActivity {
    public static final int TRUE=1,FALSE=0,LOGGEDIN=2;
    private EditText emailET;
    private EditText passwordET;
    private Button loginB;
    private ParticipantVerifier pV;
    private ProgressBar circularPb;

    public void displayUserInputs(boolean display) {
        if(display) {
            emailET.setVisibility(View.VISIBLE);
            passwordET.setVisibility(View.VISIBLE);
            loginB.setVisibility(View.VISIBLE);
        }
        else {
            emailET.setVisibility(View.GONE);
            passwordET.setVisibility(View.GONE);
            loginB.setVisibility(View.GONE);
        }
    }
    public void displayWaitingIndicator(boolean display) {
        if(display)
            circularPb.setVisibility(View.VISIBLE);
        else
            circularPb.setVisibility(View.GONE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_v5);
        getWindow().setBackgroundDrawableResource(R.drawable.tsbg);
        emailET=(EditText)findViewById(R.id.emailEditText);
        passwordET=(EditText)findViewById(R.id.passwordEditText);
        loginB=(Button)findViewById(R.id.loginButton);
        circularPb=(ProgressBar)findViewById(R.id.circularProgressBar);
        circularPb.setVisibility(View.GONE);
        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password;
                email = emailET.getText().toString();
                password = passwordET.getText().toString();
                displayUserInputs(false);
                displayWaitingIndicator(true);
                pV = new ParticipantVerifier(LoginActivity.this);// PrticipantVerifier
                pV.execute(email,password);
            }
        });
    }
}
