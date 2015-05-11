package com.sjih.hugg;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import com.parse.ParseObject;

public class MainActivity extends ActionBarActivity implements OnMapReadyCallback {
    private EditText huggName;
    private EditText huggMessage;
    private Button huggButton;
    private Bitmap huggImage;
    private String name;
    private String message;
    private static final int REQUEST_IMAGE_CAPTURE = 1;

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

        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();

        huggName = (EditText)findViewById(R.id.hugg_name);
        huggName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = huggName.getText().toString();
            }
        });

        huggMessage = (EditText)findViewById(R.id.hugg_message);
        huggMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message = huggMessage.getText().toString();
            }
        });

        huggButton = (Button)findViewById(R.id.hugg_button);
        huggButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        MapFragment mapFragment = (MapFragment)getFragmentManager().findFragmentById((R.id.map));
        mapFragment.getMapAsync(this);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
     public void onMapReady(GoogleMap map) {
        LatLng sydney = new LatLng(-33.867, 151.206);

        //map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));

        map.addMarker(new MarkerOptions()
                .title("Sydney")
                .snippet("The most populous city in Australia.")
                .position(sydney));
                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.arrow)));
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
