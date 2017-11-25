package com.mihai.citymapper.adapters;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mihai.citymapper.R;
import com.mihai.citymapper.models.Arrival;
import com.mihai.citymapper.models.StopPoint;
import com.mihai.citymapper.models.StopPointWithArrivals;

import java.util.HashMap;
import java.util.List;

/**
 * Created by mihai on 25/11/2017.
 */

public class StopPointAdapter extends RecyclerView.Adapter<StopPointAdapter.StopPointViewHolder> {

    private List<StopPointWithArrivals> stopPointWithArrivalsList;

    @Override
    public StopPointViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stop_with_arrivals_view, parent, false);
        return new StopPointViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StopPointViewHolder holder, int position) {
        StopPointWithArrivals stopPointWithArrivals = stopPointWithArrivalsList.get(position);
        StopPoint stopPoint = stopPointWithArrivals.getStopPoint();
        List<Arrival> arrivals = stopPointWithArrivals.getStopPointArrivals();

        holder.tvStopName.setText(stopPoint.getName());
        holder.rvArrivals.setLayoutManager(new LinearLayoutManager(null));
        holder.rvArrivals.setAdapter(new ArrivalAdapter(arrivals));


    }

    @Override
    public int getItemCount() {
        return stopPointWithArrivalsList != null ? stopPointWithArrivalsList.size() : 0;
    }

    public void setStopPointWithArrivalsList(List<StopPointWithArrivals> stopPointWithArrivalsList) {
        this.stopPointWithArrivalsList = stopPointWithArrivalsList;
    }

    public class StopPointViewHolder extends RecyclerView.ViewHolder {

        RecyclerView rvArrivals;
        TextView tvStopName;

        public StopPointViewHolder(View itemView) {
            super(itemView);
            tvStopName = (TextView) itemView.findViewById(R.id.tv_stop_name);
            rvArrivals = (RecyclerView) itemView.findViewById(R.id.rv_arrivals);
        }
    }
}
