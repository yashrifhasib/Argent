package com.example.argent;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

public class NewRecord extends AppCompatActivity {

    private Expense currentExpense;
    private int mYear,mMonth,mDay;
    private TextView dateText;
    private TextView recurringCharge;
    private static ArrayList<Expense> expenses;

    public Expense getCurrentExpense() {
        return currentExpense;
    }

    public void setCurrentExpense(Expense currentExpense) {
        this.currentExpense = currentExpense;
    }

    public static ArrayList getExpenses()
    {
        return expenses;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_new);
        final Button pickDate = (Button) findViewById(R.id.pick_date);
        dateText = (TextView) findViewById(R.id.date);
        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.US);

                dateText.setText(sdf.format(myCalendar.getTime()));
            }

        };




        pickDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                // Launch Date Picker Dialog taken from StockExchange
                DatePickerDialog dpd = new DatePickerDialog(NewRecord.this, new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                // Display Selected date in textbox

                                if (year < mYear)
                                    view.updateDate(mYear,mMonth,mDay);

                                if (monthOfYear < mMonth && year == mYear)
                                    view.updateDate(mYear,mMonth,mDay);

                                if (dayOfMonth < mDay && year == mYear && monthOfYear == mMonth)
                                    view.updateDate(mYear,mMonth,mDay);

                                dateText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                dpd.getDatePicker().setMinDate(System.currentTimeMillis());
                dpd.show();

            }
        });
       // loadData();
        configureFinishNew();
    }

    public void configureFinishNew()
    {
        final EditText itemPurchased = (EditText) findViewById(R.id.editText);
        final EditText amount = (EditText) findViewById(R.id.editText2);

        final Spinner spinner = findViewById(R.id.spinner);
        String[] selections = {"Not a recurring expense", "Monthly", "Annually"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, selections);
        spinner.setAdapter(adapter);

        ImageButton addButton =(ImageButton) findViewById(R.id.floatingActionButton);
        addButton.setBackgroundColor(Color.BLACK);


        boolean cancel= false;
        View currentView= null;
        //IF any of these are empty then WE DO BIGGO ERROR
        if(TextUtils.isEmpty(itemPurchased.getText().toString()))
        {
            itemPurchased.setError(getString(R.string.error_field_required));
            itemPurchased.requestFocus();
            cancel=true;
            currentView=itemPurchased;
        }else{
            cancel=false;
        }
        if(TextUtils.isEmpty(amount.getText()))
        {
            amount.setError(getString(R.string.error_field_required));
            amount.requestFocus();
            cancel=true;
            currentView=amount;
        }else{
            cancel=false;
        }
        if(TextUtils.isEmpty(dateText.getText().toString()))
        {
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            Date date = new Date();
            dateText.setText(dateFormat.format(date));
        }
        if(TextUtils.isEmpty(spinner.getSelectedItem().toString()))
        {
            recurringCharge.setText("Not a recurring expense");
        }

        expenses = new ArrayList<>();

        if(cancel)
        {
            currentView.requestFocus();

        }else {
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // saveData();
                    setCurrentExpense(new Expense(itemPurchased.getText().toString(), Double.parseDouble(amount.getText().toString()), dateText.getText().toString(), spinner.getSelectedItem().toString()));
                    startActivity(new Intent(NewRecord.this, MainActivity.class));
                }
            });
        }
    }

    private void saveData()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson= new Gson();
        String json = gson.toJson(expenses);
        editor.putString("Expenses", json);
        editor.apply();
    }

    private void loadData()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson= new Gson();
        String json= sharedPreferences.getString("Expenses", null);
        Type type = new TypeToken<ArrayList<Expense>>() {}.getType();
        expenses=gson.fromJson(json, type);

        if(expenses==null)
        {
            expenses=new ArrayList<>();
        }
    }




}
