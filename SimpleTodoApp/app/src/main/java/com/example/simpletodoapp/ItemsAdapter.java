package com.example.simpletodoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    public interface OnLongClickListener {
      void onItemLongClicked( int position);

    }

    List<String> items;
    OnLongClickListener longClickListener;

    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener) {
        this.items = items;
        this.longClickListener  = longClickListener;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       // Use Layout to inflate a view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,parent,false);
        //wrap it inside a View holder view
        return new ViewHolder(todoView);
    }


    // responsible for binding data to particular ViewHolder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    //Get item to the position
      String item = items.get(position);
    //Bind item to a specified view holder
        holder.bind(item);
    }
    //Tells the Rv how many items are in the list
    @Override
    public int getItemCount() {
        return items.size();
    }

    // Containers to provide easy access to views that represent row of the list
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }
         //update the view inside the view holder with this data
        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //notify the listener which positioned was long
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });



        }
    }

}
