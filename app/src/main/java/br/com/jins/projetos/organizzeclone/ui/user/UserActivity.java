package br.com.jins.projetos.organizzeclone.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;
import br.com.jins.projetos.organizzeclone.R;
import br.com.jins.projetos.organizzeclone.model.User;
import br.com.jins.projetos.organizzeclone.ui.main.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserActivity extends AppCompatActivity implements UserContract.View{

    private UserContract.Presenter presenter;

    @BindView(R.id.nome_edit_text)
    TextInputEditText nomeUser;

    @BindView(R.id.email_edit_text)
    TextInputEditText emailUser;

    @BindView(R.id.password_edit_text)
    TextInputEditText passwordUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        ButterKnife.bind(this);
        presenter = new UserPresenter(UserActivity.this,this);

    }

    @OnClick(R.id.botaoCadastrarId)
    public void loadNewUserData(){

        User newUser = new User();
        newUser.setNomeUser(nomeUser.getText().toString());
        newUser.setEmailUser(emailUser.getText().toString());
        newUser.setPasswordUser(passwordUser.getText().toString());

        presenter.addUser(newUser);

    }

    @Override
    public void showMessenger(String messenger) {
        Toast.makeText(this, messenger, Toast.LENGTH_SHORT).show();
    }

    public void showMain(){
        startActivity(new Intent(UserActivity.this, MainActivity.class));
        finish();
    }


}
