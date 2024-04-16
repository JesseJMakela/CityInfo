package jes.mchill.cityinfo;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
public class MyViewHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView year, population;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imgLogo);
        year = itemView.findViewById(R.id.txtYear);
        population = itemView.findViewById(R.id.txtPopulation);

    }
}
