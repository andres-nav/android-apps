package com.andresnav.trackmyshoes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.andresnav.trackmyshoes.data.model.RunModel;

import java.util.ArrayList;

public class RunsAdapter extends RecyclerView.Adapter<RunsAdapter.ViewHolder>{

    private ArrayList<RunModel> runs;
    private LayoutInflater mInflater;
    private ItemClickListener itemClickListener;

    // data is passed into the constructor
    RunsAdapter(Context context, ArrayList<RunModel> runs) {
        this.mInflater = LayoutInflater.from(context);
        this.runs = runs;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_run, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String title = runs.get(position).getName();
        holder.textViewTitle.setText(title);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return runs.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewTitle;

        ViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) itemClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    RunModel getItem(int id) {
        return runs.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
