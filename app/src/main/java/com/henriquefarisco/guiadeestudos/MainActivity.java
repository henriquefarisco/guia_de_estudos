package com.henriquefarisco.guiadeestudos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class MainActivity extends AppCompatActivity {

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

        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE); // Corrigido para "MyPrefs"
        boolean termsAccepted = prefs.getBoolean("terms_accepted", false);
        if (!termsAccepted) {
            startActivity(new Intent(MainActivity.this, TermsActivity.class));
            finish(); // Encerra esta atividade para que o usuário não possa voltar para ela pressionando o botão "Voltar"
            return; // Adicionado para evitar a execução do código abaixo quando os termos ainda não foram aceitos
        }

        // Os termos já foram aceitos, então inicie sua atividade principal normalmente
        setContentView(R.layout.activity_main);
        // Restante do código para configurar a atividade principal

        ImageView imageView = findViewById(R.id.view); // Substitua "seu_imageview" pelo ID do seu ImageView
        Animation rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_animation); // Carrega a animação de rotação do arquivo XML
        imageView.startAnimation(rotateAnimation); // Aplica a animação ao ImageView

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Localize os botões no layout da atividade principal
        CardView btnTecnologia = findViewById(R.id.tec_main);
        CardView btnIdiomas = findViewById(R.id.idiomas_main);
        CardView catalogButton = findViewById(R.id.catalog_button);
        CardView iaButton = findViewById(R.id.ia_button);
        CardView admButton = findViewById(R.id.administrativo_button);
        CardView sobreButton = findViewById(R.id.sobre_button);
        CardView empreendedorismoButton = findViewById(R.id.empreendedorismo_button);


        // Adicione um ouvinte de clique ao botão Tecnologia
        btnTecnologia.setOnClickListener(v -> iniciarActivity(TecnologiaActivity.class));

        // Adicione um ouvinte de clique ao botão Administrativo
        admButton.setOnClickListener(v -> iniciarActivity(AdministracaoActivity.class));

        // Adicione um ouvinte de clique ao botão Idiomas
        btnIdiomas.setOnClickListener(v -> iniciarActivity(IdiomasActivity.class));

        // Adicione um ouvinte de clique ao botão Catálogo
        catalogButton.setOnClickListener(v -> iniciarActivity(CatalogoActivity.class));

        // Adicione um ouvinte de clique ao botão IA
        iaButton.setOnClickListener(v -> iniciarActivity(IAActivity.class));

        // Adicione um ouvinte de clique ao botão Sobre
        sobreButton.setOnClickListener(v -> abrirSite());

        // Adicione um ouvinte de clique ao botão Empreendedorismo
        empreendedorismoButton.setOnClickListener(v -> iniciarActivity(EmpreendedorismoActivity.class));
    }

    private void iniciarActivity(Class<?> activity) {
        // Cria um Intent para iniciar a atividade passada como parâmetro
        Intent intent = new Intent(MainActivity.this, activity);

        // Inicia a atividade
        startActivity(intent);
    }

    // Método para abrir o link do site em um navegador externo
    private void abrirSite() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.henriquefarisco.com.br/home/guia-de-estudos"));
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
