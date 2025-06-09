package com.daberdev.learn.viewholder;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daberdev.learn.R;
import com.daberdev.learn.model.MahasiswaModel;
import com.google.android.material.button.MaterialButton;

public class MahasiswaViewHolder extends RecyclerView.ViewHolder {

    public TextView Nama;
    public TextView NIM;
    public TextView JenisKelamin;
    public TextView JP;

    public MaterialButton action;

    public MahasiswaViewHolder(@NonNull View itemView) {
        super(itemView);

        this.JP = itemView.findViewById(R.id.prodi);
        this.Nama = itemView.findViewById(R.id.nama);
        this.NIM = itemView.findViewById(R.id.nim);
        this.JenisKelamin = itemView.findViewById(R.id.jenis_kelamin);
        this.action = itemView.findViewById(R.id.action);

    }
}
