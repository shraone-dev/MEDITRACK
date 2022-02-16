package com.example.maditrack.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.maditrack.MediTracklFolder.Category;
import com.example.maditrack.activity.CategoryEditActivity;

import com.example.maditrack.R;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {

    private ArrayList<Category> categorylist;
    private Context context;

    public CategoryAdapter(Context context, ArrayList<Category> categorylist) {
        this.categorylist = categorylist;
        this.context = context;
    }

    @Override
    public CategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutview = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_cardview, parent, false);
        CategoryHolder recyclerViewHolders = new CategoryHolder(layoutview);
        return recyclerViewHolders;
    }

    @Override
    public void onBindViewHolder(CategoryHolder holder, int position) {
        holder.categorycode.setText(categorylist.get(position).getCategorycode());
        holder.categoryname.setText(categorylist.get(position).getCategoryname());
        holder.categorydescription.setText(categorylist.get(position).getCategorydescription());
        holder.container.setOnClickListener(onClickListener(position));
    }

    private View.OnClickListener onClickListener(final int position) {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CategoryEditActivity.class);

                intent.putExtra("Id", categorylist.get(position).getId());
                intent.putExtra("Code", categorylist.get(position).getCategorycode());
                intent.putExtra("Category", categorylist.get(position).getCategoryname());
                intent.putExtra("Description", categorylist.get(position).getCategorydescription());
                intent.putExtra("Reminder",categorylist.get(position).getCategoryreminder());

                context.startActivity(intent);

            }
        };
    }

    @Override
    public int getItemCount() {
        return (null != categorylist ? categorylist.size() : 0);
    }

    class CategoryHolder extends RecyclerView.ViewHolder {


        TextView categorycode, categoryname, categorydescription;
        View container;


        public CategoryHolder(View itemView) {
            super(itemView);

            container = itemView.findViewById(R.id.catergorycardview);
            categorycode = (TextView) itemView.findViewById(R.id.TextCategoryCode);
            categoryname = (TextView) itemView.findViewById(R.id.TextCategoryName);
            categorydescription = (TextView) itemView.findViewById(R.id.TextCategoryDescription);

        }
    }
}