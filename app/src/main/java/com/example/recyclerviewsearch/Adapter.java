package com.example.recyclerviewsearch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> implements Filterable {

    private List<Items> exampleList;
    private List<Items> exampleListFull;
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, tag;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            name = itemView.findViewById(R.id.name);
            tag = itemView.findViewById(R.id.tag);
        }
    }
    public Adapter(List<Items> exampleList) {
        this.exampleList = exampleList;
        exampleListFull = new ArrayList<>(exampleList);
    }
    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent, false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        Items currentItem = exampleList.get(position);
        holder.imageView.setImageResource(currentItem.getImage());
        holder.name.setText(currentItem.getName());
        holder.tag.setText(currentItem.getTag());
    }
    @Override
    public int getItemCount() {
        return exampleList.size();
    }
    @Override
    public Filter getFilter() {
        return myFilter;
    }
    private Filter myFilter = new Filter() {
        //Automatic on background thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Items> filteredList = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(exampleListFull);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (Items item : exampleListFull) {
                    if (item.getTag().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }
        //Automatic on UI thread
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            exampleList.clear();
            exampleList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

}
