package com.urdost.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.urdost.R;
import com.urdost.model.response.responsePayoutLedger.LstPayoutLedger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterPayoutLedger extends RecyclerView.Adapter<AdapterPayoutLedger.ViewHolder> {


    private List<LstPayoutLedger> models;
    private Context context;

    public AdapterPayoutLedger(List<LstPayoutLedger> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_payout_ledger, viewGroup, false);
        return new ViewHolder(view);
    }
    public void updatelist(List<LstPayoutLedger> models) {
        this.models = models;
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.tvNarration.setText(models.get(i).getNarration());
        viewHolder.tvDrAmount.setText(models.get(i).getDrAmount());
        viewHolder.tvCrAmount.setText(models.get(i).getCrAmount());
        viewHolder.tvAddOn.setText(models.get(i).getAddedOn());
        viewHolder.tvEwalletBalance.setText(models.get(i).getEwalletBalance());


    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_narration)
        TextView tvNarration;
        @BindView(R.id.tv_dr_amount)
        TextView tvDrAmount;
        @BindView(R.id.tv_cr_amount)
        TextView tvCrAmount;
        @BindView(R.id.tv_add_on)
        TextView tvAddOn;
        @BindView(R.id.tv_ewallet_balance)
        TextView tvEwalletBalance;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


        }
    }
}
