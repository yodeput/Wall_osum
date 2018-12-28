package id.kanalitnuk.wallpapers.pastel.activities;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.ads.MobileAds;

import id.kanalitnuk.wallpapers.R;
import id.kanalitnuk.wallpapers.pastel.others.Utils;

public class AboutAppActivity extends AppCompatActivity {
    private static final String PREF_DARK_THEME = "dark_theme";
    public boolean isTheme, isColorednav;
    SharedPreferences mPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mPref = PreferenceManager.getDefaultSharedPreferences(this);
        isTheme = mPref.getBoolean(PREF_DARK_THEME, false);
        MobileAds.initialize(this, getString(R.string.ADMOB_APP_ID));
        Utils.mTheme = isTheme;
        if (isTheme) {
            setTheme(R.style.AppTheme_Dark_NoActionBar);
        } else {
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);
        Toolbar toolbar = (Toolbar) findViewById(R.id.aboutToolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("About");
        }
        TextView txtVersion = (TextView) findViewById(R.id.txtAppVersion);
        txtVersion.setText("Version " + getAppVersion());


    }

    public String getAppVersion() {
        String versionCode = "1.0";
        try {
            versionCode = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return versionCode;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                //NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
