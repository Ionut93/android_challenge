package com.mihai.citymapper.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mihai.citymapper.R;
import com.mihai.citymapper.models.Arrival;

import java.util.List;

/**
 * Created by mihai on 25/11/2017.
 */

public class ArrivalAdapter extends RecyclerView.Adapter<ArrivalAdapter.ArrivalAdapterViewHolder> {

    private List<Arrival> arrivalList;

    public ArrivalAdapter(List<Arrival> arrivalList) {
        this.arrivalList = arrivalList;
    }

    @Override
    public ArrivalAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.arrival_view, parent, false);
        return new ArrivalAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArrivalAdapterViewHolder holder, int position) {
        if (arrivalList.size() > position) {
            Arrival arrival = arrivalList.get(position);
            holder.tvArrivalName.setText(arrival.getDestinationName());
            holder.tvArrivalTime.setText(arrival.getExpectedArrival()
                    .substring(arrival.getExpectedArrival().indexOf('T') + 1,
                            arrival.getExpectedArrival().indexOf('Z')));
        }
    }

    @Override
    public int getItemCount() {
        return arrivalList != null ? 3 : 0;
    }

    protected class ArrivalAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView tvArrivalName, tvArrivalTime;

        public ArrivalAdapterViewHolder(View itemView) {
            super(itemView);
            tvArrivalName = (TextView) itemView.findViewById(R.id.tv_arrival_name);
            tvArrivalTime = (TextView) itemView.findViewById(R.id.tv_arrival_time);
        }
    }

    public void setArrivalList(List<Arrival> arrivalList) {
        this.arrivalList = arrivalList;
    }
}
