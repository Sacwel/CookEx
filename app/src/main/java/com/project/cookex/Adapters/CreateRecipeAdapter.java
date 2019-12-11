package com.project.cookex.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.cookex.Model.RecipeModel;
import com.project.cookex.R;

import java.util.ArrayList;

public class CreateRecipeAdapter extends RecyclerView.Adapter<CreateRecipeAdapter.MyViewHolder>{

    private static ArrayList<RecipeModel> createRecipeArrayList;

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textName;
        TextView textDescription;

        public MyViewHolder(View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textView_with_step_name);
            textDescription = itemView.findViewById(R.id.textView_with_step_description);
        }
    }

    public CreateRecipeAdapter(ArrayList<RecipeModel> data){
        createRecipeArrayList = data;
    }

    @NonNull
    @Override
    public CreateRecipeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.step_list, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CreateRecipeAdapter.MyViewHolder holder, int position) {
        RecipeModel currentItem = createRecipeArrayList.get(position);
        holder.textName.setText(currentItem.getName());
        holder.textDescription.setText(currentItem.getDescription());
    }

    @Override
    public int getItemCount() {
        return (createRecipeArrayList == null) ? 0 : createRecipeArrayList.size();
    }
}
