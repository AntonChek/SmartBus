package com.example.smartbus;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.nio.ByteBuffer;
import java.util.UUID;
import android.widget.RelativeLayout;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

public class Payment extends AppCompatActivity {

    private TextView textView;
    private boolean deviceFound;
    private BluetoothManager bluetoothManager;
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothLeScanner bluetoothLeScanner;
    String mac = "24:0a:c4:85:d7:00";
    FirebaseDatabase database;
    DatabaseReference myRef, macRef;
    private Button btnSearch;
    private Button bScan;
    RelativeLayout pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        setContentView(R.layout.homepage);
        textView = findViewById(R.id.id_text_view);
        //textView.setText("Press scan");
        String Users = "Users";
        bScan = findViewById(R.id.fray_payment);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users").child("thisUsers");
        pos = findViewById(R.id.pos_element);// Key


        BluetoothDevice bluetoothDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice("00:11:22:33:AA:BB");


        bScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bluetoothLeScanner.startScan(scanCallback);
                deviceFound = false;
            }
        });

        btnSearch = findViewById(R.id.another_bus);
        bScan = findViewById(R.id.fray_payment);
        btnSearch.setOnClickListener(view -> {
            SearchDialog.newInstance().show(getSupportFragmentManager(), "search_dialog");
        });

        bScan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Snackbar.make(pos, "Проезд оплачен!", Snackbar.LENGTH_SHORT).show();// Value
            }
        });
        bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        bluetoothAdapter = bluetoothManager.getAdapter();

        if(bluetoothAdapter == null || !bluetoothAdapter.isEnabled()){
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 0);
        }

        bluetoothLeScanner = bluetoothAdapter.getBluetoothLeScanner();

        textView.setText(bluetoothAdapter.getName());

    }




    ScanCallback scanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            super.onScanResult(callbackType, result);

            ScanRecord scanRecord = result.getScanRecord();
            byte[] bytes = scanRecord.getBytes();
            BluetoothDevice bluetoothDevice = result.getDevice();


            String s = bluetoothDevice.getAddress();
            String name = scanRecord.getDeviceName();
            int rssi = result.getRssi();
            Log.d("UUID",s + " rssi: " + rssi);

            if(!deviceFound)textView.setText(name +":\n"+ s);
            if(s.equals(mac)){
                byte[] bytes2 = result.getScanRecord().getManufacturerSpecificData(76);
                byte[] bytes3 = new byte[16];

                deviceFound = true;
                bluetoothLeScanner.stopScan(scanCallback);
                textView.setText("MAC found: " + ":\n"+ s);


            }


        }
    };



}


//public class Payment extends AppCompatActivity {
//    private TextView textView;
//    private Button bScan;
//    private boolean deviceFound;
//    private BluetoothManager bluetoothManager;
//    private BluetoothAdapter bluetoothAdapter;
//    private BluetoothLeScanner bluetoothLeScanner;
//    String mac = "24:0a:c4:85:d7:00";
//    FirebaseDatabase database;
//    DatabaseReference myRef, macRef;
//
//    RelativeLayout pos;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.homepage);
//        textView = findViewById(R.id.id_text_view);
//        //textView.setText("Press scan");
//        String Users = "Users";
//        bScan = findViewById(R.id.fray_payment);
//        database = FirebaseDatabase.getInstance();
//        myRef = database.getReference("Users").child("thisUsers");
//        pos = findViewById(R.id.pos_element);// Key
//
//
//        BluetoothDevice bluetoothDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice("00:11:22:33:AA:BB");
//
//
//        bScan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                bluetoothLeScanner.startScan(scanCallback);
//                deviceFound = false;
//            }
//        });
//
//        bScan.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                Snackbar.make(pos, "Проезд оплачен!", Snackbar.LENGTH_SHORT).show();// Value
//            }
//        });
//
//        bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
//        bluetoothAdapter = bluetoothManager.getAdapter();
//
//        if(bluetoothAdapter == null || !bluetoothAdapter.isEnabled()){
//            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//            startActivityForResult(enableBtIntent, 0);
//        }
//
//        bluetoothLeScanner = bluetoothAdapter.getBluetoothLeScanner();
//
//        textView.setText(bluetoothAdapter.getName());
//
//    }
//
//
//
//
//    ScanCallback scanCallback = new ScanCallback() {
//        @Override
//        public void onScanResult(int callbackType, ScanResult result) {
//            super.onScanResult(callbackType, result);
//
//            ScanRecord scanRecord = result.getScanRecord();
//            byte[] bytes = scanRecord.getBytes();
//            BluetoothDevice bluetoothDevice = result.getDevice();
//
//
//            String s = bluetoothDevice.getAddress();
//            String name = scanRecord.getDeviceName();
//            int rssi = result.getRssi();
//            Log.d("UUID",s + " rssi: " + rssi);
//
//            if(!deviceFound)textView.setText(name +":\n"+ s);
//            if(s.equals(mac)){
//                byte[] bytes2 = result.getScanRecord().getManufacturerSpecificData(76);
//                byte[] bytes3 = new byte[16];
//
//                deviceFound = true;
//                bluetoothLeScanner.stopScan(scanCallback);
//                textView.setText("MAC found: " + ":\n"+ s);
//
//
//            }
//
//
//        }
//    };
//
//
//
//
//
//}