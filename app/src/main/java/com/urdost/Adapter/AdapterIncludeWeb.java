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
import com.urdost.interfaces.IncludeWebProfile;
import com.urdost.interfaces.RedirectWeb;
import com.urdost.model.response.GetEmail.LstEmail;
import com.urdost.model.response.WebLink.LstWeb;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterIncludeWeb extends RecyclerView.Adapter<AdapterIncludeWeb.ViewHolder> {


    private List<LstWeb> models;
    private Context context;
    private IncludeWebProfile includeWebProfile;
    private RedirectWeb redirectWeb;

    public AdapterIncludeWeb(List<LstWeb> models, Context context, IncludeWebProfile includeWebProfile, RedirectWeb redirectWeb) {
        this.models = models;
        this.context = context;
        this.includeWebProfile = includeWebProfile;
        this.redirectWeb = redirectWeb;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_contact_include, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.tvContact.setText(models.get(i).getLink());
        viewHolder.textView28.setText("WebLink");
        viewHolder.chkWhatsapp.setVisibility(View.VISIBLE);

        // Toast.makeText(context,models.get(i).getPkNfcProfileId()+ "", Toast.LENGTH_SHORT).show();

        if (models.get(i).getIsIncluded()) {
            viewHolder.chkProfilecontact.setChecked(true);
        } else {
            viewHolder.chkProfilecontact.setChecked(false);
        }
        if (models.get(i).getIsRedirect()) {
            viewHolder.chkWhatsapp.setChecked(true);
        } else {
            viewHolder.chkWhatsapp.setChecked(false);
        }


        viewHolder.chkWhatsapp.setOnClickListener((view) -> {
            if (models.get(i).getIsRedirect()) {
                models.get(i).setIsRedirect(false);
                redirectWeb.redirectWeb(models.get(i).getPkNfcProfileId(), false);
            } else {
                models.get(i).setIsRedirect(true);
                redirectWeb.redirectWeb(models.get(i).getPkNfcProfileId(), true);
            }

        });

        viewHolder.chkProfilecontact.setOnClickListener((view) -> {
            if (models.get(i).getIsIncluded()) {
                models.get(i).setIsIncluded(false);
                includeWebProfile.getIncludeWeb(models.get(i).getPkNfcProfileId(), false);
            } else {
                models.get(i).setIsIncluded(true);
                includeWebProfile.getIncludeWeb(models.get(i).getPkNfcProfileId(), true);
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
        @BindView(R.id.chk_whatsapp)
        CheckBox chkWhatsapp;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


        }
    }

}
