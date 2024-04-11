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

public class EmpreendedorismoActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_empreendedorismo);

        ImageView imageView = findViewById(R.id.view); // Substitua "seu_imageview" pelo ID do seu ImageView
        Animation rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_animation); // Carrega a animação de rotação do arquivo XML
        imageView.startAnimation(rotateAnimation); // Aplica a animação ao ImageView

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        CardView btnMarketingBasico = findViewById(R.id.marketing_digital_button);
        btnMarketingBasico.setOnClickListener(v -> abrirLink("https://sebrae.com.br/sites/PortalSebrae/cursosonline/marketing-digital-para-sua-empresa-primeiros-passos,5497125576a4e710VgnVCM100000d701210aRCRD"));

        CardView btnFundamentosBasico = findViewById(R.id.fundamentos_basicos_negocios);
        btnFundamentosBasico.setOnClickListener(v -> abrirLink("https://sebrae.com.br/sites/PortalSebrae/cursosonline/fundamentos-basicos-para-a-criacao-de-negocios,15be5fa05e239710VgnVCM100000d701210aRCRD"));

        CardView btnSkillsParaLiderarBasico = findViewById(R.id.skills_para_liderar_button);
        btnSkillsParaLiderarBasico.setOnClickListener(v -> abrirLink("https://sebrae.com.br/sites/PortalSebrae/cursosonline/flow-skills-para-liderar,10c01fae57de1810VgnVCM100000d701210aRCRD"));

        CardView btnComunicacaoEmpresarialBasico = findViewById(R.id.comunicacao_empresarial);
        btnComunicacaoEmpresarialBasico.setOnClickListener(v -> abrirLink("https://www.ev.org.br/cursos/comunicacao-empresarial"));

    }
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