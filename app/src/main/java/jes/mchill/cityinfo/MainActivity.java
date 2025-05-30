package jes.mchill.cityinfo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import jes.mchill.cityinfo.fragments.FragmentA;
import jes.mchill.cityinfo.fragments.FragmentB;
import jes.mchill.cityinfo.fragments.FragmentC;
import jes.mchill.cityinfo.fragments.FragmentD;
import jes.mchill.cityinfo.fragments.FragmentE;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button fragmentA = findViewById(R.id.btnA);
        Button fragmentB = findViewById(R.id.btnB);
        Button fragmentC = findViewById(R.id.btnC);
        Button fragmentD = findViewById(R.id.btnD);
        Button fragmentE = findViewById(R.id.btnE);

        fragmentA.setOnClickListener(listener);
        fragmentB.setOnClickListener(listener);
        fragmentC.setOnClickListener(listener);
        fragmentD.setOnClickListener(listener);
        fragmentE.setOnClickListener(listener);


    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Fragment fragment;

            if (view.getId() == R.id.btnA) {
                fragment = new FragmentA();
            } else if (view.getId() == R.id.btnB) {
                fragment = new FragmentB();
            } else if (view.getId() == R.id.btnC) {
                fragment = new FragmentC();
            } else if (view.getId() == R.id.btnD) {
                fragment = new FragmentD();
            } else if (view.getId() == R.id.btnE) {
                fragment = new FragmentE();
            } else {
                fragment = new FragmentA();
            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame, fragment)
                    .commit();
        }
    };
}
