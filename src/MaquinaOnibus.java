import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by lucas on 5/15/16.
 */
public class MaquinaOnibus extends  AMaquina{

    private static Integer serieBilhete = 1;

    public MaquinaOnibus(Integer numeroDeSerie, Map<String, List<Horario>> destinoHorario, Double valorBilhete, Integer
            vagasPorHorario, String companhia) {

        this.saldo = 0.0;
        this.numeroDeSerie = numeroDeSerie;
        this.valorDeBilheteUnico = valorBilhete;

        //Percorre a lista de origem e horarios, e salva no objeto
        for(Map.Entry<String, List<Horario>> Entry: destinoHorario.entrySet()) {

            String destino = Entry.getKey();

            // se nao existe o destino ainda, cria uma nova lista pra ele
            if(!this.bilhetes.containsKey(destino)) {
                this.bilhetes.put(destino, new LinkedList<>()) ;
            }

            // Salva todos os horarios
            for(Horario h : Entry.getValue()) {

                // TODO obter data atual de substituir um H

                // cria o numero de vagasPorHorario para cada horario
                for(int i = 0; i < vagasPorHorario; i++) {

                    BilheteOnibus b = new BilheteOnibus(numeroDeSerie, serieBilhete++, destino, h, h, "Praca da Estacao",
                            companhia,
                            valorBilhete, vagasPorHorario, "A");


                    this.bilhetes.get(destino).add(b);
                }

            }

        }



    }

    @Override
    public String toString() {

        String[] destinos = this.bilhetes.keySet().toString().split(",");

        return "\n" +
                "Maquina para vender passagens rodoviarias na rota " + destinos[0].substring(1) + " - " +
                destinos[1].substring(0,destinos[1].length() - 1) +
                "\nValor: R$ " + String.format("%.2f", this.valorDeBilheteUnico) +
                "\nhorarios de partida:\n" + this.getHorariosDePartida() +
                "Datas: diariamente" +
                "\n";
    }
}
