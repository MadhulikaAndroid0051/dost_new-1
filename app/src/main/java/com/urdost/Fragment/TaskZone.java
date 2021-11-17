package com.urdost.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.urdost.Activity.NewContainerActivity;
import com.urdost.R;
import com.urdost.common.LoggerUtil;
import com.urdost.constants.BaseFragment;
import com.urdost.model.response.ResponseGetSponseStatus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskZone extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.cv_commission)
    CardView cvCommission;
    @BindView(R.id.cv_onging)
    CardView cvOnging;
    @BindView(R.id.cv_completed)
    CardView cvCompleted;
    @BindView(R.id.cv_hold)
    CardView cvHold;
     Dialog joinProgramDialog;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.task_zone, container, false);
        unbinder = ButterKnife.bind(this, view);


        return view;
    }

    @OnClick({R.id.cv_commission, R.id.cv_onging, R.id.cv_completed, R.id.cv_hold})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cv_commission:
                showMessage("Coming Soon");
                break;
            case R.id.cv_onging:
                joinProgramDialog();

                break;
            case R.id.cv_completed:
                showMessage("Coming Soon");

                break;
            case R.id.cv_hold:
                showMessage("Coming Soon");

                break;
        }
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                goToActivityWithFinish(NewContainerActivity.class, null);

                // Handle the back button event
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

    }
    void joinProgramDialog() {
        joinProgramDialog = new Dialog(context);
        joinProgramDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        joinProgramDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        joinProgramDialog.setContentView(R.layout.dialog_new_task);

        Button btn_cancel = joinProgramDialog.findViewById(R.id.btn_cancel);

        btn_cancel.setOnClickListener(v -> joinProgramDialog.cancel());
    }


    }
