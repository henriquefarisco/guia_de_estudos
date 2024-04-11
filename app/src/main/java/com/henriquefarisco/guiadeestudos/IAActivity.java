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

public class IAActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_iaactivity);

        ImageView imageView = findViewById(R.id.view); // Substitua "seu_imageview" pelo ID do seu ImageView
        Animation rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_animation); // Carrega a animação de rotação do arquivo XML
        imageView.startAnimation(rotateAnimation); // Aplica a animação ao ImageView

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        CardView chatOpenaiButton = findViewById(R.id.gpt_button);
        chatOpenaiButton.setOnClickListener(v -> {
            String url = "https://chat.openai.com";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });

        CardView blackBoxButton = findViewById(R.id.blackbox_button);
        blackBoxButton.setOnClickListener(v -> {
            String url = "https://www.blackbox.ai/";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });

        CardView sunoAiButton = findViewById(R.id.sunoai_button);
        sunoAiButton.setOnClickListener(v -> {
            String url = "https://www.suno.ai/";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);

        });
        CardView geminiGoogleButton = findViewById(R.id.geminiai_button);
        geminiGoogleButton.setOnClickListener(v -> {
            String url = "https://bard.google.com/";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });

        CardView opusClipButton = findViewById(R.id.opus_button);
        opusClipButton.setOnClickListener(v -> {
            String url = "https://www.opus.pro/";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });

        CardView interViewerButton = findViewById(R.id.interviewer_button);
        interViewerButton.setOnClickListener(v -> {
            String url = "https://interviewer.ai/candidates/";
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