package in.evoqis.ngo.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import in.evoqis.ngo.R;
import in.evoqis.ngo.RecentActivities;

public class ActivitiesRecyclerViewAdapter extends RecyclerView.Adapter<ActivitiesRecyclerViewAdapter.MyViewHolder> {

    private List<RecentActivities> list;
    private Context context;

    public ActivitiesRecyclerViewAdapter(List<RecentActivities> list,Context context) {
      this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_activities, parent, false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       final  RecentActivities recentActivities = list.get(position);
        holder.tvEventName.setText(recentActivities.getEventName());
        holder.tvDate.setText(recentActivities.getDate());
        holder.tvTime.setText(recentActivities.getTime());
        holder.tvVenue.setText(recentActivities.getAddress());
        

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvEventName, tvTime, tvDate, tvVenue;


        public MyViewHolder(View itemView) {
            super(itemView);
            tvEventName = itemView.findViewById(R.id.tvEventName);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvVenue = itemView.findViewById(R.id.tvVenue);
        }
    }
}
