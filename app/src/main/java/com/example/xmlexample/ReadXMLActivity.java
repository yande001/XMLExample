package com.example.xmlexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileReader;

public class ReadXMLActivity extends AppCompatActivity {

    private TextView textView;

    XmlPullParserFactory factory;
    XmlPullParser xpp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_xmlactivity);

        textView = findViewById(R.id.textView1);
        textView.setText("");

        File file = new File(getFilesDir(), "example.xml");

        if (file.exists()){
            readXml(file);
        } else{
            Toast.makeText(getApplicationContext(), "File Not Found.", Toast.LENGTH_LONG).show();
        }
    }

    public void readXml(File file){
        try{
            factory = XmlPullParserFactory.newInstance();
            xpp = factory.newPullParser();

            xpp.setInput(new FileReader(file));

            // Start and end tags and end document
            int eventType = xpp.getEventType();
            String currentTag;
            String currentElement;

            int counter = 0;

            while(eventType != XmlPullParser.END_DOCUMENT){
                if(eventType == XmlPullParser.START_TAG){
                    currentTag = xpp.getName();
                    if(currentTag.equals("Contact")){
                        textView.append("Contact" + ++counter + "\n");
                        textView.append("------------------------------------\n");
                    }
                    else if(currentTag.equals("Name")){
                        currentElement = xpp.nextText();
                        textView.append("Name: " + currentElement + "\n");
                    }
                    else if(currentTag.equals("Address")){
                        currentElement = xpp.nextText();
                        textView.append("Address: " + currentElement + "\n");
                    }
                    else if(currentTag.equals("State")){
                        currentElement = xpp.nextText();
                        textView.append("State: " + currentElement + "\n");
                    }
                } else if(eventType == XmlPullParser.END_TAG){
                    currentTag = xpp.getName();
                    if (currentTag.equals("Contact"))
                    {
                        textView.append("------------------------------------\n\n");
                    }
                }
                eventType = xpp.next();
            }
        }
        catch (Exception e){
            Log.e("Exception: ", e.getMessage());
        }
    }
}