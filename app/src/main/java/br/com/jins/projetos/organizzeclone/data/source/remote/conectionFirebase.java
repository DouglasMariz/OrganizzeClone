package br.com.jins.projetos.organizzeclone.data.source.remote;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public final class conectionFirebase {

    //Declarando a variavel DataBaseReference como Static para que o valor não mude caso a mesma seja instanciada.
    private static DatabaseReference referenciaFirebase;
    private static FirebaseAuth autenticacao;

    //Método para retornar a referência criada do Firebase
    public static DatabaseReference getFirebase(){

        //Verificando se a mesma já foi instanciada para não precisar instanciar novamente.
        if(referenciaFirebase == null) {
            referenciaFirebase = FirebaseDatabase.getInstance().getReference();
        }

        //Retornando a referência
        return referenciaFirebase;
    }

    public static FirebaseAuth getFirebaseAuth(){

        //Verificando se a mesma já foi instanciada para não precisar instanciar novamente.
        if(autenticacao == null) {
            autenticacao = FirebaseAuth.getInstance();
        }

        //Retornando a referência da autenticação
        return autenticacao;
    }

}
