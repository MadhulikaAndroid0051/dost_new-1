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
import com.urdost.model.response.NfcActivateBuyDDCard.LstnfcDdCard;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterInvantoryRequest extends RecyclerView.Adapter<AdapterInvantoryRequest.ViewHolder> {

    private List<LstnfcDdCard> models;
    private Context context;
    private ActivateNfcDDCardlistner nfcDDCardlistner;

    public AdapterInvantoryRequest(List<LstnfcDdCard> models, Context context, ActivateNfcDDCardlistner nfcDDCardlistner) {
        this.models = models;
        this.context = context;
        this.nfcDDCardlistner = nfcDDCardlistner;
    }

    @Override
    public AdapterInvantoryRequest.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_activate_nfc, viewGroup, false);
        return new AdapterInvantoryRequest.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterInvantoryRequest.ViewHolder viewHolder, int i) {

       // Glide.with(context).load("https://dost.click/"+models.get(i).getEventImage()).into(viewHolder.image);
        viewHolder.tvActivate.setText(models.get(i).getEventName());
        viewHolder.tvMrp.setText("MRP: "+models.get(i).getProductPrice());
        viewHolder.tvReferBv.setText("Refer BV: "+models.get(i).getReferalBV());
        viewHolder.tvDiscount.setText("Discount: "+models.get(i).getDiscount());
        viewHolder.tvTeamBv.setText("Team BV: "+models.get(i).getBinaryBV());
        viewHolder.tvReferCode.setText("Request");


        viewHolder.tvReferCode.setOnClickListener(v -> nfcDDCardlistner.activateddCard(models.get(i).getPKEventId(),models.get(i).getProductPrice(),models.get(i).getEventName(),models.get(i).getReferalBV(),models.get(i).getBinaryBV()));

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
