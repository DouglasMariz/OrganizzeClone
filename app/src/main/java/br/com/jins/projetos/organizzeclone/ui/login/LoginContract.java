package br.com.jins.projetos.organizzeclone.ui.login;

import br.com.jins.projetos.organizzeclone.model.User;

public interface LoginContract {

    interface View{
        void loadUserData();
        void showMessenger(String messenger);
        void showMain();
    }

    interface Presenter{
        void logInUser(User userLogin);
        void checkLoggedInUser();
    }

}
