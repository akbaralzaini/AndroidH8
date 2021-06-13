package project.akbaralzaini.sesi2.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;

import project.akbaralzaini.sesi2.R;
import project.akbaralzaini.sesi2.activity.sesi10.AboutActivity;
import project.akbaralzaini.sesi2.activity.sesi10.AgencyActivity;
import project.akbaralzaini.sesi2.util.MySession;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSesi12,btnSesi34,btnSesi56, btnSesi78, btnSesi910, btnAboutme;
    SharedPreferences mSettings;
    MySession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setTitle("Menu Aplikasi");
        mSettings = this.getSharedPreferences("Login", Context.MODE_PRIVATE);
        session = new MySession(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        btnSesi12 = findViewById(R.id.sesi12);
        btnSesi34 = findViewById(R.id.sesi34);
        btnSesi56 = findViewById(R.id.sesi56);
        btnSesi78 = findViewById(R.id.sesi78);
        btnSesi910 = findViewById(R.id.sesi910);
        btnAboutme = findViewById(R.id.aboutme);

        btnSesi12.setOnClickListener(this);
        btnSesi34.setOnClickListener(this);
        btnSesi56.setOnClickListener(this);
        btnSesi78.setOnClickListener(this);
        btnSesi910.setOnClickListener(this);
        btnAboutme.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sesi12:
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                break;
            case R.id.sesi34:
                Intent a = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(a);
                break;
            case R.id.sesi56:
                menuDialog("sesi 5","sesi 6");
                break;
            case R.id.sesi78:
                Intent c = new Intent(getApplicationContext(), ListViewActivity.class);
                startActivity(c);
                break;
            case R.id.sesi910:
                Intent d = new Intent(getApplicationContext(), AgencyActivity.class);
                startActivity(d);
                break;
            case R.id.aboutme:
                Intent e = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(e);
            default:
                break;
        }
    }

    private void menuDialog(String sOne, String sTwo) {
        final String[] sPre = new String[] {
                sOne,
                sTwo
        };

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Menu Sesi");
        alertDialog.setCancelable(false);
        alertDialog.setItems(sPre, (dialog, which) -> {
            String selectedPre = Arrays.asList(sPre).get(which);
            if (selectedPre.equalsIgnoreCase(sOne)) {
                callIntent(Sesi5Activity.class);
            } else if (selectedPre.equalsIgnoreCase(sTwo)) {
                callIntent(SharePrefActivity.class);
            }
            dialog.dismiss();
        });
        AlertDialog dialog = alertDialog.create();
        dialog.show();
    }

    private void callIntent(Class act) {
        Intent i = new Intent(getApplicationContext(), act);
        startActivity(i);
    }

    private void nampilInfo(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);
        builder.setTitle("information");
        builder.setMessage("Mau ke view");
        builder.setPositiveButton("Yes",(dialog, which) -> {
            Intent webIntent = new Intent(getApplicationContext(),InfoActivity.class);
            startActivity(webIntent);
        });

        builder.setCancelable(false);
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_info,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.menu_info){
            nampilInfo();
            return true;
        }else if(id == R.id.exit_app){
            exitApp();
        }
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }

    public void exitApp(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);
        builder.setTitle("information");
        builder.setMessage("apakah mau Logout app?");
        builder.setPositiveButton("Yes",(dialog, which) -> {
            session.logoutUser();
            finish();
        });
        builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());

        builder.setCancelable(false);
        builder.show();
    }
}