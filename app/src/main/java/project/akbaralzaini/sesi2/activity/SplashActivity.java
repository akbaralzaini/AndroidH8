package project.akbaralzaini.sesi2.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.WindowManager;

import project.akbaralzaini.sesi2.R;

public class SplashActivity extends Activity {

    private static final int SPLASH_TIME = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);
        new Handler().postDelayed((Runnable) () -> {
            Intent home=new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(home);
            finish();

        },SPLASH_TIME);
    }
}