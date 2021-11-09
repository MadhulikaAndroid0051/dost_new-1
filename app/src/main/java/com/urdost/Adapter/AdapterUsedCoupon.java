package com.urdost.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.urdost.R;
import com.urdost.model.response.responseUsedCode.LstUsedCode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterUsedCoupon extends RecyclerView.Adapter<AdapterUsedCoupon.ViewHolder> {



    private List<LstUsedCode> models;
    private Context context;

    public AdapterUsedCoupon(List<LstUsedCode> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_used_coupon, viewGroup, false);
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
        viewHolder.tvRegistredTo.setText(models.get(i).getRegisteredTo());
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
        @BindView(R.id.tv_registered_To)
        TextView tvRegistredTo;
        @BindView(R.id.tv_status)
        TextView tvStatus;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


        }
    }
}
