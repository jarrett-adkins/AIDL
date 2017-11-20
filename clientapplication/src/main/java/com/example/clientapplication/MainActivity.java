package com.example.clientapplication;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ClientMainActivity";
    private IMyAidlInterface iMyAidlInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: creating intent.");
        Intent i = new Intent("com.example.admin.aidlapplication.IMyAidlInterface");
        bindService(implicitToExplicit(i), mConnection, Context.BIND_AUTO_CREATE);
        Log.d(TAG, "onCreate: called bindService.");
    }

    private Intent implicitToExplicit(Intent implicit) {
        PackageManager pm = getPackageManager();
        List<ResolveInfo> resolveInfoList = pm.queryIntentServices(implicit, 0);

        if( resolveInfoList == null || resolveInfoList.size() != 1 ) {
            Log.d(TAG, "implicitToExplicit: returning null");
            return null;
        }

        ResolveInfo serviceInfo = resolveInfoList.get(0);
        ComponentName componentName = new ComponentName(serviceInfo.serviceInfo.packageName,
                serviceInfo.serviceInfo.name);
        Intent explicit = new Intent( implicit );
        explicit.setComponent(componentName);
        return explicit;
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        // Called when the connection with the service is established
        public void onServiceConnected(ComponentName className, IBinder service) {
            // Following the example above for an AIDL interface,
            // this gets an instance of the IRemoteInterface, which we can use to call on the service
            Log.d(TAG, "onServiceConnected: ");
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);

            try {
                Toast.makeText(MainActivity.this, "" + iMyAidlInterface.getInt(),
                        Toast.LENGTH_SHORT).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        // Called when the connection with the service disconnects unexpectedly
        public void onServiceDisconnected(ComponentName className) {
            Log.d(TAG, "onServiceDisconnected: ");
            Log.e(TAG, "Service has unexpectedly disconnected");
            iMyAidlInterface = null;
        }
    };
}
