package com.sjih.hugg;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.TextView;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import com.google.android.gms.maps.GoogleMap;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.GetCallback;
import com.parse.ParseException;

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
    private LatLng here;
    private int huggs;
    private ParseObject updateObject;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            huggImage = (Bitmap)extras.get("data");
            //imageView.setImageBitmap(huggImage);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        huggsText = (TextView)findViewById(R.id.huggs);

        //ParseObject testObject = new ParseObject("Huggs");
        //testObject.put("Huggs", 0);
        //testObject.saveInBackground();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Huggs");
        query.getInBackground("mmDAL7qnoL", new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    // object will be your game score
                    updateObject = object;
                    huggs = object.getInt("Huggs");
                    huggsText.setText(String.valueOf(huggs));
                } else {
                    // something went wrong
                }
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
        imageButton = (Button)findViewById(R.id.image_button);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        addButton = (Button)findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = huggName.getText().toString();
                message = huggMessage.getText().toString();

                huggs += 1;
                updateObject.put("Huggs", huggs);
                updateObject.saveInBackground();

                if (huggImage != null && name != null && message != null) {
                    map.addMarker(new MarkerOptions()
                            .title(name)
                            .snippet(message)
                            .position(here)
                            .icon(BitmapDescriptorFactory.fromBitmap(huggImage)));
                }
                else {
                    Toast.makeText(MainActivity.this, R.string.add_image, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
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