import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Cliente {
    static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    protected String nome;
    protected int nif;
    protected String morada;
    protected int telemovel;
    protected String email;
    protected String data_nascimento;

    public Cliente(String nome, int nif, String morada,int telemovel,String email,String data_nascimento){
        setNome(nome);
        setNif(nif);
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

    public String getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }
}
