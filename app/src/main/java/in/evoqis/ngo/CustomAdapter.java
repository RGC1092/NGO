package in.evoqis.ngo;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class


CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

        ArrayList<String> id;
        ArrayList<String> user_id;
        ArrayList<String> unique_event_id;
        Context context;

        public CustomAdapter(Context context, ArrayList<String> id, ArrayList<String> user_id, ArrayList<String> unique_event_id) {
            this.context = context;
            this.id = id;
            this.user_id = user_id;

            this.unique_event_id = unique_event_id;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // infalte the item Layout
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
            MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
            return vh;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            // set the data in items
            holder.id.setText(id.get(position));
            holder.user_id.setText(user_id.get(position));
            holder.unique_event_id.setText(unique_event_id.get(position));
            // implement setOnClickListener event on item view.
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // display a toast with person name on item click
                    Toast.makeText(context, id.get(position), Toast.LENGTH_SHORT).show();
                }
            });

        }


        @Override
        public int getItemCount() {
            return id.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView id, user_id, unique_event_id;// init the item view's

            public MyViewHolder(View itemView) {
                super(itemView);

                // get the reference of item view's
                id = (TextView) itemView.findViewById(R.id.id);
                user_id = (TextView) itemView.findViewById(R.id.user_id);
                unique_event_id = (TextView) itemView.findViewById(R.id.unique_event_id);

            }
        }
    }


