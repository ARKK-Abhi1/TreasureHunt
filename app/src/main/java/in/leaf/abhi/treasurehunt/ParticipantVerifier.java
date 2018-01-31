package in.leaf.abhi.treasurehunt;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.util.Scanner;


/**
 * Created by 500060150 on 14-12-2017.
 */

class ParticipantVerifier extends AsyncTask<String,Void,String> {
    private String email,password;
    private LoginActivity activity;
    ParticipantVerifier(LoginActivity activity) {
        this.activity=activity;
    }
    @Override
    protected String doInBackground(String... args) {
        email=args[0];
        password=args[1];
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url("http://treasure-hunt.atwebpages.com/fetchParticipants.php").build();
        Response response;
        String data;
        String teamNo;
        try {
            response = client.newCall(request).execute();
            data=response.body().string();
            Scanner s=new Scanner(data);
            s.useDelimiter("!~delemiter~!");
            System.out.println(data);
            /* This code will find out the latest assigned team no
            //-------------------------------------------------------
            int max=0,temp=0;
            while(s.hasNext()) {
                s.next();
                s.next();
                if((temp=Integer.parseInt(s.next()))>max)
                    max=temp;
            }
            //--------------------------------------------------------
            s=new Scanner(data);
            s.useDelimiter("!~delemiter~!");
            */
            while(s.hasNext()){
                System.out.println(email);
                if(email.equalsIgnoreCase(s.next())&&password.equals(s.next())) {
                    System.out.println("ak");
                    if((s.next()).equals("0")) {
                        teamNo=s.next();
                        System.out.println("email : "+email);
                        Request setRequest=new Request.Builder().url("http://treasure-hunt.atwebpages.com/setLoggedIn.php?Email='"+email+"'").build();
                        Response response2=client.newCall(setRequest).execute();
                        System.out.println(response2);
                        return String.valueOf(LoginActivity.TRUE)+" "+teamNo;
                    }
                    return String.valueOf(LoginActivity.LOGGEDIN);
                }
            }
            return String.valueOf(LoginActivity.FALSE);
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onPostExecute(String result) {
        System.out.println("onPostExecute()");
        int response=-1;
        String message=null;
        try {
            if(result==null){
                message="Please check your connection";
            }
            else {
                Scanner s=new Scanner(result);
                response=s.nextInt();
                switch(response) {
                    case LoginActivity.TRUE     : Intent i=new Intent(activity,Questions_Activity.class);
                        i.putExtra("teamNo",s.nextInt());
                        activity.startActivity(i);
                        message="Verified";
                        activity.finish();
                        break;
                    case LoginActivity.FALSE    : message="Invalid Email or Password";
                        break;
                    case LoginActivity.LOGGEDIN : message="Already Logged In";
                        break;
                    default       : System.out.println("Unexpected response");
                        break;
                }
            }
        }catch(Exception e) {
            message="exception occured";
        }
        if(response!=LoginActivity.TRUE) {
            activity.displayUserInputs(true);
            activity.displayWaitingIndicator(false);
        }
        Toast t=Toast.makeText(activity,message,Toast.LENGTH_LONG);
        t.show();
        // reset the email and password field
        System.out.println(response);
    }
}
