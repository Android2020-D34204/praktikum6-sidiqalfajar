package id.ac.id.telkomuniversity.tass.praktikactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
TextView hasil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        hasil = findViewById(R.id.textView2);
        hasil.setText(getIntent().getStringExtra("edit"));
    }
}