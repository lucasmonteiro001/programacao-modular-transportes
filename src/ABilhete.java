/**
 * Created by lucas on 5/15/16.
 */
public class ABilhete {

    protected Integer numeroSerieMaquina;
    protected Integer numeroSerieBilhete;
    protected String tipoDeTransporte;
    protected String destino;
    protected Horario emissao;
    protected Horario embarque;
    protected String localEmbarque;
    protected String companhia;
    protected Double valorBilhete;

    public ABilhete(Integer numeroSerieMaquina, Integer numeroSerieBilhete, String tipoDeTransporte, String destino,
                       Horario emissao, Horario embarque, String localEmbarque, String companhia, Double
                               valorBilhete) {


        this.numeroSerieMaquina = numeroSerieMaquina;
        this.numeroSerieBilhete = numeroSerieBilhete;
        this.tipoDeTransporte = tipoDeTransporte;
        this.destino = destino;
        this.emissao = emissao;
        this.embarque = embarque;
        this.localEmbarque = localEmbarque;
        this.companhia = companhia;
        this.valorBilhete = valorBilhete;

    }

    @Override
    public String toString() {
        return "Informacoes do bilhete:" +
                "\n\tNúmero máquina\t\t" + numeroSerieMaquina +
                "\n\tNúmero bilhete\t\t" + numeroSerieBilhete +
                "\n\tMeio de transporte\t'" + tipoDeTransporte + '\'' +
                "\n\tEmissão\t\t\t\t" + emissao +
                "\n\tEmbarque\t\t\t" + embarque +
                "\n\tDestino\t\t\t\t'" + destino + '\'' +
                "\n\tLocal de embarque\t'" + localEmbarque + '\'' +
                "\n\tCompanhia\t\t\t'" + companhia + '\'' +
                "\n\tValor do bilhete \tR$ " + valorBilhete;
    }
}
