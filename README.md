#BrokenKayak
This is my first ever Android Studio project. It was done in a group of four in my second year at UofT.
See instructions.txt for detailed instructions on how you can load the app!
--
Features:

-Shows nonstop flights and connecting flights

-You can sign in as a client(see passwords.txt for client emails and passwords you can use)

-You can sign in as an admin using admin@gmail.com and password '123'

-Clients can sign in and change their own information. 

-Clients can Search flights by cost or time(see flights.txt for flights you can search) and they can click on an itinerary to book the flight

-If you search a flight that has the same source and destination, a message will say 'Origin and Destination can't be the same!'

-When a client logs out, the information persists and you can see the client's changed information and booked itineraries

-Admins can see all the clients in the system

-Admins can search flights 

--
Why it is 'broken' :

-Admin feature: If you sign in as an admin you are supposed to be able to edit client info and add or edit flights. Due to time constraints we never got that to work. Also if you try to book a flight as an admin, the app crashes

-Persisting: when you exit out of the app, all your changes to the client info and booked itineraries should still be there. We didn't get this part of persisting to work.

-If you search a flight that doesnt exist, it crashes. This could be an easy fix though.

-It is case sensitive so if you don't have the right capitalization, the app will crash

-If the app does crash you /sometimes/ have to start over the emulator or it will keep saying 'invalid password or username'
*Any fixes would be appreciated*

