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
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.estella.politecnico.R;
import com.estella.politecnico.utils.Timer;

import java.util.concurrent.TimeUnit;

/**
 * Example Activity to demonstrate the lifecycle callback methods.
 */
public class ActivityB extends AppCompatActivity implements View.OnClickListener{

    // Constants: max time to count --> 10 hours
    private final long MAX_TIME_TO_COUNT_IN_MILLIS = TimeUnit.HOURS.toMillis(10);

    // Views
    private Button bntStart;
    private TextView textViewTime;
    private CountDownTimer countDownTimer;

    private Timer timerUtil = new Timer();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        // set Button
        bntStart = findViewById(R.id.buttonStartCount);
        bntStart.setOnClickListener(this);
        // set TextView
        textViewTime = findViewById(R.id.textViewTime);
        textViewTime.setText(timerUtil.getTimeStyleTextFromMillis(0));

        setViewsDependingTheState(false);

        // Set views
        Button btnActivityA = findViewById(R.id.btn_start_a);
        Button btnActivityC = findViewById(R.id.btn_start_c);
        Button btnFinishActivityB = findViewById(R.id.btn_finish_b);
        Button btnStartDialog = findViewById(R.id.btn_start_dialog);

        // Set Listeners
        btnActivityA.setOnClickListener(startActivityAListener);
        btnActivityC.setOnClickListener(startActivityCListener);
        btnFinishActivityB.setOnClickListener(finishActivityBListener);
        btnStartDialog.setOnClickListener(startDialogListener);

        Log.i ("Activity B", "onCreate");
    }

    @Override
    public void onClick(View v) {
        if (v == bntStart) {
            if (countDownTimer == null) {
                startTimer();
            } else {
                stopTimer();
            }
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i ("Activity B", "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i ("Activity B", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i ("Activity B", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i ("Activity B", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i ("Activity B", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i ("Activity B", "onDestroy");

        // Cancel the countdown.
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
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

    public void startActivityA() {
        Intent intent = new Intent(this, ActivityA.class);
        startActivity(intent);
    }

    public void startActivityC() {
        Intent intent = new Intent(this, ActivityC.class);
        startActivity(intent);
    }

    public void finishActivityB() {
        finish();
    }

    // Listeners
    private View.OnClickListener startActivityAListener = new View.OnClickListener() {
        public void onClick(View v) {
            // do something when the button is clicked
            startActivityA();
        }
    };

    private View.OnClickListener startActivityCListener = new View.OnClickListener() {
        public void onClick(View v) {
            // do something when the button is clicked
            startActivityC();
        }
    };

    private View.OnClickListener finishActivityBListener = new View.OnClickListener() {
        public void onClick(View v) {
            // do something when the button is clicked
            finishActivityB();
        }
    };

    private View.OnClickListener startDialogListener = new View.OnClickListener() {
        public void onClick(View v) {
            // do something when the button is clicked
            showDialog();
        }
    };

    private void startTimer() {
        setViewsDependingTheState(true);
        // this code is just to check some debugging tools from Android Studio. Please see e)
        // paragraph of exercise 1.
        boolean valueToBeModifiedUsingWatches = false;
        if (!valueToBeModifiedUsingWatches) {
            Toast.makeText(this, R.string.activity_main_toast_start_timer, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.activity_main_toast_hacking_timer, Toast.LENGTH_SHORT).show();
        }

        countDownTimer = new CountDownTimer(MAX_TIME_TO_COUNT_IN_MILLIS, 10) {

            // callback fired on regular interval.
            public void onTick(long millisUntilFinished) {
                long timeToBeConverted = MAX_TIME_TO_COUNT_IN_MILLIS - millisUntilFinished;
                textViewTime.setText(timerUtil.getTimeStyleTextFromMillis(timeToBeConverted));
            }

            // callback fired when the time is up.
            public void onFinish() {
                Toast.makeText(getApplicationContext(), R.string.activity_main_toast_finish_timer, Toast.LENGTH_SHORT)
                        .show();
                setViewsDependingTheState(false);
                countDownTimer = null;
            }
            // start the countdown.
        }.start();
    }

    private void stopTimer() {
        Toast.makeText(this, R.string.activity_main_toast_stop_timer, Toast.LENGTH_SHORT).show();
        setViewsDependingTheState(false);
        // Cancel the countdown.
        countDownTimer.cancel();
        countDownTimer = null;
    }

    private void setViewsDependingTheState(boolean isCounting) {
        Drawable icon;
        if (isCounting) {
            bntStart.setText(R.string.activity_main_button_stop);
            // get play icon from android resource
            icon = ContextCompat.getDrawable(this, android.R.drawable.ic_media_pause);
        } else {
            bntStart.setText(R.string.activity_main_button_start);
            // get pause icon from android resource
            icon = ContextCompat.getDrawable(this, android.R.drawable.ic_media_play);
        }
        // add an icon in left part of the button
        bntStart.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
    }

}




