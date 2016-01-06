package app.contacts.sunny.addressbook;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import adapter.CustomAdapter;
import pojo.Persons;

public class Contacts extends AppCompatActivity {
ListView list;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_contacts);
    list=(ListView)findViewById(R.id.listView);
    context= getApplicationContext();
        ArrayList<Persons> arraylist =new ArrayList<Persons>();


        arraylist = readContacts();
        list.setAdapter(new CustomAdapter(context, arraylist));


    }



    public ArrayList<Persons> readContacts()
    {
        ArrayList<Persons> arraylist =new ArrayList<Persons>();
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, ContactsContract.Contacts.SORT_KEY_PRIMARY + " ASC");
        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {

                Persons person=new Persons();
                String number = "Not exist";
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                String URI=null;
                try {

                    URI = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.PHOTO_URI));


                }catch (Exception e)
                {
                    URI ="android.resource://"+getApplicationContext().getPackageName()+"/drawable/icon";
                }



                person.setContact_id(id);
                person.setName(name);
                person.setProfilePic(URI);

                Toast.makeText(getApplicationContext(), "ID: " + id + "  " + "NAME: " + name + "   PHOTO:", Toast.LENGTH_SHORT).show();
                arraylist.add(person);
                if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    //Query phone here.  Covered next
                }
            }
        }

        return arraylist;
    }

}
