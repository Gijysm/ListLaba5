package com.example.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SelectedActivity extends AppCompatActivity {

    private TextView Title;
    private TextView Description;
    private ImageView Image_M;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_selected);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Image_M = findViewById(R.id.tourImageView);
        Title = findViewById(R.id.titleTextView);
        Description = findViewById(R.id.descriptionTextView);
        Bundle arguments = getIntent().getExtras();
        Image_M.setImageResource(arguments.getInt("Image"));
        Title.setText(arguments.get("Name").toString());
        Description.setText(arguments.get("Info").toString());
    }
    public void OnClick(View view)
    {
        Intent intent = new Intent(SelectedActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
