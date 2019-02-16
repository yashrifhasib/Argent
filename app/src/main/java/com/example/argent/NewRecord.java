package com.example.argent;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class NewRecord extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_new);

        final EditText details = (EditText) findViewById(R.id.editText2);
        final EditText amount = (EditText) findViewById(R.id.editText);
        final EditText date = (EditText) findViewById(R.id.editText3);

        ImageButton addButton = findViewById(R.id.imageButton);
        addButton.setBackgroundColor(Color.BLACK);

        addButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                Expense currentExpense = new Expense(details.getText().toString(), Double.parseDouble(amount.getText().toString()), date.getText().toString());
                currentExpense.getFinalBalance(userBalance, currentExpense);
                startActivity(new Intent(NewRecord.this, MainActivity.class));
            }
        });
    }
}
