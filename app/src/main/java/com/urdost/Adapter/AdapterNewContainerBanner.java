package com.urdost.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.urdost.R;
import com.urdost.model.response.responseMainDashboard.LstBannerItem;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class AdapterNewContainerBanner extends SliderViewAdapter<AdapterNewContainerBanner.SliderAdapterVH> {

    private Context context;
    private List<LstBannerItem> mSliderItems;

    public AdapterNewContainerBanner(Context context, List<LstBannerItem> mSliderItems) {
        this.context = context;
        this.mSliderItems = mSliderItems;
    }

//    public void renewItems(List<SliderItem> sliderItems) {
//        this.mSliderItems = sliderItems;
//        notifyDataSetChanged();
//    }
//
//    public void deleteItem(int position) {
//        this.mSliderItems.remove(position);
//        notifyDataSetChanged();
//    }
//
//    public void addItem(SliderItem sliderItem) {
//        this.mSliderItems.add(sliderItem);
//        notifyDataSetChanged();
//    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_banner, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {
        Glide.with(viewHolder.itemView)
                .load(mSliderItems.get(position).getBannerImage())
                .fitCenter()
                .into(viewHolder.img_banner);
    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mSliderItems.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {
        ImageView img_banner;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            img_banner = itemView.findViewById(R.id.img_banner);
        }
    }
}
