package com.urdost.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.urdost.R;
import com.urdost.model.response.istributorCommissionHistory.LstDistributorCommissionHistory;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterCommissionHistory extends RecyclerView.Adapter<AdapterCommissionHistory.ViewHolder> {


    private List<LstDistributorCommissionHistory> models;
    private Context context;

    public AdapterCommissionHistory(List<LstDistributorCommissionHistory> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_commission_history, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.tvMemName.setText(models.get(i).getNarration());
        viewHolder.tvAmount.setText(models.get(i).getAmount());
        viewHolder.tvJoinDate.setText(models.get(i).getDate());


    }

    public void updatelist(List<LstDistributorCommissionHistory> models) {
        this.models = models;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_mem_name)
        TextView tvMemName;
        @BindView(R.id.tv_join_date)
        TextView tvJoinDate;
        @BindView(R.id.tv_amount)
        TextView tvAmount;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


        }
    }
}
