package br.com.jins.projetos.organizzeclone.ui.user;

import br.com.jins.projetos.organizzeclone.model.User;

public interface UserContract {
    interface View{
        void loadNewUserData();
        void showMessenger(String messenger);
        void showMain();
    }

    interface Presenter{
        void addUser(User newUser);
    }



}
