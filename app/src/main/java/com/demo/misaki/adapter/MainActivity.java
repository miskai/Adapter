package com.demo.misaki.adapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner;
    private CustomSpinner adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner= (Spinner) findViewById(R.id.spinner);
        adapter=new CustomSpinner(this,getResources().getStringArray(R.array.content));
        spinner.setAdapter(adapter);
    }
}
