package com.daberdev.learn.model;

import com.google.gson.annotations.SerializedName;

public class MahasiswaModel {

    public MahasiswaModel(
        String Nama,
        String NIM,
        String JP,
        String JenisKelamin,
        String StatusNikah,
        String TanggalLahir,
        String TempatLahir,
        String TahunMasuk
    ){
        this.Nama = Nama;
        this.NIM = NIM;
        this.JP = JP;
        this.JenisKelamin = JenisKelamin;
        this.StatusNikah = StatusNikah;
        this.TanggalLahir = TanggalLahir;
        this.TempatLahir = TempatLahir;
        this.TahunMasuk = TahunMasuk;
    }

    @SerializedName("Nama")
    private String Nama;
    @SerializedName("NIM")
    private String NIM;
    @SerializedName("JP")
    private String JP;
    @SerializedName("JenisKelamin")
    private String JenisKelamin;
    @SerializedName("StatusNikah")
    private String StatusNikah;
    @SerializedName("TanggalLahir")
    private String TanggalLahir;
    @SerializedName("TempatLahir")
    private String TempatLahir;
    @SerializedName("TahunMasuk")
    private String TahunMasuk;


    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getNIM() {
        return NIM;
    }

    public void setNIM(String NIM) {
        this.NIM = NIM;
    }

    public String getJP() {
        return JP;
    }

    public void setJP(String JP) {
        this.JP = JP;
    }

    public String getJenisKelamin() {
        return JenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        JenisKelamin = jenisKelamin;
    }

    public String getStatusNikah() {
        return StatusNikah;
    }

    public void setStatusNikah(String statusNikah) {
        StatusNikah = statusNikah;
    }

    public String getTanggalLahir() {
        return TanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        TanggalLahir = tanggalLahir;
    }

    public String getTempatLahir() {
        return TempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        TempatLahir = tempatLahir;
    }

    public String getTahunMasuk() {
        return TahunMasuk;
    }

    public void setTahunMasuk(String tahunMasuk) {
        TahunMasuk = tahunMasuk;
    }
}
