package c347.rp.edu.sg.p08_googlemaps;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import static c347.rp.edu.sg.p08_googlemaps.R.id.android_pay;
import static c347.rp.edu.sg.p08_googlemaps.R.id.map;

public class MainActivity extends AppCompatActivity {

    TextView companyName, companyInfo;
    Button btnNorth, btnEast, btnCentral;

    Spinner spnLocation;

    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        companyName = (TextView) findViewById(R.id.tvCompanyName);
        companyInfo = (TextView) findViewById(R.id.tvCompanyInfo);
        btnNorth = (Button)findViewById(R.id.buttonNorth);
        btnEast = (Button)findViewById(R.id.buttonEast);
        btnCentral = (Button)findViewById(R.id.buttonCentral);
        spnLocation = (Spinner) findViewById(R.id.spinnerLocation);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spnLocation.setAdapter(adapter);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);

        //get reference to google map object
        //when object finalized and run,code will run,obtaining reference
        //from global variable
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                //compass
                UiSettings compass = map.getUiSettings();
                compass.setCompassEnabled(true);

                //zoom control
                UiSettings zoom = map.getUiSettings();
                zoom.setZoomControlsEnabled(true);

                //show current location
                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                }

                //Marker north
                LatLng poi_North = new LatLng(1.44224, 103.785733);
                Marker north = map.addMarker(new
                        MarkerOptions()
                        .position(poi_North)
                        .title("North - HQ")
                        .snippet("Block 333, Admiralty Ave 3, 765654 \n" +
                                "Operating hours: 10am-5pm\n" +
                                "Tel:65433456\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                //Marker east
                LatLng poi_East = new LatLng(1.290270, 103.851959);
                Marker east = map.addMarker(new
                        MarkerOptions()
                        .position(poi_East)
                        .title("East")
                        .snippet("Block 555, Tampines Ave 3, 287788 \n" +
                                "Operating hours: 9am-5pm \n" +
                                "Tel:66776677")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

                //Marker central
                LatLng poi_Central = new LatLng(1.303995, 103.832036);
                Marker central = map.addMarker(new
                        MarkerOptions()
                        .position(poi_Central)
                        .title("Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542 \n" +
                                "Operating hours: 11am-8pm\n" +
                                "Tel:67788652\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker arg0) {
                        if (arg0.getTitle().equals("North - HQ")) { // if marker source is clicked
                            Toast.makeText(MainActivity.this, arg0.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                            return false;
                        } else if (arg0.getTitle().equals("East")) { // if marker source is clicked
                            Toast.makeText(MainActivity.this, arg0.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                            return false;

                        } else if (arg0.getTitle().equals("Central")) { // if marker source is clicked
                            Toast.makeText(MainActivity.this, arg0.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                            return false;
                        }
                        return false;
                    }
                });


                //modified to zoom to any specific location (e.g causeway point woodlands)
//                LatLng poi_CausewayPoint = new LatLng(1.436065, 103.786263);
//                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_CausewayPoint,
//                        15));

            }
        });

        btnNorth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLng poi_North = new LatLng(1.44224, 103.785733);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_North,
                        15));
            }
        });

        btnEast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLng poi_East = new LatLng(1.290270, 103.851959);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_East,
                        15));
            }
        });

        btnCentral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLng poi_Central = new LatLng(1.303995, 103.832036);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Central,
                        15));
            }
        });
    }
}
