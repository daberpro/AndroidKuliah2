package com.daberdev.learn.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daberdev.learn.R;
import com.daberdev.learn.model.NegaraModel;
import com.daberdev.learn.viewholder.NegaraAppViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NegaraAppAdapter extends RecyclerView.Adapter<NegaraAppViewHolder> {

    private Context ctx;
    private List<NegaraModel> data;
    public NegaraAppAdapter(Context ctx, List<NegaraModel> list_data) {
        this.ctx = ctx;
        this.data = list_data;
    }


    @NonNull
    @Override
    public NegaraAppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(this.ctx).inflate(R.layout.layout_negara_recycler,parent,false);
        return new NegaraAppViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull NegaraAppViewHolder holder, int position) {
        NegaraModel _data = this.data.get(position);
        Picasso.with(this.ctx).load(_data.GetUrl()).into(holder.bendera);
        holder.nama.setText(_data.GetNamaNegara());
        holder.deskripsi.setText(_data.GetDeskripsi());

    }

    @Override
    public int getItemCount() {
        if(this.data != null){
            return this.data.size();
        }
        return 0;
    }
}
