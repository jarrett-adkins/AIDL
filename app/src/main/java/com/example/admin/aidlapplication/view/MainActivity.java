package com.example.admin.aidlapplication.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.admin.aidlapplication.IMyAidlInterface;
import com.example.admin.aidlapplication.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

/*
Assignment 1
Create a project with two apps

1. App1 will act as a server with a Bind service
2. App2 will act as a client to get data from App1.

-Use AIDL to communicate with the server app
https://developer.android.com/guide/components/aidl.html

Assignment 2
Create an application with that finds nearby Bluetooth devices and display in a recyclerView
-using BLE
-create a separate library for the view

-You can submit the link to your repos in your “Bench chat”
 */