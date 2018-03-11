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


public class ChampionAdapter extends RecyclerView.Adapter<ChampionAdapter.ViewHolder>  {

    private Context context;
    private ArrayList<Event> eventList;
    private FragmentManager fragmentManager ;
    private DBHandler dba ;


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView teamA , teamB , venue , time ,description;
        public ImageView versus ;

        public ViewHolder(View view) {
            super(view);
            teamA =  view.findViewById(R.id.teamA);
            venue = view.findViewById(R.id.venue);
            time =  view.findViewById(R.id.time);
            teamB = view.findViewById(R.id.teamsecond);
            description = view.findViewById(R.id.description);
            versus = view.findViewById(R.id.versus);

            dba = new DBHandler(context, null, null, 1);
        }
    }

    public ChampionAdapter(Context context, ArrayList<Event> eventList ){
        this.context = context;
        this.eventList = eventList;
        this.fragmentManager = fragmentManager ;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_result_day_card, parent, false);

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
        holder.description.setText(event.getDescription());

        if (event.getType() == 1){ // athletics
            Drawable drawable = context.getResources().getDrawable(R.drawable.ath) ;
            holder.versus.setImageDrawable(drawable);
        }
        else if (event.getType() == 2){ // badminton boys
            holder.versus.setImageDrawable(context.getResources().getDrawable(R.drawable.bad));
        }
        else if (event.getType() == 3){ // badminton girls
            holder.versus.setImageDrawable(context.getResources().getDrawable(R.drawable.bad));
        }
        else if (event.getType() == 4){ // basketball boys
            holder.versus.setImageDrawable(context.getResources().getDrawable(R.drawable.bask));
        }
        else if (event.getType() == 5){ // basketball boys
            holder.versus.setImageDrawable(context.getResources().getDrawable(R.drawable.bask));
        }
        else if (event.getType() == 6){ // chess
            holder.versus.setImageDrawable(context.getResources().getDrawable(R.drawable.ches));
        }
        else if (event.getType() == 7){ // cricket
            holder.versus.setImageDrawable(context.getResources().getDrawable(R.drawable.crick));
        }
        else if (event.getType() == 8){ // football
            holder.versus.setImageDrawable(context.getResources().getDrawable(R.drawable.foot));
        }
        else if (event.getType() == 9){ // tt boys
            holder.versus.setImageDrawable(context.getResources().getDrawable(R.drawable.tt));
        }
        else if (event.getType() == 10){ // tt girls
            holder.versus.setImageDrawable(context.getResources().getDrawable(R.drawable.tt));
        }
        else if (event.getType() == 11){ // volley boys
            holder.versus.setImageDrawable(context.getResources().getDrawable(R.drawable.volley_boys));
        }
        else if (event.getType() == 12){ // volley girls
            holder.versus.setImageDrawable(context.getResources().getDrawable(R.drawable.volley_boys));
        }

    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }







}
