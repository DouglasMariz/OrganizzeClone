package br.com.jins.projetos.organizzeclone.ui.user;

import android.app.Activity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

import br.com.jins.projetos.organizzeclone.R;
import br.com.jins.projetos.organizzeclone.data.source.remote.conectionFirebase;
import br.com.jins.projetos.organizzeclone.model.User;
import br.com.jins.projetos.organizzeclone.utils.Base64Custom;

public class UserPresenter implements UserContract.Presenter {

    private Activity activity;
    private UserContract.View view;
    private FirebaseAuth autetication;
    private User newUser;

    public UserPresenter(Activity activity, UserContract.View view) {
        this.activity = activity;
        this.view = view;
        autetication = conectionFirebase.getFirebaseAuth();
    }

    @Override
    public void addUser(User user) {

        newUser = user;

        if(isValidateUserData(newUser)){
            autetication.createUserWithEmailAndPassword(
                    newUser.getEmailUser(),
                    newUser.getPasswordUser()
            ).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        //displaying the user registration confirmation message
                        view.showMessenger(activity.getString(R.string.User_add_Success));

                        //Capturing User Data Created in Firebase
                        FirebaseUser userFirebase = task.getResult().getUser();

                        //Saving the ID created in Firebase to store this information in the Firebase DadaBase along with the other user information
                        String userIdentification = Base64Custom.codificarBase64(newUser.getEmailUser());
                        newUser.setId(userIdentification);

                        newUser.saveUser();
                        view.showMain();

                    }else{

                        String ErrorExceptionString ="";

                        try{
                            throw task.getException();
                        }catch (FirebaseAuthWeakPasswordException e){
                            ErrorExceptionString = activity.getString(R.string.User_add_Error_Exception_Passoword_IsNotStrong);
                        } catch (FirebaseAuthInvalidCredentialsException e) {
                            ErrorExceptionString = activity.getString(R.string.User_Error_Exception_Email_IsNotValidate);
                        } catch (FirebaseAuthUserCollisionException e) {
                            ErrorExceptionString = activity.getString(R.string.User_add_Error_Exception_Email_IsAlreadyUsed);
                        } catch (Exception e) {
                            ErrorExceptionString = activity.getString(R.string.User_add_Error_Exception_AddUser);
                            e.printStackTrace();
                        }

                        view.showMessenger("Erro: " + ErrorExceptionString);
                    }
                }
            });
        }


    }

    private boolean isValidateUserData(User newUser) {
        //Validate User Data
        if(!newUser.getNomeUser().isEmpty()){
            if(!newUser.getEmailUser().isEmpty()){
                if(!newUser.getPasswordUser().isEmpty()){
                    return true;
                }else{
                    view.showMessenger(activity.getString(R.string.User_add_Invalid_Password));
                    return false;
                }
            }else{
                view.showMessenger(activity.getString(R.string.User_add_Invalid_Email));
                return false;
            }
        }else{
            view.showMessenger(activity.getString(R.string.User_add_Invalid_Nome));
            return false;
        }

    }


}
