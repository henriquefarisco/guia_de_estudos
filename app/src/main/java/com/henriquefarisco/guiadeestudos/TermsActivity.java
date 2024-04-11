package com.henriquefarisco.guiadeestudos;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class TermsActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

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

        setContentView(R.layout.activity_terms);

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        boolean termsAccepted = sharedPreferences.getBoolean("terms_accepted", false);

        if (!termsAccepted) {
            // Mostrar os termos de uso
            showTermsDialog();
        } else {
            // Ir para a tela principal
            goToMainActivity();
        }
    }

    private void showTermsDialog() {
        CardView acceptButton = findViewById(R.id.view);
        acceptButton.setOnClickListener(v -> {
            // Aplicar animação de brilho
            Animation brilho = AnimationUtils.loadAnimation(TermsActivity.this, R.anim.brilho_botao);
            acceptButton.startAnimation(brilho);

            // Marcar os termos como aceitos
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("terms_accepted", true);
            editor.apply();

            // Ir para a tela principal após um breve atraso para a animação ser vista
            new Handler().postDelayed(this::goToMainActivity, 200); // Ajuste o tempo conforme necessário para combinar com a duração da animação
        });
    }

    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); // Finaliza a activity atual para evitar que o usuário retorne para ela pressionando o botão "Voltar"
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
