import java.io.Serializable;
import java.time.LocalDateTime;

public class Pagamento implements Serializable {
    protected SessaoCarregamento sessao;
    protected String metodoPagamento;
    protected LocalDateTime DataHoraTransacao;
    protected boolean pago;
    protected Cliente cliente;

    public Pagamento(SessaoCarregamento sessao, String metodoPagamento, LocalDateTime DataHoraTransacao, boolean pago) {
        this.sessao = sessao;
        this.metodoPagamento = metodoPagamento;
        this.DataHoraTransacao = DataHoraTransacao;
        this.pago = pago;
        this.cliente = sessao.getCliente();
    }

    public SessaoCarregamento getSessao() {
        return sessao;
    }

    @Override
    public String toString() {
        // numero posto, sessao e metodoPagamento
        return "Numero do posto: " + sessao.getPostoCarregamento().getCodigo_posto() + "\n" +
                "Dados da sessao: " + sessao +
                "Metodo de pagamento: " + metodoPagamento + "\n";
    }
}