import java.util.*;

/**
 * Created by lucas on 5/14/16.
 */
public abstract  class  AMaquina implements IMaquina {

    protected Double saldo;
    protected Integer numeroDeSerie;
    protected HashMap<String, LinkedList<ABilhete>> bilhetes = new HashMap<>();
    protected Double valorDeBilheteUnico;


    @Override
    public void registrarVenda(Double valor) {
        this.saldo += valor;
    }

    @Override
    public Boolean verificarBilheteDisponivel(String origem, Tempo tempo) {

        // verifica se ha algum bilhete com essa origem
        if(bilhetes.get(origem).size() != 0) {

            List<ABilhete> bilhetesDisponiveis = bilhetes.get(origem);

            for(ABilhete bilhete : bilhetesDisponiveis) {

                // verifica se ha algum bilhete disponivel
                if(bilhete.embarque.getTempo().toString().equals(tempo.toString())) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public ABilhete obterBilhete(String origem, Tempo tempo) {

        // verifica se ha algum bilhete com essa origem
        if(bilhetes.get(origem).size() != 0) {

            List<ABilhete> bilhetesDisponiveis = bilhetes.get(origem);

            for(ABilhete bilhete : bilhetesDisponiveis) {

                // verifica se ha algum bilhete disponivel
                if(bilhete.embarque.getTempo().toString().equals(tempo.toString())) {
                    return bilhete;
                }
            }
        }

        return null;
    }


    @Override
    public void gerarMsgValorInsuficiente(Double quantia, Double valorBilhete) {

        System.out.println("O valor já inserido R$ (" + String.format("%.2f", quantia)  +") é insufiente para o preco" +
                " de R$ (" + String.format("%.2f", valorBilhete)
                + ")");
    }

    @Override
    public void gerarMsgCompraRealizada() {

        System.out.println("\n\nSua compra foi realizada com sucesso!\n");
    }

    @Override
    public void gerarMsgTroco(Double troco) {

        System.out.println("Seu troco eh de: R$ " + String.format("%.2f", troco) + "\n");

    }

    @Override
    public void gerarMsgEscolherOrigem() {

        System.out.print("Digite a origem, como mostrada acima: ");
    }

    @Override
    public void gerarMsgEscolherHorario(IMaquina maquinaAcessada) {

        System.out.println("Por favor, selecione um dos seguintes horarios: \n");
        System.out.print(maquinaAcessada.getHorariosDePartida());
        System.out.print("\nDigite o horario, como mostrado acima: ");
    }

    @Override
    public void gerarMsgValorBilhete(Double valorBilhete) {
        System.out.println("\nO valor do bilhete eh: R$ " + String.format("%.2f", valorBilhete) + "\n");
    }

    @Override
    public Double calcularTroco(Double quantia, Double valorBilhete) {
        Double troco =  quantia - valorBilhete;
        return troco;
    }

    @Override
    public String getHorariosDePartida() {

        String horariosDePartida = "";

        Set horarioJaMostrado = new HashSet<>();

        for(Map.Entry<String, LinkedList<ABilhete>> Entry : this.bilhetes.entrySet()) {

            horariosDePartida += "de " + Entry.getKey() + " - ";

            for(ABilhete b : Entry.getValue()) {

                if(!horarioJaMostrado.contains(b.embarque.getTempo()))
                    horariosDePartida += b.embarque.getTempo() + ", ";

                horarioJaMostrado.add(b.embarque.getTempo());
            }

            horariosDePartida = horariosDePartida.substring(0, horariosDePartida.length() - 2);

            horariosDePartida += "\n";
        }

        return horariosDePartida;
    }

    @Override
    public String mostrarRota() {

        String[] destinos = this.bilhetes.keySet().toString().split(",");

        //System.out.println(destinos[0].substring(1) + " - " + destinos[1].substring(0,destinos[1].length() - 1));

        return destinos[0].substring(1) + " - " + destinos[1].substring(0,destinos[1].length() - 1);
    }

    @Override
    public void retirarBilheteVendido(String origem, ABilhete bilheteComprado) {

        // verifica se ha algum bilhete com essa origem
        if(bilhetes.get(origem).size() != 0) {

            List<ABilhete> bilhetesDisponiveis = bilhetes.get(origem);

            bilhetesDisponiveis.remove(bilheteComprado);
        }
    }
}
