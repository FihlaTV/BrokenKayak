package group_0757.brokenkayak;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import backend.BookingSystem;
import backend.Itinerary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.view.View;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.Toast;

/**
 * This class displays searched itinerary results sorted by cost.
 */
public class ItinerariesByCost extends AppCompatActivity implements View.OnClickListener {

    /** Map with booked itineraries */
    //static ArrayList<String> booked = new ArrayList<String>();
    static Map<String, ArrayList> bookingToUser = new HashMap<String, ArrayList>();
    /** itineraries button */
    Button buttonItineraries;
    /** to initialize and access the userMap in the backend bookingSystem class. */
    private BookingSystem bs;
    /** current logged user. */
    String curr_user;
    /** key identifier from the Login class. */
    public static final String CURRENT_USER_KEY = "currentUserkey";
    public static final String SYSTEM_KEY = "systemKey";
    /** message after booking an itinerary*/
    String yeah;


    /**
     * Creates the Itineraries by cost page.
     *
     * @param savedInstanceState activity's saved state.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itineraries_by_cost);
        yeah = "";

        Intent intent = getIntent();

        buttonItineraries = (Button) findViewById(R.id.buttonItineraries);
        buttonItineraries.setOnClickListener(this);

        bs = (BookingSystem) intent.getSerializableExtra(ClientMenu.SYSTEM_KEY);
        curr_user = (String) intent.getSerializableExtra(ClientMenu.CURRENT_USER_KEY);


        populateListView();

        registerClickCallBack();
    }

    /**
     * The class is for selecting an itinerary from the sorted results and book it under
     * its account.
     *
     */
    public void registerClickCallBack() {

        ListView list = (ListView) findViewById(R.id.flightListView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * Show toast message upon selecting a desired itinerary to book.
             *
             * @param parent
             * @param view
             * @param position
             * @param id
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view;
                position = position + 1;
                yeah = textView.getText().toString();
                String message = "You booked this itinerary:" + " " + textView.getText().toString() + "."+
                        "We used your profile information to do this. Thanks for making it BrokenKayak!";

                Toast.makeText(ItinerariesByCost.this, message, Toast.LENGTH_LONG).show();
                mapBookingToUser();
            }


            //booked.add(textView.getText().toString());

            /**
             * Store desired itineraries to current user.
             */
            public void mapBookingToUser(){

                ArrayList booked = new ArrayList();
                ArrayList bookedd = new ArrayList();

                if (bookingToUser.containsKey((bs.userMap.get(ClientMenu.curr_user).getEmail()))) {
                    bookedd = bookingToUser.get((bs.userMap.get(ClientMenu.curr_user).getEmail()));
                    bookedd.add(yeah);
                    bookingToUser.put(bs.userMap.get(ClientMenu.curr_user).getEmail(), bookedd);
                }else{
                    booked.add(yeah);
                    bookingToUser.put(bs.userMap.get(ClientMenu.curr_user).getEmail(), booked);

                }



            }
        });

    }

    /**
     * Fill in the page with a list of search results sorted by costs.
     */
    private void populateListView() {
        ArrayList<String> arList = new ArrayList<String>();
        ArrayList<Itinerary> al;
        bs.searchItinerary(SearchFlights.date, SearchFlights.origin, SearchFlights.destination);
        al = BookingSystem.sortResult("cost");
        for (Itinerary it: al) {
            arList.add(it.toString());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, R.layout.da_item, arList);

//        String[] myItems = {"Blue", "Green", "Purple", "Red"};
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                this,   // Context for the activity
//                R.layout.da_item, //Layout to use
//                myItems);   // Items to be displayed

        ListView list = (ListView) findViewById(R.id.flightListView);
        list.setAdapter(adapter);
    }

    /**
     * Direct to Booked Itineraries activity upon clicking Itineraries button.
     * @param v button view
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonItineraries) {
            Intent intent = new Intent(this, BookedItineraries.class);
            intent.putExtra(SYSTEM_KEY, bs);
            intent.putExtra(CURRENT_USER_KEY, curr_user);
            startActivity(intent);
        }
    }
}

