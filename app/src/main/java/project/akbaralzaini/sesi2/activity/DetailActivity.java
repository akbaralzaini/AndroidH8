package project.akbaralzaini.sesi2.activity;

import android.content.Intent;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import project.akbaralzaini.sesi2.R;

public class DetailActivity extends AppCompatActivity {

    TextView txtNama,txtJeniskelamin, txtAlamat;
    String nama, jeniskelamin, alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("stvsyc");
        super.onCreate(savedInstanceState);

        setTitle("Detail");

        setContentView(R.layout.activity_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        txtNama = findViewById(R.id.txtNama);
        txtJeniskelamin = findViewById(R.id.txtJeniskelamin);
        txtAlamat = findViewById(R.id.txtAlamat);

        Intent main = getIntent();
        Bundle bundle = main.getExtras();
        if (bundle !=  null){
            txtNama.setText(main.getExtras().getString("nama"));
            txtJeniskelamin.setText(main.getExtras().getString("jeniskelamin"));
            txtAlamat.setText(main.getExtras().getString("alamat"));
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}