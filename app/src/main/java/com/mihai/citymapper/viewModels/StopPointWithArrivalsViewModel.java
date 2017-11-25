package com.mihai.citymapper.viewModels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.mihai.citymapper.models.StopPointWithArrivals;

import java.util.List;

/**
 * Created by mihai on 25/11/2017.
 */

public class StopPointWithArrivalsViewModel extends ViewModel {

    private MutableLiveData<List<StopPointWithArrivals>> data;


    public MutableLiveData<List<StopPointWithArrivals>> getData() {
        if (data == null) {
            data = new MutableLiveData<>();
        }
        return data;
    }
}
