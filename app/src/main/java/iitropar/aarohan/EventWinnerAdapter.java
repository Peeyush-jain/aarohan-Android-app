package iitropar.aarohan;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class EventWinnerAdapter extends RecyclerView.Adapter<EventWinnerAdapter.ViewHolder>  {

    private Context context;
    private ArrayList<EventWinnerModel> eventList;
    private FragmentManager fragmentManager ;
    private DBHandler dba ;


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name , first , second , third;


        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            first = view.findViewById(R.id.first);
            second = view.findViewById(R.id.second);
            third = view.findViewById(R.id.third);
            dba = new DBHandler(context, null, null, 1);
        }
    }

    public EventWinnerAdapter(Context context, ArrayList<EventWinnerModel> eventList ){
        this.context = context;
        this.eventList = eventList;
        this.fragmentManager = fragmentManager ;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_winner_fragment_card, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final EventWinnerModel event = eventList.get(position);
        // setting the value of perameter
       holder.first.setText(event.getWinner1());
       holder.name.setText(event.getEventName());
       holder.second.setText(event.getWinner2());
       holder.third.setText(event.getWinner3());
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }







}
