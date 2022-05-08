package com.example.xmlexample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.EditText;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;

public class WriteXMLActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etAddress;
    private EditText etState;

    FileOutputStream fileOutputStream;
    XmlSerializer xmlSerializer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_xmlactivity);

        etName = findViewById(R.id.editName);
        etAddress = findViewById(R.id.editAddress);
        etState = findViewById(R.id.editState);

        try{
            File myFile = new File(getFilesDir(), "example.xml");

            if(!myFile.exists()){
                fileOutputStream = openFileOutput("example.xml", Activity.MODE_APPEND);
                xmlSerializer = Xml.newSerializer();
                xmlSerializer.setOutput(fileOutputStream, "UTF-8");

                xmlSerializer.startDocument(null, Boolean.valueOf(true));
                xmlSerializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
                xmlSerializer.startTag(null, "AddressBook");
            } else{
                fileOutputStream = openFileOutput("example.xml", Activity.MODE_APPEND);
                xmlSerializer = Xml.newSerializer();
                xmlSerializer.setOutput(fileOutputStream, "UTF-8");
            }



        } catch (Exception e){
            Log.e("Exception", e.getMessage());
        }
    }


    public void onSaveButton(View view) {
        try{
            xmlSerializer.startTag(null,"Contact");

            xmlSerializer.startTag(null,"Name");
            xmlSerializer.text(etName.getText().toString());
            xmlSerializer.endTag(null, "Name");

            xmlSerializer.startTag(null,"Address");
            xmlSerializer.text(etAddress.getText().toString());
            xmlSerializer.endTag(null, "Address");

            xmlSerializer.startTag(null,"State");
            xmlSerializer.text(etState.getText().toString());
            xmlSerializer.endTag(null, "State");

            xmlSerializer.endTag(null, "Contact");

            xmlSerializer.flush();

            etName.setText("");
            etAddress.setText("");
            etState.setText("");

        } catch (Exception e){
            Log.e("Exception:", e.getMessage());
        }
    }
}