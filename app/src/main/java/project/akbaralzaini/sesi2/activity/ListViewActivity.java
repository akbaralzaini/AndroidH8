package project.akbaralzaini.sesi2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import project.akbaralzaini.sesi2.R;

public class ListViewActivity extends AppCompatActivity {
    private ListView listView;
    private String[] stringLabel = {"Label A","Label B","Label C","Label D","Label E","Label F","Label G","Label H","Label D","Label E","Label F","Label G","Label H"};
    private Integer[] iHarga = {10000,10000,10000,10000,10000,10000,10000,10000};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

//        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        listView = findViewById(R.id.listView);
        listView.setOnItemClickListener(((parent, view, position, id) -> {
            HashMap<String,String> map = (HashMap) parent.getItemAtPosition(position);
            String sLabel = map.get("label").toString();
            Toast.makeText(this,"Label: "+sLabel,Toast.LENGTH_SHORT).show();
        }));

        callList();
    }

    public static void justifyListViewHeightBasedOnChildren(ListView listView){
        ListAdapter adapter = listView.getAdapter();

        if (adapter == null) return;

        ViewGroup vg = listView;
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i,null,vg);
            listItem.measure(0,0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams par = listView.getLayoutParams();
        par.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));
        listView.setLayoutParams(par);
        listView.requestLayout();
    }

    private void callList() {
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        for (int cc = 0; cc < stringLabel.length; cc++) {
            try {
                HashMap<String, String> showData = new HashMap<>();
                showData.put("label", stringLabel[cc]);
                showData.put("harga",cc+"0000");
                showData.put("deskripsi","syvtrcsuyvb vsubin tsryvubi");

                list.add(showData);
            }
            catch (Exception e){
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        }

        ListAdapter adapter = new SimpleAdapter(ListViewActivity.this,list, R.layout.list_view_item,
                new String[]{"label","harga","deskripsi"},
                new int[]{R.id.txtLabel, R.id.txtHarga, R.id.txtDeskripsi});
        listView.setAdapter(adapter);

//        justifyListViewHeightBasedOnChildren(listView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}