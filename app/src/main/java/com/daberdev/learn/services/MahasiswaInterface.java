package com.daberdev.learn.services;

import com.daberdev.learn.model.MahasiswaModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MahasiswaInterface {

    @GET("tampilMahasiswa.php")
    Call<List<MahasiswaModel>> getDataMahasiswa();

    @GET("tambahMahasiswa.php")
    Call<ResponseBody> tambahDataMahasiswa(
            @Query("nama") String nama,
            @Query("nim") String nim,
            @Query("jenisKelamin") String jenis_kelamin,
            @Query("tempatLahir") String tempat_lahir,
            @Query("tanggalLahir") String tanggal_lahir,
            @Query("jp") String jp,
            @Query("statusPernikahan") String status_pernikahan,
            @Query("tahunLahir") String tahun_lahir,
            @Query("tahunMasuk") String tahun_masuk,
            @Query("alamat") String alamat
    );

}
