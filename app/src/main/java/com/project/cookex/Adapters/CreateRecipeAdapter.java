package com.project.cookex.Adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.cookex.Model.DataModelCreateRecipe;
import com.project.cookex.R;

import java.util.ArrayList;

public class CreateRecipeAdapter extends RecyclerView.Adapter<CreateRecipeAdapter.MyViewHolder>{


    private LayoutInflater inflater;
    public static ArrayList<DataModelCreateRecipe> createRecipeArrayList;

    public CreateRecipeAdapter(Context context, ArrayList<DataModelCreateRecipe> createRecipeArrayList){
        inflater = LayoutInflater.from(context);
        this.createRecipeArrayList = createRecipeArrayList;
    }

    @NonNull
    @Override
    public CreateRecipeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_create_steps, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CreateRecipeAdapter.MyViewHolder holder, int position) {
        holder.editTextName.setText(createRecipeArrayList.get(position).getEditName());
        holder.editTextDescription.setText(createRecipeArrayList.get(position).getEditDescription());
    }

    @Override
    public int getItemCount() {
        return createRecipeArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        protected EditText editTextName;
        protected EditText editTextDescription;

        public MyViewHolder(View view) {
            super(view);

            editTextName = (EditText) view.findViewById(R.id.textInputLayout_step_name);
            editTextDescription = (EditText) view.findViewById(R.id.textInputLayoutDescription);


            editTextName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    createRecipeArrayList.get(getAdapterPosition()).setEditName(editTextName.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

        }
    }
}
