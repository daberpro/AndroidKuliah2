package com.daberdev.learn;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.daberdev.learn.adapter.ForexAdapter;
import com.daberdev.learn.adapter.MahasiswaAdapter;
import com.daberdev.learn.model.ForexDataModel;
import com.daberdev.learn.model.ForexModel;
import com.daberdev.learn.model.MahasiswaModel;
import com.daberdev.learn.services.ForexInterface;
import com.daberdev.learn.services.MahasiswaInterface;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Forex#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Forex extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Forex() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Forex.
     */
    // TODO: Rename and change types and number of parameters
    public static Forex newInstance(String param1, String param2) {
        Forex fragment = new Forex();
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
    }

    private ForexModel data;
    private Map<String,String> currency_description;
    private ForexAdapter forex_adapter;
    private TextView tanggal;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recycler = view.findViewById(R.id.recycler);
        tanggal = view.findViewById(R.id.tanggal);

        String API_URL = "https://openexchangerates.org/api/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ForexInterface forex = retrofit.create(ForexInterface.class);
        Call<ForexModel> getData = forex.GetForexData("09e84604b1ff40c8805179afc1aba8e8");
        Call<Map<String,String>> getDescription = forex.GetCurrencyDescription();

        getDescription.enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                currency_description = response.body();

                getData.enqueue(new Callback<ForexModel>() {
                    @Override
                    public void onResponse(Call<ForexModel> call, Response<ForexModel> response) {
                        data = response.body();
                        List<ForexDataModel> list = new ArrayList<>();
                        Map<String,Double> _rates = data.getRates();
                        setTimestamp(data.getTimestamp());

                        for(String key : _rates.keySet()){
                            ForexDataModel _data = new ForexDataModel();
                            _data.setCurrency(key);
                            _data.setValue(_rates.get(key));
                            _data.setDescription(currency_description.get(key));
                            list.add(_data);
                        }

                        forex_adapter = new ForexAdapter(getContext(),list);
                        recycler.setAdapter(forex_adapter);
                        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

                    }

                    @Override
                    public void onFailure(Call<ForexModel> call, Throwable t) {
                        Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {

            }
        });

        SwipeRefreshLayout swiper = view.findViewById(R.id.swiper_fresh);

        swiper.setOnRefreshListener(() -> {
            this.LoadData(view);
            swiper.setRefreshing(false);
        });
    }

    private void setTimestamp(long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        format.setTimeZone(TimeZone.getTimeZone("Asia/Jakarta"));
        String dateTime = format.format(new Date(timestamp * 1000));

        tanggal.setText("Tanggal dan Waktu (UTC): " + dateTime);
    }


    private void LoadData(View view){


        String API_URL = "https://openexchangerates.org/api/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ForexInterface forex = retrofit.create(ForexInterface.class);
        Call<ForexModel> getData = forex.GetForexData("09e84604b1ff40c8805179afc1aba8e8");
        Call<Map<String,String>> getDescription = forex.GetCurrencyDescription();

        getDescription.enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                currency_description = response.body();

                getData.enqueue(new Callback<ForexModel>() {
                    @Override
                    public void onResponse(Call<ForexModel> call, Response<ForexModel> response) {
                        data = response.body();
                        List<ForexDataModel> list = new ArrayList<>();
                        Map<String,Double> _rates = data.getRates();
                        setTimestamp(data.getTimestamp());

                        for(String key : _rates.keySet()){
                            ForexDataModel _data = new ForexDataModel();
                            _data.setCurrency(key);
                            _data.setValue(_rates.get(key));
                            _data.setDescription(currency_description.get(key));
                            list.add(_data);
                        }

                        forex_adapter.Reload(list);

                    }

                    @Override
                    public void onFailure(Call<ForexModel> call, Throwable t) {
                        Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forex, container, false);
    }
}