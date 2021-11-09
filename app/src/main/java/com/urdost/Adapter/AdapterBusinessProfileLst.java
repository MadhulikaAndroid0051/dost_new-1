package com.urdost.Adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.urdost.R;
import com.urdost.interfaces.BusinessProfileId;
import com.urdost.interfaces.Chackboxbusinessprofile;
import com.urdost.model.response.BusinessProfile.LstBusnessProfile;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterBusinessProfileLst extends RecyclerView.Adapter<AdapterBusinessProfileLst.ViewHolder> {


    private List<LstBusnessProfile> list;
    private Context mContext;
    private BusinessProfileId businessProfileId;
    Chackboxbusinessprofile chackboxbusinessprofile;

    public AdapterBusinessProfileLst(List<LstBusnessProfile> list, Context mContext, BusinessProfileId businessProfileId, Chackboxbusinessprofile chackboxbusinessprofile) {
        this.list = list;
        this.mContext = mContext;
        this.businessProfileId = businessProfileId;
        this.chackboxbusinessprofile = chackboxbusinessprofile;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_business_profile, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int i) {
        // holder.tvBusinessName.setText(list.get(i).getBusinessName());
        holder.tvName.setText(list.get(i).getProfileNAme());
        holder.tvBusinessName.setVisibility(View.GONE);
        holder.textView28.setText("Profile Name");
        holder.textView29.setVisibility(View.GONE);
        holder.tvEdit.setOnClickListener(v -> businessProfileId.getbusinessId(list.get(i).getPKProfileId(), list.get(i).getFKUserId()));


        if (list.get(i).getIsActive()) {
            holder.chackbox.setChecked(true);
        } else {
            holder.chackbox.setChecked(false);
        }

        holder.chackbox.setOnClickListener((view) -> {
            /*if (list.get(i).getIsActive()) {
                list.get(i).setIsActive(false);
                chackboxbusinessprofile.getBusinessprofilecheckbox(list.get(i).getPKProfileId(), false);
            } else*/
            {
                list.get(i).setIsActive(true);
                chackboxbusinessprofile.getBusinessprofilecheckbox(list.get(i).getPKProfileId(), true);
            }
            new Handler().postDelayed(() -> notifyDataSetChanged(), 2);
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textView28)
        TextView textView28;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_edit)
        TextView tvEdit;
        @BindView(R.id.textView29)
        TextView textView29;
        @BindView(R.id.tv_business_name)
        TextView tvBusinessName;
        @BindView(R.id.chackbox)
        CheckBox chackbox;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }


}
