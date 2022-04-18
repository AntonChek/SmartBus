package com.example.smartbus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.ViewHolder> {

    private List<Device> deviceList = new ArrayList<>();


    public void addDevices(List<Device> list) {
        deviceList.clear();
        deviceList.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_device, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(deviceList.get(position));
    }

    @Override
    public int getItemCount() {
        return deviceList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameDevice;
        private TextView macAddress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameDevice = itemView.findViewById(R.id.nameDevice);
            macAddress = itemView.findViewById(R.id.macAddress);
        }

        void bind(Device device) {
            nameDevice.setText(
                    (device.getNameDevice() == null || device.getNameDevice().isEmpty()) ? " = Unnamed = " : device.getNameDevice()
            );
            macAddress.setText(device.getMacAddress());
        }
    }

}
