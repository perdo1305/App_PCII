import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Classe que disponibiliza metodos para guardar e obter informacao a partir de ficheiros.
 * @version 1.0
 * @author luis.correia
 */
public class Ficheiro {

    /**
     * Permite ler um objeto de um ficheiro
     *
     * @param nomeFicheiro nome do ficheiro que contém a informação.
     * @return objeto lido do ficheiro.
     */
    public static Object lerObjetoFicheiro(String nomeFicheiro) {
        Object obj;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(nomeFicheiro));
            obj = in.readObject();
            in.close();
            return obj;
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    /**
     * Permite ler uma lista de objetos de um ficheiro
     *
     * @param nomeFicheiro nome do ficheiro que contém a informação.
     * @return lista de objetos lido do ficheiro.
     */
    public static ArrayList lerArrayListFicheiro(String nomeFicheiro) {
        ArrayList<Object> objs;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(nomeFicheiro));
            objs = (ArrayList<Object>) in.readObject();
            in.close();
            return objs;
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    /**
     * Permite gravar um objeto num ficheiro
     *
     * @param objetoAGuardar objeto a guardar no ficheiro.
     * @param nomeFicheiro nome do ficheiro que irá guardar a informação.
     */
    public static void gravarObjetoFicheiro(Object objetoAGuardar, String nomeFicheiro) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomeFicheiro));
            out.writeObject(objetoAGuardar);
            out.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     * Permite gravar uma lista de objetos num ficheiro 
     *
     * @param listaObjetosAGuardar lista de objetos a guardar no ficheiro.
     * @param nomeFicheiro nome do ficheiro que irá guardar a informação.
     */
    public static void gravarArrayListFicheiro(ArrayList listaObjetosAGuardar, String nomeFicheiro) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomeFicheiro));
            out.writeObject(listaObjetosAGuardar);
            out.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
