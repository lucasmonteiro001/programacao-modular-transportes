/**
 * Created by lucas on 5/15/16.
 */
public interface IMaquina {

    /**
     * Adiciona o valor da venda no caixa da maquina
     * @param valor
     */
    public void registrarVenda(Double valor);

    /**
     * Verifica se ha bilhetes disponiveis para uma viagem, dados a origem e o horario
     * @param origem
     * @param tempo
     * @return
     */
    public Boolean verificarBilheteDisponivel(String origem, Tempo tempo);

    /**
     * Retorna um bilhete, dados sua origem e horario
     * @param origem
     * @param tempo
     * @return
     */
    public ABilhete obterBilhete(String origem, Tempo tempo);

    /**
     * Gera uma mensagem de valor insuficiente
     * @param quantia
     * @param valorBilhete
     */
    public void gerarMsgValorInsuficiente(Double quantia, Double valorBilhete);

    /**
     * Gera uma mensagem informando o troco do cliente
     * @param troco
     */
    public void gerarMsgTroco(Double troco);

    /**
     * Gera uma mensagem de compra realizada com sucesso
     */
    public void gerarMsgCompraRealizada();

    /**
     * Gera uma mensagem perguntando a origem da viagem
     */
    public void gerarMsgEscolherOrigem();

    /**
     * Gera uma mensagem perguntando o horario que se quer a passagem
     * @param maquinaAcessada
     */
    public void gerarMsgEscolherHorario(IMaquina maquinaAcessada);

    /**
     * Gera uma mensagem informando o valor do bilhete
     * @param bilhete
     */
    public void gerarMsgValorBilhete(ABilhete bilhete);

    /**
     * Calcula o troco do cliente, dada uma quantia recebida e o valor do bilhete
     * @param quantia
     * @param valorBilhete
     * @return
     */
    public Double calcularTroco(Double quantia, Double valorBilhete);

    /**
     * Obtem todos os horarios de partida
     * @return
     */
    public String getHorariosDePartida();

    /**
     * Mostra qual a rota que a maquina esta vendendo. Ex: BHZ - RJ
     */
    public String mostrarRota();

    /**
     * Dado que um bilhete foi vendido, retira-o da lista de bilhetes disponiveis da maquina
     * @param origem
     * @param bilheteComprado
     */
    void retirarBilheteVendido(String origem, ABilhete bilheteComprado);
}
