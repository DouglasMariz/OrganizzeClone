package br.com.jins.projetos.organizzeclone.ui.intro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;

import br.com.jins.projetos.organizzeclone.R;
import br.com.jins.projetos.organizzeclone.ui.login.LoginActivity;
import br.com.jins.projetos.organizzeclone.ui.main.MainActivity;
import br.com.jins.projetos.organizzeclone.ui.user.UserActivity;

public class MainIntroActivity extends IntroActivity implements MainIntroContract.View{

    MainIntroContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new MainIntroPresenter(this);

        //Verify that the user is already logged in
        presenter.checkLoggedInUser();

        setButtonBackVisible(false);
        setButtonNextVisible(false);

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_1)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_2)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_3)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_4)
                //.canGoBackward(true)// Define se ao chegar nesse slider pode voltar
                //.canGoForward(false)// Define se após esse slider pode continuar adiante
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_cadastro)
                .canGoForward(false)// Define se após esse slider pode continuar adiante
                .build());




    }

    public void botaoEntrar(View view){
        startActivity(new Intent(this,LoginActivity.class));

    }

    public void botaoCadastrar(View view){
        startActivity(new Intent(this,UserActivity.class));
    }


    public void showMain(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}
