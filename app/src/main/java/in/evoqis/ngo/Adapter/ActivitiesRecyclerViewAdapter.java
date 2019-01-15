package in.evoqis.ngo.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import in.evoqis.ngo.R;
import in.evoqis.ngo.RecentActivities;

public class ActivitiesRecyclerViewAdapter extends RecyclerView.Adapter<ActivitiesRecyclerViewAdapter.MyViewHolder> implements View.OnClickListener {

    private List<RecentActivities> list;
    private Context context ;
    Dialog myDialog;

    public ActivitiesRecyclerViewAdapter(List<RecentActivities> list,Context context) {
      this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_activities, parent, false);
        myDialog = new Dialog(context);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       final  RecentActivities recentActivities = list.get(position);
        holder.tvEventName.setText(recentActivities.getEventName());
        holder.tvDate.setText(recentActivities.getDate());
        holder.tvTime.setText(recentActivities.getTime());
        holder.tvVenue.setText(recentActivities.getAddress());

        holder.rlActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowPopup();
            }
        });
        

    }


        public void ShowPopup() {
            TextView txtclose;
            Button btnFollow;
            myDialog.setContentView(R.layout.custompopup);
            txtclose = (TextView) myDialog.findViewById(R.id.txtclose);
            txtclose.setText("X");
           // btnFollow = (Button) myDialog.findViewById(R.id.btnfollow);
            txtclose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myDialog.dismiss();
                }
            });
            myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            myDialog.show();
        }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View view) {

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvEventName, tvTime, tvDate, tvVenue;
        RelativeLayout rlActivities;


        public MyViewHolder(View itemView) {
            super(itemView);
            tvEventName = itemView.findViewById(R.id.tvEventName);
            tvDate = itemView.findViewById(R.id.tvDate);
            rlActivities = itemView.findViewById(R.id.rlActivities);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvVenue = itemView.findViewById(R.id.tvVenue);
        }
    }
}
