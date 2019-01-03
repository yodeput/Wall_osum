package id.kanalitnuk.spiderman.others;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import id.kanalitnuk.spiderman.R;

public class KanalitnuK extends Application {

    private static KanalitnuK mInstance;
    private Preferences prefManager;
    private InterstitialAd mInterstitialAd;
    public static synchronized KanalitnuK getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        prefManager = new Preferences(this.getApplicationContext());
        MobileAds.initialize(this, getString(R.string.ADMOB_APP_ID));
        mInstance = this;
    }



    public void init_InterstitialAd(Context context){
        MobileAds.initialize(context, getString(R.string.ADMOB_APP_ID));
        mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId(getString(R.string.Interstitial_AD_ID));
        mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
    }

    public void add_click(){
      prefManager.setClickCounter();
    }

    public void check_click(){
        int counter = prefManager.getClickCounter();
        Log.e("Click Counter ---->",Integer.toString(counter));
        if (counter>6){
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
                prefManager.resetClickCounter();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }

        }
    }

    public void InterstitialAd(Context context){
        init_InterstitialAd(context);
        add_click();
        check_click();
    }

}
