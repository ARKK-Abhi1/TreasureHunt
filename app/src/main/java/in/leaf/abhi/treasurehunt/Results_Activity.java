package in.leaf.abhi.treasurehunt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import in.leaf.abhi.treasurehunt.database.ResultsDao;

public class Results_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_);
        Bundle bundle = getIntent().getExtras();
        long time=bundle.getLong("timeTaken");
        time= TimeUnit.SECONDS.convert(time,TimeUnit.MILLISECONDS);
        int minutes=(int)time/60;
        int seconds=(int)time-minutes*60;
        TextView tV=(TextView)findViewById(R.id.textView6);
        tV.setText(String.valueOf(minutes)+":"+String.valueOf(seconds));
    }
}
