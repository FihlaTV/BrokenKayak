package group_0757.brokenkayak;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import java.io.OptionalDataException;
import java.util.Scanner;


import backend.BookingSystem;
import backend.User;

/**
 * Administer's page, able to manage client accounts, search flights and logout.
 */
public class AdminMenu extends AppCompatActivity implements View.OnClickListener {

    /** to initialize and access the userMap in the backend bookingsystem class */
    private BookingSystem bs;

//    public static final String FILENAMECLIENT = "client.txt";
//    public static final String CLIENTDATADIR = "clientdata";


    /** fields for the buttons on the Admin View */
    Button buttonDisplayUsers;
    Button buttonSearchFlights;
    Button buttonLogout;

    /** display all clients with this textview field */
    TextView showallclientsintextview;

    /**
     * Creates the Admin page, uploads client information automatically
     * after login.
     *
     * @param savedInstanceState activity's saved state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);

        Intent intent = getIntent();
        // bs =(BookingSystem) intent.getSerializableExtra(Login.)

        // buttons by their R.id.
        buttonDisplayUsers = (Button) findViewById(R.id.buttonDisplayUsers);
        buttonSearchFlights = (Button) findViewById(R.id.buttonSearchFlights);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);

        // display all clients with this textview field
        showallclientsintextview = (TextView) findViewById(R.id.displayallclients);

        // button listeners
        buttonDisplayUsers.setOnClickListener(this);
        buttonSearchFlights.setOnClickListener(this);
        buttonLogout.setOnClickListener(this);

        // after clients.txt file is pushed on the android device
        // upon admin login, clients are automatically uploaded into the system




        // public void uploadClients() {
        // accesses ADM's data -> data -> group_0757brokenkayak -> files directory
        File filesdir = this.getApplicationContext().getFilesDir();
        // open a new file from "clients.txt"
        File pleasework = new File(filesdir, "clients.txt");

        StringBuilder textoutput = new StringBuilder();

        try {
            Scanner scanner = new Scanner(new FileInputStream(pleasework));
            String[] record;
            while(scanner.hasNextLine()) {
                record = scanner.nextLine().split(",");
                User user = new User(record[0],  record[1], record[2],
                        record[3], record[4], record[5]);
                if (!bs.userMap.containsKey(record[2])){
                    bs.userMap.put(record[2], user);
                } else {
                    System.out.println(record[2] + " is already exist");
                }
                bs.userMap.put(record[2], user);
                //text output will be added to the textview
                //use this for the time being until we figure out how ListView works.
                textoutput.append(user);
                textoutput.append("\n");
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //showallclientsintextview.setText(textoutput);
    }

    /**
     * Link each button on admin page to respective page.
     * @param v buttons on admin page.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonDisplayUsers) {
            Intent display_users_intent = new Intent(this, DisplayAllClients.class);
            startActivity(display_users_intent);
            //TODO make display button work onclick.
            // calling uploadclients does not seem to work - i.e. below
            // uploadClients();


        } else if (v.getId() == R.id.buttonSearchFlights) {
            Intent search_flights_intent = new Intent(this, SearchFlights.class);
            startActivity(search_flights_intent);


        } else if (v.getId() == R.id.buttonLogout) {
            Intent logout_intent = new Intent(this, Login.class);
            startActivity(logout_intent);
            // finish();
        }
    }
}