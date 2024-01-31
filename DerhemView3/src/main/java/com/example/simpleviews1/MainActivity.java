package com.example.simpleviews1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    // Define an array of image resource IDs
    private final int[] imageIds = {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4};
    private int currentImageIndex = 0;

    // Method to handle ImageButton click
    public void onImageButtonClick(View view){
        currentImageIndex = (currentImageIndex + 1) % imageIds.length;// Rotate through the images
        ImageButton imageButton = findViewById(R.id.btnImg1);// Get the ImageButton
        imageButton.setImageResource(imageIds[currentImageIndex]);// Set the new image
        String imageName = "image" + (currentImageIndex + 1);
        DisplayToast(getString(R.string.myname) + imageName);// Display a toast with your name and the image name
    }

    public void btnSaved_clicked (View view) {

        // Launch a website when the Save Button is clicked
        String web_codecadmy = "https://www.codecademy.com/";

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(web_codecadmy));
        startActivity(intent);
    }

    /** Called when the activity is first created. */



    @Override
    public void onBackPressed(){

        //A dialog that can show a title, up to three buttons, a list of selectable items, or a custom layout.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.Message)
                .setTitle(R.string.app_name)
                .setIcon(R.drawable.yes_no_ic)
                .setCancelable(false) // method ensures that the user cannot dismiss the dialog without answering "Yes" or "No."
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();  // Close the activity (exit the app)
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();  // Dismiss the dialog and stay in the app
                    }
                });

        // 3. Get the AlertDialog.
        AlertDialog dialog = builder.create();

        // 4. Show the AlertDialog.
        dialog.show();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //---Button view---
        Button btnOpen = (Button) findViewById(R.id.btnOpen);
        //register the button with an event listener
        btnOpen.setOnClickListener(new View.OnClickListener()
        { //anonymous class
            //implement your event handler method
            // Launch device settings screen
            public void onClick(View v) {
                startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
            }
        });

		/*
		//---Button view---
		Button btnSave = (Button) findViewById(R.id.btnSave);
		btnSave.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v) {
				DisplayToast("You have clicked the Save button");
			}
		});
        */

        //---CheckBox---
        CheckBox checkBox = (CheckBox) findViewById(R.id.chkAutosave);
        checkBox.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                if (((CheckBox)v).isChecked())
                    DisplayToast(getString(R.string.cbx_checked));
                else
                    DisplayToast(getString(R.string.cbx_unchecked));
            }
        });

        //---RadioButton---
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.rdbGp1);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb1 = (RadioButton) findViewById(R.id.rdb1);
                if (rb1.isChecked()) {
                    DisplayToast(getString(R.string.option_1_checked));
                } else {
                    DisplayToast(getString(R.string.option_2_checked));
                }
            }
        });

        //---ToggleButton---
        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggle1);
        toggleButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                if (((ToggleButton)v).isChecked())
                    DisplayToast(getString(R.string.button_is_on));
                else
                    DisplayToast(getString(R.string.button_is_off));
            }
        });


        Switch sw = (Switch) findViewById(R.id.switch1);

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    DisplayToast(getString(R.string.sw_is_on));
                } else {
                    DisplayToast(getString(R.string.sw_is_off));
                }
            }
        });
    }

    @Override
    protected void onPause(){
        super.onPause();
        String studentId = "N01533313";
        Log.d("AppBackground",  getString(R.string.myname)+  studentId);
    }


    private void DisplayToast(String msg)
    {
        Toast.makeText(getBaseContext(), msg,
                Toast.LENGTH_SHORT).show();
    }
}