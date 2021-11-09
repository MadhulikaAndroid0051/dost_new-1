package com.urdost.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.urdost.R;
import com.urdost.interfaces.ActivateNfcDDCardlistner;
import com.urdost.model.response.responseSubscription.LstSubscription;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterSubscription extends RecyclerView.Adapter<AdapterSubscription.ViewHolder> {

    private List<LstSubscription> models;
    private Context context;
    private ActivateNfcDDCardlistner nfcDDCardlistner;

    public AdapterSubscription(List<LstSubscription> models, Context context, ActivateNfcDDCardlistner nfcDDCardlistner) {
        this.models = models;
        this.context = context;
        this.nfcDDCardlistner = nfcDDCardlistner;
    }




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_activate_nfc, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Glide.with(context).load(models.get(i).getSubscriptionImage()).into(viewHolder.image);
        viewHolder.tvActivate.setText(models.get(i).getSubscriptionName());
        viewHolder.tvMrp.setText("MRP: "+models.get(i).getPrice());
        viewHolder.tvReferBv.setText("Refer BV: "+models.get(i).getReferralPercentage());
        viewHolder.tvDiscount.setText("Discount: "+models.get(i).getDiscount());
        viewHolder.tvTeamBv.setText("Team BV: "+models.get(i).getBinaryPercentage());
        viewHolder.tvReferCode.setOnClickListener(v -> nfcDDCardlistner.activateddCard(models.get(i).getSubscriptionName(),
                models.get(i).getPrice(),models.get(i).getSubscriptionId(),models.get(i).getReferralPercentage(),models.get(i).getBinaryPercentage()));


    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.tv_activate)
        TextView tvActivate;
        @BindView(R.id.tv_mrp)
        TextView tvMrp;
        @BindView(R.id.tv_refer_bv)
        TextView tvReferBv;
        @BindView(R.id.tv_discount)
        TextView tvDiscount;
        @BindView(R.id.tv_team_bv)
        TextView tvTeamBv;
        @BindView(R.id.tv_refer_code)
        TextView tvReferCode;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


        }
    }
}