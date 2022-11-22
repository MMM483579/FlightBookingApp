package com.example.kiwi30;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Random;

public class RandomNumListAdapter extends RecyclerView.Adapter<RecyclerViewHolderOne>{
    private Random random;
    List<Flight> mFlights;

    public RandomNumListAdapter(List<Flight> flights, int seed) {
        this.mFlights = flights;
        Log.d("this.mFlights", String.valueOf(this.mFlights));
        this.random = new Random(seed);
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.search_list;
    }

    @NonNull
    @Override
    public RecyclerViewHolderOne onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new RecyclerViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolderOne holder, int position) {

        holder.getView().setText(String.valueOf(mFlights.get(position).getfDepatureTown()));
        holder.getViewTwo().setText(String.valueOf(mFlights.get(position).getfDestinationTown()));
        holder.getViewThree().setText(String.valueOf(mFlights.get(position).getfTimeDuration()));
        holder.getViewFour().setText(String.valueOf(mFlights.get(position).getfAirline()));
        holder.getViewFive().setText(String.valueOf(mFlights.get(position).getfDepatureTime()));
        holder.getViewSix().setText(String.valueOf(mFlights.get(position).getfArrivalTime()));
        holder.getViewSeven().setText(String.valueOf(mFlights.get(position).getfPrice()));

    }

    @Override
    public int getItemCount() {
        return mFlights.size();
    }
}
