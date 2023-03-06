package com.example.tebakgambar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tebakgambar.databinding.ActivityTemplateBinding;

public class Template extends AppCompatActivity {

    private ActivityTemplateBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTemplateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        int level = bundle.getInt("Level"); //Mengambil int yang telah dipassing

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Template.this, Main.class));
                finish();
            }
        });


        binding.gambarImageView.setImageResource(gambarHewan[level]);
        binding.tebakButtonCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = (EditText) findViewById(R.id.inputEditText);

                AlertDialog alertDialog;
                AlertDialog.Builder builder = new AlertDialog.Builder(Template.this);

                final View hasil = getLayoutInflater().inflate(R.layout.hasil, null);

                builder.setView(hasil);
                alertDialog = builder.create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.show();

                TextView status = hasil.findViewById(R.id.statusHasilTextView);

                if (namaHewan[level].equals(input.getText().toString().toLowerCase())) {
                    Intent intent = new Intent(getIntent());
                    intent.putExtra("Level", level + 1);

                    status.setText("Benar");

                    if (level < 5) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                alertDialog.dismiss();
                                startActivity(intent);
                            }
                        },2000);
                    } else {
                        status.setText("You Win");
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                alertDialog.dismiss();
                            }
                        },2000);
                    }

                } else {
                    status.setText("Salah");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            alertDialog.dismiss();
                        }
                    },1500);
                }
            }
        });
    }

    private int[] gambarHewan = {
            R.drawable.anjing,
            R.drawable.jerapah,
            R.drawable.gajah,
            R.drawable.kuda,
            R.drawable.ayam,
            R.drawable.ikan,
    };

    private String[] namaHewan = {
            "anjing",
            "jerapah",
            "gajah",
            "kuda",
            "ayam",
            "ikan"
    };
}