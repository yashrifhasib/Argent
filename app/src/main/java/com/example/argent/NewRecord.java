package com.example.argent;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class NewRecord extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_new);

        ImageButton addButton = findViewById(R.id.imageButton);
        addButton.setBackgroundColor(Color.BLACK);

        addButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                startActivity(new Intent(NewRecord.this, MainActivity.class));
            }
        });
    }
}
