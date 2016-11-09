package com.antonkazakov.squats.Widget;

import android.view.View;

/**
 * Created by antonkazakov on 03.07.16.
 */
public interface ClickListener {

    void onClick(View view, int position);

    void onLongClick(View view, int position);

}
