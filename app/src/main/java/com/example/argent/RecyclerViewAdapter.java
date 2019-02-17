package com.example.argent;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    private ArrayList<Expense> expenses;

    public RecyclerViewAdapter(ArrayList<Expense> expenses)
    {
        this.expenses= expenses;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_list, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.Date.setText(expenses.get(i).getDate());
        viewHolder.Item.setText(expenses.get(i).getItemPurchased());
        viewHolder.Amount.setText((int)(expenses.get(i).getAmount()));

    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView Date;
        TextView Item;
        TextView Amount;
        ConstraintLayout constraintLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            Date = itemView.findViewById(R.id.date);
            Item = itemView.findViewById(R.id.item);
            Amount= itemView.findViewById(R.id.amount);
        }
    }
}
