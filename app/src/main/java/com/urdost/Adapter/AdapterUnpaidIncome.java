package com.urdost.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.urdost.R;
import com.urdost.model.response.responseUnpaidIncome.LstUnpaidIncome;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterUnpaidIncome extends RecyclerView.Adapter<AdapterUnpaidIncome.ViewHolder> {


    private List<LstUnpaidIncome> models;
    private Context context;

    public AdapterUnpaidIncome(List<LstUnpaidIncome> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_unpaid_income, viewGroup, false);
        return new ViewHolder(view);
    }
    public void updatelist(List<LstUnpaidIncome> models) {
        this.models = models;
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.tvLoginId.setText(models.get(i).getFromLoginId());
        viewHolder.tvFromName.setText(models.get(i).getFromUserName());
        viewHolder.tvJoinDate.setText(models.get(i).getDate());
        viewHolder.tvIncomeType.setText(models.get(i).getIncomeType());
        viewHolder.tvAmount.setText(models.get(i).getAmount());


    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_loginId)
        TextView tvLoginId;
        @BindView(R.id.tv_from_name)
        TextView tvFromName;
        @BindView(R.id.tv_join_date)
        TextView tvJoinDate;
        @BindView(R.id.tv_income_type)
        TextView tvIncomeType;
        @BindView(R.id.tv_amount)
        TextView tvAmount;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


        }
    }
}

