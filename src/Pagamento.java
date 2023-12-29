import java.io.Serializable;
import java.time.LocalDateTime;

public class Pagamento implements Serializable {
    protected SessaoCarregamento sessao;
    protected String metodoPagamento;
    protected LocalDateTime DataHoraTransacao;
    protected boolean pago;
    protected Cliente cliente;

    /**
     * Construtor da classe Pagamento
     * @param sessao objeto da classe SessaoCarregamento
     * @param metodoPagamento metodo de pagamento (string) - MB, Debito direto, Transferencia bancaria
     * @param DataHoraTransacao data e hora da transacao (LocalDateTime)
     * @param pago booleano que indica se o pagamento foi efetuado ou nao
     */

    public Pagamento(SessaoCarregamento sessao, String metodoPagamento, LocalDateTime DataHoraTransacao, boolean pago) {
        this.sessao = sessao;
        this.metodoPagamento = metodoPagamento;
        this.DataHoraTransacao = DataHoraTransacao;
        this.pago = pago;
        this.cliente = sessao.getCliente();
    }

    /**
     * Getter sessaoCarregamento associada ao pagamento
     * @return objeto da classe SessaoCarregamento
     */
    public SessaoCarregamento getSessao() {
        return sessao;
    }

    /**
     * ToString da classe Pagamento
     * @return ‘string’ com a informação do pagamento
     */
    @Override
    public String toString() {
        // numero posto, sessao e metodoPagamento
        return "Numero do posto: " + sessao.getPostoCarregamento().getCodigo_posto() + "\n" +
                "Dados da sessao: " + sessao +
                "Metodo de pagamento: " + metodoPagamento + "\n";
    }
}