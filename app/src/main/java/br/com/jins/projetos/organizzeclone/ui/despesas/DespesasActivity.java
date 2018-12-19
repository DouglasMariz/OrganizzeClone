package br.com.jins.projetos.organizzeclone.ui.despesas;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;
import br.com.jins.projetos.organizzeclone.R;
import br.com.jins.projetos.organizzeclone.model.Movimentacao;
import br.com.jins.projetos.organizzeclone.ui.main.MainActivity;
import br.com.jins.projetos.organizzeclone.utils.Constants;
import br.com.jins.projetos.organizzeclone.utils.DateCustom;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DespesasActivity extends AppCompatActivity implements DespesasContract.View{

    DespesasContract.Presenter presenter;

    Calendar dateTime = Calendar.getInstance();

    @BindView(R.id.fabAddDespesaId)
    FloatingActionButton fabAddDespesa;

    @BindView(R.id.editValorDespesasId)
    EditText editValorDespesas;

    @BindView(R.id.editDataDespesaId)
    TextInputEditText editDataDespesa;

    @BindView(R.id.btnDateDespesaSelectId)
    Button btnDateDespesaSelect;

    @BindView(R.id.editCategoriaDespesaId)
    TextInputEditText editCategoriaDespesa;

    @BindView(R.id.editDescricaoDespesaId)
    TextInputEditText editDescricaoDespesa;

    @BindView(R.id.progressSaveDespesa)
    ProgressBar mProgressView;

    private Movimentacao movimentacao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesas);

        ButterKnife.bind(this);

        presenter = new DespesasPresenter(DespesasActivity.this,this);

        presenter.recoverExpenseTotal();

        editDataDespesa.setText(DateCustom.dataAtual());

        btnDateDespesaSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });


    }

    private void showDatePickerDialog(){
        new DatePickerDialog(this, d, dateTime.get(Calendar.YEAR),dateTime.get(Calendar.MONTH),dateTime.get(Calendar.DAY_OF_MONTH)).show();
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            editDataDespesa.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);

        }
    };

    @OnClick(R.id.fabAddDespesaId)
    public void salvarDespesa(){

        if(isValidadeDataForm()){
            movimentacao = new Movimentacao();

            movimentacao.setValor(Double.parseDouble(editValorDespesas.getText().toString()));
            movimentacao.setCategoria(editCategoriaDespesa.getText().toString());
            movimentacao.setDescricao(editDescricaoDespesa.getText().toString());
            movimentacao.setData(editDataDespesa.getText().toString());
            movimentacao.setTipo("d");

            presenter.addExpense(movimentacao);
        }
    }


    @Override
    public boolean isValidadeDataForm() {

        showProgress(true);
        String txValor = editValorDespesas.getText().toString();
        String txDataEscolhida = editDataDespesa.getText().toString();
        String txCategoria = editCategoriaDespesa.getText().toString();
        String txDescricao = editDescricaoDespesa.getText().toString();

        if(txValor == null || txValor.isEmpty()){
            editValorDespesas.setError("O valor deve ser preenchido!");
            editValorDespesas.requestFocus();
            showProgress(false);
            return false;
        }else{
            if(!txDataEscolhida.isEmpty()) {
                if(!txCategoria.isEmpty()) {
                    if(!txDescricao.isEmpty()) {
                        return true;
                    }else{
                        editDescricaoDespesa.setError("A descrição deve ser preenchida!");
                        showProgress(false);
                        editDescricaoDespesa.requestFocus();
                        return false;
                    }

                }else{
                    editCategoriaDespesa.setError("A categoria deve ser preenchida!");
                    showProgress(false);
                    editCategoriaDespesa.requestFocus();
                    return false;
                }
            }else{
                editDataDespesa.setError("A data deve ser preenchida!");
                showProgress(false);
                editDataDespesa.requestFocus();
                return false;
            }

        }

    }

    @Override
    public void showMainActivity(boolean isSuccess) {
        Intent intent = new Intent(DespesasActivity.this, MainActivity.class);
        if(isSuccess) {
            intent.putExtra(Constants.EXTRA_MESSAGE_LAUNCH, Constants.CODE_RESPONSE_ADD_SUCCESS);
        }else{
            intent.putExtra(Constants.EXTRA_MESSAGE_LAUNCH, Constants.CODE_RESPONSE_ADD_FAILURE);
        }
        startActivity(intent);
        finish();
    }

    @Override
    public void showMessenger(String messenger) {
        Toast.makeText(this, messenger, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress(final boolean show){
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        fabAddDespesa.setVisibility(show ? View.GONE : View.VISIBLE);
    }

}
