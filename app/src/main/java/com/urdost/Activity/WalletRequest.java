package com.urdost.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.urdost.R;
import com.urdost.app.PreferencesManager;
import com.urdost.common.FileUtils;
import com.urdost.common.LoggerUtil;
import com.urdost.common.NetworkUtils;
import com.urdost.common.Utils;
import com.urdost.constants.BaseActivity;
import com.urdost.model.ResponseStatusMessage;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.enums.EPickType;
import com.vansuita.pickimage.listeners.IPickCancel;
import com.vansuita.pickimage.listeners.IPickResult;

import java.io.File;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WalletRequest extends BaseActivity implements IPickCancel, IPickResult {

    @BindView(R.id.radio_neft)
    RadioButton radioNeft;
    @BindView(R.id.radio_imps)
    RadioButton radioImps;
    @BindView(R.id.radio_rtgs)
    RadioButton radioRtgs;
    @BindView(R.id.radio_cheque)
    RadioButton radioCheque;

    String payMode = "";
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.etCompanyName)
    TextView etCompanyName;
    @BindView(R.id.tv_acco_name)
    TextView tvAccoName;
    @BindView(R.id.tv_acco_number)
    TextView tvAccoNumber;
    @BindView(R.id.tv_ifsc)
    TextView tvIfsc;
    @BindView(R.id.tv_bank_name)
    TextView tvBankName;
    @BindView(R.id.tv_branch)
    TextView tvBranch;
    @BindView(R.id.et_RequestAmount)
    TextInputEditText etRequestAmount;
    @BindView(R.id.radioGroup_payment)
    RadioGroup radioGroupPayment;
    @BindView(R.id.etPaymentDate)
    TextInputEditText etPaymentDate;
    @BindView(R.id.et_TransactionNumber)
    TextInputEditText etTransactionNumber;
    @BindView(R.id.et_bank_name)
    TextInputEditText etBankName;
    @BindView(R.id.et_branch)
    TextInputEditText etBranch;
    @BindView(R.id.documentPreview)
    ImageView documentPreview;
    @BindView(R.id.til_transdate)
    TextInputLayout tilTransdate;
    @BindView(R.id.til_transnumber)
    TextInputLayout tilTransnumber;

    PickImageDialog dialog;
    File documentFile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet_request);
        ButterKnife.bind(this);

        tvTitle.setText("Wallet Request");

        radioNeft.setOnClickListener(view -> {
            payMode = "NEFT";
            radioNeft.setChecked(true);
            radioImps.setChecked(false);
            radioRtgs.setChecked(false);
            radioCheque.setChecked(false);
            tilTransdate.setHint("Transaction Date");
            tilTransnumber.setHint("Transaction Number");
        });
        radioImps.setOnClickListener(view -> {
            payMode = "IMPS";
            radioNeft.setChecked(false);
            radioImps.setChecked(true);
            radioRtgs.setChecked(false);
            radioCheque.setChecked(false);
            tilTransdate.setHint("Transaction Date");
            tilTransnumber.setHint("Transaction Number");
        });
        radioRtgs.setOnClickListener(view -> {
            payMode = "RTGS";
            radioNeft.setChecked(false);
            radioImps.setChecked(false);
            radioRtgs.setChecked(true);
            radioCheque.setChecked(false);
            tilTransdate.setHint("Transaction Date");
            tilTransnumber.setHint("Transaction Number");
        });
        radioCheque.setOnClickListener(view -> {
            payMode = "CHEQUE";
            radioNeft.setChecked(false);
            radioImps.setChecked(false);
            radioRtgs.setChecked(false);
            radioCheque.setChecked(true);
            tilTransdate.setHint("Cheque Date");
            tilTransnumber.setHint("Cheque Number");
        });
    }

    @OnClick({R.id.img_back, R.id.selectDocument, R.id.etPaymentDate, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.selectDocument:
                showDialog();
                break;
            case R.id.btn_submit:
                if (validation()) {
                    if (NetworkUtils.getConnectivityStatus(context) != 0) {
                        try {
                            if (documentFile != null)
                                uploadBagRequestFile(documentFile);
                            else
                                showMessage("Please attach any respective image of your offline payment.");
                        } catch (Error | Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            case R.id.etPaymentDate:
                datePicker(etPaymentDate);
                break;
        }
    }

    private void datePicker(final EditText et) {
        Calendar cal = Calendar.getInstance();
        int mYear, mMonth, mDay;
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context, R.style.DatePickerDialogTheme, (view, year, monthOfYear, dayOfMonth) -> {
            et.setText(Utils.changeDateFormatSlash(dayOfMonth, monthOfYear, year));

        }, mYear, mMonth, mDay);

        datePickerDialog.getDatePicker().setMaxDate(cal.getTimeInMillis());
        datePickerDialog.show();
    }

    void showDialog() {
        PickSetup pickSetup = new PickSetup();
        pickSetup.setTitle("Choose Document");
        pickSetup.setPickTypes(EPickType.GALLERY, EPickType.CAMERA);

        pickSetup.setGalleryIcon(R.mipmap.gallery_colored);
        pickSetup.setCameraIcon(com.vansuita.pickimage.R.mipmap.camera_colored);
        pickSetup.setCancelTextColor(R.color.colorAccent);
        dialog = PickImageDialog.build(pickSetup);
        //dialog = PickImageDialog.build(pickSetup);

        dialog.setOnPickCancel(this);
        dialog.show(getSupportFragmentManager());
    }

    @Override
    public void onPickResult(PickResult pickResult) {
        //Log.e("RESULT", " = " + pickResult.getPath());
        if (pickResult.getError() == null) {
            CropImage.activity(pickResult.getUri())
                    .setCropShape(CropImageView.CropShape.RECTANGLE)
                    .setAspectRatio(16, 9)
                    .start(context);
        } else {
            //Log.e("RESULT", "ERROR = " + pickResult.getError());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                documentFile = FileUtils.getFile(context, result.getUri());
                Bitmap bitmap = Utils.getCompressedBitmap(documentFile.getAbsolutePath());
                documentPreview.setImageBitmap(bitmap);
                documentPreview.setVisibility(View.VISIBLE);
                //Log.e("Document File ", documentFile.toString());
            }
        }
    }

    @Override
    public void onCancelClick() {
    }

    private boolean validation() {
        if (etRequestAmount.getText().toString().equalsIgnoreCase("")) {
            etRequestAmount.setError("Request amount cannot be empty.");
            requestFocus(etRequestAmount);
            return false;
        } else if (payMode.equalsIgnoreCase("")) {
            showMessage("Please choose any payment mode.");
            return false;
        } else if (etPaymentDate.getText().toString().equalsIgnoreCase("")) {
            showMessage("Please select payment date");
            return false;
        } else if (etTransactionNumber.getText().toString().equalsIgnoreCase("")) {
            showMessage("Please enter transaction number");
            requestFocus(etTransactionNumber);
            return false;
        } else if (etTransactionNumber.getText().toString().length() < 6) {
            showMessage("Invalid transaction number");
            requestFocus(etTransactionNumber);
            return false;
        } else if (etBankName.getText().toString().length() < 3) {
            showMessage("Enter Bank Name");
            requestFocus(etBankName);
            return false;
        } else if (etBranch.getText().toString().length() < 3) {
            showMessage("Enter Branch Name");
            requestFocus(etBranch);
            return false;
        } else if (documentFile == null) {
            showMessage("Please select file..");
            return false;
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private void uploadBagRequestFile(File file) {
        try {
            showLoading();
            //creating request body for Profilefile
            RequestBody requestBody = null;
            if (file != null)
                requestBody = RequestBody.create(MediaType.parse("image/*"), file);
            // TODO CHANGE ACTION AS PER REQUIREMENT
            RequestBody userId = RequestBody.create(MediaType.parse("text/plain"), PreferencesManager.getInstance(context).getUserid());
            RequestBody PaymentMode = RequestBody.create(MediaType.parse("text/plain"), payMode);
            RequestBody TransactionNo = RequestBody.create(MediaType.parse("text/plain"), etTransactionNumber.getText().toString().trim());
            RequestBody TransactionDate = RequestBody.create(MediaType.parse("text/plain"), etPaymentDate.getText().toString().trim());
            RequestBody Amount = RequestBody.create(MediaType.parse("text/plain"), etRequestAmount.getText().toString().trim());
            RequestBody BankName = RequestBody.create(MediaType.parse("text/plain"), etBankName.getText().toString().trim());
            RequestBody BranchName = RequestBody.create(MediaType.parse("text/plain"), etBranch.getText().toString().trim());
//            RequestBody uniquenoBody = RequestBody.create(MediaType.parse("text/plain"), reqId);
            MultipartBody.Part body = MultipartBody.Part.createFormData("DocumentImg", file.getName(), requestBody);

//            creating a call and calling the upload image method
            Call<ResponseStatusMessage> call = apiServices.uploadOfflineRequest(userId, PaymentMode, TransactionNo, TransactionDate, Amount, BankName, BranchName, body);
//            finally performing the call
            call.enqueue(new Callback<ResponseStatusMessage>() {
                @Override
                public void onResponse(@NonNull Call<ResponseStatusMessage> call, @NonNull Response<ResponseStatusMessage> response) {
                    LoggerUtil.logItem(response.body());
                    hideLoading();
                    if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                        showMessage("Request Added Successfully");
                        onBackPressed();
                    } else showMessage(response.body().getMessage());
                }

                @Override
                public void onFailure(@NonNull Call<ResponseStatusMessage> call, @NonNull Throwable t) {
                    Log.e("***********", call.request().url().toString());
                    Log.e("***********", "= " + t.getMessage());
                    showMessage(t.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
