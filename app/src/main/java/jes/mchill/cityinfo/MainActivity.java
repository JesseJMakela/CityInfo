package jes.mchill.cityinfo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import jes.mchill.cityinfo.fragments.FragmentA;
import jes.mchill.cityinfo.fragments.FragmentB;
import jes.mchill.cityinfo.fragments.FragmentC;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button fragmentA = findViewById(R.id.btnA);
        Button fragmentB = findViewById(R.id.btnB);
        Button fragmentC = findViewById(R.id.btnC);

        fragmentA.setOnClickListener(listener);
        fragmentB.setOnClickListener(listener);
        fragmentC.setOnClickListener(listener);


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
            } else {
                fragment = new FragmentA();
            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame, fragment)
                    .commit();
        }
    };
}
