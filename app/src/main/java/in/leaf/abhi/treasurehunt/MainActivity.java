package in.leaf.abhi.treasurehunt;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;
import in.leaf.abhi.treasurehunt.database.*;

public class MainActivity extends AppCompatActivity {
    private final long SPLASH_DISPLAY_TIME=2000;
    Results results;
    long timeTaken;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db= Room.databaseBuilder(MainActivity.this,Database.class,"AppDatabase").build();
        BackgroundThread bgt=BackgroundThread.getBackgroundThread(true);
        bgt.execute(new Runnable() {
            @Override
            public void run() {
                ResultsDao rD=db.getResultsDao();
                results=rD.getResults();
                if(results==null)
                    timeTaken=-1;
                else
                    timeTaken=results.timeInMillis;
            }
        });
        while(timeTaken==0);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(timeTaken==-1) {
                    Intent logAct = new Intent(MainActivity.this, LoginActivity.class);
                    MainActivity.this.startActivity(logAct);
                }
                else {
                    Intent intent=new Intent(MainActivity.this,Results_Activity.class);
                    intent.putExtra("timeTaken",timeTaken);
                    intent.putExtra("questionsCompleted",results.questionsCompleted);
                    intent.putExtra("availableQuestions",results.availableQuestions);
                    MainActivity.this.startActivity(intent);
                }
                MainActivity.this.finish();
            }
        },SPLASH_DISPLAY_TIME);
    }
}
