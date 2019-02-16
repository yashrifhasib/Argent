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


        final double currentBalance = getIntent().getDoubleExtra("currentBalance", 2000);

        final EditText details = findViewById(R.id.editText2);
        final EditText amount = findViewById(R.id.editText);
        final EditText date = findViewById(R.id.editText3);

        ImageButton addButton = findViewById(R.id.imageButton);
        addButton.setBackgroundColor(Color.BLACK);

        if (details.getText().toString().length() == 0)
            details.setError("Details is required");


        addButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                Expense currentExpense = new Expense(details.getText().toString(), Double.parseDouble(amount.getText().toString()), date.getText().toString());
                double newBalance = currentExpense.getFinalBalance(currentBalance, currentExpense);
                startActivity(new Intent(NewRecord.this, MainActivity.class).putExtra("newBalance", newBalance));
            }
        });
    }
}
