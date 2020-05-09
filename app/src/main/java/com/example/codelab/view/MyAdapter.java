package com.example.codelab.view;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.codelab.R;
import com.example.codelab.controller.AdapterController;
import com.example.codelab.model.ContainerJSON;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private final List<ContainerJSON> values;
    private int expandedPosition = -1;
    private final OnItemClickListener listener;
    private AdapterController Ad_controller;

    public interface OnItemClickListener {
        void onItemClick(ContainerJSON item);
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView txtHeader;
        TextView txtFooter;
        ImageView img;
        View sub_item;
        View layout;

        ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = v.findViewById(R.id.firstLine);
            txtFooter = v.findViewById(R.id.secondLine);
            img = v.findViewById(R.id.icon);
            sub_item = v.findViewById(R.id.llExpandArea);
        }
    }

    public void add(int position, ContainerJSON item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    private void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    MyAdapter(List<ContainerJSON> myDataset, OnItemClickListener listener) {
        this.values = myDataset;
        this.listener = listener;
    }

    // Create new views (invoked by the layout manager)
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh;
        vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final ContainerJSON C = values.get(position);
        holder.txtHeader.setText(C.getName());

     /*   if (position == expandedPosition) {
            holder.sub_item.setVisibility(View.VISIBLE);
        } else {
            holder.sub_item.setVisibility(View.GONE);
        }
        holder.sub_item.setActivated(C.getExpanded());

          holder.txtHeader.setOnClickListener(v -> {
          //  int prev = Ad_controller.Start(holder.getAdapterPosition(),expandedPosition);

            if (expandedPosition >= 0) {
                int prev = expandedPosition;
                notifyItemChanged(prev);
            }
            // Set the current position to "expanded"
            expandedPosition = holder.getAdapterPosition();
            notifyItemChanged(expandedPosition);
        });
*/

        holder.txtFooter.setText(String.join(" ",C.getRoles()));


        final ImageView myImageView;
        Glide.with(holder.img.getContext())
                .load(C.getFemaleImg())
                .centerCrop()
                .apply(new RequestOptions().override(96, 96))
                .into(holder.img);

        holder.itemView.setOnClickListener(v -> listener.onItemClick(C));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

   /* public void updateList(List<String> from){
        names = new ArrayList<>()
    }*/

}
