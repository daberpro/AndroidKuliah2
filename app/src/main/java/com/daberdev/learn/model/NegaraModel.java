package com.daberdev.learn.model;

public class NegaraModel {

    private String nama_negara;
    private String deskripsi;
    private String url;

    public NegaraModel(String nama, String url, String deskripsi){
        this.nama_negara = nama;
        this.url = url;
        this.deskripsi = deskripsi;
    }

    public String GetNamaNegara(){
        return this.nama_negara;
    }

    public String GetUrl(){
        return this.url;
    }

    public String GetDeskripsi(){
        return this.deskripsi;
    }

    public void SetNamaNegara(String nama){
        this.nama_negara = nama;
    }

    public void SetUrl(String url){
        this.url = url;
    }

    public void SetDeskripsi(String deskripsi){
        this.deskripsi = deskripsi;
    }


}
