package com.urdost.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.urdost.R;
import com.urdost.model.response.responseDiractComminsion.LstDirectCommission;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterDiractCommison extends RecyclerView.Adapter<AdapterDiractCommison.ViewHolder> {


  private List<LstDirectCommission> models;
  private Context context;

  public AdapterDiractCommison(List<LstDirectCommission> models, Context context) {
    this.models = models;
    this.context = context;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_diract_commision, viewGroup, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(ViewHolder viewHolder, int i) {
    viewHolder.tvLoginId.setText(models.get(i).getFromLoginId());
    viewHolder.tvFromName.setText(models.get(i).getFromUserName());
    viewHolder.tvDate.setText(models.get(i).getDate());
    viewHolder.tvAmount.setText(models.get(i).getAmount());
    viewHolder.tvIncrementType.setText(models.get(i).getIncomeType());


  }

  public void updatelist(List<LstDirectCommission> models) {
    this.models = models;
    notifyDataSetChanged();
  }

  @Override
  public int getItemCount() {
    return models.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_loginId)
    TextView tvLoginId;
    @BindView(R.id.tv_from_name)
    TextView tvFromName;
    @BindView(R.id.tv_Date)
    TextView tvDate;
    @BindView(R.id.tv_amount)
    TextView tvAmount;
    @BindView(R.id.tv_increment_type)
    TextView tvIncrementType;


    public ViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);


    }
  }
}
