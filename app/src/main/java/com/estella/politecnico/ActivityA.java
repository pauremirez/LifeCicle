/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.estella.politecnico;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.estella.politecnico.R;

/**
 * Example Activity to demonstrate the lifecycle callback methods.
 */
public class ActivityA extends AppCompatActivity implements View.OnClickListener{

    private Button btnActivityB;
    private Button bntActivityC;
    private Button btnFinishActivityA;
    private Button btnStartDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);

        // Set views
        btnActivityB = findViewById(R.id.btn_start_b);
        bntActivityC = findViewById(R.id.btn_start_c);
        btnFinishActivityA = findViewById(R.id.btn_finish_a);
        btnStartDialog = findViewById(R.id.btn_start_dialog);

        // Set Listeners
        btnActivityB.setOnClickListener(this);
        bntActivityC.setOnClickListener(this);
        btnFinishActivityA.setOnClickListener(this);
        btnStartDialog.setOnClickListener(this);

        Log.i ("Activity A", "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i ("Activity A", "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i ("Activity A", "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i ("Activity A", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i ("Activity A", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i ("Activity A", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i ("Activity A", "onDestroy");
    }

    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Add the buttons
        builder.setPositiveButton(R.string.dialog_ok_button, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(R.string.dialog_cancel_button, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                dialog.dismiss();
            }
        });
        builder.setTitle(R.string.dialog_title);
        builder.setMessage(R.string.dialog_message);
        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void startActivityB() {
        Intent intent = new Intent(this, ActivityB.class);
        startActivity(intent);
    }

    public void startActivityC() {
        Intent intent = new Intent(this, ActivityC.class);
        startActivity(intent);
    }

    public void finishActivityA() {
        finish();
    }

    @Override
    public void onClick(View v) {
        if (v == btnActivityB) {
            startActivityB();
        } else if (v == bntActivityC) {
            startActivityC();
        } else if (v == btnFinishActivityA) {
            finishActivityA();
        } else if (v == btnStartDialog) {
            showDialog();
        }
    }
}
