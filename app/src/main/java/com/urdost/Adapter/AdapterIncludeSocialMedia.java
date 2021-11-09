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
import com.urdost.interfaces.IncludeSocialMedia;
import com.urdost.interfaces.RedirectSocial;
import com.urdost.model.response.GeSocialMediaLink.LstEmail;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterIncludeSocialMedia extends RecyclerView.Adapter<AdapterIncludeSocialMedia.ViewHolder> {


    private List<LstEmail> models;
    private Context context;
    private IncludeSocialMedia includeSocialMedia;
    private RedirectSocial redirectSocial;

    public AdapterIncludeSocialMedia(List<LstEmail> models, Context context, IncludeSocialMedia includeSocialMedia, RedirectSocial redirectSocial) {
        this.models = models;
        this.context = context;
        this.includeSocialMedia = includeSocialMedia;
        this.redirectSocial = redirectSocial;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_contact_include, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.tvContact.setText(models.get(i).getLink());
        viewHolder.textView28.setText("Social Media");
        viewHolder.chkWhatsapp.setVisibility(View.VISIBLE);

        // Toast.makeText(context,models.get(i).getPkNfcProfileId()+ "", Toast.LENGTH_SHORT).show();

        if (models.get(i).getIncluded()) {
            viewHolder.chkProfilecontact.setChecked(true);
        } else {
            viewHolder.chkProfilecontact.setChecked(false);
        }

        if (models.get(i).getIsRedirect()) {
            viewHolder.chkWhatsapp.setChecked(true);
        } else {
            viewHolder.chkWhatsapp.setChecked(false);
        }

        viewHolder.chkProfilecontact.setOnClickListener((view) -> {
            if (models.get(i).getIncluded()) {
                models.get(i).setIncluded(false);
                includeSocialMedia.getIncludeMedia(models.get(i).getPkNfcProfileId(), false);
            } else {
                models.get(i).setIncluded(true);
                includeSocialMedia.getIncludeMedia(models.get(i).getPkNfcProfileId(), true);
            }
            new Handler().postDelayed(() -> notifyDataSetChanged(), 100);
        });
        viewHolder.chkWhatsapp.setOnClickListener((view) -> {
            if (models.get(i).getIsRedirect()) {
                models.get(i).setRedirect(false);
                redirectSocial.redirectSocial(models.get(i).getPkNfcProfileId(), false);
            } else {
                models.get(i).setRedirect(true);
                redirectSocial.redirectSocial(models.get(i).getPkNfcProfileId(), true);
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
