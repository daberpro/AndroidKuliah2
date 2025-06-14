package com.daberdev.learn.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daberdev.learn.R;
import com.daberdev.learn.model.ForexDataModel;
import com.daberdev.learn.viewholder.ForexViewHolder;
import com.daberdev.learn.viewholder.MahasiswaViewHolder;

import java.util.List;

public class ForexAdapter extends RecyclerView.Adapter<ForexViewHolder> {

    public Context ctx;
    public List<ForexDataModel> list;

    public ForexAdapter(Context ctx, List<ForexDataModel> list) {
        this.list = list;
        this.ctx = ctx;
    }

    public void Reload(List<ForexDataModel> list){
        this.list = list;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ForexViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.ctx).inflate(R.layout.forex_card,parent,false);
        return new ForexViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForexViewHolder holder, int position) {
        ForexDataModel data = list.get(position);
        holder.value.setText(String.valueOf(data.getValue()));
        holder.description.setText(data.getDescription());
        holder.currency.setText(data.getCurrency());
    }

    @Override
    public int getItemCount() {
        if(this.list == null){
            return 0;
        }
        return this.list.size();
    }
}
