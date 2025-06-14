package com.daberdev.learn;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.daberdev.learn.services.MahasiswaInterface;
import com.daberdev.learn.util.ReloadListenerMahasiswaData;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MahasiswaBottomSheed extends BottomSheetDialogFragment {

    private ReloadListenerMahasiswaData listener;
    public MahasiswaBottomSheed(ReloadListenerMahasiswaData listener){
        this.listener = listener;
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        listener.Reload();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_mahasiswa_form,container,false);
        String API_URL = "https://stmikpontianak.cloud/011100862/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TextInputEditText nama = view.findViewById(R.id.nama);
        TextInputEditText nim = view.findViewById(R.id.nim);
        AutoCompleteTextView jenis_kelamin = view.findViewById(R.id.jenis_kelamin);
        TextInputEditText tahun_masuk = view.findViewById(R.id.tahun_masuk);
        TextInputEditText tanggal_lahir = view.findViewById(R.id.tanggal_lahir);
        TextInputEditText tempat_lahir = view.findViewById(R.id.tempat_lahir);
        TextInputEditText alamat = view.findViewById(R.id.alamat);
        AutoCompleteTextView status_pernikahan = view.findViewById(R.id.status_pernikahan);
        AutoCompleteTextView jp = view.findViewById(R.id.jurusan_prodi);

        List<String> gender = new ArrayList<>();
        List<String> _jp = new ArrayList<>();
        List<String> _status_pernikahan = new ArrayList<>();

        gender.add("Laki-laki");
        gender.add("Perempuan");
        _jp.add("TI/TI");
        _jp.add("SI/SI");
        _jp.add("SI/AK");
        _status_pernikahan.add("Belum Menikah");
        _status_pernikahan.add("Sudah Menikah");
        _status_pernikahan.add("Janda");
        _status_pernikahan.add("Duda");

        jenis_kelamin.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line,gender));
        jp.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line,_jp));
        status_pernikahan.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line,_status_pernikahan));

        MahasiswaInterface mahasiswa = retrofit.create(MahasiswaInterface.class);

        MaterialButton btn = view.findViewById(R.id.tambah);
        btn.setOnClickListener(v ->{
            String str_alamat ,str_nama, str_nim, str_jenis_kelamin, str_tempat_lahir, str_tanggal_lahir, str_tahun_lahir,str_status_pernikahan,str_tahun_masuk,str_jp;

            str_nama = nama.getText().toString();
            str_nim = nim.getText().toString();
            str_jenis_kelamin = jenis_kelamin.getText().toString();
            str_tempat_lahir = tempat_lahir.getText().toString();
            str_tahun_lahir = tanggal_lahir.getText().toString();
            str_jp = jp.getText().toString();
            str_status_pernikahan = status_pernikahan.getText().toString();
            str_tahun_masuk = tahun_masuk.getText().toString();
            str_tanggal_lahir = tanggal_lahir.getText().toString();
            str_alamat = alamat.getText().toString();

            Call<ResponseBody> tambah = mahasiswa.tambahDataMahasiswa(
                str_nama,
                str_nim,
                str_jenis_kelamin,
                str_tempat_lahir,
                str_tanggal_lahir,
                str_jp,
                str_status_pernikahan,
                str_tahun_lahir,
                str_tahun_masuk,
                str_alamat
            );

            Log.d("Debug Fetch data", tambah.request().url().toString());
            tambah.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response.body().string());
                        String value = jsonObject.getString("message");
                        String status = jsonObject.getString("status");

                        if(!status.equals("error")){
                            Toast.makeText(getContext(),value,Toast.LENGTH_LONG).show();
                            dismiss();
                        }else{
                            Toast.makeText(getContext(),value,Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(getContext(),"Gagal menambahkan data",Toast.LENGTH_LONG).show();
                    dismiss();
                }
            });
        });

        return view;
    }
}
