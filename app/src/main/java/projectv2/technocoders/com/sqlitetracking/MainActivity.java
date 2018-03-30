package projectv2.technocoders.com.sqlitetracking;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    public static double lat;
    public static double log;
 //  public static String time;
    GPSDatabase myDB;
   // MapsActivity myMAP;
    LocationManager locationManager;
    static final int REQUEST_LOCATION = 1;
   // double lat;
   // double log;
    Button button;
    Calendar calander;
    SimpleDateFormat simpleDateFormat;
    String Date;
    TextView dateTimeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new GPSDatabase(this);
        button = (Button)findViewById(R.id.button_1);
       // myMAP = new MapsActivity(this);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
           getLocation();

          button.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                //myDB.insertData(log,lat);
                /*Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(intent);*/
                Intent intent = new Intent(MainActivity.this,Viewall.class);
                startActivity(intent);
            }
        });
    }
    void getLocation(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
           else if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 30000, 10, new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                       lat = location.getLatitude();
                       log = location.getLongitude();
                        dateTimeView = (TextView)findViewById(R.id.textView);
                        calander = Calendar.getInstance();
                        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                        Date = simpleDateFormat.format(calander.getTime());
                        dateTimeView.setText(Date);
                       // time = parseTime(location.getTime());
                        ContentValues values = new ContentValues();
                      /*  values.put(GPSDatabase.COL_1, log);
                        values.put(GPSDatabase.COL_2, lat);
                        values.put(GPSDatabase.COL_3, Date);*/
                       // AddData();
                        ((EditText)findViewById(R.id.editText_lat)).setText("Latitude :" + lat);
                        ((EditText)findViewById(R.id.editText_log)).setText("Longitude :" + log);
                       boolean isInserted =  myDB.insertData(lat,log,Date);
                        if (isInserted = true){
                            Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onStatusChanged(String s, int i, Bundle bundle) {

                    }

                    @Override
                    public void onProviderEnabled(String s) {

                    }

                    @Override
                    public void onProviderDisabled(String s) {

                    }
                });
            }

            else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30000, 10, new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {

                              lat = location.getLatitude();
                              log = location.getLongitude();
                             // time = parseTime(location.getTime());
                        dateTimeView = (TextView)findViewById(R.id.textView);
                        calander = Calendar.getInstance();
                        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                        Date = simpleDateFormat.format(calander.getTime());
                        dateTimeView.setText(Date);

                        ContentValues values = new ContentValues();
                      /*  values.put(GPSDatabase.COL_1, log);
                        values.put(GPSDatabase.COL_2, lat);
                        values.put(GPSDatabase.COL_3, Date);*/
                       // AddData();
                        ((EditText)findViewById(R.id.editText_lat)).setText("Latitude :" + lat);
                        ((EditText)findViewById(R.id.editText_log)).setText("Longitude :" + log);
                         myDB.insertData(log,lat,Date);
                    }

                    @Override
                    public void onStatusChanged(String s, int i, Bundle bundle) {

                    }

                    @Override
                    public void onProviderEnabled(String s) {

                    }

                    @Override
                    public void onProviderDisabled(String s) {

                    }
                });

            }




    }

 /* private String parseTime(long t) {
        DateFormat df = DateFormat.getTimeInstance(DateFormat.MEDIUM);
        df.setTimeZone(TimeZone.getTimeZone("GMT+5.30"));
        String gmtTime = df.format(t);
        return gmtTime;
    }*/


   /* public static String Datetime()
    {
        Calendar c = Calendar .getInstance();
       // System.out.println("Current time => "+c.getTime());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mms");
       String time = df.format(c.getTime());
        return time;
    }*/


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_LOCATION:
              /*  if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                        mMap.setMyLocationEnabled(true);
                    }

                }else{
                    Toast.makeText(getApplicationContext(),"This app requires location permission to be granted",Toast.LENGTH_LONG).show();
                    finish();
                }*/
              getLocation();
                break;
        }
    }

}
