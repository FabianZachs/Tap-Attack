package com.thezs.fabianzachs.tapattack;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.util.Log;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

/**
 * Created by fabianzachs on 14/08/18.
 */

public class MyRewardVideoAd implements RewardedVideoAdListener {

    ImageView videoImage;
    RewardedVideoAd videoAd;
    Context context;
    SharedPreferences prefs;

    public MyRewardVideoAd(Context context, SharedPreferences prefs, ImageView videoImage) {
        this.context = context;
        this.prefs = prefs;
        this.videoImage = videoImage;
        loadAd();

        videoImage.setImageResource(R.drawable.playvideobutton);
        ColorFilter filter = new PorterDuffColorFilter(context.getResources().getColor(R.color.item_holder), PorterDuff.Mode.SRC_IN);
        videoImage.setColorFilter(filter);
    }

    public void playVideoAd() {
        if (videoAd.isLoaded())
            videoAd.show();
    }

    private void loadAd() {
        videoAd = MobileAds.getRewardedVideoAdInstance(context);
        videoAd.setRewardedVideoAdListener(this);
        videoAd.loadAd("ca-app-pub-3940256099942544/5224354917", new AdRequest.Builder().build());

    }

    @Override
    public void onRewardedVideoAdLoaded() {
        // todo need reference to video image to turn white on loaded
        ColorFilter filter = new PorterDuffColorFilter(context.getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);
        videoImage.setColorFilter(filter);
        //videoAd.show();

    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {
        ColorFilter filter = new PorterDuffColorFilter(context.getResources().getColor(R.color.item_holder), PorterDuff.Mode.SRC_IN);
        videoImage.setColorFilter(filter);
        loadAd();

    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        int currentPoints = prefs.getInt("points", 0);
        SharedPreferences.Editor prefsEditor = prefs.edit();

        prefsEditor.putInt("points", currentPoints + Constants.videoAdReward);
        prefsEditor.apply();

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }
}
