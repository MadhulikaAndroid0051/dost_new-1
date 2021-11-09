package com.urdost.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.gson.JsonObject;
import com.urdost.R;
import com.urdost.app.PreferencesManager;
import com.urdost.common.LoggerUtil;
import com.urdost.constants.BaseFragment;
import com.urdost.model.response.ResponseAadharData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AadharCardBack extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.tv_adahrback)
    TextView tvAdahrback;
    @BindView(R.id.tv_aadhar_back_eng)
    TextView tvAadharBackEng;
    @BindView(R.id.tv_back_adhar_name)
    TextView tvBackAdharName;
    @BindView(R.id.tv_back_adhar_name_eng)
    TextView tvBackAdharNameEng;
    @BindView(R.id.tv_adhar_back_number)
    TextView tvAdharBackNumber;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.aadhar_back, container, false);
        unbinder = ButterKnife.bind(this, view);
        getData();
        return view;
    }
    private void getData() {
        // showLoading();

        JsonObject object = new JsonObject();

        object.addProperty("Fk_UserId", PreferencesManager.getInstance(context).getPk_userId());
        Call<ResponseAadharData> call = apiServices.getAadharData(object);
        call.enqueue(new Callback<ResponseAadharData>() {
            @Override
            public void onResponse(Call<ResponseAadharData> call, Response<ResponseAadharData> response) {
                //  hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    tvBackAdharNameEng.setText(""+response.body().getFatherName()+","+response.body().getAddress()+","+response.body().getPinCode());
                    tvBackAdharName.setText(response.body().getFatherNameH()+","+response.body().getAddressH()+","+response.body().getPinCode());
                    tvAdharBackNumber.setText(response.body().getAdharNo());
                   // tvAdahrback.setText(response.body().get);
                   // tvAddressAdhar.setText("जन्म तिथि/DOB:"+" "+response.body().getDob());
                   // tvBarthAdhar.setText("Gender"+""+response.body().getGender());
                   // tvAdharNumber.setText(response.body().getAdharCardNo());
                  //  aadahrpk_id=response.body().getPKCardId();
                  //  Glide.with(context).load(response.body().getPhoto()).into(imgAdharProfile);
                  //  Glide.with(context).load(response.body().getQrImage()).into(imgeAdhar);

                    //  tvBarth.setText(response.body().getD);

                }
            }

            @Override
            public void onFailure(Call<ResponseAadharData> call, Throwable t) {
                // hideLoading();
            }
        });

    }

}
