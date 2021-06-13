package project.akbaralzaini.sesi2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import project.akbaralzaini.sesi2.R;
import project.akbaralzaini.sesi2.models.Agency;

public class AgencyListAdapter extends RecyclerView.Adapter<AgencyListAdapter.MyViewHolder>{

    List<Agency> agencyList;
    private Context context;

    public AgencyListAdapter(List<Agency> agencyList, Context a) {
        this.agencyList = agencyList;
        this.context = a;
    }



    @Override
    public AgencyListAdapter.@NotNull MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.agency_list_item, parent, false);
        return new MyViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AgencyListAdapter.MyViewHolder holder, int position) {
        holder.id.setText(String.valueOf(agencyList.get(position).getId()));
        holder.name.setText(agencyList.get(position).getName());
        holder.code.setText(agencyList.get(position).getCode());
        holder.detail.setText(agencyList.get(position).getDetail());

    }

    @Override
    public int getItemCount() {
        return agencyList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView id,code,name,detail;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id_agency);
            name = itemView.findViewById(R.id.nama_agency);
            code = itemView.findViewById(R.id.code_agency);
            detail = itemView.findViewById(R.id.details_agency);
        }
    }
}
