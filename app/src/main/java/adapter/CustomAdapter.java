package adapter;

/**
 * Created by DELL1 on 1/6/2016.
 */
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import app.contacts.sunny.addressbook.R;
import app.contacts.sunny.addressbook.RoundedImageView;
import pojo.Persons;

public class CustomAdapter extends BaseAdapter{

    Context context;

    private  LayoutInflater mInflater;
    private ArrayList<Persons> person_object;
    private static LayoutInflater inflater=null;
    public TextView txtHeader;
    private RoundedImageView mImage;

    public CustomAdapter(Context context,ArrayList<Persons> contact_persons) {
        // TODO Auto-generated constructor stub

        this.person_object= contact_persons;
        this.context=context;
        this.mInflater = (LayoutInflater) this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return person_object.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv;
        ImageView img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        Holder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(
                    R.layout.contact_item, null);


            holder = new Holder();
            holder.tv = (TextView) convertView
                    .findViewById(R.id.tv_label);
holder.img =(ImageView) convertView.findViewById(R.id.rounded_iv_profile);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        final String name = (person_object.get(position)).getName();
        final String id= (person_object.get(position)).getContact_id();
        final String URI = (person_object.get(position)).getProfilePic();

        holder.tv.setText(name);

        try
        {
            holder.img.setImageURI(Uri.parse(URI));
        }catch (Exception e)
        {
            e.printStackTrace();
            holder.img.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.person));
        }

convertView.setOnClickListener(new OnClickListener() {
    @Override
    public void onClick(View v) {

        Toast.makeText(context, "You Clicked "+name, Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(context,app.contacts.sunny.addressbook.Details.class);
        myIntent.putExtra("contact_id", id);
        myIntent.putExtra("contact_name", name);
        myIntent.putExtra("contact_uri", URI);
        //Optional parameters
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(myIntent);
}
});

/*
        View rowView;
        rowView = inflater.inflate(R.layout.contact_item, null);
        holder.tv=(TextView) rowView.findViewById(R.id.tv_label);
        holder.img=(ImageView) rowView.findViewById(R.id.rounded_iv_profile);
        holder.tv.setText(result[position]);
        holder.img.setImageResource(imageId[position]);
        rowView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked "+result[position], Toast.LENGTH_LONG).show();
            }
        });
*/

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.push_up_in);
        animation.setDuration(500);
        convertView.startAnimation(animation);
        animation = null;
        return convertView;

    }

}
