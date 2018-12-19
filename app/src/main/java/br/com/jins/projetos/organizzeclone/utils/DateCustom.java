package br.com.jins.projetos.organizzeclone.utils;

import java.text.SimpleDateFormat;

public class DateCustom {

    public static String dataAtual(){

        long data = System.currentTimeMillis();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        String dataString = simpleDateFormat.format(data);

        return dataString;

    }

    public static String mesAnoDataEscolhida(String dataEscolhida){

        String dataPartes[] = dataEscolhida.split("/");

        String strMes = dataPartes[1];
        String strAno = dataPartes[2];

        String strMesAno = strMes + strAno;

        return strMesAno;

    }


}
