package com.example.shenchen.experiment9;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import android.Manifest;


public class MainActivity extends AppCompatActivity {

    List<Contract> contactsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.READ_CONTACTS },1);
        }
        else
            readContacts();

        ContractAdapter adapter = new ContractAdapter(MainActivity.this, R.layout.item_view, contactsList);
        ListView contactsView = (ListView)findViewById(R.id.contacts_view);
        contactsView.setAdapter(adapter);

    }

    private void readContacts(){
        Cursor cursor = null;
        try{
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
            if(cursor != null){
                while (cursor.moveToNext()){
                    String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    Contract contract = new Contract(displayName,number);
                    contactsList.add(contract);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if(cursor!=null){
                cursor.close();
            }
        }
    }

    public void onRequestPermissionsResult(int requestCode,  String[] permissions, int[] grantResults) {
        switch(requestCode){
            case 1:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    readContacts();
                else
                    Toast.makeText(this, "你拒绝了授权", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
    }
}
