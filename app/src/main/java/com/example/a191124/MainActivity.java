package com.example.a191124;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        SharedPreferences sharedPreferences = getSharedPreferences("TestApp", MODE_PRIVATE);
        TextView tv = findViewById(R.id.tv);
        tv.setText(sharedPreferences.getString("userprint","no input"));
        Button btn =findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Are you sure darling")
                        .setMessage("just make sure this is what you wanttt")
                        .setPositiveButton("Yes",null)
                        .setNegativeButton("I regret", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this,"Not listening!",Toast.LENGTH_LONG)
                                        .show();
                            }
                        })
                        .show();

                EditText etd = findViewById(R.id.edi);
                String name = etd.getText().toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();

                //put data into shared preferences
                editor.putString("userprint", name);
                editor.apply();

            }
        });
    }
}