package com.mihai.citymapper.activities.mainActivity;

/**
 * Created by mihai on 25/11/2017.
 */

public interface MainActivityView {

    public void showProgress();
    public void hideProgress();
    public void showNoInternetErrorMessage(String errorMessage);
}
