package com.urdost.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.urdost.R;
import com.urdost.interfaces.EventDetailslistner;
import com.urdost.model.response.responseMainDashboard.ProductsItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterDashboardMenuItems extends RecyclerView.Adapter<AdapterDashboardMenuItems.ViewHolder> {
    private List<ProductsItem> list;
    private Activity mContext;
    private EventDetailslistner mvp;

    public AdapterDashboardMenuItems(Activity context, List<ProductsItem> list, EventDetailslistner mvp) {
        mContext = context;
        this.list = list;
        this.mvp = mvp;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_dashboard_menu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int listPosition) {
        if (list.get(listPosition).getPKSubCategoryId().equalsIgnoreCase("1")) {
           holder.androidGridviewText.setVisibility(View.GONE);
        }
        else
        holder.androidGridviewText.setVisibility(View.VISIBLE);
        holder.androidGridviewText.setText(list.get(listPosition).getName());
        Glide.with(mContext).load(list.get(listPosition).getImage())
                .apply(RequestOptions.circleCropTransform().placeholder(R.drawable.digi_logo)
                        .error(R.drawable.digi_logo))
                .into(holder.androidGridviewImage);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.android_gridview_image)
        ImageView androidGridviewImage;
        @BindView(R.id.android_gridview_text)
        TextView androidGridviewText;
        @BindView(R.id.android_custom_gridview_layout)
        LinearLayout androidCustomGridviewLayout;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            androidCustomGridviewLayout.setOnClickListener(v -> mvp.openeventDetails(list.get(getAdapterPosition()).getPkEventId(), list.get(getAdapterPosition()).getPKSubCategoryId()));
        }
    }
}