package com.antonkazakov.roadsigns.ui.views;

import com.antonkazakov.roadsigns.content.Result;
import com.antonkazakov.roadsigns.ui.views.BaseView;

/**
 * Created by antonkazakov on 21.10.16.
 */

public interface RecognitionView extends BaseView {

    void loadData(Result result);

}
