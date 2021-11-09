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
import com.urdost.interfaces.IncludeEmailProfile;
import com.urdost.model.response.GetEmail.LstEmail;
import com.urdost.model.response.NFcContact.LstContactNo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterIncludeEmail extends RecyclerView.Adapter<AdapterIncludeEmail.ViewHolder> {



    private List<LstEmail> models;
    private Context context;
    private IncludeEmailProfile includeEmailProfile;

    public AdapterIncludeEmail(List<LstEmail> models, Context context, IncludeEmailProfile includeEmailProfile) {
        this.models = models;
        this.context = context;
        this.includeEmailProfile = includeEmailProfile;
    }

    @Override
    public AdapterIncludeEmail.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_contact_include, viewGroup, false);
        return new AdapterIncludeEmail.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterIncludeEmail.ViewHolder viewHolder, int i) {
        viewHolder.tvContact.setText(models.get(i).getEmail());
        viewHolder.textView28.setText("Email");

        // Toast.makeText(context,models.get(i).getPkNfcProfileId()+ "", Toast.LENGTH_SHORT).show();

        if (models.get(i).getIsIncluded()) {
            viewHolder.chkProfilecontact.setChecked(true);
        } else {
            viewHolder.chkProfilecontact.setChecked(false);
        }


        viewHolder.chkProfilecontact.setOnClickListener((view) -> {
            if (models.get(i).getIsIncluded()) {
                models.get(i).setIsIncluded(false);
                includeEmailProfile.getIncludeEmailProfile(models.get(i).getPkNfcProfileId(), false);
            } else {
                models.get(i).setIsIncluded(true);
                includeEmailProfile.getIncludeEmailProfile(models.get(i).getPkNfcProfileId(), true);
            }
            new Handler().postDelayed(() -> notifyDataSetChanged(), 100);
        });
    }


    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textView28)
        TextView textView28;
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
