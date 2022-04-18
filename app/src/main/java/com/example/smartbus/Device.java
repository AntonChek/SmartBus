package com.example.smartbus;

public class Device {

    private String nameDevice;
    private String macAddress;


    public Device(String nameDevice, String macAddress) {
        this.nameDevice = nameDevice;
        this.macAddress = macAddress;
    }

    public String getNameDevice() {
        return nameDevice;
    }

    public void setNameDevice(String nameDevice) {
        this.nameDevice = nameDevice;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
}
