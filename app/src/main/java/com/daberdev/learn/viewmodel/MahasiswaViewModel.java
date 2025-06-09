package com.daberdev.learn.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.daberdev.learn.model.MahasiswaModel;
import com.daberdev.learn.util.RecyclerViewOffsetMemory;

import java.util.List;

public class MahasiswaViewModel extends ViewModel {
    public final MutableLiveData<MahasiswaModel> dataMahasiswa = new MutableLiveData<MahasiswaModel>();
    public final MutableLiveData<List<MahasiswaModel>> allData = new MutableLiveData<List<MahasiswaModel>>();
    public final MutableLiveData<RecyclerViewOffsetMemory> offset = new MutableLiveData<RecyclerViewOffsetMemory>();
    public LiveData<MahasiswaModel> getMahasiswaData(){
        return this.dataMahasiswa;
    }

    public LiveData<List<MahasiswaModel>> getAllData(){
        return this.allData;
    }
    public LiveData<RecyclerViewOffsetMemory> getOffset(){ return this.offset; }

    public void setDataMahasiswa(MahasiswaModel data){
        this.dataMahasiswa.setValue(data);
    }
    public void setAllData(List<MahasiswaModel> data){
        this.allData.setValue(data);
    }
    public void setOffset(RecyclerViewOffsetMemory offset){ this.offset.setValue(offset); }


}
