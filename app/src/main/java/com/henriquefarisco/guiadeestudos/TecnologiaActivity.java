package com.henriquefarisco.guiadeestudos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class TecnologiaActivity extends AppCompatActivity {

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean isChromeOS = isDeviceChromeOS();

        // Definir a orientação da tela de acordo com o dispositivo
        if (isChromeOS) {
            // Permitir orientação livre (unspecified) no Chrome OS
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        } else {
            // Restringir a orientação ao retrato em outros dispositivos
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        setContentView(R.layout.activity_tecnologia);

        ImageView imageView = findViewById(R.id.view); // Substitua "seu_imageview" pelo ID do seu ImageView
        Animation rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_animation); // Carrega a animação de rotação do arquivo XML
        imageView.startAnimation(rotateAnimation); // Aplica a animação ao ImageView

        // Initialize the basic_tech Button
        CardView btnInformaticaBasica = findViewById(R.id.basic_tech);

        // Set a long click listener for the basic_tech Button
        btnInformaticaBasica.setOnLongClickListener(view -> {
            Toast.makeText(this, R.string.basic_tech_txt, Toast.LENGTH_LONG).show();
            return true;
        });

        // Set a click listener for the basic_tech Button
        btnInformaticaBasica.setOnClickListener(view -> {
            String url = "https://www.ev.org.br/trilhas-de-conhecimento/primeiros-passos-em-tecnologia";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });

        // Initialize the basic_programming Button
        CardView basicProgrammingButton = findViewById(R.id.basic_programming);

        // Set a long click listener for the basic_programming Button
        basicProgrammingButton.setOnLongClickListener(view -> {
            Toast.makeText(this, R.string.basic_programming_txt, Toast.LENGTH_LONG).show();
            return true;
        });

        // Set a click listener for the basic_programming Button
        basicProgrammingButton.setOnClickListener(view -> {
            String url = "https://www.ev.org.br/trilhas-de-conhecimento/linguagem-de-programacao-python";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });

        // Initialize the office_button Button
        CardView basicofficeButton = findViewById(R.id.office_button);

        // Set a long click listener for the office_button Button
        basicofficeButton.setOnLongClickListener(view -> {
            Toast.makeText(this, R.string.basic_office_txt, Toast.LENGTH_LONG).show();
            return true;
        });

        // Fix redundant click listener definition
        basicofficeButton.setOnClickListener(view -> {
            String url = "https://www.ev.org.br/trilhas-de-conhecimento/pacote-office-2016";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });
    }
    private boolean isDeviceChromeOS() {
        PackageManager packageManager = getPackageManager();
        try {
            packageManager.getApplicationInfo("org.chromium.arc", 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}