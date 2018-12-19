package br.com.jins.projetos.organizzeclone.ui.despesas;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import br.com.jins.projetos.organizzeclone.data.source.remote.conectionFirebase;
import br.com.jins.projetos.organizzeclone.model.Movimentacao;
import br.com.jins.projetos.organizzeclone.model.User;
import br.com.jins.projetos.organizzeclone.utils.Base64Custom;
import br.com.jins.projetos.organizzeclone.utils.Constants;
import br.com.jins.projetos.organizzeclone.utils.DateCustom;

public class DespesasPresenter implements DespesasContract.Presenter {

    private DespesasContract.View view;
    private FirebaseAuth authFirebase;
    private DatabaseReference referenceFirebase;
    private Double despesaTotal;
    private Double despesaNova;
    private Double despesaAtualizada;

    private String idUser;
    private DatabaseReference userReference;

    public DespesasPresenter(Activity activity, DespesasContract.View view) {
        this.view = view;

        authFirebase = conectionFirebase.getFirebaseAuth();
        referenceFirebase = conectionFirebase.getFirebase();

        idUser = Base64Custom.codificarBase64(authFirebase.getCurrentUser().getEmail());
        userReference = referenceFirebase.child(Constants.FIREBASE_DATABASE_CHIELD_USER).child(idUser);

    }


    @Override
    public void addExpense(Movimentacao movimentacao) {

        String strMesAno = DateCustom.mesAnoDataEscolhida(movimentacao.getData());

        despesaNova = movimentacao.getValor();
        despesaAtualizada = despesaTotal + despesaNova;

        referenceFirebase.child(Constants.FIREBASE_DATABASE_CHIELD_MOVEMENT)
                .child(idUser)
                .child(strMesAno)
                .push()
                .setValue(movimentacao);


        userReference.child(Constants.FIREBASE_DATABASE_CHIELD_USER_COLUNM_DESPESA_TOTAL).setValue(despesaAtualizada);

        view.showProgress(false);
        view.showMainActivity(true);


    }

    @Override
    public void recoverExpenseTotal(){

        userReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                despesaTotal = user.getDespesaTotal();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }







}
