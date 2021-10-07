package com.pennueng.pennuengrobotremote;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private BluetoothAdapter bluetoothAdapter;
    private Set<BluetoothDevice> pairedDevices;
    private ListView devicesView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        devicesView = findViewById(R.id.devicesView);



        //
        requestPermissions();

        //
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();




    }

    public void handleTurnOnButton(View view) {
        Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivity(turnOn);

    }

    public void handleTurnOffButton(View view) {
        bluetoothAdapter.disable();
    }

    public void handleGetVisibleButton(View view) {
        Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivity(getVisible);
    }

    public void handleListDevicesButton(View view) {
        pairedDevices = bluetoothAdapter.getBondedDevices();

        ArrayList items = new ArrayList();

        for(BluetoothDevice bluetoothDevice : pairedDevices) {
            items.add(bluetoothDevice.getName());
            //Log.d("myDebug", "bluetoothDevice:"+bluetoothDevice.getName());
        }

        //
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);

        devicesView.setAdapter(itemsAdapter);

        //Log.d("myDebug", "bluetoothDevices:"+list.size());
    }

    private void requestPermissions(){
        int androidVersion = Build.VERSION.SDK_INT;
        if (androidVersion >= 23){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                    }, 0);
        }
    }
}