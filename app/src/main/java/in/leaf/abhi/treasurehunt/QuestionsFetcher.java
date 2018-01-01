package in.leaf.abhi.treasurehunt;

import android.os.AsyncTask;

/**
 * Created by 500060150 on 16-12-2017.
 */

public class QuestionsFetcher extends AsyncTask<Object,Void,Question[]> {
    public Question[] doInBackground(Object... args) {
        Database db=(Database)args[0];
        int qSet[]=(int[])args[1];
        int availableQuestions=qSet.length;
        Question[] questions=new Question[availableQuestions];
        QuestionDao qD=db.getQuestionDao();
        for(int i=0;i<availableQuestions;i++) {
            try {
                questions[i] = qD.getQuestion(qSet[i]);
            }catch(Exception e) {
                System.out.println("Exception occured while fetching question in Asynctask"+qSet[i]);
            }
        }
        return questions;
    }
}
