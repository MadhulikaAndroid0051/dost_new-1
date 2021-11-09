package com.urdost.Activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.urdost.R;
import com.urdost.app.PreferencesManager;
import com.urdost.constants.BaseActivity;
import com.google.android.gms.tasks.Task;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.ShortDynamicLink;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReferEarn extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_refer_id)
    TextView tvReferId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer_earn);
        ButterKnife.bind(this);

        tvReferId.setText("Referral Code: " + PreferencesManager.getInstance(context).getUserCode());
        tvTitle.setText("Invite People");
    }

    @OnClick({R.id.img_back, R.id.cv_share, R.id.tv_refer_id})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.cv_share:
                showLoading();
                createShareLink();
                break;
            case R.id.tv_refer_id:
                ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Refer Code ", PreferencesManager.getInstance(context).getUserid());
                clipboard.setPrimaryClip(clip);
                showMessage("Referral Code Copied");
                break;
        }
    }

    //    private Firebase
    private void createShareLink() {
        Log.e("main", "create link ");
        DynamicLink dynamicLink = FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLink(Uri.parse("https://www.blueappsoftware.com/"))
                .setDomainUriPrefix("urdost.page.link")
                // Open links with this app on Android
                .setAndroidParameters(new DynamicLink.AndroidParameters.Builder().build())
                // Open links with com.example.ios on iOS
                //.setIosParameters(new DynamicLink.IosParameters.Builder("com.example.ios").build())
                .buildDynamicLink();
//click -- link -- google play store -- inistalled/ or not  ----
        Uri dynamicLinkUri = dynamicLink.getUri();
        Log.e("main", "  Long refer " + dynamicLink.getUri());
        //   https://referearnpro.page.link?apn=blueappsoftware.referearnpro&link=https%3A%2F%2Fwww.blueappsoftware.com%2F
        // apn  ibi link
        // manuall link
        // https://play.google.com/store/apps/details?id=dost.com
        String msg = "Hi, \nDownload DOST App NOW !"+"\n"+
                "1. Start working as a Digital Marketer."+"\n" +"2. Start working as a Reseller."+"\n"+"3. Get certified & participate in big projects."+"\n"+"4. Start managing contactless Exchanges."+"\n"+"5. Go Limitless, Go Digital & Go Green.\n\n"+
                "Join the momentum;\nUse Referral Code : "
                + PreferencesManager.getInstance(context).getUserCode() + "\n\nDownload link;\n";
        String sharelinktext = "https://urdost.page.link/?" +
                "link=https://urdost.in/referId=" + PreferencesManager.getInstance(context).getUserCode() +
                "&apn=" + getPackageName();
        // shorten the link
        Task<ShortDynamicLink> shortLinkTask = FirebaseDynamicLinks.getInstance().createDynamicLink()
                //.setLongLink(dynamicLink.getUri())
                .setLongLink(Uri.parse(sharelinktext))  // manually
                .buildShortDynamicLink()
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        hideLoading();
                        // Short link created
                        Uri shortLink = task.getResult().getShortLink();
                        Uri flowchartLink = task.getResult().getPreviewLink();
                        Log.e("main ", "short link " + msg + shortLink.toString());
                        // share app dialog
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_SEND);
                        intent.putExtra(Intent.EXTRA_TEXT, msg + shortLink.toString());
                        intent.setType("text/plain");
                        startActivity(intent);
                        Toast.makeText(context, task+"", Toast.LENGTH_SHORT).show();
                    } else {
                        // Error
                        // .
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                        hideLoading();
                        Log.e("main", " error " + task.getException());
                    }
                });
    }
}
