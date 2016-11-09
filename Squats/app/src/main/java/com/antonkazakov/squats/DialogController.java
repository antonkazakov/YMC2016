package com.antonkazakov.squats;

import android.app.Activity;
import android.support.v4.content.ContextCompat;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by antonkazakov on 02.08.16.
 */
public class DialogController {

    /**
     *
     * Call dialog window with dismiss callback
     *
     * @param activity
     * @return
     */
    public static MaterialDialog errorDialog(Activity activity, String error){

        return new MaterialDialog.Builder(activity)
                .canceledOnTouchOutside(false)
                .title("Error")
                .content(error)
                .onPositive((dialog, which) -> dialog.dismiss())
                .positiveText("ОК")
                .build();
    }



    public static MaterialDialog progressDialog(Activity activity){

        return new MaterialDialog.Builder(activity)
                .content("Loading")
                .autoDismiss(true)
                .canceledOnTouchOutside(false)
                .cancelable(false)
                .progress(true, 100)
                .build();
    }

    public static MaterialDialog infoDialog(Activity activity, String content){

        return new MaterialDialog.Builder(activity)
                .title(R.string.how_to)
                .icon(ContextCompat.getDrawable(AppDelegate.getContext(),R.drawable.ic_info))
                .content(content)
                .canceledOnTouchOutside(false)
                .positiveText("ОК")
                .onPositive((dialog, which) -> dialog.dismiss())
                .build();
    }


}
