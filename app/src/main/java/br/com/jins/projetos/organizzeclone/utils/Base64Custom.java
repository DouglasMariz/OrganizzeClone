package br.com.jins.projetos.organizzeclone.utils;

import android.util.Base64;

public class Base64Custom {

    public static String codificarBase64(String textoNormal ){
        return Base64.encodeToString(textoNormal.getBytes(),Base64.DEFAULT).replaceAll("(\\n|\\r)","");
    }

    public static String decodificarBase64(String textoCodificado ){
        return new String(Base64.decode(textoCodificado,Base64.DEFAULT));
    }

}
