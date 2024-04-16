package jes.mchill.cityinfo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

import jes.mchill.cityinfo.MunicipalityData;
import jes.mchill.cityinfo.R;
import jes.mchill.cityinfo.SharedViewModel;

public class FragmentC extends Fragment {

    private SharedViewModel sharedViewModel;
    private TextView populationTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_c, container, false);
        populationTextView = view.findViewById(R.id.populationTextView);

        sharedViewModel.getPopulationData().observe(getViewLifecycleOwner(), new Observer<ArrayList<MunicipalityData>>() {
            @Override
            public void onChanged(ArrayList<MunicipalityData> populationData) {
                if (populationData != null) {
                    TextView populationTextView = view.findViewById(R.id.populationTextView);

                    StringBuilder populationInfo = new StringBuilder();

                    for (MunicipalityData data : populationData) {
                        populationInfo.append("Year: ").append(data.getYear())
                                .append(" Population: ").append(data.getPopulation()).append("\n");
                    }

                    populationTextView.setText(populationInfo.toString());
                }
            }
        });
        return view;
    }
}