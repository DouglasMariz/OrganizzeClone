package br.com.jins.projetos.organizzeclone.ui.intro;

import com.google.firebase.auth.FirebaseAuth;

import br.com.jins.projetos.organizzeclone.data.source.remote.conectionFirebase;
import br.com.jins.projetos.organizzeclone.ui.login.LoginContract;

public class MainIntroPresenter implements MainIntroContract.Presenter {

    private FirebaseAuth autetication;
    private MainIntroContract.View view;

    public MainIntroPresenter( MainIntroContract.View view) {

        this.view = view;
        autetication = conectionFirebase.getFirebaseAuth();

    }

    @Override
    public void checkLoggedInUser() {
        if(autetication.getCurrentUser()!=null){
            view.showMain();
        }
    }
}
