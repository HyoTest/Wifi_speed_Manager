package com.example.hyojin.wifi_speed_manager;

import android.net.DhcpInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    WifiManager wifiManager, wifiManager2;

    TextView txt_Wifi, txt_wifi_ip;

    WifiInfo wifi, wifi2;
    DhcpInfo dhcpInfo;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        txt_Wifi = (TextView) findViewById(R.id.txt_Wifi);
        txt_wifi_ip = (TextView) findViewById(R.id.txt_wifi_ip);
        wifiManager2 = (WifiManager) getSystemService(WIFI_SERVICE);

        wifi2 = wifiManager2.getConnectionInfo();


        handler.post(callback);

    }

    Handler handler = new Handler();

    public Runnable callback = new Runnable() {

        @Override

        public void run() {

            Boolean bool = wifiManager2.isWifiEnabled();

// TODO Auto-generated method stub

            handler.postDelayed(callback, 1000);

            wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);

            wifi = wifiManager.getConnectionInfo();

            String wifiinfo = WifiInfo.LINK_SPEED_UNITS;

            int speed = wifi.getLinkSpeed();

            String name = wifi.getSSID();

            String TEXT = "Wifi Name : " + name + "\n" + "Speed : " + String.valueOf(speed) + " " + wifiinfo;

            txt_Wifi.setText(TEXT);

            /**** wifi ip ****/
            dhcpInfo = wifiManager.getDhcpInfo();
            int serverIp = dhcpInfo.gateway;
            String ipAdress = String.format("%d.%d.%d.%d", serverIp & 0xff, serverIp >> 8 & 0xff, serverIp >> 16 & 0xff, serverIp >> 24 & 0xff);

            txt_wifi_ip.setText(ipAdress);


        }

    };

}