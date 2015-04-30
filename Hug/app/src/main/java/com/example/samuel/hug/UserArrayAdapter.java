package com.example.samuel.hug;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;


/**
 * Created by Samuel on 4/27/2015.
 */
public class UserArrayAdapter extends ArrayAdapter<User> {
    private Context context;
    private List<User> users;

    public UserArrayAdapter(Context context, List<User> users) {
        super(context, R.layout.item_list_view, users);
        this.context = context;
        this.users = users;
    }

    public void add(User object) {
        users.add(object);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_list_view, parent, false);

        ImageView imageView = (ImageView)rowView.findViewById(R.id.imageInItem);
        TextView textView = (TextView)rowView.findViewById(R.id.textInItem);

        imageView.setImageResource(R.drawable.samowl);
        textView.setText("hello");

        /*if (users.get(position).getImageUrl() == null || users.get(position).getImageUrl().isEmpty()) {
            imageView.setImageResource(R.drawable.samowl);
            textView.setText("hello");
        }
        else {
            //Picasso.with(context).load(users.get(position).getImageUrl()).into(imageView);
        }*/

        return rowView;
    }
}
