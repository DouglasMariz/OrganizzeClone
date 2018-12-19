package br.com.jins.projetos.organizzeclone.ui.login;

import android.app.Activity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

import br.com.jins.projetos.organizzeclone.R;
import br.com.jins.projetos.organizzeclone.data.source.remote.conectionFirebase;
import br.com.jins.projetos.organizzeclone.model.User;

public class LoginPresenter implements LoginContract.Presenter{

    private Activity activity;
    private LoginContract.View view;
    private FirebaseAuth autetication;

    public LoginPresenter(Activity activity, LoginContract.View view) {
        this.activity = activity;
        this.view = view;

        autetication = conectionFirebase.getFirebaseAuth();

    }

    @Override
    public void logInUser(User userLogin) {

        if(isValidateUserData(userLogin)) {
            autetication.signInWithEmailAndPassword(
                    userLogin.getEmailUser(),
                    userLogin.getPasswordUser()
            ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        view.showMain();
                        view.showMessenger(activity.getString(R.string.User_login_Success));
                    } else {
                        String ErrorExceptionString = "";

                        try {
                            throw task.getException();
                        } catch (FirebaseAuthInvalidUserException e) {
                            ErrorExceptionString = activity.getString(R.string.User_Error_Exception_Email_IsNotValidate);
                        } catch (FirebaseAuthInvalidCredentialsException e) {
                            ErrorExceptionString = activity.getString(R.string.User_Error_Exception_Passoword_IsNotValidate);
                        } catch (Exception e) {
                            ErrorExceptionString = activity.getString(R.string.User_login_Error);
                            e.printStackTrace();
                        }

                        view.showMessenger("Erro: " + ErrorExceptionString);

                    }
                }
            });
        }
    }

    private boolean isValidateUserData(User userLogin) {
        //Validate User Data
        if(!userLogin.getEmailUser().isEmpty()){
            if(!userLogin.getPasswordUser().isEmpty()){
                return true;
            }else{
                view.showMessenger(activity.getString(R.string.User_add_Invalid_Password));
                return false;
            }
        }else{
            view.showMessenger(activity.getString(R.string.User_add_Invalid_Email));
            return false;
        }
    }


    @Override
    public void checkLoggedInUser() {
        if(autetication.getCurrentUser()!=null){
            view.showMain();
        }
    }

}

