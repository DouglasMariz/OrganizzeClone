package br.com.jins.projetos.organizzeclone.ui.main;

public interface MainContract {

    interface View{
        void addDespesa();
        void addReceita();
        void showMessenger(String messenger);
        void showLogin();
    }

    interface Presenter{
        void logoff();
    }

}
