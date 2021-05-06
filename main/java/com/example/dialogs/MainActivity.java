package com.example.dialogs;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dialogs.databinding.ActivityMainBinding;
import com.example.dialogs.databinding.CustomDialogeBinding;
import com.example.dialogs.databinding.CustomToastBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    MainActivity mContext;
    ActivityMainBinding binding;
    ArrayList<String> choice;
    boolean[] tic;
    final long today = System.currentTimeMillis() - 1000;
    String title = "Diloge";
    String msg = "Want to Continue";
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mContext = this;
        tic = new boolean[]{false, false, false, false, false};
        choice = new ArrayList<>();

        binding.etDatePicker1.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                ShowDate(binding.etDatePicker1);
                binding.etDatePicker1.clearFocus();
            }
        });

        binding.etDatePicker2.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                ShowDate(binding.etDatePicker2);
                binding.etDatePicker2.clearFocus();
            }
        });

        binding.etTimePickerIn.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                ShowTime(binding.etTimePickerIn);
                binding.etTimePickerIn.clearFocus();
            }
        });

        binding.etTimePickerOut.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                ShowTime(binding.etTimePickerOut);
                binding.etTimePickerOut.clearFocus();
            }
        });
    }

    public void onButtonClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSimpleAlertDialog:
                ShowSimpleDialog();
                break;
            case R.id.btnSingleChoiceSelection:
                ShowSingleChoiceSelectionDialog();
                break;
            case R.id.btnDateDiff:
                ShowDateDiff();
                break;
            case R.id.btnMultiChoiceSelection:
                ShowMultiChoiceSelectionDialog();
                break;
            case R.id.btnCustomToast:
                showToastMessage(mContext, "message");
                break;
            case R.id.btnTotalTime:
                ShowTotalTime();
                break;
            case R.id.btnCustomDialog:
                CustomDialog();
                break;
        }
    }

    private void ShowSimpleDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(title)
                .setMessage(msg)
                .setIcon(R.drawable.notification_important_24)
                .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(mContext, "neutral button clicked", Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(mContext, "positive button clicked", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(mContext, "negative button clicked", Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    private void ShowSingleChoiceSelectionDialog() {
        String[] city = {"Jamnagar", "Junagadh", "Kutch", "Kheda", "Mehsana", "Narmada"};

    /*TODO //Withot Radio button !!!
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("color list")
                .setItems(color, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int pos) {
                        Toast.makeText(MainActivity.this, color[pos]+" color selected", Toast.LENGTH_SHORT).show();
                    }
                }).show();*/

        // TODO : shows Radio Button !!!
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("City list")
                .setIcon(R.drawable.ic_launcher_background)
                .setCancelable(false)
                // TODO: for selected Item !!!
                .setSingleChoiceItems(city, position, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int pos) {
                        position = pos;
                        binding.tv.setText(city[pos]);
                        dialogInterface.dismiss();
                        Toast.makeText(mContext, city[pos] + " selected", Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    private void ShowDateDiff() {
        //  loadsave();
        try {
            String d1 = binding.etDatePicker1.getText().toString();
            String d2 = binding.etDatePicker2.getText().toString();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date1 = simpleDateFormat.parse(d1);
            Date date2 = simpleDateFormat.parse(d2);
            long difference = Math.abs(date1.getTime() - date2.getTime());

            long difftDays = difference / (24 * 60 * 60 * 1000);
            long difftMonths = difftDays / (30);
            long difftYears = difftMonths / (12);

            Log.i("Testing", "days" + difftDays);
            binding.btnDateDiff.setText("days " + difftDays + " months " + difftMonths + " years " + difftYears);

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    private void ShowMultiChoiceSelectionDialog() {

        String[] city = getResources().getStringArray(R.array.City);

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("Multi Selection")
                .setMultiChoiceItems(city, tic, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int pos, boolean isChecked) {
                        if (isChecked) {
                            choice.add(city[pos]);
                            tic[pos] = true;
                            binding.tv.setText(choice.toString());
                        } else {
                            choice.remove(city[pos]);
                            tic[pos] = false;
                            binding.tv.setText(choice.toString());
                        }
                    }
                }).show();
    }

    private void showToastMessage(Context context, String message) {
        Toast toast = new Toast(context);
        CustomToastBinding binding1 = CustomToastBinding.inflate(getLayoutInflater());
        toast.setView(binding1.getRoot());
        binding1.image.setImageResource(R.drawable.notification_important_24);
        binding1.text.setText(message);
        toast.setGravity(Gravity.BOTTOM, 0, 96);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    private void ShowTotalTime() {
        //  loadsave();
        try {
            String t1 = binding.etTimePickerIn.getText().toString();
            String t2 = binding.etTimePickerOut.getText().toString();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
            Date time1 = simpleDateFormat.parse(t1); //time in
            Date time2 = simpleDateFormat.parse(t2); //time out
            long difference = Math.abs(time1.getTime() - time2.getTime());

            long minutes = ((difference / (1000 * 60)) % 60);
            long hours = ((difference / (1000 * 60 * 60)) % 24);

            binding.btnTotalTime.setText(hours + " : " + minutes);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void CustomDialog() {
        CustomDialogeBinding bind = CustomDialogeBinding.inflate(getLayoutInflater());
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setView(bind.getRoot());
        AlertDialog dialog = builder.create();
        dialog.show();
        //bind.ratingBar.setRating(3);
        bind.btnSubmit.setOnClickListener(view -> {
            dialog.dismiss();
            showToastMessage(mContext, bind.etText.getText().toString().trim());
        });
        bind.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Toast.makeText(mContext, "Cancel Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ShowDate(EditText etDatePicker) {
        int year, month, dayofmonth;
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayofmonth = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayofmonth) {

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayofmonth);
                //If user tries to select date in past (or today)
                if (calendar.getTimeInMillis() < today) {
                    //success
                    month = month + 1;
                    etDatePicker.setText(dayofmonth + "-" + month + "-" + year);

                } else {
                    //Make them try again
                    Toast.makeText(mContext, "Invalid date, please try again", Toast.LENGTH_LONG).show();
                }
            }
        }, year, month, dayofmonth);
        datePickerDialog.show();
    }

    private void ShowTime(EditText btn) {
        int hour, minute;
        Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {

                btn.setText(hour + ":" + minute);
            }
        }, hour, minute, true);
        timePickerDialog.show();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(mContext)
                .setTitle("Alert")
                .setMessage("Are you sure you want to exit?")
                .setIcon(R.drawable.notification_important_24)
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}