package br.com.jins.projetos.organizzeclone.ui.receitas;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;
import br.com.jins.projetos.organizzeclone.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ReceitasActivity extends AppCompatActivity implements ReceitasContract.View{

    private ReceitasContract.Presenter presenter;

    Calendar dateTime = Calendar.getInstance();

    @BindView(R.id.editDataReceitaId)
    TextInputEditText editDataReceita;

    @BindView(R.id.btnDateSelectId)
    Button btnDateSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receitas);

        ButterKnife.bind(this);

        presenter = new ReceitasPresenter(this);

        btnDateSelect.setOnClickListener(new View.OnClickListener() {
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
            //dateTime.set(Calendar.YEAR, year);
            //dateTime.set(Calendar.MONTH, monthOfYear);
            //dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            //editDataReceita.setText(formatDateTime.format(dateTime.getTime()));
            editDataReceita.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);

        }
    };





}
