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

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class IdiomasActivity extends AppCompatActivity {

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
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_idiomas);

        ImageView imageView = findViewById(R.id.view); // Substitua "seu_imageview" pelo ID do seu ImageView
        Animation rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_animation); // Carrega a animação de rotação do arquivo XML
        imageView.startAnimation(rotateAnimation); // Aplica a animação ao ImageView

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        CardView basicEnglishButton = findViewById(R.id.basic_english);
        basicEnglishButton.setOnClickListener(v -> {
            String url = "https://aprendamais.mec.gov.br/course/search.php?areaids=core_course-course&q=identifica%C3%A7%C3%A3o+e+caracteriza%C3%A7%C3%A3o+pessoal%3B+localiza%C3%A7%C3%A3o+no+tempo+e+no+espa%C3%A7o+2024";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });
        CardView basicEspanishButton = findViewById(R.id.basic_espanhol);
        basicEspanishButton.setOnClickListener(v -> {
            String url = "https://aprendamais.mec.gov.br/course/search.php?search=espanhol";
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