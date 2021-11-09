package com.urdost.Adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.urdost.R;
import com.urdost.interfaces.IsReDiractSocial;
import com.urdost.interfaces.NfcSocialDelate;
import com.urdost.interfaces.RediractCheckbox;
import com.urdost.model.response.GeSocialMediaLink.LstEmail;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterSocialMedia extends RecyclerView.Adapter<AdapterSocialMedia.ViewHolder> {


    private List<LstEmail> models;
    private Context context;
    private NfcSocialDelate nfcDeletId;
    private IsReDiractSocial isReDiractSocial;

    public AdapterSocialMedia(List<LstEmail> models, Context context, NfcSocialDelate nfcDeletId, IsReDiractSocial isReDiractSocial) {
        this.models = models;
        this.context = context;
        this.nfcDeletId = nfcDeletId;
        this.isReDiractSocial = isReDiractSocial;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_social_media, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (models.get(i).getIsRedirect())
            viewHolder.textView32.setChecked(true);
        else viewHolder.textView32.setChecked(false);

        viewHolder.tvSocialMedia.setText(models.get(i).getLink());
        viewHolder.textView32.setVisibility(View.VISIBLE);

        viewHolder.textView32.setOnClickListener((view) -> {
            if (models.get(i).getIsRedirect()) {
                models.get(i).setIsRedirect(false);
                isReDiractSocial.getSocialIsRedirect(models.get(i).getPkNfcProfileId(), false);
            } else {
                models.get(i).setIsRedirect(true);
                isReDiractSocial.getSocialIsRedirect(models.get(i).getPkNfcProfileId(), true);
            }
            new Handler().postDelayed(() -> notifyDataSetChanged(), 100);
        });

        viewHolder.textView31.setOnClickListener(v ->{ nfcDeletId.getSocialDelate(models.get(i).getPkNfcProfileId()
        );
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
        @BindView(R.id.tv_social_media)
        TextView tvSocialMedia;
        @BindView(R.id.textView32)
        CheckBox textView32;
        @BindView(R.id.textView31)
        ImageView textView31;
        @BindView(R.id.cv_login)
        CardView cvLogin;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            // AdapterSocialMedia.this.notify();
            //  itemView.notifyItemRangeChanged(position, list.size());
        }
    }


}
