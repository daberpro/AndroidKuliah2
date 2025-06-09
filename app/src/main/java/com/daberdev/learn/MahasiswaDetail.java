package com.daberdev.learn;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daberdev.learn.databinding.ActivityMainBinding;
import com.daberdev.learn.databinding.FragmentMahasiswaDetailBinding;
import com.daberdev.learn.model.MahasiswaModel;
import com.daberdev.learn.viewmodel.MahasiswaViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.transition.MaterialSharedAxis;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MahasiswaDetail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MahasiswaDetail extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MahasiswaDetail() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MahasiswaDetail.
     */
    // TODO: Rename and change types and number of parameters
    public static MahasiswaDetail newInstance(String param1, String param2) {
        MahasiswaDetail fragment = new MahasiswaDetail();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MaterialButton btn = view.findViewById(R.id.back);
        btn.setOnClickListener(v ->{
            Navigation.findNavController(view).navigate(R.id.to_dataMahasiswa);
        });
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentMahasiswaDetailBinding binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_mahasiswa_detail,
                container,
                false
        );

        MahasiswaViewModel viewModel = new ViewModelProvider(requireActivity()).get(MahasiswaViewModel.class);
        MahasiswaModel data = viewModel.getMahasiswaData().getValue();
        binding.setData(data);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}