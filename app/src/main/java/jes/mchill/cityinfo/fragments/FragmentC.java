package jes.mchill.cityinfo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import jes.mchill.cityinfo.SelfsufficiencyData;
import jes.mchill.cityinfo.R;
import jes.mchill.cityinfo.SharedViewModel;
import jes.mchill.cityinfo.MyGenericAdapter;

public class FragmentC extends Fragment {

    private SharedViewModel sharedViewModel;
    private RecyclerView recyclerView;
    private MyGenericAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_c, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        adapter = new MyGenericAdapter(requireContext(), new ArrayList<>());
        recyclerView.setAdapter(adapter);

        sharedViewModel.getSelfSufficiencyData().observe(getViewLifecycleOwner(), new Observer<ArrayList<SelfsufficiencyData>>() {
            @Override
            public void onChanged(ArrayList<SelfsufficiencyData> populationData) {
                if (populationData != null) {
                    adapter.setItems(populationData);
                }
            }
        });

        return view;
    }
}

