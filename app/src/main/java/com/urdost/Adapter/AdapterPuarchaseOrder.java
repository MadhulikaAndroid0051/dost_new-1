package com.urdost.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.urdost.R;
import com.urdost.interfaces.NfcDeletId;
import com.urdost.model.response.PurchaseOrder.LstPurchaseOrder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterPuarchaseOrder extends RecyclerView.Adapter<AdapterPuarchaseOrder.ViewHolder> {

    private List<LstPurchaseOrder> models;
    private Context context;
    private NfcDeletId puracheshId;

    public AdapterPuarchaseOrder(List<LstPurchaseOrder> models, Context context, NfcDeletId puracheshId) {
        this.models = models;
        this.context = context;
        this.puracheshId = puracheshId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_purchase_order, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

      //  Glide.with(context).load("https://dost.click/" + models.get(i).getImage()).into(viewHolder.imageView);
        viewHolder.cardName.setText(models.get(i).getEventName());
        viewHolder.tvDiscription.setText(models.get(i).getDescription());
        viewHolder.tvInvoiceNo.setText( "InvoiceNo" +" "+models.get(i).getInvoiceNo());
        viewHolder.tvDate.setText("Date : " + models.get(i).getActivationDate());



        viewHolder.btnView.setOnClickListener(v -> puracheshId.getDeleatId(models.get(i).getInvoiceNo()));

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.image_view)
        ImageView imageView;
        @BindView(R.id.card_name)
        TextView cardName;
        @BindView(R.id.tv_discription)
        TextView tvDiscription;
        @BindView(R.id.tv_invoice_no)
        TextView tvInvoiceNo;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.btn_view)
        Button btnView;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


        }
    }
}
