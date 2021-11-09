package com.urdost.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.urdost.R;
import com.urdost.interfaces.EventDetailslistner;
import com.urdost.model.response.responseMainDashboard.LstSubCategoryItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterDashboardMenu extends RecyclerView.Adapter<AdapterDashboardMenu.ViewHolder> {

    private Activity mContext;
    private List<LstSubCategoryItem> list;
    private EventDetailslistner mvpView;

    public AdapterDashboardMenu(Activity context, List<LstSubCategoryItem> list, EventDetailslistner mvp) {
        mContext = context;
        this.list = list;
        mvpView = mvp;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_dashboard_menu_head, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int listPosition) {
        holder.tvHeading.setText(list.get(listPosition).getSubCategoryName());
        AdapterDashboardMenuItems adapterViewAndroid = new AdapterDashboardMenuItems(mContext, list.get(listPosition).getProducts(), mvpView);
        holder.rcMenuItems.setLayoutManager(new GridLayoutManager(mContext, 4));
        holder.rcMenuItems.setAdapter(adapterViewAndroid);
        holder.rcMenuItems.setHasFixedSize(true);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_heading)
        TextView tvHeading;
        @BindView(R.id.rc_menu_items)
        RecyclerView rcMenuItems;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
