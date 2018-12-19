package br.com.jins.projetos.organizzeclone.ui.despesas;

import br.com.jins.projetos.organizzeclone.model.Movimentacao;

public interface DespesasContract {

    interface View{
        boolean isValidadeDataForm();
        void showMainActivity(boolean isSuccess);
        void showMessenger(String messenger);
        void showProgress(final boolean show);
    }

    interface Presenter{
        void addExpense(Movimentacao movimentacao);
        void recoverExpenseTotal();

    }

}
