package com.kaibalya.note;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ListAdapter.BtnListener {

    private RecyclerView recyclerView;
    ListAdapter listAdapter;
    List<String> sList = new ArrayList<>();
    Button btn;
    TextView textView;
    AlertDialog.Builder alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // recyler view
        recyclerView = findViewById(R.id.recylerViewId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btn = findViewById(R.id.goId);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView = findViewById(R.id.textId);
                if(textView.getText().toString().length()> 0){
                    sList.add(textView.getText().toString());
                    // initialize listAdapter
                    listAdapter = new ListAdapter(sList,MainActivity.this, MainActivity.this);
                    recyclerView.setAdapter(listAdapter);
                }else{
                    textView.setHint("Enter Text");
                    textView.setHintTextColor(Color.RED);
                }
            }
        });
    }

    @Override
    public void delete(int position) {
        Toast.makeText(this, "clicked..."+position, Toast.LENGTH_SHORT).show();
        sList.remove(position);
        listAdapter.notifyItemRemoved(position);
    }

    @Override
    public void edit(int position) {

        final EditText input = new EditText(MainActivity.this);
        input.setHeight(100);
        input.setWidth(340);
        input.setGravity(Gravity.LEFT);

        input.setImeOptions(EditorInfo.IME_ACTION_DONE);
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Edit Item")
                .setMessage("Current Item Name:: "+sList.get(position))


                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String name = input.getText().toString();

                        sList.set(position, name);
                        Toast.makeText(MainActivity.this, "Update sucessful", Toast.LENGTH_SHORT).show();
                        listAdapter.notifyItemChanged(position);
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                //.setIcon(android.R.drawable.ic_dialog_alert)
                .setView(input).show();
    }
}