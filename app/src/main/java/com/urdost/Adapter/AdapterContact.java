package com.urdost.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.urdost.R;
import com.urdost.interfaces.DeleteContactItem;
import com.urdost.model.response.NFcContact.LstContactNo;
import com.urdost.retrofit.CheckMobile;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterContact extends RecyclerView.Adapter<AdapterContact.ViewHolder> {


    private List<LstContactNo> models;
    private Context context;
    private DeleteContactItem nfcDeletId;
    private CheckMobile checkMobile;


    public AdapterContact(List<LstContactNo> models, Context context, DeleteContactItem nfcDeletId, CheckMobile checkMobile) {
        this.models = models;
        this.context = context;
        this.nfcDeletId = nfcDeletId;
        this.checkMobile = checkMobile;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_contact, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.tvContact.setText(models.get(i).getContact());
        viewHolder.chkWhatsapp.setVisibility(View.GONE);
        viewHolder.chkProfilecontact.setVisibility(View.GONE);
       // Toast.makeText(context,models.get(i).getPkNfcProfileId()+ "", Toast.LENGTH_SHORT).show();
        if (models.get(i).getIsWhatsup()) {
            viewHolder.chkWhatsapp.setChecked(true);
        }
        else {
            viewHolder.chkWhatsapp.setChecked(false);
        }

        if (models.get(i).getIsIncluded()) {
            viewHolder.chkProfilecontact.setChecked(true);
        }
        else {
            viewHolder.chkProfilecontact.setChecked(false);
        }

        viewHolder.chkWhatsapp.setOnClickListener((view) -> {
            if (models.get(i).getIsWhatsup()) {
                models.get(i).setIsWhatsup(false);
                checkMobile.MobileCheck(models.get(i).getPkNfcProfileId(), false);
            } else {
                models.get(i).setIsWhatsup(true);
                checkMobile.MobileCheck(models.get(i).getPkNfcProfileId(), true);
            }

        });


        viewHolder.textView31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               nfcDeletId.getDeleteContact(models.get(i).getPkNfcProfileId());
            }
        });




       /* if (models.get(i).getIsIncluded())
        {
            viewHolder.chkProfilecontact.setChecked(true);
        }else {
            viewHolder.chkProfilecontact.setChecked(false);

        }*/


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
