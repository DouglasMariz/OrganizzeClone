package br.com.jins.projetos.organizzeclone.ui.intro;

public interface MainIntroContract {

    interface View{
        void showMain();
    }

    interface Presenter{
        void checkLoggedInUser();
    }


}
