package com.urdost.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.urdost.R;
import com.urdost.model.response.AvalableInvantory.LstInAvaliable;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterAvalabileInvantory extends RecyclerView.Adapter<AdapterAvalabileInvantory.ViewHolder> {



    private List<LstInAvaliable> models;
    private Context context;

    public AdapterAvalabileInvantory(List<LstInAvaliable> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_avalable_invantory, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.tvSrNo.setText("SrNo:- "+models.get(i).getSrNo());
        viewHolder.tvName.setText(models.get(i).getName());
        viewHolder.tvCode.setText(models.get(i).getCode());
        viewHolder.tvMobile.setText(models.get(i).getMobileNo());
        viewHolder.tvEmail.setText(models.get(i).getEmail());
        viewHolder.tvStatus.setText(models.get(i).getNFCStatus());

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_sr_no)
        TextView tvSrNo;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_email)
        TextView tvEmail;
        @BindView(R.id.tv_code)
        TextView tvCode;
        @BindView(R.id.tv_mobile)
        TextView tvMobile;
        @BindView(R.id.tv_status)
        TextView tvStatus;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


        }
    }
}
