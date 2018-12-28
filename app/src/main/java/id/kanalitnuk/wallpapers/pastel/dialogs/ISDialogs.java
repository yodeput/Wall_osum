package id.kanalitnuk.wallpapers.pastel.dialogs;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;

public final class ISDialogs {

    public static MaterialDialog showDownloadDialog(Context context) {
        return new MaterialDialog.Builder(context)
                .content("downloading_wallpaper")
                .progress(true, 0)
                .cancelable(false)
                .build();
    }

}