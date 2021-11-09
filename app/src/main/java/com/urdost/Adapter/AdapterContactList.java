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
import com.urdost.interfaces.DeleteContactItem;
import com.urdost.interfaces.UpdateEmailIncludeProfile;
import com.urdost.interfaces.contactList;
import com.urdost.model.response.NFcContact.LstContactNo;
import com.urdost.retrofit.CheckMobile;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterContactList extends RecyclerView.Adapter<AdapterContactList.ViewHolder> {


    private List<LstContactNo> models;
    private Context context;
    private contactList updateEmailIncludeProfile;

    public AdapterContactList(List<LstContactNo> models, Context context, contactList updateEmailIncludeProfile) {
        this.models = models;
        this.context = context;
        this.updateEmailIncludeProfile = updateEmailIncludeProfile;
    }

    @Override
    public AdapterContactList.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_contact, viewGroup, false);
        return new AdapterContactList.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterContactList.ViewHolder viewHolder, int i) {
        viewHolder.tvContact.setText(models.get(i).getContact());
        // Toast.makeText(context,models.get(i).getPkNfcProfileId()+ "", Toast.LENGTH_SHORT).show();

        if (models.get(i).getIsIncluded()) {
            viewHolder.chkProfilecontact.setChecked(true);
        }
        else {
            viewHolder.chkProfilecontact.setChecked(false);
        }

        viewHolder.chkWhatsapp.setVisibility(View.GONE);
        viewHolder.textView31.setVisibility(View.GONE);
        viewHolder.chkProfilecontact.setOnClickListener((view) -> {
            if (models.get(i).getIsIncluded()) {
                models.get(i).setIsIncluded(false);
                updateEmailIncludeProfile .getContactList(models.get(i).getPkNfcProfileId(), false);
            } else {
                models.get(i).setIsIncluded(true);
                updateEmailIncludeProfile.getContactList(models.get(i).getPkNfcProfileId(), true);
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
        @BindView(R.id.chk_whatsapp)
        CheckBox chkWhatsapp;
        @BindView(R.id.chk_profilecontact)
        CheckBox chkProfilecontact;
        @BindView(R.id.textView31)
        ImageView textView31;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


        }
    }
}
