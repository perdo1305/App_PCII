import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  Classe Cliente
 * @version 1.0
 * @Author Pedro Ferreira nº 2222035 | Bernardo Santos nº 2222033
 */

public class Cliente implements Serializable {
    static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    protected String nome;
    protected int nif;
    protected String morada;
    protected int telemovel;
    protected String email;
    protected Date data_nascimento;

    /**
     * Construtor da classe Cliente
     * @param nome nome do cliente (‘string’)
     * @param nif nif do cliente (int)
     * @param morada morada do cliente (‘string’)
     * @param telemovel telemovel do cliente (int)
     * @param email email do cliente (‘string’)
     * @param data_nascimento data de nascimento do cliente (Date)
     */

    public Cliente(String nome, int nif, String morada, int telemovel, String email, Date data_nascimento) {
        setNome(nome);
        setNif(nif);
        setMorada(morada);
        setTelemovel(telemovel);
        setEmail(email);
        setData_nascimento(data_nascimento);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public int getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(int telemovel) {
        this.telemovel = telemovel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    /**
     * Metodo toString da classe Cliente
     * @return ‘string’ com os atributos da classe Cliente
     */
    @Override
    public String toString() {
        String str = "";
        str += "Cliente: " + getNome() + "\n"
                + "NIF: " + getNif() + "\n"
                + "Morada: " + getMorada() + "\n"
                + "Telemovel: " + getTelemovel() + "\n"
                + "Email: " + getEmail() + "\n"
                + "Data de nascimento: " + dateFormat.format(getData_nascimento()) + "\n";
        return str;
    }
}
