package com.urdost.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.urdost.R;
import com.urdost.model.response.MyBussinessProfile.SocialMedia1;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterSocialContect extends RecyclerView.Adapter<AdapterSocialContect.ViewHolder> {


    private Context mContext;
    private List<SocialMedia1> list;

    public AdapterSocialContect(Context mContext, List<SocialMedia1> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_socila_link, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int i) {
        holder.tvSocialLink.setText(list.get(i).getTypeName());
        holder.rvOrders.setVisibility(View.GONE);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.rvOrders.getVisibility()==View.GONE) {
                    holder.rvOrders.setVisibility(View.VISIBLE);
                    AdapterNfcProfileItem adapterNfcProfileItem = new AdapterNfcProfileItem(mContext, list.get(i).getTypeItem());

                    holder.rvOrders.setLayoutManager(new GridLayoutManager(mContext, 1));
                    holder.rvOrders.setAdapter(adapterNfcProfileItem);
                    holder.rvOrders.setHasFixedSize(true);
                }else
                {
                    holder.rvOrders.setVisibility(View.GONE);
                }
            }

        });

      /* AdapterNfcProfileItem adapterNfcProfileItem = new AdapterNfcProfileItem(mContext,list.get(i).getTypeItem().get(i).getContent());
// AdapterNfcProfileItem adapterViewAndroid = new AdapterNfcProfileItem(mContext,list.get(listPosition).getTypeItemName());
        holder.rvOrders.setLayoutManager(new GridLayoutManager(mContext, 1));
        holder.rvOrders.setAdapter(adapterNfcProfileItem);
        holder.rvOrders.setHasFixedSize(true);*/
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_social_link)
        TextView tvSocialLink;
        @BindView(R.id.rv_orders)
        RecyclerView rvOrders;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

}
