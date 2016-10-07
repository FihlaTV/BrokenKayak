package group_0757.brokenkayak;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import backend.BookingSystem;
import backend.Flight;
import backend.Node;
import backend.User;
import backend.UserID;
import group_0757.brokenkayak.AdminMenu;
import group_0757.brokenkayak.ClientMenu;
import group_0757.brokenkayak.R;

/**
 * The startup menu of the app, may log in users with email and password.
 */
public class Login extends AppCompatActivity implements View.OnClickListener {

    EditText fieldEmail;
    EditText fieldPassword;
    Button buttonLogin;
    TextView loginpromptfield;

    /** to initialize and access the userMap in the backend bookingSystem class. */
    // BookingSystem bs = new BookingSystem();
    private BookingSystem bs;


    public static final String SYSTEM_KEY = "systemKey";
    public static final String CURRENT_USER_KEY = "currentUserkey";

    /**
     * Creates a Login page. Uploads client and flight information stored in the system upon start.
     * @param savedInstanceState activity's saved state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fieldEmail = (EditText) findViewById(R.id.fieldEmail);
        fieldPassword = (EditText) findViewById(R.id.fieldPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        loginpromptfield = (TextView) findViewById(R.id.loginprompt);

        buttonLogin.setOnClickListener(this);
        File filesDir = this.getApplicationContext().getFilesDir();
        File clientsFile = new File(filesDir, "clients.txt");
        File flightsFile = new File(filesDir, "flights.txt");

        Intent intent = getIntent();
        if (getIntent().getExtras() != null) {

            bs = (BookingSystem) intent.getSerializableExtra(ClientMenu.SYSTEM_KEY);

        } else {
            try {
                BookingSystem.uploadClients(clientsFile);
                BookingSystem.uploadFlights(flightsFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            authenticate();
        }

    }


//    public void updateFlightInfoData() {
//
//        File filesDir = this.getApplicationContext().getFilesDir();
//        File flightsFile = new File(filesDir, "flights.txt");
//
//        try {
//            Scanner scanner = new Scanner(new FileInputStream(flightsFile));
//            String[] rec;
//            while (scanner.hasNextLine()) {
//                rec = scanner.nextLine().split(",");
//                Flight flight = new Flight(rec[0], rec[1], rec[2], rec[3], rec[4], rec[5], rec[6]);
//                BookingSystem.createNode(rec[5]);
//                BookingSystem.createNode(rec[4]);
//                Node node = BookingSystem.nodeMap.get(rec[4]);
//                node.addEdge(rec[5], flight);
//                if (!bs.flightMap.containsKey(rec[0])) {
//                    bs.flightMap.put(rec[0], flight);
//                } else {
//                    bs.flightMap.remove(rec[0]);
//                    bs.flightMap.put(rec[0], flight);
//                }
//            }
//            scanner.close();
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void updateClientInfoData() {
//
//        File filesDir = this.getApplicationContext().getFilesDir();
//        File clientsFile = new File(filesDir, "clients.txt");
//
//        try {
//            Scanner scanner = new Scanner(new FileInputStream(clientsFile));
//            String[] rec;
//            while (scanner.hasNextLine()) {
//                rec = scanner.nextLine().split(",");
//                User user = new User(rec[0], rec[1], rec[2], rec[3], rec[4], rec[5]);
//
//                if (!bs.userMap.containsKey(rec[2])) {
//                    bs.userMap.put(rec[2], user);
//                } else {
//                    bs.userMap.remove(rec[2]);
//                    bs.userMap.put(rec[2], user); // overwrite existing client info
//                }
//            }
//            scanner.close();
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * Check if user enterd valid username(email) and password.
     */
    public void authenticate() {

        File filesDir = this.getApplicationContext().getFilesDir();
        File passwordFile = new File(filesDir, "passwords.txt");

        try {
            Scanner scanner = new Scanner(new FileInputStream(passwordFile));
            String[] rec;
            while (scanner.hasNextLine()) {
                rec = scanner.nextLine().split(",");
                UserID uid = new UserID(rec[0], rec[1], rec[2]);

                if (!bs.userIDMap.containsKey(rec[0])) {
                    bs.userIDMap.put(rec[0], uid);
                } else {
                    bs.userIDMap.remove(rec[0]);
                    bs.userIDMap.put(rec[0], uid); // overwrite existing password/username
                }
                // bs.userMap.put(rec[2], uid);
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        loginpromptfield.setText("Welcome!");
    }

    /**
     * onClick button for login to determine correct username/password and type of login.
     * @param v view of the button.
     */

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonLogin) {
            if (bs.userIDMap.containsKey(fieldEmail.getText().toString())) {

                if (fieldPassword.getText().toString().matches(bs.userIDMap.get(fieldEmail.getText().toString()).getPassword())){
                    loginpromptfield.setText("Login successful!");
                    if (bs.userIDMap.get(fieldEmail.getText().toString()).getType().matches("2")) {
                        Intent intent = new Intent(this, ClientMenu.class);
                        intent.putExtra(SYSTEM_KEY, bs);
                        intent.putExtra(CURRENT_USER_KEY, fieldEmail.getText().toString());
                        startActivity(intent);
                    } else if (bs.userIDMap.get(fieldEmail.getText().toString()).getType().matches("1")) {
                        Intent intent = new Intent(this, AdminMenu.class);
                        startActivity(intent);
                    }
                } else {loginpromptfield.setText("Invalid Password.");}
            } else {loginpromptfield.setText("Invalid Username.");}
        }
    }
}
