package com.daberdev.learn;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomePage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomePage extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomePage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomePage.
     */
    // TODO: Rename and change types and number of parameters
    public static HomePage newInstance(String param1, String param2) {
        HomePage fragment = new HomePage();
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MaterialButton btn_grid_layout = view.findViewById(R.id.grid_layout);
        MaterialButton btn_frame_layout = view.findViewById(R.id.frame_layout);
        MaterialButton btn_constrain_layout = view.findViewById(R.id.constrain_layout);
        MaterialButton btn_hotel_app = view.findViewById(R.id.hotel_app);
        MaterialButton btn_recycler_view_app= view.findViewById(R.id.recycler_view_app);
        MaterialButton btn_data_mahasiswa = view.findViewById(R.id.data_mahasiswa);
        btn_grid_layout.setOnClickListener(v ->{
            Navigation.findNavController(view).navigate(R.id.to_gridLayout);
        });
        btn_frame_layout.setOnClickListener(v ->{
            Navigation.findNavController(view).navigate(R.id.to_frameLayout);
        });
        btn_constrain_layout.setOnClickListener(v ->{
            Navigation.findNavController(view).navigate(R.id.to_constrainLayout);
        });
        btn_hotel_app.setOnClickListener(v ->{
            Navigation.findNavController(view).navigate(R.id.to_HotelApp);
        });
        btn_recycler_view_app.setOnClickListener(v ->{
            Navigation.findNavController(view).navigate(R.id.to_RecyclerViewApp);
        });
        btn_data_mahasiswa.setOnClickListener(v ->{
            Navigation.findNavController(view).navigate(R.id.to_dataMahasiswa);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_page, container, false);
    }
}