package com.urdost.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.urdost.R;
import com.urdost.interfaces.UnUsedListner;
import com.urdost.model.response.responseUnusedCoupon.Lstunusedcoupon;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterUnusedCoupon extends RecyclerView.Adapter<AdapterUnusedCoupon.ViewHolder> {


    private UnUsedListner listener;
    private List<Lstunusedcoupon> models;
    private Context context;

    public AdapterUnusedCoupon(UnUsedListner listener, List<Lstunusedcoupon> models, Context context) {
         this.listener = listener;
        this.models = models;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_unused_coupons, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.tvCouponsCodes.setText(models.get(i).getCouponCode());
        viewHolder.tvCategory.setText(models.get(i).getCategoryName());
        viewHolder.tvEventNfcPlan.setText(models.get(i).getEventName());
        viewHolder.tvCreatetedDate.setText(models.get(i).getCreatedDate());
        viewHolder.tvCouponsPrice.setText(models.get(i).getCouponPrice());
        viewHolder.tvStatus.setText(models.get(i).getCuopnStatus());
          viewHolder.btnTransfer.setOnClickListener(v -> listener.ActivateId(models.get(i).getCouponCode(), "" + models.get(i).getCouponPrice()));
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_coupons_codes)
        TextView tvCouponsCodes;
        @BindView(R.id.tv_coupons_price)
        TextView tvCouponsPrice;
        @BindView(R.id.tv_category)
        TextView tvCategory;
        @BindView(R.id.tv_event_nfc_plan)
        TextView tvEventNfcPlan;
        @BindView(R.id.tv_createted_date)
        TextView tvCreatetedDate;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.btn_transfer)
        TextView btnTransfer;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


        }
    }
}
