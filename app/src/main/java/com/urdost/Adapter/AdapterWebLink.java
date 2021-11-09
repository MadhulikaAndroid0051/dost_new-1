package com.urdost.Adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.urdost.R;
import com.urdost.interfaces.NFCWebDelate;
import com.urdost.interfaces.WebIsDiract;
import com.urdost.model.response.WebLink.LstWeb;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterWebLink extends RecyclerView.Adapter<AdapterWebLink.ViewHolder> {



    private List<LstWeb> models;
    private Context context;
    private NFCWebDelate nfcDeletId;
    private WebIsDiract webIsDiract;

    public AdapterWebLink(List<LstWeb> models, Context context, NFCWebDelate nfcDeletId, WebIsDiract webIsDiract) {
        this.models = models;
        this.context = context;
        this.nfcDeletId = nfcDeletId;
        this.webIsDiract = webIsDiract;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_web_link, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.tvSocialMedia.setText(models.get(i).getLink());
        viewHolder.chakboxInclude.setVisibility(View.GONE);
        viewHolder.textView32.setVisibility(View.VISIBLE);


        // Toast.makeText(context,models.get(i).getPkNfcProfileId()+ "", Toast.LENGTH_SHORT).show();
        if (models.get(i).getIsRedirect()) {
            viewHolder.textView32.setChecked(true);
        }
        else {
            viewHolder.textView32.setChecked(false);
        }
        viewHolder.textView32.setOnClickListener((view) -> {
            if (models.get(i).getIsRedirect()) {
                models.get(i).setIsRedirect(false);
                webIsDiract.webcheckbox(models.get(i).getPkNfcProfileId(), false);
            } else {
                models.get(i).setIsRedirect(true);
                webIsDiract.webcheckbox(models.get(i).getPkNfcProfileId(), true);
            }
            new Handler().postDelayed(() -> notifyDataSetChanged(), 100);
        });
        viewHolder.textView31.setOnClickListener(v -> {
            nfcDeletId.getWebDelate(models.get(i).getPkNfcProfileId()
            );


        });




    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_social_media)
        TextView tvSocialMedia;
        @BindView(R.id.textView32)
        CheckBox textView32;
        @BindView(R.id.textView31)
        ImageView textView31;
        @BindView(R.id.chakbox_include)
        CheckBox chakboxInclude;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


        }

    }


}
