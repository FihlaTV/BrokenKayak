package group_0757.brokenkayak;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import backend.BookingSystem;

/**
 * This class takes in user input and searches flights by desired origin, destination and time.
 */
public class SearchFlights extends AppCompatActivity implements View.OnClickListener {

    /** key identifier from the Login class. */
    public static final String SYSTEM_KEY = "systemKey";
    public static final String CURRENT_USER_KEY = "currentUserkey";
    /** to initialize and access the userMap in the backend bookingSystem class. */
    private BookingSystem bs;
    /** current logged user. */
    String curr_user;

    /** buttons and text fields on the page */
    static String origin;
    EditText originField;
    EditText destinationField;
    static String destination;
    static String date;
    Button SearchByTimeButton;
    Button SearchByCostButton;
    TextView searchPromptField;

    /**
     * Initializes a search Flights page.
     *
     * @param savedInstanceState Activity's saved state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_flights);

        Intent intent = getIntent();
        bs = (BookingSystem) intent.getSerializableExtra(Login.SYSTEM_KEY);
        curr_user = (String) intent.getSerializableExtra(Login.CURRENT_USER_KEY);

        searchPromptField = (TextView) findViewById(R.id.searchPrompt);
        originField = (EditText) findViewById(R.id.fieldOrigin);
        destinationField = (EditText) findViewById(R.id.fieldDestination);
        SearchByTimeButton = (Button) findViewById(R.id.SearchByTimeButton);

        SearchByTimeButton.setOnClickListener(this);
        SearchByCostButton = (Button) findViewById(R.id.SearchByCostButton);

        SearchByCostButton.setOnClickListener(this);

    }

    /**
     * Directs to Itinerary By Cost or Itinerary By Time pages depending on the button clicked.
     *
     * This class also checks if origin and destination inputs are the same, which would not be a
     * valid search.
     *
     * @param v button view.
     */
    @Override
    public void onClick(View v) {
        //Gets the Origin
        EditText originField = (EditText) findViewById(R.id.fieldOrigin);
        origin = originField.getText().toString();

        //Gets the Destination
        EditText destinationField = (EditText) findViewById(R.id.fieldDestination);
        destination = destinationField.getText().toString();

        //Gets departure date
        EditText departureField = (EditText) findViewById(R.id.fieldDepartureDate);
        date = departureField.getText().toString();

        //gets itinerary given date, origin, destination
        if (v.getId() == R.id.SearchByCostButton) {
            if (originField.getText().toString().matches(destinationField.getText().toString())) {
                //if (origin == destination) {
                searchPromptField.setText("Origin and Destination can't be the same!");
            } else {
                Intent intent = new Intent(this, ItinerariesByCost.class);
                intent.putExtra(Login.SYSTEM_KEY, bs);
                intent.putExtra(Login.CURRENT_USER_KEY, curr_user);
                startActivity(intent);
            }


        } else if (v.getId() == R.id.SearchByTimeButton) {
            if (originField.getText().toString().matches(destinationField.getText().toString())) {
                //if (origin == destination) {
                searchPromptField.setText("Origin and Destination can't be the same!");
            } else {
                Intent intent_time = new Intent(this, ItinerariesByTime.class);
                intent_time.putExtra(Login.SYSTEM_KEY, bs);
                intent_time.putExtra(Login.CURRENT_USER_KEY, curr_user);
                startActivity(intent_time);


            }
        }
    }
}