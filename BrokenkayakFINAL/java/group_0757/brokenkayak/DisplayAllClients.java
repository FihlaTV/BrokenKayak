package group_0757.brokenkayak;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import backend.BookingSystem;

/**
 * This activity page displays all clients stored on the system in a list.
 */
public class DisplayAllClients extends AppCompatActivity {

    /** to initialize and access the userMap in the backend bookingsystem class. */
    private BookingSystem bs;
    /** key identifier of the user. */
    public static final String SYSTEM_KEY = "systemKey";
    public static final String CURRENT_USER_KEY = "currentUserkey";
    /** current logged user. */
    String curr_user;

    /**
     * Creates the display all clients page.
     *
     * @param savedInstanceState activity's saved state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all_clients);

        Intent intent = getIntent();

        // Use the key to extract EMAIL_ID_KEY that was put into the login intent
        bs = (BookingSystem) intent.getSerializableExtra(Login.SYSTEM_KEY);
        // String curr_user = (String) intent.getSerializableExtra(Login.CURRENT_USER_KEY);
        curr_user = (String) intent.getSerializableExtra(Login.CURRENT_USER_KEY);

        populateListView();
    }

    /**
     * adds each user info from the system onto the display list.
     */
    private void populateListView() {

        ArrayList users = new ArrayList();
        for (String i : bs.userMap.keySet()) {
            users.add(bs.userMap.get(i));
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,   // Context for the activity
                R.layout.da_button, //Layout to use
                users);   // Items to be displayed

        //Configure the list view

        ListView list = (ListView) findViewById(R.id.clientListView);
        list.setAdapter(adapter);
    }
}
//    private void registerClickCallBack() {
//
//        ListView list = (ListView) findViewById(R.id.flightListView);
//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (position = 0)
//            }
//                TextView textView = (TextView) view;
//                position = position + 1;
//                String message = "You booked this itinerary:" + " " + textView.getText().toString() + "." + "We used your profile information to do this. Thanks for making it BrokenKayak!";
//                booked.add(textView.getText().toString());
//
//                bookingToUser.put(bs.userMap.get(ClientMenu.curr_user).getEmail(), booked);
//
//                Toast.makeText(ItinerariesByCost.this, message, Toast.LENGTH_LONG).show();
//
//            }
//        });
//
//    }
//
//
//
//    @Override
//    public void onItemClick(View v) {
//        if (v.getId() == R.id.da_button) {
//    }
//}
