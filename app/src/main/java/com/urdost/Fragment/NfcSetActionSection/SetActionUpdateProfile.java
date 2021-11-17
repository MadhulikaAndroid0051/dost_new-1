package com.urdost.Fragment.NfcSetActionSection;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.urdost.R;
import com.urdost.constants.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.urdost.app.AppConfig.PAYLOAD_BUNDLE;

public class SetActionUpdateProfile extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.cv_profile_name)
    CardView cvProfileName;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.img_member)
    ImageView imgMember;
    @BindView(R.id.cardview1)
    RelativeLayout cardview1;
    @BindView(R.id.tv_add_photo)
    TextView tvAddPhoto;
    @BindView(R.id.et_first_name)
    EditText etFirstName;
    @BindView(R.id.et_last_name)
    EditText etLastName;
    @BindView(R.id.et_company)
    EditText etCompany;
    @BindView(R.id.img_add_email)
    ImageView imgAddEmail;
    @BindView(R.id.tv_add_email)
    TextView tvAddEmail;
    @BindView(R.id.tv_email_list)
    TextView tvEmailList;
    @BindView(R.id.btn_add_email)
    Button btnAddEmail;
    @BindView(R.id.img_add_url)
    ImageView imgAddUrl;
    @BindView(R.id.tv_add_url)
    TextView tvAddUrl;
    @BindView(R.id.tv_select_url)
    TextView tvSelectUrl;
    @BindView(R.id.btn_add_url)
    Button btnAddUrl;
    @BindView(R.id.img_social)
    ImageView imgSocial;
    @BindView(R.id.tv_socail_url)
    TextView tvSocailUrl;
    @BindView(R.id.tv_select_social_url)
    TextView tvSelectSocialUrl;
    @BindView(R.id.btn_add_social)
    Button btnAddSocial;
    @BindView(R.id.img_contact)
    ImageView imgContact;
    @BindView(R.id.tv_contact)
    TextView tvContact;
    @BindView(R.id.tv_select_contact)
    TextView tvSelectContact;
    @BindView(R.id.btn_contact)
    Button btnContact;
    @BindView(R.id.et_notes)
    EditText etNotes;
    Bundle bundle;
    @BindView(R.id.profile_name)
    TextView profileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_action_edit_profile);
        ButterKnife.bind(this);
        bundle = getIntent().getBundleExtra(PAYLOAD_BUNDLE);
        profileName.setText(bundle.getString("ProfileName"));

        tvTitle.setText("NFC ZONE");
    }
}
