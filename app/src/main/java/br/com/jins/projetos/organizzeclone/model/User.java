package br.com.jins.projetos.organizzeclone.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import br.com.jins.projetos.organizzeclone.data.source.remote.conectionFirebase;
import br.com.jins.projetos.organizzeclone.utils.Constants;

public class User {

    private String id;
    private String nomeUser;
    private String emailUser;
    private String passwordUser;
    private Double despesaTotal = 0.00;
    private Double receitaTotal = 0.00;

    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomeUser() {
        return nomeUser;
    }

    public void setNomeUser(String nomeUser) {
        this.nomeUser = nomeUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    @Exclude
    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    public Double getDespesaTotal() {
        return despesaTotal;
    }

    public void setDespesaTotal(Double despesaTotal) {
        this.despesaTotal = despesaTotal;
    }

    public Double getReceitaTotal() {
        return receitaTotal;
    }

    public void setReceitaTotal(Double receitaTotal) {
        this.receitaTotal = receitaTotal;
    }

    public void saveUser(){
        DatabaseReference referenceFirebase = conectionFirebase.getFirebase();
        referenceFirebase.child(Constants.FIREBASE_DATABASE_CHIELD_USER).child(getId()).setValue(this);
    }

}
