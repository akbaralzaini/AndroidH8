package project.akbaralzaini.sesi2.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import project.akbaralzaini.sesi2.R;

public class Sesi5Activity extends AppCompatActivity {

    EditText edKomentar;
    String strKomentar;
    SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesi5);
        setTitle("Sesi 5");

        edKomentar = findViewById(R.id.edKomentar);
        mSettings = this.getSharedPreferences("Settings", Context.MODE_PRIVATE);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        strKomentar = mSettings.getString("komentar", "");
        edKomentar.setText(strKomentar);
        Log.d("lifecycle","sedang menjalankan onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString("komentar",edKomentar.getText().toString());
        editor.apply();

        Log.d("lifecycle","sedang menjalankan onPause");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_info,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_info:
                nampilInfo();
                return true;
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void nampilInfo(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(Sesi5Activity.this);
        builder.setTitle("information");
        builder.setMessage("Dibuat Oleh Akbar Al Zaini");
        builder.setPositiveButton("Yes",(dialog, which) -> dialog.dismiss());

        builder.setCancelable(false);
        builder.show();
    }
}