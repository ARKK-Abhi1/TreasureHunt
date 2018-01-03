package in.leaf.abhi.treasurehunt;

import android.os.AsyncTask;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.util.Scanner;

import in.leaf.abhi.treasurehunt.database.Database;
import in.leaf.abhi.treasurehunt.database.Question;
import in.leaf.abhi.treasurehunt.database.QuestionDao;

/**
 * Created by 500060150 on 16-12-2017.
 */

class QuestionsDownloader extends AsyncTask<Object,Void,String> {
    @Override
    public String doInBackground(Object... params) {
        Database db=(Database)params[0];
        QuestionDao qd=db.getQuestionDao();
        OkHttpClient client = new OkHttpClient();
        Request request=new Request.Builder().url("http://treasure-hunt.atwebpages.com/fetchQuestions.php").build();
        Response response;
        String data=null;
        int counter=0;
        try {
            response=client.newCall(request).execute();
            data=response.body().string();
            Scanner s=new Scanner(data);
            s.useDelimiter("!@!");
            System.out.println(data);
            int q_no;
            String question,answer;
            while(s.hasNext()) {
                q_no=Integer.parseInt(s.next());
                question=s.next();
                answer=s.next();
                Question newQ=new Question(q_no,question,answer);
                Question oldQ=qd.getQuestion(q_no);
                if(oldQ==null) {
                    qd.insertQuestion(newQ);
                    System.out.println("New Question inserted");
                }
                else if(!(oldQ.equals(newQ))) {
                    qd.deleteQuestion(oldQ);
                    qd.insertQuestion(newQ);
                    System.out.println("Question "+q_no+" Replaced");
                }
                else
                    System.out.println("Question already exists");
                counter++;
            }
        }catch(Exception e) {
            System.out.println("EXception occured while downloading questions");
            e.printStackTrace();
        }
        return String.valueOf(counter);
    }
}
