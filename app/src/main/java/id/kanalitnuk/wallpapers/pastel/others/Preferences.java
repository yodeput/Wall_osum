package id.kanalitnuk.wallpapers.pastel.others;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class Preferences {

    private static final String
            PREFERENCES_NAME = "APP_PREFERENCES",
            FAVORITES = "Favorite";
    public static final String KEY_NAVBAR = "coloredNavbar";
    public static final String KEY_COUNTER = "ClickCounter";
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    private final Context _context;

    public Preferences(Context context) {
        this._context = context;
        prefs = _context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public SharedPreferences getSharedPreferences() {
        return _context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public SharedPreferences getSharedPreferencess() {
        return PreferenceManager.getDefaultSharedPreferences(_context);
    }

    public boolean getBoolean(String name, boolean defaultValue) {
        prefs = _context.getSharedPreferences(PREFERENCES_NAME, 0);
        return prefs.getBoolean(name, defaultValue);
    }

    public boolean isNavbarColored() {
        return getSharedPreferencess().getBoolean(KEY_NAVBAR, false);
    }

    public void setClickCounter(){
        editor = prefs.edit();
        int a = prefs.getInt(KEY_COUNTER, 0);
        a = a+1;
        editor.putInt(KEY_COUNTER, a);
        editor.apply();
    }

    public void resetClickCounter(){
        editor.putInt(KEY_COUNTER, 0);
        editor.apply();
    }

    public int getClickCounter(){
        prefs = _context.getSharedPreferences(PREFERENCES_NAME, 0);
        return prefs.getInt(KEY_COUNTER, 0);
    }

    public void setNavbar (boolean bool) {
        getSharedPreferencess().edit().putBoolean(KEY_NAVBAR, bool).apply();
    }

    public int getInteger(String name, int DefaultValue) {
        prefs = _context.getSharedPreferences(PREFERENCES_NAME, 0);
        return prefs.getInt(name, DefaultValue);
    }

    public String getString(String name, String defaultValue) {
        prefs = _context.getSharedPreferences(PREFERENCES_NAME, 0);
        return prefs.getString(name, defaultValue);
    }

    public void saveBoolean(String name, boolean value) {
        prefs = _context.getSharedPreferences(PREFERENCES_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(name, value);
        editor.apply();
    }

    public void saveInteger(String name, int value) {
        prefs = _context.getSharedPreferences(PREFERENCES_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor = prefs.edit();
        editor.putInt(name, value);
        editor.apply();
    }

    public void saveString(String name, String value) {
        prefs = _context.getSharedPreferences(PREFERENCES_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor = prefs.edit();
        editor.putString(name, value);
        editor.apply();
    }
}