package kathure.com.geofire;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.firebase.client.Firebase;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        Firebase.setAndroidContext(this);
        Firebase myFirebaseRef = new Firebase("https://kathure.firebaseio.com/");

        //myFirebaseRef.child("geoFire");

        myFirebaseRef.child("message").setValue("Do you have data? You'll love Firebase.");

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("https://kathure.firebaseio.com/geoFire");
        GeoFire geoFire = new GeoFire(ref);


        geoFire.setLocation("firebase-hq", new GeoLocation(37.7853889, -122.4056973), new GeoFire.CompletionListener() {

            @Override
            public void onComplete(String key, DatabaseError error) {
                if (error != null) {
                    System.err.println("There was an error saving the location to GeoFire: " + error);
                } else {
                    System.out.println("Location saved on server successfully!");
                }
            }

    });

}
}
