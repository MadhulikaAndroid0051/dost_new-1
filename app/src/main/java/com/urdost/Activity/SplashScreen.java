package com.urdost.Activity;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.urdost.R;
import com.urdost.app.PreferencesManager;
import com.urdost.common.LoggerUtil;
import com.urdost.common.NetworkUtils;
import com.urdost.constants.BaseActivity;
import com.github.javiersantos.appupdater.AppUpdater;
import com.github.javiersantos.appupdater.AppUpdaterUtils;
import com.github.javiersantos.appupdater.enums.AppUpdaterError;
import com.github.javiersantos.appupdater.enums.Display;
import com.github.javiersantos.appupdater.enums.UpdateFrom;
import com.github.javiersantos.appupdater.objects.Update;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.scottyab.rootbeer.RootBeer;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashScreen extends BaseActivity {

    @BindView(R.id.imageView)
    ImageView imageView;
    public String referId;
    @BindView(R.id.tv_version)
    TextView tvVersion;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        ButterKnife.bind(this);


        Animation aniSlide = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
        imageView.startAnimation(aniSlide);
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            String version = pInfo.versionName;
            tvVersion.setText(getString(R.string.version) + version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        FirebaseDynamicLinks.getInstance()
                .getDynamicLink(getIntent())
                .addOnSuccessListener(this, pendingDynamicLinkData -> {
                    // Get deep link from result (may be null if no link is found)
                    Uri deepLink = null;
                    if (pendingDynamicLinkData != null) {
                        deepLink = pendingDynamicLinkData.getLink();
                        LoggerUtil.logItem(deepLink.toString());
                        String link = deepLink.toString();
                        if (link.contains("referId")) {
                            link = link.substring(link.lastIndexOf("=") + 1);
                            referId = link;


                            //Toast.makeText(context, referId+"", Toast.LENGTH_SHORT).show();
                            LoggerUtil.logItem(referId);
                        }
//                        else if (link.contains("invitedby")) {
//                            link = link.substring(link.lastIndexOf("=") + 1);
//                            inviteCode = link;
//                            LoggerUtil.logItem(inviteCode);
//                        }
                    }
                });
        new Handler().postDelayed(() -> {
            /*if (checkForSuBinary() || checkForBusyBoxBinary() || checkSuExists()) {
                createExitDialog(context, "Rooted Device", "This device is rooted. You can't use this app.", true);
            } else*/
                {
                if (NetworkUtils.getConnectivityStatus(context) != 0) {
                    checkUpdate();
                } else {
                    showMessage(getString(R.string.alert_internet));
                }
            }
        }, 1500);
    }

    public void createExitDialog(Context context, String title,
                                 String msg, boolean isBack) {
        androidx.appcompat.app.AlertDialog.Builder builder1 = new androidx.appcompat.app.AlertDialog.Builder(context);
        builder1.setTitle(title);
        builder1.setMessage(msg);
        builder1.setCancelable(true);

        builder1.setPositiveButton("OK", (dialog, which) -> {
            if (isBack)
                finishAffinity();
            dialog.cancel();
        });

//        builder1.setNegativeButton(
//                "Cancel",
//                (dialog, id) -> dialog.cancel());

        androidx.appcompat.app.AlertDialog alert11 = builder1.create();
        alert11.show();
    }




    private AppUpdater updater;

    private void checkUpdate() {
        AppUpdaterUtils appUpdaterUtils = new AppUpdaterUtils(this)
                .setUpdateFrom(UpdateFrom.GOOGLE_PLAY)
                .withListener(new AppUpdaterUtils.UpdateListener() {
                    @Override
                    public void onSuccess(Update update, Boolean isUpdateAvailable) {
                        Log.d("Latest Version", update.getLatestVersion());
                        Log.d("Latest Version Code", "=" + update.getLatestVersionCode());
                        Log.d("Release notes", update.getReleaseNotes());
                        Log.d("URL", "=" + update.getUrlToDownload());
                        Log.d("Is update available?", Boolean.toString(isUpdateAvailable));
                        if (isUpdateAvailable) {
                            if (updater == null) {
                                updater = new AppUpdater(context).setDisplay(Display.DIALOG);
                                updater.setContentOnUpdateAvailable("Update " + update.getLatestVersion() + " is available to download. Update the latest version of Dost to get latest " +
                                        "features, improvements and bug fixes.");
                                updater.setButtonDoNotShowAgain("");
                                updater.setButtonDismiss("");
                                updater.setCancelable(false);
                            }
                            updater.start();
                        }else
                        {
                            try {
                                PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                                String version = pInfo.versionName;
                                // tvVersion.setText(getString(R.string.version) + version);
                            } catch (PackageManager.NameNotFoundException e) {
                                e.printStackTrace();
                            }

                            new Handler().postDelayed(() -> {

                                // bundle.putString("Id", referId);
                             //  Toast.makeText(context,referId+ "", Toast.LENGTH_SHORT).show();
                                if (PreferencesManager.getInstance(context).getIsFirstTimeLaunch()) {
                                    bundle = new Bundle();
                                    bundle.putString("Id",referId);
                                    //Toast.makeText(context, referId+"", Toast.LENGTH_SHORT).show();
                                    goToActivityWithFinish(context, WelcomeIntro.class, bundle);

                                }
                                else {
                                    if (PreferencesManager.getInstance(context).getUserid().equalsIgnoreCase("")) {

                                      // Toast.makeText(context, referId+"", Toast.LENGTH_SHORT).show();
                                        bundle = new Bundle();
                                       // bundle.getString("Id",referId);
                                        bundle.putString("Id",referId);

                                        goToActivityWithFinish(context, LoginActivity.class, bundle);
                                      //  Bundle bundle = new Bundle();
                                       // bundle.putString("sponserId", referId);

                                    } else {
                                        goToActivityWithFinish(context, NewContainerActivity.class, bundle);
                                    }
                                }
                            }, 2500);
                        }
                    }

                    @Override
                    public void onFailed(AppUpdaterError error) {
                        Log.d("AppUpdater Error", "Something went wrong");
                    }
                });
        appUpdaterUtils.start();
    }

    private boolean isRooted() {
        boolean isRooted = false;
        RootBeer rootBeer = new RootBeer(context);
        if (rootBeer.isRooted()) {
            //we found indication of root
            isRooted = true;
        } else {
            isRooted = false;
            //we didn't find indication of root
        }
        return isRooted;
    }

    private boolean checkForSuBinary() {
        return checkForBinary("su"); // function is available below
    }

    private boolean checkForBusyBoxBinary() {
        return checkForBinary("busybox");//function is available below
    }

    private String[] binaryPaths = {
            "/data/local/",
            "/data/local/bin/",
            "/data/local/xbin/",
            "/sbin/",
            "/su/bin/",
            "/system/bin/",
            "/system/bin/.ext/",
            "/system/bin/failsafe/",
            "/system/sd/xbin/",
            "/system/usr/we-need-root/",
            "/system/xbin/",
            "/system/app/Superuser.apk",
            "/cache",
            "/data",
            "/dev"
    };

    private boolean checkForBinary(String filename) {
        for (String path : binaryPaths) {
            File f = new File(path, filename);
            boolean fileExists = f.exists();
            if (fileExists) {
                return true;
            }
        }
        return false;
    }

    private boolean checkSuExists() {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(new String[]
                    {"/system/xbin/which", "su"});
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String line = in.readLine();
            process.destroy();
            return line != null;
        } catch (Exception e) {
            if (process != null) {
                process.destroy();
            }
            return false;
        }
    }
}
