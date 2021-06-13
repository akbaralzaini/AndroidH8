package project.akbaralzaini.sesi2.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import project.akbaralzaini.sesi2.R;
import project.akbaralzaini.sesi2.util.MySession;

public class LoginActivity extends Activity {

    private String PasswordHolder, UserHolder;
    private EditText edUser, edPassword;
    private Button bLogin;
    private Boolean CheckEditText;
    private MySession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        session = new MySession(this);

        edUser = findViewById(R.id.edUser);
        edPassword = findViewById(R.id.edPass);
        bLogin = findViewById(R.id.bLogin);

        if (session.isLoggedIn()){
            Intent i = new Intent(getApplicationContext(),MenuActivity.class);
            startActivity(i);
            finish();
        }
//        if (isEmulator()){
//            AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
//            alertDialog.setTitle("Info");
//            alertDialog.setMessage("Mohon maaf!!\ntidak dapat menjalankan aplikasi di emulator");
//            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL,"Ok",
//                    (dialog, which) -> {
//                        dialog.dismiss();
//                        finishAffinity();
//                    });
//            alertDialog.show();
//            return;
//        }
        bLogin.setOnClickListener(view->cekLoginTwo());
    }


    public void CheckEditTextIsEmptyOrNot(){
        UserHolder = edUser.getText().toString();
        PasswordHolder = edPassword.getText().toString();
        if (TextUtils.isEmpty(UserHolder) || TextUtils.isEmpty(PasswordHolder)){
            CheckEditText = false;
        }
        else{
            CheckEditText = true;
        }
    }
    public void cekLoginTwo(){
        CheckEditTextIsEmptyOrNot();
        if (CheckEditText){
            SyncCek();
        }
        else{
            Toast.makeText(LoginActivity.this,"Data Masih Kosong !",Toast.LENGTH_LONG).show();
        }
    }

    private void SyncCek(){
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(2000);
                    if (progressDialog.isShowing()) progressDialog.dismiss();
                    callValidateTrue();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void callValidateTrue() {
        try {
            runOnUiThread(()->{
                if (UserHolder.equals("admin") && PasswordHolder.equals("admin")){
                    session.createLoginSession("009","admins","admin","jyrctsyvuvcytrvurvdytv");
                    Intent i = new Intent(getApplicationContext(),MenuActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "username/password tidak sesuai!!",Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
            Log.wtf("Error: ", e.getMessage());
        }

    }

    public boolean isEmulator(){
        boolean result = Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.HARDWARE.contains("goldfish")
                || Build.HARDWARE.contains("ranchu")
                || Build.HARDWARE.equals("vbox86")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MODEL.toLowerCase().contains("droid4x")
                || Build.PRODUCT.contains("sdk_google")
                || Build.PRODUCT.contains("google_sdk")
                || Build.PRODUCT.contains("sdk")
                || Build.PRODUCT.contains("sdk_x86")
                || Build.PRODUCT.contains("sdk_google_phone_x86")
                || Build.PRODUCT.contains("vbox86p")
                || android.os.Build.PRODUCT.contains("emulator")
                || Build.PRODUCT.contains("simulator")
                || Build.MANUFACTURER.contains("Genymotion")
                || Build.MANUFACTURER.contains("CMDC")
                || Build.MANUFACTURER.contains("BlueStacks")
                || Build.BOARD.toLowerCase().contains("nox")
                || Build.BOOTLOADER.toLowerCase().contains("nox")
                || Build.HARDWARE.toLowerCase().contains("nox")
                || Build.PRODUCT.toLowerCase().contains("nox")
                || Build.SERIAL.toLowerCase().contains("nox");

        if (result) return true;
        result |= Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic");
        if (result) return true;
        result |= "google_sdk".equals(Build.PRODUCT);
        return result;

    }


}