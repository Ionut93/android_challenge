package com.mihai.citymapper.activities.mainActivity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mihai.citymapper.activities.lineActivity.LineActivity;
import com.mihai.citymapper.adapters.ArrivalAdapter;
import com.mihai.citymapper.adapters.StopPointAdapter;
import com.mihai.citymapper.R;
import com.mihai.citymapper.models.Arrival;
import com.mihai.citymapper.models.StopPointWithArrivals;
import com.mihai.citymapper.viewModels.StopPointWithArrivalsViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    public static final String ARRIVAL = "arrival";
    public static final String BUNDLE = "bundle";

    private final static String TAG = MainActivity.class.getSimpleName();
    private MainActivityPresenter presenter;
    private StopPointWithArrivalsViewModel model;

    private RecyclerView rvStops;
    private ProgressBar progressBar;
    private StopPointAdapter stopPointAdapter;
    private Handler handler;
    private Runnable runnable;
    private AlertDialog alertDialog;
    private ArrivalAdapter.ArrivalAdapterCallback adapterCallback = new ArrivalAdapter.ArrivalAdapterCallback() {
        @Override
        public void onArrivalClicked(Arrival arrival) {
            Intent intent = new Intent(MainActivity.this, LineActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable(ARRIVAL, arrival);
            intent.putExtra(BUNDLE, bundle);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        initializeData();
        getDataFromServer();
        initializeRefresh();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getDataFromServer();
        initializeRefresh();
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (presenter != null) {
            presenter.cancelRequests();
        }
        if (handler != null) {
            handler.removeCallbacks(runnable);
        }
    }

    private void bindViews() {
        rvStops = (RecyclerView) this.findViewById(R.id.rv_stops);
        progressBar = (ProgressBar) this.findViewById(R.id.progress_bar);
    }

    private void initializeData() {

        model = ViewModelProviders.of(this).get(StopPointWithArrivalsViewModel.class);
        presenter = new MainActivityPresenter(this, model);

        Observer<List<StopPointWithArrivals>> observer = new Observer<List<StopPointWithArrivals>>() {
            @Override
            public void onChanged(@Nullable List<StopPointWithArrivals> stopPointWithArrivals) {
                if (presenter != null) {
                    presenter.setStopPointWithArrivals(stopPointWithArrivals);
                }
                stopPointAdapter.setStopPointWithArrivalsList(stopPointWithArrivals);
                stopPointAdapter.notifyDataSetChanged();
            }
        };

        model.getData().observe(this, observer);

        stopPointAdapter = new StopPointAdapter(adapterCallback);
        rvStops.setLayoutManager(new LinearLayoutManager(this));
        rvStops.setAdapter(stopPointAdapter);

    }

    private void initializeRefresh() {

        handler = new Handler(Looper.getMainLooper());
        runnable = new Runnable() {
            @Override
            public void run() {
                if (presenter != null) {
                    presenter.refreshArrivals();
                    handler.postDelayed(runnable, 5000);
                }
            }
        };
        handler.postDelayed(runnable, 5000);
    }

    private void getDataFromServer() {
        presenter.getNearbyStops();
    }


    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showNoInternetErrorMessage(String errorMessage) {
        if (alertDialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(errorMessage);
            builder.setCancelable(false);
            builder.setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (presenter != null) {
                        presenter.refreshArrivals();
                    }
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    alertDialog.dismiss();
                    if (handler != null) {
                        handler.removeCallbacks(runnable);
                    }
                }
            });
            alertDialog = builder.create();
            alertDialog.show();
        } else if (!alertDialog.isShowing()) {
            alertDialog.setMessage(errorMessage);
            alertDialog.show();
        }
    }

}
