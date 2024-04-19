package jes.mchill.cityinfo;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
public class MyGenericViewHolder<T> extends RecyclerView.ViewHolder {

    private TextView year, value;

    public MyGenericViewHolder(@NonNull View itemView) {
        super(itemView);
        year = itemView.findViewById(R.id.txtYear);
        value = itemView.findViewById(R.id.txtValue);
    }

    public void bind(T item) {
        if (item instanceof SelfsufficiencyData) {
            SelfsufficiencyData data = (SelfsufficiencyData) item;
            year.setText(String.valueOf(data.getYear()));
            value.setText(String.valueOf(data.getWorkplaceSelfSufficiency()));
        } else if (item instanceof PopulationData) {
            PopulationData data = (PopulationData) item;
            year.setText(String.valueOf(data.getYear()));
            value.setText(String.valueOf(data.getPopulation()));
        }
    }
}
