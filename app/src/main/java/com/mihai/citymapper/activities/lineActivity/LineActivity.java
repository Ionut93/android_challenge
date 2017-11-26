package com.mihai.citymapper.activities.lineActivity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.mihai.citymapper.R;
import com.mihai.citymapper.activities.mainActivity.MainActivity;
import com.mihai.citymapper.adapters.LineAdapter;
import com.mihai.citymapper.models.Arrival;
import com.mihai.citymapper.models.StopPoint;
import com.mihai.citymapper.viewModels.StopPointsViewModel;

import java.util.List;

public class LineActivity extends AppCompatActivity implements LineActivityView {

    private final String MIN_DISTANCE_POSITION = "min_dist";

    private RecyclerView rvLine;
    private Arrival selectedArrival;
    private LineAdapter lineAdapter;
    private LineActivityPresenter presenter;
    private StopPointsViewModel stopPointsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line);
        bindViews();
        createData();
        if (savedInstanceState != null) {
            int minPosition = savedInstanceState.getInt(MIN_DISTANCE_POSITION, -1);
            Log.e("MIN POS ", "" + minPosition);
            presenter.setMinDistancePosition(minPosition);
            lineAdapter.setMinDistancePosition(presenter.getMinDistancePosition());
            lineAdapter.notifyDataSetChanged();
        }
        initializeData();
        getLinesStop();
    }

    private void createData() {
        stopPointsViewModel = ViewModelProviders.of(this).get(StopPointsViewModel.class);
        presenter = new LineActivityPresenter(stopPointsViewModel);
        if (getIntent() != null) {
            Bundle b = getIntent().getBundleExtra(MainActivity.BUNDLE);
            selectedArrival = b.getParcelable(MainActivity.ARRIVAL);
        }
        lineAdapter = new LineAdapter(selectedArrival);
    }

    private void initializeData() {
        rvLine.setLayoutManager(new LinearLayoutManager(this));
        rvLine.setAdapter(lineAdapter);

        Observer<List<StopPoint>> observer = new Observer<List<StopPoint>>() {
            @Override
            public void onChanged(@Nullable List<StopPoint> stopPoints) {
                lineAdapter.setMinDistancePosition(presenter.getMinDistancePosition());
                lineAdapter.setStopPointList(stopPoints);
                lineAdapter.notifyDataSetChanged();
            }
        };
        stopPointsViewModel.getStopPointMutableLiveData().observe(this, observer);

    }

    private void bindViews() {
        rvLine = (RecyclerView) findViewById(R.id.rv_line);
    }

    private void getLinesStop() {
        if (selectedArrival != null) {
            presenter.getLineStops(selectedArrival.getLineId());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("Save state ", " " + presenter.getMinDistancePosition());
        outState.putInt(MIN_DISTANCE_POSITION, presenter.getMinDistancePosition());

    }

}


