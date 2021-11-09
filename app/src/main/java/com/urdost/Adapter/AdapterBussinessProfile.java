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
import com.urdost.model.response.MyBussinessProfile.Nfc;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterBussinessProfile extends RecyclerView.Adapter<AdapterBussinessProfile.ViewHolder> {
    private Context mContext;
    private List<Nfc> list;

    public AdapterBussinessProfile(Context mContext, List<Nfc> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_nfc_profile, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int i) {
        holder.tvHeading.setText(list.get(i).getTypeName());
        holder.rcMenuItems.setVisibility(View.GONE);


        
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.rcMenuItems.getVisibility()==View.GONE)
                {
                    holder.rcMenuItems.setVisibility(View.VISIBLE);
                    AdapterNfcProfileItem adapterNfcProfileItem = new AdapterNfcProfileItem(mContext, list.get(i).getTypeItem());

                    holder.rcMenuItems.setLayoutManager(new GridLayoutManager(mContext, 1));
                    holder.rcMenuItems.setAdapter(adapterNfcProfileItem);
                    holder.rcMenuItems.setHasFixedSize(true);
                }else {
                    holder.rcMenuItems.setVisibility(View.GONE);
                }
               // holder.rcMenuItems.notifySubtreeAccessibilityStateChanged();
            }

        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_heading)
        TextView tvHeading;
        @BindView(R.id.rc_menu_items)
        RecyclerView rcMenuItems;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }


}
