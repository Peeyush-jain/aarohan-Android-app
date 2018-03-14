package iitropar.aarohan;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import pl.droidsonroids.gif.GifTextView;


public class SplashScreen extends AppCompatActivity {
    //private ProgressBar mProgress;
    private GifTextView gifImageView ;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show the splash screen
        setContentView(R.layout.splash_screen);
        //mProgress = (ProgressBar) findViewById(R.id.splash_screen_progress_bar);
        gifImageView = findViewById(R.id.gifImageView);

        // Start lengthy operation in a background thread


        new Thread(new Runnable() {
            public void run() {
                doWork();
                startApp();
                finish();
            }
        }).start();
    }

    private void doWork() {
        //for (int progress=0; progress<100; progress+=10) {
            try {
                Thread.sleep(2000);
                //mProgress.setProgress(progress);
            } catch (Exception e) {
                e.printStackTrace();

            }
        //}
    }

    private void startApp() {
        Intent intent = new Intent();
        intent.setClass(SplashScreen.this, MainActivity.class);
        startActivity(intent);

    }
}