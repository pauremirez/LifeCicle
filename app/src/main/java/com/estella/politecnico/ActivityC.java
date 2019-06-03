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

import com.estella.politecnico.R;

/**
 * Example Activity to demonstrate the lifecycle callback methods.
 */
public class ActivityC extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);

        Log.i ("Activity C", "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i ("Activity C", "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i ("Activity C", "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i ("Activity C", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i ("Activity C", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i ("Activity C", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i ("Activity C", "onDestroy");
    }

    public void showDialog(View v) {
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

    public void startActivityA(View v) {
        Intent intent = new Intent(this, ActivityA.class);
        startActivity(intent);
    }

    public void startActivityB(View v) {
        Intent intent = new Intent(this, ActivityB.class);
        startActivity(intent);
    }

    public void finishActivityC(View v) {
        finish();
    }
}
