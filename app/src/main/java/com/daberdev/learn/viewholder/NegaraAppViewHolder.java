package com.daberdev.learn.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daberdev.learn.R;

public class NegaraAppViewHolder extends RecyclerView.ViewHolder {

    public ImageView bendera;
    public TextView nama;
    public TextView deskripsi;
    public NegaraAppViewHolder(@NonNull View itemView) {
        super(itemView);

        this.bendera = (ImageView) itemView.findViewById(R.id.bendera);
        this.nama = (TextView) itemView.findViewById(R.id.nama);
        this.deskripsi = (TextView) itemView.findViewById(R.id.deskripsi);
    }


}
