package iitropar.aarohan;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import com.synnapps.carouselview.CarouselView ;
import com.synnapps.carouselview.ImageListener ;

public class HomeEventFragment extends Fragment {
    // showing event that get started in 30 minutes
    private View myView;
    private Context mainContext;
    private DBHandler dba;
    private ArrayList<Event> eventList;
    private ArrayList<Event> upcomingEventList;
    private RecyclerView recyclerView;
    private static UpcomingEventAdapter upcomingEventAdapter;
    private static LinearLayoutManager layoutManager;
    CarouselView customCarouselView;
    int NUMBER_OF_PAGES = 3;
    int[] sampleImages = {R.drawable.i1, R.drawable.i2, R.drawable.i3};

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };
    private ImageView athletics , badmintonBoys , badmintonGirls , basketballBoys , basketballGirls , chess , cricket , football , lawnBoys , lawnGirls , tableBoys , tableGirls , volleyBoys , volleyGirls ;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.fragment_home_sub_event, container, false);
        mainContext = myView.getContext();
        customCarouselView = (CarouselView) myView.findViewById(R.id.carouselView);
        customCarouselView.setPageCount(NUMBER_OF_PAGES);
        customCarouselView.setImageListener(imageListener);
        setHasOptionsMenu(true);

        athletics = myView.findViewById(R.id.athletics);
        badmintonBoys = myView.findViewById(R.id.badmintionBoys);
        badmintonGirls = myView.findViewById(R.id.badmintionGirls);
        basketballBoys = myView.findViewById(R.id.basketballBoys);
        basketballGirls = myView.findViewById(R.id.basketballGirls);
        chess = myView.findViewById(R.id.chess);
        cricket = myView.findViewById(R.id.cricket);
        football = myView.findViewById(R.id.football);
        lawnBoys = myView.findViewById(R.id.lawnBoys);
        lawnGirls = myView.findViewById(R.id.lawnGirls);
        tableBoys = myView.findViewById(R.id.tableBoys);
        tableGirls = myView.findViewById(R.id.tableGirls);
        volleyBoys = myView.findViewById(R.id.volleyBoys);
        volleyGirls = myView.findViewById(R.id.volleyGirls);

        athletics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final BottomSheetDialogFragment userViewBS = EventBS.newInstance("Athletics","Lorem <i>ipsum</i> dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
                userViewBS.show(getFragmentManager(), userViewBS.getTag());
            }
        });

        return myView;
    }


}
