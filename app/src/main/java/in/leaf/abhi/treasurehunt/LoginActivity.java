package in.leaf.abhi.treasurehunt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;
import java.util.Scanner;

public class LoginActivity extends AppCompatActivity {
    public static final int TRUE=1,FALSE=0,LOGGEDIN=2;
    private EditText emailET;
    private EditText passwordET;
    private Button loginB;
    private ParticipantVerifier pV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_v2);
        emailET=(EditText)findViewById(R.id.emailEditText);
        passwordET=(EditText)findViewById(R.id.passwordEditText);
        loginB=(Button)findViewById(R.id.loginButton);
        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,password;
                email=emailET.getText().toString();
                password=passwordET.getText().toString();
                pV=new ParticipantVerifier();// PrticipantVerifier
                String result=null;
                int response=-1;
                String message=null;
                try {
                    result=pV.execute(email,password).get();//verifiying the credentials
                    if(result==null){
                        message="Please check your connection";
                    }
                    else {
                        Scanner s=new Scanner(result);
                        response=s.nextInt();
                        switch(response) {
                            case TRUE     : Intent i=new Intent(LoginActivity.this,Questions_Activity.class);
                                            i.putExtra("teamNo",s.nextInt());
                                            startActivity(i);
                                            message="Verified";
                                            LoginActivity.this.finish();
                                            break;
                            case FALSE    : message="Invalid Email or Password";
                                            break;
                            case LOGGEDIN : message="Already Logged In";
                                            break;
                            default       : System.out.println("Unexpected response");
                                            break;
                        }
                    }
                }catch(Exception e) {
                    message="exception occured";
                }
                Toast t=Toast.makeText(LoginActivity.this,message,Toast.LENGTH_LONG);
                t.show();
                // reset the email and password field
                System.out.println(response);
            }
        });
    }
}
