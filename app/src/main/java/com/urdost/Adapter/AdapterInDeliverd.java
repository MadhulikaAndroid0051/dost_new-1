package com.urdost.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.urdost.R;
import com.urdost.model.response.AvalableInvantory.LstInAvaliable;
import com.urdost.model.response.DelaveredInvatory.LstDelaverd;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterInDeliverd extends RecyclerView.Adapter<AdapterInDeliverd.ViewHolder> {
    private List<LstDelaverd> models;
    private Context context;

    public AdapterInDeliverd(List<LstDelaverd> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapte_delivered_inventory, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterInDeliverd.ViewHolder viewHolder, int i) {
        //viewHolder.tvSrNo.setText(models.get(i).ge);
        viewHolder.tvSrNo.setText("SrNo:- "+models.get(i).getSrNo());
       viewHolder.tvAllotedOn.setText(models.get(i).getAllotedOn());
        viewHolder.tvCode.setText(models.get(i).getCode());
        viewHolder.tvAllotedBy.setText(models.get(i).getNFCStatus());
        viewHolder.tvStatus.setText(models.get(i).getAssignedBy());

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_sr_no)
        TextView tvSrNo;
        @BindView(R.id.tv_alloted_on)
        TextView tvAllotedOn;
        @BindView(R.id.tv_mem_id)
        TextView tvMemId;
        @BindView(R.id.tv_code)
        TextView tvCode;
        @BindView(R.id.tv_cr_amount)
        TextView tvAllotedBy;
        @BindView(R.id.tv_status)
        TextView tvStatus;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


        }
    }
}
