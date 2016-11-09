package com.antonkazakov.radio.ui.stations;

import com.antonkazakov.radio.data.content.Station;
import com.antonkazakov.radio.ui.base.BaseView;

import java.util.List;

/**
 * Created by antonkazakov on 22.10.16.
 */

public interface StationsView extends BaseView {

    void refreshStations(List<Station> stations);

    void updateView(int tag);
}
