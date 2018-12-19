package br.com.jins.projetos.organizzeclone.ui.receitas;

public class ReceitasPresenter implements ReceitasContract.Presenter {
    private ReceitasContract.View view;

    public ReceitasPresenter(ReceitasContract.View view) {
        this.view = view;
    }
}
