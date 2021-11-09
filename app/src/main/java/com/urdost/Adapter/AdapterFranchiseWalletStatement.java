package com.urdost.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.urdost.R;
import com.urdost.model.response.responseFranchiseWalletStatement.LstFranchiseWalletStatement;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterFranchiseWalletStatement extends RecyclerView.Adapter<AdapterFranchiseWalletStatement.ViewHolder> {


    private List<LstFranchiseWalletStatement> models;
    private Context context;

    public AdapterFranchiseWalletStatement(List<LstFranchiseWalletStatement> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_franchise_wallet_statement, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.tvMemName.setText(models.get(i).getNarration());
        viewHolder.tvMemId.setText(models.get(i).getEwalletBalance());
        viewHolder.tvJoinDate.setText(models.get(i).getAddedOn());
        viewHolder.tvCrAmount.setText(models.get(i).getCrAmount());
        viewHolder.tvDrAmount.setText(models.get(i).getDrAmount());


    }

    public void updatelist(List<LstFranchiseWalletStatement> models) {
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
        @BindView(R.id.tv_mem_id)
        TextView tvMemId;
        @BindView(R.id.tv_join_date)
        TextView tvJoinDate;
        @BindView(R.id.tv_cr_amount)
        TextView tvCrAmount;
        @BindView(R.id.tv_dr_amount)
        TextView tvDrAmount;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


        }
    }
}
