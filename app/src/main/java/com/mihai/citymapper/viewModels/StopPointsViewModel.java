package com.mihai.citymapper.viewModels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.mihai.citymapper.models.StopPoint;

import java.util.List;

/**
 * Created by mihai on 26/11/2017.
 */

public class StopPointsViewModel extends ViewModel{

    private MutableLiveData<List<StopPoint>> stopPointMutableLiveData;

    public MutableLiveData<List<StopPoint>> getStopPointMutableLiveData() {
        if (stopPointMutableLiveData == null) {
            stopPointMutableLiveData = new MutableLiveData<>();
        }
        return stopPointMutableLiveData;
    }
}
