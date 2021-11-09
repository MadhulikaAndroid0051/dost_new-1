package com.urdost.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.urdost.R;
import com.urdost.model.response.exchangeAnalytics.LstAnalytics;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterAnalytics extends RecyclerView.Adapter<AdapterAnalytics.ViewHolder> {


    private List<LstAnalytics> models;
    private Context context;

    public AdapterAnalytics(List<LstAnalytics> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_analytis, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
    viewHolder.tvDeviceName.setText(models.get(i).getDevice());
    viewHolder.tvLocation.setText(models.get(i).getLocation());
    viewHolder.tvDate.setText(models.get(i).getViewDateTime());
    viewHolder.tvIp.setText(models.get(i).getIp());
    viewHolder.tvBrowser.setText(models.get(i).getBrowser());

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_Device_name)
        TextView tvDeviceName;
        @BindView(R.id.tv_location)
        TextView tvLocation;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_browser)
        TextView tvBrowser;
        @BindView(R.id.tv_ip)
        TextView tvIp;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


        }
    }
}
