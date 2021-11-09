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
import com.urdost.interfaces.NFcFullDetails;
import com.urdost.model.response.NfcActivateBuyDDCard.LstnfcDdCard;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterListData extends RecyclerView.Adapter<AdapterListData.ViewHolder> {

    private NFcFullDetails fcFullDetails;
    private List<LstnfcDdCard> models;
    private Context context;

    public AdapterListData(NFcFullDetails fcFullDetails, List<LstnfcDdCard> models, Context context) {
        this.fcFullDetails = fcFullDetails;
        this.models = models;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_event_details, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        Glide.with(context).load("https://dost.click/" + models.get(i).getEventImage()).into(viewHolder.imageViewProductThumb);
        viewHolder.textViewProductName.setText(models.get(i).getEventName());
        viewHolder.textViewProductPrice.setText("MRP: " + models.get(i).getProductPrice());
        viewHolder.tvReferBv.setText("Bv:" +models.get(i).getReferalBV());
        viewHolder.tvTeamBv.setText("Team BV:"+models.get(i).getBinaryBV());

        viewHolder.itemView.setOnClickListener(v -> fcFullDetails.getNFcFullDetails(models.get(i).getPKEventId(),models.get(i).getEventImage()
        ,models.get(i).getReferalBV(),models.get(i).getBinaryBV(),models.get(i).getDiscount(),models.get(i).getEventName(),models.get(i).getProductPrice()));


    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.imageViewProductThumb)
        ImageView imageViewProductThumb;
        @BindView(R.id.textViewProductName)
        TextView textViewProductName;
        @BindView(R.id.tv_refer_bv)
        TextView tvReferBv;
        @BindView(R.id.tv_team_bv)
        TextView tvTeamBv;
        @BindView(R.id.textViewProductPrice)
        TextView textViewProductPrice;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


        }
    }
}


