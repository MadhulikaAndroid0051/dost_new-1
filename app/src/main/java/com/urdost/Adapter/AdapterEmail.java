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
import com.urdost.interfaces.NfcEmailDelate;
import com.urdost.interfaces.UpdateEmailIncludeProfile;
import com.urdost.model.response.GetEmail.LstEmail;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterEmail extends RecyclerView.Adapter<AdapterEmail.ViewHolder> {



    private List<LstEmail> models;
    private Context context;
    private NfcEmailDelate nfcDeletId;


    public AdapterEmail(List<LstEmail> models, Context context, NfcEmailDelate nfcDeletId) {
        this.models = models;
        this.context = context;
        this.nfcDeletId = nfcDeletId;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_email, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.tvEmail.setText(models.get(i).getEmail());
        viewHolder.textView31.setOnClickListener(v -> nfcDeletId.getEmailDeleatId(models.get(i).getPkNfcProfileId()));
        viewHolder.EmailStatus.setVisibility(View.GONE);
     //   new Handler().postDelayed(() -> notifyDataSetChanged(), 100);



    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textView28)
        TextView textView28;
        @BindView(R.id.tv_email)
        TextView tvEmail;
        @BindView(R.id.textView31)
        ImageView textView31;

        @BindView(R.id.email_status)
        CheckBox EmailStatus;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


        }
    }

}
