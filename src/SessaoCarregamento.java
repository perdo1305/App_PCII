public class SessaoCarregamento {
    protected int matricula;
    protected double custo_kwh;
    protected int minutos;
    protected int segundos;
    protected int horas;
    protected String estado_pagamento;
    protected double custo_sessao;

    public SessaoCarregamento(int matricula, double custo_kwh, String estado_pagamento,
            double custo_sessao, int minutos, int segundos, int horas) {
        setMatricula(matricula);
        setCusto_kwh(custo_kwh);
        setMinutos(minutos);
        setSegundos(segundos);
        setHoras(horas);
        setEstado_pagamento(estado_pagamento);
        setCusto_sessao(custo_sessao);
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public double getCusto_kwh() {
        return custo_kwh;
    }

    public void setCusto_kwh(double custo_kwh) {
        this.custo_kwh = custo_kwh;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public String getEstado_pagamento() {
        return estado_pagamento;
    }

    public void setEstado_pagamento(String estado_pagamento) {
        this.estado_pagamento = estado_pagamento;
    }

    public double getCusto_sessao() {
        return custo_sessao;
    }

    public void setCusto_sessao(double custo_sessao) {
        this.custo_sessao = custo_sessao;
    }

}
