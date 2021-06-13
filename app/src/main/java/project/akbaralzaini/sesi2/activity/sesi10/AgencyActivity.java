package project.akbaralzaini.sesi2.activity.sesi10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import project.akbaralzaini.sesi2.R;
import project.akbaralzaini.sesi2.adapter.AgencyListAdapter;
import project.akbaralzaini.sesi2.models.Agency;
import project.akbaralzaini.sesi2.rest.AgencyApiInterface;
import project.akbaralzaini.sesi2.rest.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgencyActivity extends AppCompatActivity {
    RecyclerView rvListAgency;
    TextView tInternetHilang;
    AgencyApiInterface agencyApiInterface;
    private RecyclerView.Adapter mAgencyAdapter;
    private RecyclerView.LayoutManager mLayoutManagerAgency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agency);
        tInternetHilang = findViewById(R.id.internet_hilang);

        rvListAgency = findViewById(R.id.listAgency);
        mLayoutManagerAgency = new LinearLayoutManager(this);
        rvListAgency.setLayoutManager(mLayoutManagerAgency);
        agencyApiInterface = ApiClient.getClient().create(AgencyApiInterface.class);

        refresh();
    }

    private void refresh() {
        Call<List<Agency>> listCall = agencyApiInterface.getAgency();
        listCall.enqueue(new Callback<List<Agency>>() {
            @Override
            public void onResponse(Call<List<Agency>> call, Response<List<Agency>> response) {
                List<Agency> agencyList = response.body();
                mAgencyAdapter = new AgencyListAdapter(agencyList,AgencyActivity.this);
                rvListAgency.setAdapter(mAgencyAdapter);
            }

            @Override
            public void onFailure(Call<List<Agency>> call, Throwable t) {
                tInternetHilang.setVisibility(View.VISIBLE);
            }
        });
    }
}