package jes.mchill.cityinfo.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;

import jes.mchill.cityinfo.R;
import jes.mchill.cityinfo.SharedViewModel;
import jes.mchill.cityinfo.WeatherData;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentB#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentB extends Fragment {

    private SharedViewModel sharedViewModel;
    private TextView weatherTextView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentB() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentB.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentB newInstance(String param1, String param2) {
        FragmentB fragment = new FragmentB();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_b, container, false);
        weatherTextView = view.findViewById(R.id.weatherTextView);

        sharedViewModel.getWeatherData().observe(getViewLifecycleOwner(), new Observer<WeatherData>() {
            @Override
            public void onChanged(WeatherData weatherData) {
                if (weatherData != null) {
                    double temperatureInCelsius = Double.parseDouble(weatherData.getTemperature()) - 273.15;

                    TextView locationTextView = view.findViewById(R.id.locationTextView);
                    locationTextView.setText("Location: " + weatherData.getName());

                    TextView weatherTextView = view.findViewById(R.id.weatherTextView);
                    weatherTextView.setText("Weather: " + weatherData.getMain());

                    TextView descriptionTextView = view.findViewById(R.id.descriptionTextView);
                    descriptionTextView.setText("Description: " + weatherData.getDescription());

                    TextView temperatureTextView = view.findViewById(R.id.temperatureTextView);
                    temperatureTextView.setText(String.format("Temperature: %.2f°C", temperatureInCelsius));

                    TextView windSpeedTextView = view.findViewById(R.id.windSpeedTextView);
                    windSpeedTextView.setText("Wind Speed: " + weatherData.getWindSpeed() + " m/s");
                }
            }
        });
        return view;
    }
}


