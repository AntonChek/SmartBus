package com.example.smartbus;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchDialog extends DialogFragment {

    private boolean isScan = false;
    private DeviceAdapter adapter;

    private RecyclerView deviceListRecycler;
    private Button btnSearch;


    public static SearchDialog newInstance() {
        return new SearchDialog();
    }

    @NonNull
    @Override


    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(requireContext())
                .inflate(R.layout.search_dialog, null);

        btnSearch = view.findViewById(R.id.btnSearch);
        deviceListRecycler = view.findViewById(R.id.deviceListRecycler);

        adapter = new DeviceAdapter();

        deviceListRecycler.setHasFixedSize(true);
        deviceListRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        deviceListRecycler.setAdapter(adapter);
        deviceListRecycler.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        deviceListRecycler.setAdapter(adapter);

        btnSearch.setOnClickListener(btnView -> {
            isScan = !isScan;
            updateUI((Button) btnView);
        });

        List<Device> devices = new ArrayList<>();
        devices.add(new Device("ESP32", "24:0a:c4:85:d7:00"));



        adapter.addDevices(devices);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(requireContext())
                .setView(view);

        return dialogBuilder.create();
    }



    private void updateUI(Button button) {
        if (isScan) {
            button.setText("Остановить поиск");
        } else {
            button.setText("Найти");
        }
    }


}
