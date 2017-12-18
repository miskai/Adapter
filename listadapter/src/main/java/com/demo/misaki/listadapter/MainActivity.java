package com.demo.misaki.listadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
        private ListView listView;
        private CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView= (ListView) findViewById(R.id.listview);
        String[]strings=getResources().getStringArray(R.array.content);
        adapter=new CustomAdapter(this,strings);
        listView.setAdapter(adapter);
    }
}
