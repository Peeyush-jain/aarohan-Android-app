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
import android.widget.RelativeLayout;

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
    int NUMBER_OF_PAGES = 6;
    int[] sampleImages = {R.drawable.i1, R.drawable.i2, R.drawable.i3,R.drawable.p5 ,R.drawable.p6 , R.drawable.p10 };

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };
    private RelativeLayout athletics, badmintonBoys   , basketballBoys   , chess , cricket , football   , tableBoys  , volleyBoys  ;

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

        basketballBoys = myView.findViewById(R.id.basketballBoys);

        chess = myView.findViewById(R.id.chess);
        cricket = myView.findViewById(R.id.cricket);
        football = myView.findViewById(R.id.football);


        tableBoys = myView.findViewById(R.id.tableBoys);

        volleyBoys = myView.findViewById(R.id.volleyBoys);


        athletics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final BottomSheetDialogFragment userViewBS = EventBS.newInstance("Athletics","yellow","Lorem <i>ipsum</i> dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
                userViewBS.show(getFragmentManager(), userViewBS.getTag());
            }
        });

        badmintonBoys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final BottomSheetDialogFragment userViewBS = EventBS.newInstance("Badminton","brown","Lorem <i>ipsum</i> dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
                userViewBS.show(getFragmentManager(), userViewBS.getTag());
            }
        });

        basketballBoys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final BottomSheetDialogFragment userViewBS = EventBS.newInstance("BasketBall","red","Lorem <i>ipsum</i> dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
                userViewBS.show(getFragmentManager(), userViewBS.getTag());
            }
        });

        chess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final BottomSheetDialogFragment userViewBS = EventBS.newInstance("Chess","red","Lorem <i>ipsum</i> dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
                userViewBS.show(getFragmentManager(), userViewBS.getTag());
            }
        });

        football.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final BottomSheetDialogFragment userViewBS = EventBS.newInstance("Football","lightgreen","Lorem <i>ipsum</i> dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
                userViewBS.show(getFragmentManager(), userViewBS.getTag());
            }
        });

        cricket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final BottomSheetDialogFragment userViewBS = EventBS.newInstance("Cricket","skyblue","Lorem <i>ipsum</i> dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
                userViewBS.show(getFragmentManager(), userViewBS.getTag());
            }
        });



        tableBoys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final BottomSheetDialogFragment userViewBS = EventBS.newInstance("Table Tennis","green","Lorem <i>ipsum</i> dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
                userViewBS.show(getFragmentManager(), userViewBS.getTag());
            }
        });
        volleyBoys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final BottomSheetDialogFragment userViewBS = EventBS.newInstance("VolleyBall","purple","Lorem <i>ipsum</i> dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
                userViewBS.show(getFragmentManager(), userViewBS.getTag());
            }
        });


        return myView;
    }


}
