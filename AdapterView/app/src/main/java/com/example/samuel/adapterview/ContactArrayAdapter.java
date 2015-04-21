package com.example.samuel.adapterview;

import android.widget.ArrayAdapter;
import android.content.Context;
import java.util.List;

/**
 * Created by Samuel on 4/20/2015.
 */
public class ContactArrayAdapter extends ArrayAdapter<Contact> {
    private Context context;
    private List<Contact> contacts;

    public ContactArrayAdapter(Context context, List<Contact> contacts) {
        super(context, R.layout.item_list_view, contacts);

    }




}
