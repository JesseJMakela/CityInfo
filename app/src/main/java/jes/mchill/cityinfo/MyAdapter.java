package jes.mchill.cityinfo;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

    Context context;
    List<MunicipalityData> items;

    public MyAdapter(Context context, List<MunicipalityData> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MunicipalityData currentItem = items.get(position);
        holder.year.setText(String.valueOf(currentItem.getYear()));
        holder.population.setText(String.valueOf(currentItem.getPopulation()));
    }


    @Override
    public int getItemCount() {

        return items.size();

    }
    public void setItems(List<MunicipalityData> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}
