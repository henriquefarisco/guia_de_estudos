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

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class CatalogoActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_catalogo);
        ImageView imageView = findViewById(R.id.view); // Substitua "seu_imageview" pelo ID do seu ImageView
        Animation rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_animation); // Carrega a animação de rotação do arquivo XML
        imageView.startAnimation(rotateAnimation); // Aplica a animação ao ImageView

        CardView fundBradescoButton = findViewById(R.id.fund_bradesco_button);

        fundBradescoButton.setOnClickListener(v -> {
            // URL do link que você quer abrir
            String url = "https://www.ev.org.br/cursos";

            // Cria um Intent com a ação ACTION_VIEW e a URL
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));

            // Inicia a atividade para abrir o link no navegador externo padrão
            startActivity(intent);
        });
        CardView mecButton = findViewById(R.id.mec_button);
        mecButton.setOnClickListener(v -> {
            String url = "https://aprendamais.mec.gov.br/course/index.php";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });
        CardView fgvButton = findViewById(R.id.fgv_button);
        fgvButton.setOnClickListener(v -> {
            String url = "https://educacao-executiva.fgv.br/cursos/gratuitos";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });

        CardView cieeButton = findViewById(R.id.ciee_button);
        cieeButton.setOnClickListener(v -> {
            String url = "https://sabervirtual.ciee.org.br/sabervirtual?institution=sabervirtual";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });

        CardView sebraeButton = findViewById(R.id.sebrae_button);
        sebraeButton.setOnClickListener(v -> {
            String url = "https://sebrae.com.br/sites/PortalSebrae/cursosonline";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });

        CardView ifrsButton = findViewById(R.id.ifrs_button);
        ifrsButton.setOnClickListener(v -> {
            String url = "https://moodle.ifrs.edu.br/course/index.php";
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
