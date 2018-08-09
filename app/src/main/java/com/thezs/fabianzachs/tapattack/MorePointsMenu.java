package com.thezs.fabianzachs.tapattack;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

/**
 * Created by fabianzachs on 08/08/18.
 */

public class MorePointsMenu implements RewardedVideoAdListener {

    private SharedPreferences prefs;
    private Activity mainMenuActivity;
    private RewardedVideoAd pointsAd;

    private View morePointsSectionAlertView;
    final private AlertDialog morePointsSectionDialog;

    public MorePointsMenu(Activity mainMenuActivity, SharedPreferences prefs) {
        this.mainMenuActivity = mainMenuActivity;
        this.prefs = prefs;

        requestNewVidForPointsAd();

        this.morePointsSectionAlertView = helper.getAlertView(mainMenuActivity, R.layout.more_points_menu);
        this.morePointsSectionDialog = helper.getBuiltDialog(mainMenuActivity, morePointsSectionAlertView);
        helper.setupStandardDialogDismissButton(R.id.ok_button,morePointsSectionAlertView, morePointsSectionDialog);

        setupLockImage();

    }

    private void setupLockImage() {
        if (prefs.getInt("multiplierInt",0) == 2) {
            ImageView lock = (ImageView) morePointsSectionAlertView.findViewById(R.id.locked_image);
            lock.setImageResource(android.R.color.transparent);
        }
    }

    public Dialog getMainMorePointsDialog() {
        return morePointsSectionDialog;
    }

    public void pointsSectionClick() {
        helper.dialogFullscreen(mainMenuActivity, morePointsSectionDialog);
    }

    public void extraPointsViaVidClick() {
        if (pointsAd.isLoaded()) {
            pointsAd.show();
            //requestNewVidForPointsAd(); // todo have a max of 3 an hour
        }
    }

    public void doublePointsPurchaseClick() {
        // todo launch in app purchase -- if successful set points multiplier to 2 in shared prefs

        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putInt("multiplierInt",2);
        prefsEditor.apply();
        setupLockImage();
    }

    private void requestNewVidForPointsAd() {

        pointsAd = MobileAds.getRewardedVideoAdInstance(mainMenuActivity);
        pointsAd.setRewardedVideoAdListener(this);
        pointsAd.loadAd("ca-app-pub-3940256099942544/5224354917", new AdRequest.Builder().build());
    }








    @Override
    public void onRewardedVideoAdLoaded() {
        //StyleableToast.makeText(Constants.CURRENT_CONTEXT, "PLAY VIDEO2", R.style.successtoast).show();

    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {
        StyleableToast.makeText(Constants.CURRENT_CONTEXT, "closed", R.style.successtoast).show();
        requestNewVidForPointsAd(); // todo have a max of 3 an hour

    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        SharedPreferences.Editor prefsEditor = prefs.edit();

        prefsEditor.putInt("points", 500 + prefs.getInt("points",0));
        prefsEditor.apply();

        ((MainMenuActivity) mainMenuActivity).updatePoints();
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

        StyleableToast.makeText(Constants.CURRENT_CONTEXT, "leftApp", R.style.successtoast).show();
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }
}
