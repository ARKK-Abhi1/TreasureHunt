package in.leaf.abhi.treasurehunt;

import android.os.AsyncTask;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.util.Scanner;


/**
 * Created by 500060150 on 14-12-2017.
 */

class ParticipantVerifier extends AsyncTask<String,Void,String> {
    private String email,password;
    @Override
    protected String doInBackground(String... args) {
        email=args[0];
        password=args[1];
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url("http://treasure-hunt.atwebpages.com/fetchParticipants.php").build();
        Response response;
        String data;
        int teamNo;
        try {
            response = client.newCall(request).execute();
            data=response.body().string();
            Scanner s=new Scanner(data);
            s.useDelimiter("!~delemiter~!");
            System.out.println(data);
            /* This code will find out the latest assigned team no*/
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
            while(s.hasNext()){
                System.out.println(email);
                if(email.equalsIgnoreCase(s.next())&&password.equals(s.next())) {
                    System.out.println("ak");
                    if((s.next()).equals("0")) {
                        teamNo=max+1;
                        System.out.println("email : "+email);
                        Request setRequest=new Request.Builder().url("http://treasure-hunt.atwebpages.com/setLoggedIn.php?Email='"+email+"'&Team_no='"+
                                                                      String.valueOf(teamNo)+"'").build();
                        Response response2=client.newCall(setRequest).execute();
                        System.out.println(response2);
                        return String.valueOf(LoginActivity.TRUE)+" "+String.valueOf(teamNo);
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
}
