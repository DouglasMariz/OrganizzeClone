package br.com.jins.projetos.organizzeclone.ui.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import br.com.jins.projetos.organizzeclone.R;
import br.com.jins.projetos.organizzeclone.ui.despesas.DespesasActivity;
import br.com.jins.projetos.organizzeclone.ui.login.LoginActivity;
import br.com.jins.projetos.organizzeclone.ui.receitas.ReceitasActivity;
import br.com.jins.projetos.organizzeclone.utils.Constants;
import br.com.jins.projetos.organizzeclone.utils.Utility;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainContract.Presenter presenter;

    @BindView(R.id.toolbar_mainId)
    Toolbar toolbarMain;

    @BindView(R.id.menuFab)
    FloatingActionMenu menuFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new MainPresenter(MainActivity.this, this);

        configureActionBar();

        checkMessengerIntent();


    }

    private void checkMessengerIntent() {
        Intent intent = getIntent();

        int responseLaunch = intent.getIntExtra(Constants.EXTRA_MESSAGE_LAUNCH,0);

        if(responseLaunch == Constants.CODE_RESPONSE_ADD_SUCCESS){
            Utility.showSnackBarTopScreen(getApplicationContext(),
                    findViewById(R.id.layoutMainId),
                    getString(R.string.LaunchSaveSucessText),
                    R.color.colorAccentGreen,
                    R.drawable.ic_check_circle_black_24dp);
        }
    }

    @OnClick(R.id.fabDespesaId)
    public void addDespesa(){
        startActivity(new Intent(MainActivity.this,DespesasActivity.class));
    }

    @OnClick(R.id.fabReceitaId)
    public void addReceita(){
        startActivity(new Intent(MainActivity.this,ReceitasActivity.class));
    }


    private void configureActionBar() {
        toolbarMain.setTitle(this.getString(R.string.app_name));
        toolbarMain.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbarMain);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.item_sairId:
                presenter.logoff();
                return true;
            case R.id.item_configuracoesId:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showMessenger(String messenger) {
        Toast.makeText(this, messenger, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLogin(){
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        finish();
    }
}
