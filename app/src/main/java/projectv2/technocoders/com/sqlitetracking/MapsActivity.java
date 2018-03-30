package projectv2.technocoders.com.sqlitetracking;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
  /*  double longitude;
    double latitude;
    public MapsActivity(GPSDatabase mainActivity) {

    }*/
  GPSDatabase myLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        myLocation = new GPSDatabase(this);

    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        // GPSDatabase gps = new GPSDatabase(this);

            mMap = googleMap;
        // Add a marker in Sydney and move the camera
        //LatLng location = new LatLng(6.79,79.901);
        LatLng location = new LatLng(MainActivity.lat,MainActivity.log);
        Geocoder geocoder = new Geocoder(getApplicationContext());
        try {
            List<Address> addressList = geocoder.getFromLocation(MainActivity.lat, MainActivity.log, 1);
            String str = addressList.get(0).getSubLocality() + ",";
            str += addressList.get(0).getCountryName();
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(location).title(str));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
        } catch (IOException e) {
            e.printStackTrace();
        }
       // mMap.addMarker(new MarkerOptions().position(location).title("Marker in my location"));
      //  mMap.moveCamera(CameraUpdateFactory.newLatLng(location));


}

    }
