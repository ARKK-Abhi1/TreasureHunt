package in.leaf.abhi.treasurehunt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import in.leaf.abhi.treasurehunt.database.ResultsDao;

public class Results_Activity extends AppCompatActivity {
    TextView timeView;
    TextView completedView;
    TextView msgView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_);
        Bundle bundle = getIntent().getExtras();
        /* getting the information from bundle */
        long time=bundle.getLong("timeTaken");
        int questionsCompleted=bundle.getInt("questionsCompleted");
        int availableQuestions=bundle.getInt("availableQuestions");
        //--------------------------------------------------------------------------

        /*Converting the time into proper units */
        time= TimeUnit.SECONDS.convert(time,TimeUnit.MILLISECONDS);
        int minutes=(int)time/60;
        int seconds=(int)time-minutes*60;
        //-----------------------------------------------------------------------

        /*Setting the text views */
        timeView=(TextView)findViewById(R.id.timeView);
        completedView=(TextView)findViewById(R.id.completedView);
        msgView=(TextView)findViewById(R.id.msgView);
        if(questionsCompleted<availableQuestions)
            msgView.setText(R.string.failed);
        else
            msgView.setText(R.string.congrats);
        timeView.setText(String.valueOf(minutes)+" minutes "+String.valueOf(seconds)+" seconds");
        completedView.setText(String.valueOf(questionsCompleted)+" completed out of "+String.valueOf(availableQuestions));
        //-----------------------------------------------------------------------------------------------------------------
    }
}
