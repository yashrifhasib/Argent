package com.example.argent;

import java.time.LocalDate;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

public class NewRecord extends AppCompatActivity {

    private Expense currentExpense;

    public Expense getCurrentExpense() {
        return currentExpense;
    }

    public void setCurrentExpense(Expense currentExpense) {
        this.currentExpense = currentExpense;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_new);


        configureFinishNew();
    }

    public void configureFinishNew()
    {
        final EditText itemPurchased = (EditText) findViewById(R.id.editText);
        final EditText amount = (EditText) findViewById(R.id.editText2);
        final EditText date = (EditText) findViewById(R.id.editText3);
        final EditText placeOfPurchase = (EditText) findViewById(R.id.editText4);



        final Spinner spinner = findViewById(R.id.spinner);
        String[] selections = {"Not a recurring expense", "Monthly", "Annually"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, selections);
        spinner.setAdapter(adapter);

        ImageButton addButton =(ImageButton) findViewById(R.id.floatingActionButton);
        addButton.setBackgroundColor(Color.BLACK);
        if(TextUtils.isEmpty(itemPurchased.getText().toString()))
        {
            itemPurchased.setError(getString(R.string.error_field_required));
            itemPurchased.requestFocus();
        }
        if(TextUtils.isEmpty(amount.getText()))
        {
            amount.setError(getString(R.string.error_field_required));
            amount.requestFocus();
        }
        if(TextUtils.isEmpty(placeOfPurchase.getText().toString()))
        {
            placeOfPurchase.setError(getString(R.string.error_field_required));
            placeOfPurchase.requestFocus();
        }

        addButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                setCurrentExpense (new Expense(itemPurchased.getText().toString(), Double.parseDouble(amount.getText().toString()), date.getText().toString(), placeOfPurchase.getText().toString(), spinner.getSelectedItem().toString()));
                finish();
            }
        });
    }




}
