package br.com.jins.projetos.organizzeclone.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import br.com.jins.projetos.organizzeclone.R;
import br.com.jins.projetos.organizzeclone.model.User;
import br.com.jins.projetos.organizzeclone.ui.main.MainActivity;
import br.com.jins.projetos.organizzeclone.ui.user.UserActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity implements LoginContract.View{

    private LoginContract.Presenter presenter;

    @BindView(R.id.email_login_edit_text)
    TextInputEditText emailUserLogin;

    @BindView(R.id.password_login_edit_text)
    TextInputEditText passwordUserLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        presenter = new LoginPresenter(LoginActivity.this,this);

        //Verify that the user is already logged in
        presenter.checkLoggedInUser();
    }


    @OnClick(R.id.botaoEntrarId)
    public void loadUserData() {
        User userLogin = new User();
        userLogin.setEmailUser(emailUserLogin.getText().toString());
        userLogin.setPasswordUser(passwordUserLogin.getText().toString());
        presenter.logInUser(userLogin);
    }

    @Override
    public void showMessenger(String messenger) {
        Toast.makeText(this, messenger, Toast.LENGTH_SHORT).show();
    }

    public void showMain(){
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }
}
