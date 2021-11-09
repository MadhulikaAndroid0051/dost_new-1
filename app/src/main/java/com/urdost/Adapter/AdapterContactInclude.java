package com.urdost.Adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.urdost.R;
import com.urdost.model.response.NFcContact.LstContactNo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterContactInclude extends RecyclerView.Adapter<AdapterContactInclude.ViewHolder> {



    private List<LstContactNo> models;
    private Context context;
    private ContactIncludeProfile contactIncludeProfile;

    public AdapterContactInclude(List<LstContactNo> models, Context context, ContactIncludeProfile contactIncludeProfile) {
        this.models = models;
        this.context = context;
        this.contactIncludeProfile = contactIncludeProfile;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_contact_include, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.tvContact.setText(models.get(i).getContact());
        // Toast.makeText(context,models.get(i).getPkNfcProfileId()+ "", Toast.LENGTH_SHORT).show();

        if (models.get(i).getIsIncluded()) {
            viewHolder.chkProfilecontact.setChecked(true);
        } else {
            viewHolder.chkProfilecontact.setChecked(false);
        }


        viewHolder.chkProfilecontact.setOnClickListener((view) -> {
            if (models.get(i).getIsIncluded()) {
                models.get(i).setIsIncluded(false);
                contactIncludeProfile.getcontactIncludeProfile(models.get(i).getPkNfcProfileId(), false);
            } else {
                models.get(i).setIsIncluded(true);
                contactIncludeProfile.getcontactIncludeProfile(models.get(i).getPkNfcProfileId(), true);
            }
            new Handler().postDelayed(() -> notifyDataSetChanged(), 100);
        });
    }


    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_contact)
        TextView tvContact;
        @BindView(R.id.chk_profilecontact)
        CheckBox chkProfilecontact;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


        }
    }
}
