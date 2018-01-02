package in.leaf.abhi.treasurehunt;

import android.os.AsyncTask;

/**
 * Created by 500060150 on 16-12-2017.
 */

public class QuestionsFetcher extends AsyncTask<Object,Void,Question[]> {
    public Question[] doInBackground(Object... args) {
        Database db=(Database)args[0];
        int qSet[]=(int[])args[1];
        int totalQuestions=(Integer)args[2];
        Question[] questions=new Question[qSet.length];
        int randomQuestions=qSet.length-1;// length-1 because the last question will be fixed
        QuestionDao qD=db.getQuestionDao();
        for(int i=0;i<randomQuestions;i++) {
            try {
                questions[i] = qD.getQuestion(qSet[i]);
            }catch(Exception e) {
                System.out.println("Exception occured while fetching question in Asynctask"+qSet[i]);
            }
        }
        try {
            questions[randomQuestions]=qD.getQuestion(totalQuestions);
        }catch(Exception e) {
            System.out.println("Exception occured while fetching last question");
        }
        return questions;
    }
}
