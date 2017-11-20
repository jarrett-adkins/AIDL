package com.example.admin.aidlapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.admin.aidlapplication.IMyAidlInterface;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Return the interface
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    // File>New>AIDL
    private final IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub() {
        public void basicTypes(int anInt, long aLong, boolean aBoolean,
                               float aFloat, double aDouble, String aString) {
            // Does nothing
        }

        public int getInt() {
            return 10;
        }
    };
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
