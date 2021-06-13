package project.akbaralzaini.sesi2.activity;

import android.content.Intent;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import project.akbaralzaini.sesi2.R;

public class MainActivity extends AppCompatActivity {

    EditText edtNama, edtJeniskelamin, edtAlamat;
    String sNama,sJeniskelamin, sAlamat;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Sesi 1 Bundle");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        edtNama = findViewById(R.id.edtNama);
        edtJeniskelamin = findViewById(R.id.edtJeniskelamin);
        edtAlamat = findViewById(R.id.edtAlamat);

        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(v -> {

            sNama = edtNama.getText().toString();
            sJeniskelamin = edtJeniskelamin.toString();
            sAlamat = edtJeniskelamin.toString();

            if (sNama.equals("")){
                Toast.makeText(MainActivity.this,"Nama Kosong", Toast.LENGTH_SHORT).show();
            }else if(sJeniskelamin.equals("")){
                Toast.makeText(MainActivity.this,"jenis kelamin kosong",Toast.LENGTH_SHORT).show();
            }else if(sAlamat.equals("")){
                Toast.makeText(MainActivity.this,"alamat kosong",Toast.LENGTH_SHORT).show();
            }else{
                Bundle bundle = new Bundle();

                bundle.putString("nama",edtNama.getText().toString());
                bundle.putString("jeniskelamin",edtJeniskelamin.getText().toString());
                bundle.putString("alamat",edtAlamat.getText().toString());
                Intent details = new Intent(getApplicationContext(),DetailActivity.class);
                details.putExtras(bundle);
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("information");
                builder.setMessage("Sukses memasukan data");
                builder.setPositiveButton("Yes",(dialog, which) -> startActivity(details));
                builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());

                builder.setCancelable(false);
                builder.show();


            }

        });
    }

    private void nampilInfo(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("information");
        builder.setMessage("apakah mau keluar app?");
        builder.setPositiveButton("Yes",(dialog, which) -> super.finish());
        builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());

        builder.setCancelable(false);
        builder.show();
    }
}