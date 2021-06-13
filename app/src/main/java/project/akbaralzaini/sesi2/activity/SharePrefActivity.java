package project.akbaralzaini.sesi2.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import project.akbaralzaini.sesi2.R;

public class SharePrefActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputEditText edKomentar;
    String strKomentar;
    Button btnIntents,btnExplicit,btnBundle;
    SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_pref);
        setTitle("Sesi 6 State");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        edKomentar = findViewById(R.id.edKomentar);
        mSettings = this.getSharedPreferences("Settings", Context.MODE_PRIVATE);

        btnIntents = findViewById(R.id.btnImplicit);
        btnIntents.setOnClickListener(this);

        btnExplicit = findViewById(R.id.btnExlicit);
        btnExplicit.setOnClickListener(this);

        btnBundle = findViewById(R.id.btnBundle);
        btnBundle.setOnClickListener(this);

        if (savedInstanceState != null) {
            String count = savedInstanceState.getString("count");
            if (edKomentar != null)
                edKomentar.setText(count);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnImplicit:
                String url = "http://www.google.com";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;
            case R.id.btnExlicit:
                Intent a = new Intent(SharePrefActivity.this,IntentActivity.class);
                startActivity(a);
                break;
            case R.id.btnBundle:
                Intent b = new Intent(SharePrefActivity.this,MainActivity.class);
                startActivity(b);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("count", String.valueOf(edKomentar.getText()));
    }

    @Override
    public void onRestoreInstanceState (Bundle mySavedState) {
        super.onRestoreInstanceState(mySavedState);
        if (mySavedState != null) {
            String count = mySavedState.getString("count");
            if (count != null)
                edKomentar.setText(count);
        }
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
            case R.id.menu_info :
                nampilInfo();
                return true;
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void nampilInfo(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(SharePrefActivity.this);
        builder.setTitle("information");
        builder.setMessage("Dibuat Oleh Akbar Al Zaini");
        builder.setPositiveButton("Yes",(dialog, which) -> dialog.dismiss());

        builder.setCancelable(false);
        builder.show();
    }


}