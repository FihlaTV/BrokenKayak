package group_0757.brokenkayak;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.io.IOException;

import backend.BookingSystem;
import backend.User;

/**
 * The profile page of currently logged user. Able to edit user information if the user already
 * exists in the system, else create a new user with the input information.
 */
public class MyProfile extends AppCompatActivity implements View.OnClickListener {

    /**
     * text fields to take in user input with respected user information.(First name, last name...)
     */
    EditText fieldFirstName;
    EditText fieldLastName;

    EditText fieldAddress;
    EditText fieldCreditCardNumber;
    EditText fieldExpiryDate;

    EditText fieldEmail2;
    EditText fieldPassword;
    /** save button. */
    Button buttonSave;
    /** logout button. */
    Button buttonBacktoLogin;
    /** Header for My Profile. */
    TextView myProfileprompt;

    /** to initialize and access the userMap in the backend bookingSystem class. */
    private BookingSystem bs;
    /** current logged user. */
    String curr_user;
    /** key identifier from the Login class. */
    public static final String CURRENT_USER_KEY = "currentUserkey";
    public static final String SYSTEM_KEY = "systemKey";

    /**
     * Initializes a My Profile page.
     *
     * @param savedInstanceState Activity's saved state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        Intent intent = getIntent();
        bs = (BookingSystem) intent.getSerializableExtra(ClientMenu.SYSTEM_KEY);
        curr_user = (String) intent.getSerializableExtra(ClientMenu.CURRENT_USER_KEY);

        myProfileprompt = (TextView) findViewById(R.id.MyProfileprompt);
        fieldFirstName = (EditText) findViewById(R.id.fieldFirstName);
        fieldLastName = (EditText) findViewById(R.id.fieldLastName);
        fieldAddress = (EditText) findViewById(R.id.fieldAddress);
        fieldCreditCardNumber = (EditText) findViewById(R.id.fieldCreditCardNumber);
        fieldExpiryDate = (EditText) findViewById(R.id.fieldExpiryDate);

        // Note: username = email
        // optional for ability to change password
        fieldEmail2 = (EditText) findViewById(R.id.fieldEmail2);
        fieldPassword = (EditText) findViewById(R.id.loginPassword2);

        // buttons
        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonBacktoLogin = (Button) findViewById(R.id.buttonBacktoLogin);

        buttonSave.setOnClickListener(this);
        buttonBacktoLogin.setOnClickListener(this);

        // sets the Last Name to previous Last Name if any
        if (bs.userMap.get(curr_user).getLastName().length() == 0) {
            fieldLastName.setText("");
        } else {
            fieldLastName.setText(bs.userMap.get(curr_user).getLastName());
        }
        // sets the First Name to previous First Name if any
        if (bs.userMap.get(curr_user).getFirstName().length() == 0) {
            fieldFirstName.setText("");
        } else {
            fieldFirstName.setText(bs.userMap.get(curr_user).getFirstName());
        }
        // sets the Address to previous Address Name if any
        if (bs.userMap.get(curr_user).getAddress().length() == 0) {
            fieldAddress.setText("");
        } else {
            fieldAddress.setText(bs.userMap.get(curr_user).getAddress());
        }
        // sets the Credit Card Number to previous CCN if any
        if (bs.userMap.get(curr_user).getCreditCardNumber().length() == 0) {
            fieldCreditCardNumber.setText("");
        } else {
            fieldCreditCardNumber.setText(bs.userMap.get(curr_user).getCreditCardNumber());
        }
        // sets the Expiry Date of CCN to previous if any
        if (bs.userMap.get(curr_user).getExpiryDate().length() == 0) {
            fieldExpiryDate.setText("");
        } else {
            fieldExpiryDate.setText(bs.userMap.get(curr_user).getExpiryDate());
        }
        // sets the Username/Email to previous or current
        if (bs.userMap.get(curr_user).getEmail().length() == 0) {
            fieldEmail2.setText("");
        } else {
            fieldEmail2.setText(bs.userMap.get(curr_user).getEmail());
        }
        //displays length of previous password (as xml input type is ="textpassword")
        if (bs.userIDMap.get(curr_user).getPassword().length() == 0) {
            fieldPassword.setText("");
        } else {
            fieldPassword.setText(bs.userIDMap.get(curr_user).getPassword());
        }

    }

    /**
     * Creates a new user, and stores onto system.
     * @param v button view
     */
    public void saveNewUserOnClick(View v) {
        String FILENAME = "users.csv";
        // LastName,FirstNames,Email,Address,CreditCardNumber,ExpiryDate
        String user_entry = fieldLastName.getText().toString() + ","
                + fieldFirstName.getText().toString() + ","
                + fieldLastName.getText().toString() + ","
                + fieldEmail2.getText().toString() + ","
                + fieldAddress.getText().toString() + ","
                + fieldCreditCardNumber.getText().toString() + ","
                + fieldExpiryDate.getText().toString() + "\n";
        try {
            FileOutputStream out = openFileOutput(FILENAME, Context.MODE_APPEND);
            out.write(user_entry.getBytes());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Edits current user's information, incuding name, billing info, and password.
     * @param v button view.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonSave) {

            // if any of the fields are empty, prompt an error message
            if (fieldFirstName.getText().toString().length() == 0) {
                fieldFirstName.setError("Please fill in your First Name.");
            }
            if (fieldLastName.getText().toString().length() == 0) {
                fieldLastName.setError("Please fill in your Last Name.");
            }
            if (!fieldEmail2.getText().toString().matches(bs.userMap.get(curr_user).getEmail())){
                fieldEmail2.setError("Your Username/Email cannot be changed.");
                fieldEmail2.setText(bs.userMap.get(curr_user).getEmail());
            }
            if (fieldAddress.getText().toString().length() == 0) {
                fieldAddress.setError("Please fill in your address.");
            }
            if (fieldCreditCardNumber.getText().toString().length() == 0) {
                fieldCreditCardNumber.setError("Please fill in your credit card number.");
            }
            if (fieldPassword.getText().toString().length() == 0) {
                fieldPassword.setError("Please fill in your password.");
            }
            if (fieldExpiryDate.getText().toString().length() == 0){
                fieldExpiryDate.setError("Please fill in the expiry date for your CC#");
            }

            // if all fields are completed...
            if ((fieldFirstName.getText().toString().length() != 0) &&
                    (fieldLastName.getText().toString().length() != 0) &&
                    (fieldEmail2.getText().toString().length() !=0) &&
                    (fieldAddress.getText().toString().length() !=0) &&
                    (fieldCreditCardNumber.getText().toString().length() != 0) &&
                    (fieldPassword.getText().toString().length() !=0) &&
                    (fieldExpiryDate.getText().toString().length() !=0))
            {
                myProfileprompt.setText("Changes saved!");
            }

            bs.userMap.get(curr_user).setName(fieldFirstName.getText().toString(),
                    fieldLastName.getText().toString());
            bs.userMap.get(curr_user).setAddress(fieldAddress.getText().toString());
            bs.userMap.get(curr_user).setCreditCardNumber(fieldCreditCardNumber.getText().toString());
            bs.userMap.get(curr_user).setExpiryDate(fieldExpiryDate.getText().toString());
            bs.userIDMap.get(curr_user).setPassword(fieldPassword.getText().toString());

            // saveNewUserOnClick(v);

            Intent intent = new Intent(this, ClientMenu.class);
            intent.putExtra(SYSTEM_KEY, bs);
            intent.putExtra(CURRENT_USER_KEY, curr_user);
            startActivity(intent);

        } else if (v.getId() == R.id.buttonBacktoLogin)
            //startActivity(new Intent(this, Login.class));
            super.onBackPressed();
    }


}