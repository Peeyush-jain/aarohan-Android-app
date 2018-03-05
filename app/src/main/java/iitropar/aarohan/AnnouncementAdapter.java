package iitropar.aarohan;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.ViewHolder>  {

    private Context context;
    private ArrayList<AnnouncementModel> dataList;
   


    public class ViewHolder extends RecyclerView.ViewHolder {
       public TextView name , description , time ;
        public ViewHolder(View view) {
            super(view);
            name =  view.findViewById(R.id.title);
            time =  view.findViewById(R.id.time);
            description = view.findViewById(R.id.description);
            
        }
    }

    public AnnouncementAdapter(Context context, ArrayList<AnnouncementModel> dataList){
        this.context = context;
        this.dataList = dataList;
      
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.announcement_card, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        AnnouncementModel announcement = dataList.get(position);
        // setting the value of perameter
        holder.name.setText(announcement.getTitle());
        holder.time.setText(announcement.getTime());
        holder.description.setText(announcement.getDescription());
        



    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


}
