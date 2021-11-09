package com.urdost.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.urdost.R;
import com.urdost.model.response.responseDiractList.LstDirect;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterDiractList extends RecyclerView.Adapter<AdapterDiractList.ViewHolder> {


    private List<LstDirect> models;
    private Context context;

    public AdapterDiractList(List<LstDirect> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_diract_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.tvLoginId.setText(models.get(i).getMobile());
        viewHolder.tvName.setText(models.get(i).getName());
        viewHolder.tvLeg.setText(models.get(i).getLeg());
        viewHolder.tvJoiningDate.setText(models.get(i).getJoiningDate());
        viewHolder.tvActivationDate.setText(models.get(i).getPermanentDate());
        viewHolder.tvStatus.setText(models.get(i).getStatus());

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_loginId)
        TextView tvLoginId;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_leg)
        TextView tvLeg;
        @BindView(R.id.tv_joining_date)
        TextView tvJoiningDate;
        @BindView(R.id.tv_activation_date)
        TextView tvActivationDate;
        @BindView(R.id.tv_status)
        TextView tvStatus;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


        }
    }
}
