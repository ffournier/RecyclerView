package com.android2ee.recyclerview;

import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<ViewModel> items;
    private int itemLayout;

    public RecyclerViewAdapter(List<ViewModel> items, int itemLayout) {
        this.items = items;
        this.itemLayout = itemLayout;
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        ViewModel item = items.get(position);
        holder.primaryText.setText(item.getPrimaryText());
        holder.secondText.setText(item.getSecondText());
        holder.icon.setImageResource(item.getIcon());
        holder.itemView.setTag(item);
    }

    @Override public int getItemCount() {
        return items.size();
    }
    
    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView icon;
        public TextView primaryText;
        public TextView secondText;

        public ViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.myicon);
            primaryText = (TextView) itemView.findViewById(R.id.myfirstLine);
            secondText = (TextView) itemView.findViewById(R.id.mysecondLine);
        }
    }
}
