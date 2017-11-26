package com.mihai.citymapper.adapters;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mihai.citymapper.R;
import com.mihai.citymapper.models.Arrival;
import com.mihai.citymapper.models.StopPoint;

import java.util.List;

/**
 * Created by mihai on 26/11/2017.
 */

public class LineAdapter extends RecyclerView.Adapter<LineAdapter.LineViewHolder> {

    private List<StopPoint> stopPointList;
    private Arrival selectedArrival;
    private int minDistancePosition;

    public LineAdapter(Arrival selectedArrival) {
        this.selectedArrival = selectedArrival;
    }

    @Override
    public LineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.line_layout, parent, false);
        return new LineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LineViewHolder holder, int position) {
        StopPoint stopPoint = stopPointList.get(position);
        Log.e("Distnace to stoppoint ", "" + stopPoint.getDistanceToStopPoint());
        holder.tvLineName.setText(stopPoint.getName());
        holder.tvLineName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        holder.tvLineName.setTypeface(holder.tvLineName.getTypeface(), Typeface.NORMAL);
        if (selectedArrival.getDestinationName() != null &&
                selectedArrival.getDestinationName().equalsIgnoreCase(stopPoint.getName())) {
            holder.tvLineName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            holder.tvLineName.setTypeface(holder.tvLineName.getTypeface(), Typeface.BOLD);
        }
        holder.viewPosition.setVisibility(View.INVISIBLE);
        if (position == minDistancePosition) {
            holder.viewPosition.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return stopPointList != null ? stopPointList.size() : 0;
    }

    public void setStopPointList(List<StopPoint> stopPointList) {
        this.stopPointList = stopPointList;
    }

    public void setMinDistancePosition(int minDistancePosition) {
        this.minDistancePosition = minDistancePosition;
    }

    protected class LineViewHolder extends RecyclerView.ViewHolder {

        public TextView tvLineName;
        public View viewPosition;

        public LineViewHolder(View itemView) {
            super(itemView);
            tvLineName = (TextView) itemView.findViewById(R.id.tv_line_name);
            viewPosition = itemView.findViewById(R.id.view_position);
        }
    }
}
