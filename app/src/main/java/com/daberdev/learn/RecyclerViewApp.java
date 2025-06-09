package com.daberdev.learn;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daberdev.learn.adapter.NegaraAppAdapter;
import com.daberdev.learn.model.NegaraModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecyclerViewApp#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecyclerViewApp extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RecyclerViewApp() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecyclerViewApp.
     */
    // TODO: Rename and change types and number of parameters
    public static RecyclerViewApp newInstance(String param1, String param2) {
        RecyclerViewApp fragment = new RecyclerViewApp();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recycler_view_app, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recycle = view.findViewById(R.id.recycler);
        List<NegaraModel> data = new ArrayList<>();

        data.add(new NegaraModel("Albania","https://icons.iconarchive.com/icons/custom-icon-design/all-country-flag/128/Albania-Flag-icon.png","Belum ada deskripsi"));
        data.add(new NegaraModel("Hungary","https://icons.iconarchive.com/icons/custom-icon-design/all-country-flag/128/Hungary-Flag-icon.png","Belum ada deskripsi"));
        data.add(new NegaraModel("Slovenia","https://icons.iconarchive.com/icons/custom-icon-design/all-country-flag/128/Slovenia-Flag-icon.png","Belum ada deskripsi"));

        recycle.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recycle.setAdapter(new NegaraAppAdapter(this.getContext(),data));

    }
}