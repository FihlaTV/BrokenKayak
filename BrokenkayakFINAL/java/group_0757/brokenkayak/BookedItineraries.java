package group_0757.brokenkayak;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Button;
import android.content.Intent;
import java.util.ArrayList;

import backend.BookingSystem;

/**
 * This class displays user's booked itineraries.
 */
public class BookedItineraries extends AppCompatActivity  implements View.OnClickListener {

    /** key identifier from the Login class. */
    public static final String CURRENT_USER_KEY = "currentUserkey";
    public static final String SYSTEM_KEY = "systemKey";
    /** logout button. */
    Button buttonLogout;
    /** to initialize and access the userMap in the backend bookingSystem class */
    private BookingSystem bs;
    /** current logged user. */
    String curr_user;

    /**
     * Initializes activity page, noting current logged in user.
     *
     * @param savedInstanceState Activity's previously saved state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked_itineraries);

        Intent intent = getIntent();

        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        buttonLogout.setOnClickListener(this);

        bs = (BookingSystem) intent.getSerializableExtra(ClientMenu.SYSTEM_KEY);
        curr_user = (String) intent.getSerializableExtra(ClientMenu.CURRENT_USER_KEY);


        displayBookedItineraries();
    }

    /**
     * Show the booked itineraries of the current user.
     */
    private void displayBookedItineraries() {

        ArrayList<String> NoBookings = new ArrayList<String>();

        //If the user already booked an itinerary
        if (ItinerariesByCost.bookingToUser.containsKey((bs.userMap.get(ClientMenu.curr_user).getEmail()))) {

            ArrayList bookings = ItinerariesByCost.bookingToUser.get(bs.userMap.get(ClientMenu.curr_user).getEmail());


            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    this,   // Context for the activity
                    R.layout.da_item, //Layout to use
                    bookings);   // Items to be displayed

            //Configure the list view

            ListView list = (ListView) findViewById(R.id.bookedItinerariesListview);
            list.setAdapter(adapter);

            //If User booked when clicking searchByTime
//        }else if
//            (ItinerariesByTime.bookingToUser.containsKey((bs.userMap.get(ClientMenu.curr_user).getEmail()))) {
//
//                ArrayList bookings = ItinerariesByTime.bookingToUser.get(bs.userMap.get(ClientMenu.curr_user).getEmail());
//
//
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                        this,   // Context for the activity
//                        R.layout.da_item, //Layout to use
//                        bookings);   // Items to be displayed
//
//                //Configure the list view
//
//                ListView list = (ListView) findViewById(R.id.bookedItinerariesListview);
//                list.setAdapter(adapter);

            //If user hasn't booked anything
        }else {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    this,   // Context for the activity
                    R.layout.da_item, //Layout to use
                    NoBookings);   // Items to be displayed

            //Configure the list view

            ListView list = (ListView) findViewById(R.id.bookedItinerariesListview);
            list.setAdapter(adapter);

        }

    }

    /**
     * Log out user upon clicking logout.
     * @param v button view.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonLogout) {
            Intent intent = new Intent(this, Login.class);
            intent.putExtra(SYSTEM_KEY, bs);
            intent.putExtra(CURRENT_USER_KEY, curr_user);
            startActivity(intent);

        }
    }
}

