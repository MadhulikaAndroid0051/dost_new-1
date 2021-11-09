package com.urdost.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.urdost.R;
import com.urdost.model.response.responsePaidCommission.LstPaidCommission;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterPaidCommission extends RecyclerView.Adapter<AdapterPaidCommission.ViewHolder> {


    private List<LstPaidCommission> models;
    private Context context;

    public AdapterPaidCommission(List<LstPaidCommission> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.paid_commision, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.tvLoginId.setText(models.get(i).getLoginId());
        viewHolder.tvName.setText(models.get(i).getName());
        viewHolder.tvDate.setText(models.get(i).getPaymentDate());
        viewHolder.tvAmount.setText(models.get(i).getAmount());
        viewHolder.tvTranscation.setText(models.get(i).getTransactionNo());
        viewHolder.tvDiscription.setText(models.get(i).getDescription());



    }

    public void updatelist(List<LstPaidCommission> models) {
        this.models = models;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_loginId)
        TextView tvLoginId;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_Date)
        TextView tvDate;
        @BindView(R.id.tv_amount)
        TextView tvAmount;
        @BindView(R.id.tv_transcation)
        TextView tvTranscation;
        @BindView(R.id.tv_discription)
        TextView tvDiscription;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


        }
    }
}
