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

public class AdministracaoActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_administracao);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView imageView = findViewById(R.id.view); // Substitua "seu_imageview" pelo ID do seu ImageView
        Animation rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_animation); // Carrega a animação de rotação do arquivo XML
        imageView.startAnimation(rotateAnimation); // Aplica a animação ao ImageView

        // Localize os botões no layout da atividade de administração
        CardView btnAdministracaoBasica = findViewById(R.id.administracao_basica_button);
        CardView btnAprimoramentoProfissional = findViewById(R.id.aprimoramento_profissional_button);
        CardView btnComunicacaoOralEscrita = findViewById(R.id.comunicacao_oral_escrita_button);
        CardView btnFinancasBasicas = findViewById(R.id.financas_basicas_button);

        // Adicione um listener de clique ao botão Administração Básica
        btnAdministracaoBasica.setOnClickListener(v -> abrirLink("https://www.ev.org.br/trilhas-de-conhecimento/administracao-no-seculo-21"));

        // Adicione um listener de clique ao botão Aprimoramento Profissional
        btnAprimoramentoProfissional.setOnClickListener(v -> abrirLink("https://www.ev.org.br/trilhas-de-conhecimento/aprimoramento-profissional"));

        // Adicione um listener de clique ao botão Comunicação Oral e Escrita
        btnComunicacaoOralEscrita.setOnClickListener(v -> abrirLink("https://www.ev.org.br/trilhas-de-conhecimento/comunicacao-escrita-e-oral"));

        // Adicione um listener de clique ao botão Finanças Básicas
        btnFinancasBasicas.setOnClickListener(v -> abrirLink("https://www.ev.org.br/trilhas-de-conhecimento/financas"));
    }

    // Método para abrir o link fornecido em um navegador externo
    private void abrirLink(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
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
