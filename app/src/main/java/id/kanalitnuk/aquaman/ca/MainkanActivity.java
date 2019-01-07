package id.kanalitnuk.aquaman.ca;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import id.kanalitnuk.aquaman.R;

import static id.kanalitnuk.aquaman.ca.Preferences.AD_APP_ID;
import static id.kanalitnuk.aquaman.ca.Preferences.BANNER_ID1;


public class MainkanActivity extends AppCompatActivity {

    private String delay_banner, delay_inter;
    private AdView adView1;
    private AdView adView2;

    private Preferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ca_activity_main);
        prefs = new Preferences(getApplicationContext());

        MobileAds.initialize(this, AD_APP_ID);
        final EditText edit_ban = findViewById(R.id.edit_ban);
        final EditText edit_inter = findViewById(R.id.edit_inter);
        final EditText edit_id_ban = findViewById(R.id.edit_id_ban);
        final EditText edit_id_inter = findViewById(R.id.edit_id_inter);
        final EditText edit_limit = findViewById(R.id.edit_limit);
        final RelativeLayout rl_main = findViewById(R.id.rl_main);
        String a = prefs.getBannerID();
        String b = prefs.getInterID();
        String c = prefs.getLimit();
        String d = prefs.getdelayban();
        String e = prefs.getdelayin();
        if (a.isEmpty() || a.equals("")) {
            edit_id_ban.setText(getString(R.string.BANNER_ID_1));
            edit_id_inter.setText(R.string.INTER_ID_2);
            edit_limit.setText(R.string.limit);
            edit_ban.setText(R.string.delay_ban);
            edit_inter.setText(R.string.delay_in);
        } else {
            edit_id_ban.setText(a);
            edit_id_inter.setText(b);
            edit_limit.setText(c);
            edit_ban.setText(d);
            edit_inter.setText(e);
        }

        Intent intent = getIntent();
        int aa = intent.getIntExtra("counter", 0);

        AdView mAdView = new AdView(this);
        mAdView.setAdSize(AdSize.SMART_BANNER);
        mAdView.setAdUnitId(BANNER_ID1);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("3F23B17195E76665CE257C017D63D531").build();
        mAdView.loadAd(adRequest);



        LimitDialog limitDialog = new LimitDialog(MainkanActivity.this);

        String limit = prefs.getLimit();
        if (limit.equals("")) {
            limit = "0";
        }
        int l = Integer.parseInt(limit);
       if ( l>1 && ((l == aa) || (aa >= l))) {
            limitDialog.show();
        }

        Button but = findViewById(R.id.button_start);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefs.saveBannerID(edit_id_ban.getText().toString());
                prefs.saveInterID(edit_id_inter.getText().toString());
                prefs.saveLimit(edit_limit.getText().toString());
                prefs.saveDelay(edit_ban.getText().toString(), edit_inter.getText().toString());
                prefs.resetClickCounter();
                Intent i = new Intent(MainkanActivity.this, BannerActivity.class);
                delay_banner = edit_ban.getText().toString();
                delay_inter = edit_inter.getText().toString();
                i.putExtra("delay_banner", delay_banner);
                i.putExtra("delay_inter", delay_inter);
                startActivity(i);
            }
        });


    }

}
