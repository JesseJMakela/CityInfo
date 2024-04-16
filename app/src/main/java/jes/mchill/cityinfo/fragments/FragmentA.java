package jes.mchill.cityinfo.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import jes.mchill.cityinfo.MunicipalityData;
import jes.mchill.cityinfo.MunicipalityDataRetriever;
import jes.mchill.cityinfo.R;
import jes.mchill.cityinfo.SharedViewModel;
import jes.mchill.cityinfo.WeatherData;
import jes.mchill.cityinfo.WeatherDataRetriever;

public class FragmentA extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private TextView txtPopulationData;
    private TextView txtWeatherData;
    private EditText editTextLocation;
    private SharedViewModel sharedViewModel;

    public FragmentA() {
        // Required empty public constructor
    }

    public static FragmentA newInstance(String param1, String param2) {
        FragmentA fragment = new FragmentA();
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
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        editTextLocation = view.findViewById(R.id.txtEditLocation);
        Button findBtn = view.findViewById(R.id.findBtn);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        findBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFindBtnClick(v);
            }
        });
        return view;
    }

    public void onFindBtnClick(View view) {
        Log.d("Lut", "Nappula toimii");
        String location = editTextLocation.getText().toString();

        MunicipalityDataRetriever mr = new MunicipalityDataRetriever();
        WeatherDataRetriever wr = new WeatherDataRetriever();

        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            @Override
            public void run() {
                Context context = getContext();
                final ArrayList<MunicipalityData> populationData = mr.getData(context, location);
                final WeatherData weatherData = wr.getWeatherData(location);

                if (getActivity() == null || getActivity().isFinishing())
                    return;

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        sharedViewModel.setPopulationData(populationData);
                        sharedViewModel.setWeatherData(weatherData);

                        // Check if the search is successful
                        if (populationData != null || weatherData != null) {
                            // Display a Toast message
                            Toast.makeText(context, "Search successful!", Toast.LENGTH_SHORT).show();
                        } else {
                            // Display a Toast message
                            Toast.makeText(context, "Search failed. Please try again.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}