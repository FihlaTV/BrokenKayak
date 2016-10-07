package group_0757.brokenkayak;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import backend.BookingSystem;


/**
 * This class displays the client menu after a client logs in. The client may
 * check the profile, search flights/itineraries, view booked itineraries and logout.
 */

public class ClientMenu extends AppCompatActivity implements View.OnClickListener {

    /** to initialize and access the userMap in the backend bookingSystem class. */
    private BookingSystem bs;
    /** key identifier from the Login class. */
    public static final String SYSTEM_KEY = "systemKey";
    public static final String CURRENT_USER_KEY = "currentUserkey";
    /** current logged user. */
    static String curr_user;

    /** welcome text. */
    TextView WelcomeUserfield;
    /** client page buttons. */
    Button buttonMyProfile;
    Button buttonSearchFlights;
    Button buttonItineraries;
    Button buttonLogout;

    /**
     * Creates the Client page, has a welcome text.
     *
     * @param savedInstanceState activity's saved state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_menu);

        // Get the intent from Login or MyProfile
        Intent intent = getIntent();

        // Use the key to extract EMAIL_ID_KEY that was put into the login intent
        bs = (BookingSystem) intent.getSerializableExtra(Login.SYSTEM_KEY);
        curr_user = (String) intent.getSerializableExtra(Login.CURRENT_USER_KEY);

        WelcomeUserfield = (TextView) findViewById(R.id.WelcomeUser);
        WelcomeUserfield.setText("Welcome, " + bs.userMap.get(curr_user).getFirstName());


        buttonMyProfile = (Button) findViewById(R.id.buttonMyProfile);
        buttonSearchFlights = (Button) findViewById(R.id.buttonSearchFlights);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        buttonItineraries = (Button) findViewById(R.id.buttonItineraries);

        buttonMyProfile.setOnClickListener(this);
        buttonSearchFlights.setOnClickListener(this);
        buttonLogout.setOnClickListener(this);
        buttonItineraries.setOnClickListener(this);

    }

    /**
     * Link each button on client page to respective page.
     * @param v buttons on client page.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonMyProfile) {

            Intent intent = new Intent(this, MyProfile.class);
            intent.putExtra(SYSTEM_KEY, bs);
            intent.putExtra(CURRENT_USER_KEY, curr_user);
            startActivity(intent);

        } else if (v.getId() == R.id.buttonSearchFlights) {

            Intent intent = new Intent(this, SearchFlights.class);
            intent.putExtra(SYSTEM_KEY, bs);
            intent.putExtra(CURRENT_USER_KEY, curr_user);
            startActivity(intent);

        } else if (v.getId() == R.id.buttonLogout) {

            Intent intent = new Intent(this, Login.class);
            intent.putExtra(SYSTEM_KEY, bs);
            intent.putExtra(CURRENT_USER_KEY, curr_user);
            startActivity(intent);

        }  else if (v.getId() == R.id.buttonItineraries) {
            Intent intent = new Intent(this, BookedItineraries.class);
            intent.putExtra(SYSTEM_KEY, bs);
            intent.putExtra(CURRENT_USER_KEY, curr_user);
            startActivity(intent);
        }

    }
}