package iitropar.aarohan;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;


public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder>  {

    private Context context;
    private ArrayList<Event> eventList;
    private FragmentManager fragmentManager ;
    private DBHandler dba ;


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView teamA , teamB , venue , time ;

        public ViewHolder(View view) {
            super(view);
            teamA =  view.findViewById(R.id.teamA);
            venue = view.findViewById(R.id.venue);
            time =  view.findViewById(R.id.time);
            teamB = view.findViewById(R.id.teamsecond);

            dba = new DBHandler(context, null, null, 1);
        }
    }

    public EventAdapter(Context context, ArrayList<Event> eventList , FragmentManager fragmentManager){
        this.context = context;
        this.eventList = eventList;
        this.fragmentManager = fragmentManager ;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.schedule_day_card, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Event event = eventList.get(position);
        // setting the value of perameter
        holder.teamA.setText(event.getTeamA());
        holder.venue.setText(event.getVenue());
        holder.time.setText(event.getTime());
        holder.teamB.setText(event.getTeamB());

    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }






  
}
