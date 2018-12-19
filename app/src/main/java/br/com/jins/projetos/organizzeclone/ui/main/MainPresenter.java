package br.com.jins.projetos.organizzeclone.ui.main;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

import br.com.jins.projetos.organizzeclone.data.source.remote.conectionFirebase;

public class MainPresenter implements MainContract.Presenter {

    private Activity activity;
    private MainContract.View view;
    private FirebaseAuth autetication;

    public MainPresenter(Activity activity, MainContract.View view) {
        this.activity = activity;
        this.view = view;
        autetication = conectionFirebase.getFirebaseAuth();
    }

    @Override
    public void logoff() {
        autetication.signOut();
        view.showLogin();
    }
}
