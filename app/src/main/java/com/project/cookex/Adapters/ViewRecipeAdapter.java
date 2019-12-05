package com.project.cookex.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.cookex.DataModel;
import com.project.cookex.R;

import java.util.ArrayList;

public class ViewRecipeAdapter extends RecyclerView.Adapter<ViewRecipeAdapter.ViewRecipeViewHolder> {
    public ArrayList<DataModel> dataset;

    public static class ViewRecipeViewHolder extends RecyclerView.ViewHolder{

        public ImageView mImageView;
        public TextView mName;
        public TextView mDescription;
        public TextView rating;

        public ViewRecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.Recipe_name_view_recipe);
            mImageView = itemView.findViewById(R.id.image_of_recipe_main_menu);
            mDescription = itemView.findViewById(R.id.description_main_menu);
            rating = itemView.findViewById(R.id.rating_main_menu);
        }
    }

    public ViewRecipeAdapter(ArrayList<DataModel> data){
        dataset = data;
    }

    @NonNull
    @Override
    public ViewRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_of_recipes_layout, parent, false);
        ViewRecipeViewHolder mViewHolder = new ViewRecipeViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewRecipeViewHolder holder, int position) {
        DataModel currentItem = dataset.get(position);

        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mName.setText(currentItem.getName());
        holder.mDescription.setText(currentItem.getDescription());
        holder.rating.setText(currentItem.getRating());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
