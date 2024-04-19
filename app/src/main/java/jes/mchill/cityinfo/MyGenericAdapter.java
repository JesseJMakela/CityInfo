package jes.mchill.cityinfo;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
public class MyGenericAdapter<T> extends RecyclerView.Adapter<MyGenericViewHolder<T>> {

    private Context context;
    private List<T> items;

    public MyGenericAdapter(Context context, List<T> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyGenericViewHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyGenericViewHolder<>(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyGenericViewHolder<T> holder, int position) {
        T currentItem = items.get(position);
        holder.bind(currentItem);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<T> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}
