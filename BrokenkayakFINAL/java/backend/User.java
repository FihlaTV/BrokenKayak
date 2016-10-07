package backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** A class that represents a user who is either a client or administrator. */
public class User implements Serializable {

    private static final long serialVersionUID = 5L;

    /**
     * Last name of the user.
     */
    private String lastName;
    /**
     * First name of the user.
     */
    private String firstName;
    /**
     * Email address of the user.
     */
    private String email;
    /**
     * Address of the user.
     */
    private String address;
    /**
     * Credit card number of the user.
     */
    private String creditCardNumber;
    /**
     * Expiry date of the user.
     */
    private String expiryDate;
    /**
     * Booked itineraries of the user.
     */
    private List<Itinerary> itineraries;

    /**
     * Constructor.
     *
     * @param lastName  Last name of the user in string.
     * @param firstName  First name of the user in string.
     * @param email  Email address of the user in string.
     * @param address  Address of the user in string.
     * @param creditCardNumber  Credit card number of the user in string.
     * @param expiryDate  Expiry date of the user in string.
     */
    public User(String lastName, String firstName, String email, String
            address, String creditCardNumber, String expiryDate) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.address = address;
        this.creditCardNumber = creditCardNumber;
        this.expiryDate = expiryDate;
        this.itineraries = new ArrayList<Itinerary>();
    }

    /**
     * Store selected itineraries.
     * @param itinerary  the itinerary selected by the user.
     */

    public void storeItineraries(Itinerary itinerary){
        itineraries.add(itinerary);
    }

    /**
     * View all booked itineraries of user.
     * @return User's booked itineraries.
     */

    public String viewItineraries(){
        String result = "";
        for (Itinerary i: itineraries){
            result += i.toString();
        }
        return result;
    }

    /**
     * Remove selected itinerary.
     * @param itinerary  the itinerary the user wants to remove from booked itineraries.
     */

    public void removeItineraries(Itinerary itinerary){
        itineraries.remove(itinerary);
    }

    //Getters and Setters

    /**
     * Get the address.
     * @return User's address.
     */

    public String getAddress(){
        return address;
    }

    /**
     * Set new address.
     * @param address User's address.
     */

    public void setAddress(String address){
        this.address = address;
    }

    /**
     * Get the email address of user.
     * @return email address of user.
     */

    public String getEmail(){
        return email;
    }

    /**
     * Set new email address.
     * @param email  User's email address.
     */

    public void setEmail(String email){
        this.email = email;
    }

    /**
     * Get the Credit Card Number.
     * @return the credit card number.
     */

    public String getCreditCardNumber(){
        return creditCardNumber;
    }

    /**
     * Set new credit card number.
     * @param creditCardNumber  User's credit card number.
     */

    public void setCreditCardNumber(String creditCardNumber){
        this.creditCardNumber = creditCardNumber;
    }

    /**
     * Get the expiry date of user's credit card info.
     * @return the expiry date.
     */

    public String getExpiryDate(){
        return expiryDate;
    }

    /**
     * Set new expiry date of user's credit card.
     * @param expiryDate  Expiry date of the credit card number.
     */

    public void setExpiryDate(String expiryDate){
        this.expiryDate = expiryDate;
    }

    /**
     * Get the first name of user.
     * @return first name of the user.
     */

    public String getFirstName(){
        return firstName;
    }

    /**
     * Get the last name of user.
     * @return last name of the user.
     */

    public String getLastName(){
        return lastName;
    }

    /**
     * User sets a new name.
     *
     * @param firstName  User's first name.
     * @param lastName  User's last name.
     */

    public void setName(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Print user info.
     * @return the user info in string.
     */

    public String toString() {
        return lastName + "," + firstName + "," + email + "," + address + "," +
                creditCardNumber + "," + expiryDate;
    }


}
