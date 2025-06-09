package com.daberdev.learn.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daberdev.learn.R;
import com.daberdev.learn.model.MahasiswaModel;
import com.daberdev.learn.util.OnClickListenerMahasiswaDetail;
import com.daberdev.learn.viewholder.MahasiswaViewHolder;

import java.util.List;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaViewHolder> {


    public MahasiswaAdapter(Context ctx, List<MahasiswaModel> data, OnClickListenerMahasiswaDetail listener){
        this.ctx = ctx;
        this.dataMahasiswa = data;
        this.listener = listener;
    }

    private Context ctx;
    private List<MahasiswaModel> dataMahasiswa;
    private OnClickListenerMahasiswaDetail listener;

    @NonNull
    @Override
    public MahasiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.ctx).inflate(R.layout.layout_data_mahasiswa,parent,false);
        return new MahasiswaViewHolder(view);
    }

    public void filter(List<MahasiswaModel> new_data){
        this.dataMahasiswa = new_data;
        this.notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(@NonNull MahasiswaViewHolder holder, int position) {
        MahasiswaModel data = this.dataMahasiswa.get(position);
        holder.Nama.setText(data.getNama());
        holder.JP.setText(data.getJP());
        holder.NIM.setText("Nim : "+data.getNIM());
        holder.JenisKelamin.setText("Jenis Kelamin : "+ data.getJenisKelamin());

        holder.action.setOnClickListener(v ->{
            this.listener.OnClick(data);
        });
    }

    @Override
    public int getItemCount() {
        if(this.dataMahasiswa != null){
            return this.dataMahasiswa.size();
        }
        return 0;
    }
}
