package com.sjih.hugg;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;

import android.widget.TextView;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import com.google.android.gms.maps.GoogleMap;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {
    private TextView huggsText;
    private EditText huggName;
    private EditText huggMessage;
    private Button imageButton;
    private Button addButton;
    private Bitmap huggImage;
    private String name;
    private String message;

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private GoogleMap map;
    private Marker myMarker;
    private LatLng here;
    private Location myLocation;
    private List<ParseObject> allData;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            huggImage = (Bitmap)extras.get("data");
        }
    }

    public void confirmHuggAlert() {
        DialogFragment newFragment = new HuggAlert();
        newFragment.show(getSupportFragmentManager(), "missiles");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        huggsText = (TextView)findViewById(R.id.huggs);
        huggsText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });

        MapFragment mapFragment = (MapFragment)getFragmentManager().findFragmentById((R.id.map));
        mapFragment.getMapAsync(new OnMapReadyCallback() {

            @Override
            public void onMapReady(GoogleMap googleMap) {
                //LatLng sydney = new LatLng(-33.867, 151.206);

                map = googleMap;
                map.setMyLocationEnabled(true);
                //map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));

                map.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                    @Override
                    public void onMyLocationChange(Location location) {
                        here = new LatLng(location.getLatitude(), location.getLongitude());
                        // You can update location whenever location changes with focusOnMarkers(ll)
                    }
                });
            }
        });

        huggName = (EditText)findViewById(R.id.hugg_name);
        huggMessage = (EditText)findViewById(R.id.hugg_message);

        // Add hugg image
        imageButton = (Button)findViewById(R.id.image_button);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        // Share hugg
        addButton = (Button)findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = huggName.getText().toString();
                message = huggMessage.getText().toString();

                if (huggImage != null && name != null && message != null) {
                    addData(name, message, here.latitude, here.longitude, huggImage); // Send data to Parse
                    addNewMarker(name, message, here, huggImage);

                    // Update hugg count
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Huggs");
                    query.getInBackground("mmDAL7qnoL", new GetCallback<ParseObject>() {
                        public void done(ParseObject object, ParseException e) {
                            if (e == null) {
                                object.increment("Huggs");
                                object.saveInBackground();
                                int count = object.getInt("Huggs");

                                if (count == 1)
                                    huggsText.setText(String.valueOf(count) + " hugg!");
                                else
                                    huggsText.setText(String.valueOf(count) + " huggs!");
                            } else {
                                // something went wrong
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(MainActivity.this, R.string.add_item, Toast.LENGTH_SHORT).show();
                }
            }
        });

        loadFromLocalParseStore();
        refresh();
    }

    public void refresh() {
        loadAllData();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Huggs");
        query.getInBackground("mmDAL7qnoL", new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    int count = object.getInt("Huggs");

                    if (count == 1)
                        huggsText.setText(String.valueOf(count) + " hugg!");
                    else
                        huggsText.setText(String.valueOf(count) + " huggs!");
                } else {
                    // something went wrong
                }
            }
        });
    }

    public void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void addData(String title, String snippet, double lat, double lon, Bitmap icon) {
        ParseObject object = new ParseObject("Markers");
        object.put("Name", title);
        object.put("Message", snippet);
        object.put("Latitude", lat);
        object.put("Longitude", lon);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        icon.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        object.put("Image", byteArray);
        object.saveInBackground();
    }

    private void addNewMarker(String title, String snippet, LatLng position, Bitmap icon) {
        if (myMarker != null)
            myMarker.remove();

        myMarker = map.addMarker(new MarkerOptions()
                .title(title)
                .snippet(snippet)
                .position(position)
                .icon(BitmapDescriptorFactory.fromBitmap(icon))
        );

        CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(here, 21); // Second parameter is float zoom 2-21
        map.animateCamera(cu);
    }

    private void loadAllData() {
        Log.i("TEST", "Loading data remotely!");

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Markers");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                allData = list;

                if (allData != null)
                    ParseObject.pinAllInBackground(allData);

                addAllMarkers();
            }
        });
    }

    private void addAllMarkers() {
        List<MarkerOptions> markers = new ArrayList<MarkerOptions>();
        byte[] image;
        Bitmap bitmap;

        for (ParseObject object : allData) {
            image = (byte[])object.get("Image");
            bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);

            MarkerOptions marker = new MarkerOptions()
                    .title(object.get("Name").toString())
                    .snippet(object.get("Message").toString())
                    .position(new LatLng(object.getDouble("Latitude"), object.getDouble("Longitude")))
                    .icon(BitmapDescriptorFactory.fromBitmap(bitmap));
            markers.add(marker);
        }

        map.clear(); // Clear map
        LatLngBounds.Builder builder = new LatLngBounds.Builder(); // Set bounds

        for (MarkerOptions marker : markers) {
            Marker m = map.addMarker(marker);
            builder.include(m.getPosition());
        }
        LatLngBounds bounds = builder.build();

        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 12); // Number is padding
        map.animateCamera(cu);
    }

    private void loadFromLocalParseStore() {
        Log.i("TEST", "Loading data locally!");

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Markers");
        query.fromLocalDatastore();
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                allData = list;

                if (allData != null && allData.size() > 0)
                    loadAllData();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}