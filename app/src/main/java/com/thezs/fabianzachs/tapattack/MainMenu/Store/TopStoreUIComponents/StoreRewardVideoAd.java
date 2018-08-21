package com.thezs.fabianzachs.tapattack.MainMenu.Store.TopStoreUIComponents;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.thezs.fabianzachs.tapattack.ButtonOnTouchListener;
import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.R;

/**
 * Created by fabianzachs on 15/08/18.
 */

public class StoreRewardVideoAd implements RewardedVideoAdListener {
    // todo needs the image, and the entire Linear layout for the onlcik

    private RewardAdListener listener;
    private ImageView videoImage;
    private TextView rewardText;
    private RewardedVideoAd videoAd;
    private Activity activity;

    @SuppressLint("ClickableViewAccessibility")
    public StoreRewardVideoAd(Activity activity, View view) {
        this.activity = activity;
        this.videoImage = view.findViewById(R.id.reward_video_image);
        setupRewardText(view);
        videoImage.setImageResource(R.drawable.playvideobutton);
        setVideoAdColor(activity.getResources().getColor(R.color.item_holder));
        loadAd();

        LinearLayout rewardSection = view.findViewById(R.id.reward_video_ad_section);
        rewardSection.setOnTouchListener(new ButtonOnTouchListener(activity, rewardSection,
                new ButtonOnTouchListener.ButtonExecuteListener() {
            @Override
            public void buttonAction() {
                playVideoAd();
            }
        }));

    }

    private void setupRewardText(View view) {
        this.rewardText = view.findViewById(R.id.reward_text);
        rewardText.setText("+"+Constants.VIDEO_AD_REWARD);
    }


    public interface RewardAdListener {
        void videoAdCompleted(int earnedPoints);
    }

    public void setRewardAdListener(RewardAdListener listener) {
        this.listener = listener;
    }


    private void setVideoAdColor(int videoAdColor) {
        ColorFilter filter = new PorterDuffColorFilter(videoAdColor, PorterDuff.Mode.SRC_IN);
        videoImage.setColorFilter(filter);
    }

    private void loadAd() {
        videoAd = MobileAds.getRewardedVideoAdInstance(activity);
        videoAd.setRewardedVideoAdListener(this);
        videoAd.loadAd("ca-app-pub-3940256099942544/5224354917", new AdRequest.Builder().build());

    }

    private void playVideoAd() {
        if (videoAd.isLoaded())
            videoAd.show();
    }



    @Override
    public void onRewardedVideoAdLoaded() {
        setVideoAdColor(activity.getResources().getColor(android.R.color.white));

    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {
        setVideoAdColor(activity.getResources().getColor(R.color.item_holder));
        loadAd();

    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        listener.videoAdCompleted(Constants.VIDEO_AD_REWARD);

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }

}
