package id.kanalitnuk.aquaman.ca;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import id.kanalitnuk.aquaman.R;

import static id.kanalitnuk.aquaman.ca.Preferences.AD_APP_ID;


public class BannerActivity extends AppCompatActivity {
    private AdView mAdView;
    private AdView mAdView2;
    private AdView mAdView3;
    private AdView mAdView4;
    private AdView mAdView5;
    private AdView mAdView6;
    private String delay_banner, delay_inter;
    public static Activity banner;
    private Preferences prefs;
    private Handler handler = new Handler();
    private  Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ca_activity_banner);
        prefs = new Preferences(getApplicationContext());
        MobileAds.initialize(this, AD_APP_ID);
        banner=this;
        Intent intent = getIntent();
        delay_banner = intent.getStringExtra("delay_banner");
        delay_inter = intent.getStringExtra("delay_inter");

        prefs.setClickCounter();prefs.setClickCounter();prefs.setClickCounter();prefs.setClickCounter();prefs.setClickCounter();prefs.setClickCounter();
        String ad_id = prefs.getBannerID();
        String limit = prefs.getLimit();
        final int l = Integer.parseInt(limit);
        final int counter = prefs.getClickCounter();


        runnable = new Runnable(){
            @Override
            public void run() {
                Intent i = new Intent(BannerActivity.this, InterActivity.class);
                i.putExtra("delay_banner", delay_banner);
                i.putExtra("delay_inter", delay_inter);
                startActivity(i);
                BannerActivity.this.finish();
                BannerActivity.this.finishAffinity();
            }
        };
        handler.postDelayed(runnable, (long) (Integer.valueOf(Integer.parseInt(delay_banner) * 1000)));
        if ((l==counter)||(counter>=l)) {
            Intent i = new Intent(BannerActivity.this, MainkanActivity.class);
            i.putExtra("counter", counter);
            startActivity(i);
            InterActivity.inter.finish();
            InterActivity.inter.finishAffinity();
            BannerActivity.this.finish();
            BannerActivity.this.finishAffinity();
            prefs.resetClickCounter();
            handler.removeCallbacks(runnable);
        }

        Toast.makeText(BannerActivity.this,counter+" Ads has been shown",Toast.LENGTH_LONG).show();
        this.mAdView = new AdView(this);
        this.mAdView.setAdSize(AdSize.SMART_BANNER);
        this.mAdView.setAdUnitId(ad_id);
        this.mAdView2 = new AdView(this);
        this.mAdView2.setAdSize(AdSize.SMART_BANNER);
        this.mAdView2.setAdUnitId(ad_id);
        this.mAdView3 = new AdView(this);
        this.mAdView3.setAdSize(AdSize.SMART_BANNER);
        this.mAdView3.setAdUnitId(ad_id);
        this.mAdView4 = new AdView(this);
        this.mAdView4.setAdSize(AdSize.SMART_BANNER);
        this.mAdView4.setAdUnitId(ad_id);
        this.mAdView5 = new AdView(this);
        this.mAdView5.setAdSize(AdSize.SMART_BANNER);
        this.mAdView5.setAdUnitId(ad_id);
        this.mAdView6 = new AdView(this);
        this.mAdView6.setAdSize(AdSize.SMART_BANNER);
        this.mAdView6.setAdUnitId(ad_id);

        LinearLayout layout = findViewById(R.id.ll_ads);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundColor(Color.parseColor("#ffffff"));
        layout.setPadding(5,10,5,10);
        layout.addView(this.mAdView);
        layout.addView(this.mAdView2);
        layout.addView(this.mAdView3);
        layout.addView(this.mAdView4);
        layout.addView(this.mAdView5);
        layout.addView(this.mAdView6);

        AdRequest adRequest = new AdRequest.Builder().build();
        try {
            this.mAdView.loadAd(adRequest);
            this.mAdView2.loadAd(adRequest);
            this.mAdView3.loadAd(adRequest);
            this.mAdView4.loadAd(adRequest);
            this.mAdView5.loadAd(adRequest);
            this.mAdView6.loadAd(adRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    @Override
    public void onBackPressed() {
        handler.removeCallbacks(runnable);
        Intent i = new Intent(BannerActivity.this,MainkanActivity.class);
        startActivity(i);
        BannerActivity.this.finish();
        BannerActivity.this.finishAffinity();
    }

}
