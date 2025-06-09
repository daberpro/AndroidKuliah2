package com.daberdev.learn;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daberdev.learn.adapter.MahasiswaAdapter;
import com.daberdev.learn.model.MahasiswaModel;
import com.daberdev.learn.services.MahasiswaInterface;
import com.daberdev.learn.util.OnClickListenerMahasiswaDetail;
import com.daberdev.learn.util.RecyclerViewOffsetMemory;
import com.daberdev.learn.util.ReloadListenerMahasiswaData;
import com.daberdev.learn.viewmodel.MahasiswaViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.transition.MaterialSharedAxis;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DataMahasiswa#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DataMahasiswa extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DataMahasiswa() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DataMahasiswa.
     */
    // TODO: Rename and change types and number of parameters
    public static DataMahasiswa newInstance(String param1, String param2) {
        DataMahasiswa fragment = new DataMahasiswa();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        MaterialSharedAxis enter = new MaterialSharedAxis(MaterialSharedAxis.X,true);
        MaterialSharedAxis returns = new MaterialSharedAxis(MaterialSharedAxis.X,false);

        setEnterTransition(enter);
        setReturnTransition(returns);

    }

    public void filter(String nama_mahasiswa){
        List<MahasiswaModel> filtered = new ArrayList<>();

        for(MahasiswaModel m : this.all_data){
            if(m.getNama().toLowerCase().contains(nama_mahasiswa.toLowerCase())){
                filtered.add(m);
            }
        }

        if(filtered.isEmpty()){
            Toast.makeText(this.getContext(),"Tidak ditemukan nama mahasiswa", Toast.LENGTH_LONG).show();
        }

        if(this.mahasiswa_adapter != null){
            this.mahasiswa_adapter.filter(filtered);
        }

    }




    private List<MahasiswaModel> all_data;
    private  MahasiswaViewModel mahasiswaViewModel;
    private RecyclerView recyclerView;
    private MahasiswaAdapter mahasiswa_adapter;
    private MahasiswaInterface dataMahasiswa;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mahasiswaViewModel = new ViewModelProvider(requireActivity()).get(MahasiswaViewModel.class);
        this.recyclerView = view.findViewById(R.id.data_mahasiswa);

        String API_URL = "https://stmikpontianak.cloud/011100862/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.dataMahasiswa = retrofit.create(MahasiswaInterface.class);
        Call<List<MahasiswaModel>> getData = this.dataMahasiswa.getDataMahasiswa();

        if(mahasiswaViewModel.getAllData().getValue() == null){

            getData.enqueue(new Callback<List<MahasiswaModel>>() {
                @Override
                public void onResponse(Call<List<MahasiswaModel>> call, Response<List<MahasiswaModel>> response) {
                    all_data = response.body();
                    mahasiswa_adapter = new MahasiswaAdapter(getContext(),all_data, new OnClickListenerMahasiswaDetail(){
                        @Override
                        public void OnClick(MahasiswaModel data) {
                            mahasiswaViewModel.setDataMahasiswa(data);
                            mahasiswaViewModel.setAllData(all_data);
                            Navigation.findNavController(view).navigate(R.id.to_mahasiswaDetail);
                        }
                    });
                    recyclerView.setAdapter(mahasiswa_adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                }

                @Override
                public void onFailure(Call<List<MahasiswaModel>> call, Throwable t) {
                    Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            this.all_data = mahasiswaViewModel.getAllData().getValue();
            this.mahasiswa_adapter = new MahasiswaAdapter(getContext(),all_data, new OnClickListenerMahasiswaDetail(){
                @Override
                public void OnClick(MahasiswaModel data) {
                    mahasiswaViewModel.setDataMahasiswa(data);
                    Navigation.findNavController(view).navigate(R.id.to_mahasiswaDetail);
                }
            });
            this.recyclerView.setAdapter(mahasiswa_adapter);
            this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }

        this.recyclerView.post(new Runnable() {
            @Override
            public void run() {
                RecyclerViewOffsetMemory data_pos = mahasiswaViewModel.getOffset().getValue();
                if(data_pos != null){
                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    if (layoutManager != null) {
                        layoutManager.scrollToPositionWithOffset(data_pos.position, data_pos.offset);
                    }
                }
            }
        });

        MaterialButton src_btn = view.findViewById(R.id.src_btn);
        TextInputEditText input = view.findViewById(R.id.tf_nama);


        Handler handler = new Handler(Looper.getMainLooper());
        final Runnable[] runnable = {null};
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                handler.removeCallbacks(runnable[0]);
            }

            @Override
            public void afterTextChanged(Editable s) {
                runnable[0] = new Runnable() {
                    @Override
                    public void run() {
                        String query = s.toString();

                        if (input.getText().toString().isEmpty() && mahasiswa_adapter != null) {
                            mahasiswa_adapter.filter(all_data);
                        } else {
                            filter(input.getText().toString());
                        }
                    }
                };
                handler.postDelayed(runnable[0], 300);
            }
        });

        src_btn.setOnClickListener(v ->{
            if(input.getText().toString().isEmpty() && mahasiswa_adapter != null){
                mahasiswa_adapter.filter(all_data);
                return;
            }

            filter(input.getText().toString());
        });

        SwipeRefreshLayout swiper = view.findViewById(R.id.swiper_fresh);

        swiper.setOnRefreshListener(() -> {
           this.LoadData();
           swiper.setRefreshing(false);
        });

        FloatingActionButton fab_add = view.findViewById(R.id.fab_add);
        fab_add.setOnClickListener(v ->{
            MahasiswaBottomSheed m = new MahasiswaBottomSheed(new ReloadListenerMahasiswaData(){
                @Override
                public void Reload() {
                    super.Reload();
                    LoadData();
                }
            });
            m.show(getParentFragmentManager(),"Mahasiswa Bottom Sheet");
        });

    }

    private void LoadData(){
        Call<List<MahasiswaModel>> newData = dataMahasiswa.getDataMahasiswa();
        newData.enqueue(new Callback<List<MahasiswaModel>>() {
            @Override
            public void onResponse(Call<List<MahasiswaModel>> call, Response<List<MahasiswaModel>> response) {
                all_data = response.body();
                mahasiswaViewModel.setAllData(all_data);
                if(mahasiswa_adapter != null){
                    mahasiswa_adapter.filter(all_data);
                }
                Log.d("Update data", "Success update data");
            }

            @Override
            public void onFailure(Call<List<MahasiswaModel>> call, Throwable t) {
                Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        LinearLayoutManager layout = (LinearLayoutManager) this.recyclerView.getLayoutManager();
        if(layout != null){
            int position = layout.findFirstVisibleItemPosition();
            View view = layout.findViewByPosition(position);
            int offset = (view == null)? 0 : view.getTop();
            this.mahasiswaViewModel.setOffset(new RecyclerViewOffsetMemory(position,offset));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data_mahasiswa, container, false);
    }
}