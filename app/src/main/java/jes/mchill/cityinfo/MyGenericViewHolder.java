package jes.mchill.cityinfo;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
public class MyGenericViewHolder<T> extends RecyclerView.ViewHolder {

    private TextView placement1, placement2;

    public MyGenericViewHolder(@NonNull View itemView) {
        super(itemView);
        placement1 = itemView.findViewById(R.id.txtYear);
        placement2 = itemView.findViewById(R.id.txtValue);
    }

    public void bind(T item) {
        ImageView imagePlacement = itemView.findViewById(R.id.imgLogo);
        if (item instanceof SelfsufficiencyData) {
            SelfsufficiencyData data = (SelfsufficiencyData) item;
            placement1.setText("Year: " + data.getYear());
            placement2.setText("Workplace self-sufficiency: " + data.getWorkplaceSelfSufficiency() + "%");
            imagePlacement.setImageResource(R.drawable.workplace);
        } else if (item instanceof PopulationData) {
            PopulationData data = (PopulationData) item;
            placement1.setText("Year: " + data.getPopulationYear());
            placement2.setText("Population: " + data.getPopulation());
            imagePlacement.setImageResource(R.drawable.people);
        } else if (item instanceof EmploymentData) {
            EmploymentData data = (EmploymentData) item;
            placement1.setText("Year: " + data.getEmploymentYear());
            placement2.setText("Employment rate: " + Math.round(data.getEmploymentRate())+"%");
            imagePlacement.setImageResource(R.drawable.businessman);
        }

    }
}
