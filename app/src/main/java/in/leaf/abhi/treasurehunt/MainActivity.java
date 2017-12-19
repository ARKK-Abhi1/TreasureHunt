package in.leaf.abhi.treasurehunt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    private final long SPLASH_DISPLAY_TIME=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent logAct=new Intent(MainActivity.this,LoginActivity.class);
                MainActivity.this.startActivity(logAct);
                MainActivity.this.finish();
            }
        },SPLASH_DISPLAY_TIME);
    }
}
