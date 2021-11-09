package com.urdost.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.urdost.R;
import com.urdost.model.response.MyBussinessProfile.TypeItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterNfcProfileItem extends RecyclerView.Adapter<AdapterNfcProfileItem.ViewHolder> {

    private List<TypeItem> typeItemName;
    private Context mContext;


    public AdapterNfcProfileItem(Context mContext, List<TypeItem> list) {
        this.typeItemName = list;
        this.mContext = mContext;

    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_nfc_profile_menu_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int i) {

        holder.tvContact.setText(typeItemName.get(i).getContent());
        holder.tvName.setText(typeItemName.get(i).getType());

    }

    @Override
    public int getItemCount() {
        return typeItemName.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_contact)
        TextView tvContact;
        @BindView(R.id.tv_name)
        TextView tvName;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            // androidCustomGridviewLayout.setOnClickListener(v -> mvp.openeventDetails(list.get(getAdapterPosition()).getPk_EventId(), list.get(getAdapterPosition()).getPKSubCategoryId()));
        }
    }
}
