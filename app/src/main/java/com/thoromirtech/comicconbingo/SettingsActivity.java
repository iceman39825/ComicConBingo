package com.thoromirtech.comicconbingo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class SettingsActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        //final Button button = (Button) findViewById(R.id.play);
        //button.setOnClickListener(new View.OnClickListener() {
        //    public void onClick(View v) {
        //        // Perform action on clicks
        //        Intent intent = new Intent(ResultActivity.this, BingoActivity.class);
        //        startActivity(intent);
        //    }
        //});
    }
}
