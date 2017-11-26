package com.mihai.citymapper.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mihai.citymapper.R;
import com.mihai.citymapper.models.Arrival;

import java.util.List;

/**
 * Created by mihai on 25/11/2017.
 */

public class ArrivalAdapter extends RecyclerView.Adapter<ArrivalAdapter.ArrivalAdapterViewHolder> {

    private List<Arrival> arrivalList;
    private ArrivalAdapterCallback callback;

    public ArrivalAdapter(List<Arrival> arrivalList, ArrivalAdapterCallback callback) {
        this.arrivalList = arrivalList;
        this.callback = callback;
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
            final Arrival arrival = arrivalList.get(position);
            holder.tvArrivalName.setText(arrival.getDestinationName());
            holder.tvArrivalTime.setText(arrival.getExpectedArrival()
                    .substring(arrival.getExpectedArrival().indexOf('T') + 1,
                            arrival.getExpectedArrival().indexOf('Z')));
            holder.llItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.onArrivalClicked(arrival);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return arrivalList != null ? 3 : 0;
    }

    protected class ArrivalAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView tvArrivalName, tvArrivalTime;
        LinearLayout llItem;

        public ArrivalAdapterViewHolder(View itemView) {
            super(itemView);
            tvArrivalName = (TextView) itemView.findViewById(R.id.tv_arrival_name);
            tvArrivalTime = (TextView) itemView.findViewById(R.id.tv_arrival_time);
            llItem = (LinearLayout) itemView.findViewById(R.id.ll_item);
        }
    }

    public void setArrivalList(List<Arrival> arrivalList) {
        this.arrivalList = arrivalList;
    }

    public interface ArrivalAdapterCallback {
        public void onArrivalClicked(Arrival arrival);
    }
}
