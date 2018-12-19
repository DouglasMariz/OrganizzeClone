package br.com.jins.projetos.organizzeclone.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.androidadvance.topsnackbar.TSnackbar;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.content.ContextCompat;
import br.com.jins.projetos.organizzeclone.R;

public class Utility {
    public static void showSnackBar(Context context, View view, String text, int colorBackground) {
        Snackbar sb = Snackbar.make(view, text, Snackbar.LENGTH_SHORT);
        sb.getView().setBackgroundColor(ContextCompat.getColor(context, colorBackground));
        sb.show();
    }

    //Método showSnackBarTopScreen simples
    public static void showSnackBarTopScreen(Context context, View view, String text) {
        TSnackbar sb = TSnackbar.make(view, text, TSnackbar.LENGTH_SHORT);
        //Adicionar aqui como parametro adicional
        sb.getView().setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
        TextView textView = sb.getView().findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        sb.show();
    }

    //Método showSnackBarTopScreen passando a cor de background
    public static void showSnackBarTopScreen(Context context, View view, String text, int colorBackground) {

        TSnackbar sb = TSnackbar.make(view, text, TSnackbar.LENGTH_SHORT);
        sb.getView().setBackgroundColor(ContextCompat.getColor(context, colorBackground));
        TextView textView = sb.getView().findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        sb.show();

    }

    //Método showSnackBarTopScreen passando a cor de background e icon que será alinhado a esquerda
    public static void showSnackBarTopScreen(Context context, View view, String text, int colorBackground, int iconDrawable) {

        TSnackbar sb = TSnackbar.make(view, text, TSnackbar.LENGTH_SHORT);
        sb.setIconLeft(iconDrawable, 24);
        sb.setIconPadding(8);
        sb.getView().setBackgroundColor(ContextCompat.getColor(context, colorBackground));
        TextView textView = sb.getView().findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        sb.show();

    }


}
