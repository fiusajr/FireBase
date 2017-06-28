package Helper;

import android.util.Base64;

/**
 * Created by Assis on 11/06/2017.
 */

public class Base64Custom {


    public static String codificarBase64(String texto){
        return Base64.encodeToString(texto.getBytes(), Base64.DEFAULT).replaceAll("(\\n|\\r)", "");

    }

    public static String decodificarBase64(String textocodificado){
        return new String (Base64.decode(textocodificado, Base64.DEFAULT));

    }
}
