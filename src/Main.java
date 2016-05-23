import java.util.*;
import java.util.concurrent.ExecutionException;

public class Main {

    public static void menu(List<IMaquina> maquinas) {

        while(true) {

            Integer resposta = -2;
            Scanner leitor = new Scanner(System.in);
            String classeVoo = "";

            while(resposta < -1 || resposta > maquinas.size() - 1) {

                System.out.println("Selecione alguma das rotas e meio de transporte\n");

                for(int i = 0; i < maquinas.size(); i++) {
                    System.out.println("[" +  i + "] " + "\t" + maquinas.get(i));
                }

                System.out.println("OU DIGITE -1 PARA SAIR\n");

                System.out.print("Digite o numero da maquina: ");

                try {
                    resposta = Integer.parseInt(leitor.next());
                } catch (Exception e) {
                    resposta = -2; // faz a resposta ser menor que -1 e entrar novamente no loop
                }


            }

            if(resposta == -1) break;

            // se o numero digitado e invalido
            if(resposta < 0 || resposta > maquinas.size() - 1) {
                throw new IllegalArgumentException("Numero invalido. Por favor, reiniciei o processo!");
            }
            // se eh um numero valido
            else {

                IMaquina maquinaAcessada = maquinas.get(resposta);

                String[] horario;

                System.out.print("\nMaquina selecionada: ");
                System.out.println(maquinaAcessada.mostrarRota());

                while (true) {

                    System.out.println("");
                    maquinaAcessada.gerarMsgEscolherHorario(maquinaAcessada);
                    horario = leitor.next().split(":");

                    Integer hora, minutos;

                    try {

                        hora = Integer.parseInt(horario[0]);
                        minutos = Integer.parseInt(horario[1]);

                        // verifica se o horario esta na forma correta
                        if (horario.length != 2) {
                            System.out.println("\nO horario deve ser na seguinte forma: <hora>:<minutos>");
                        }
                        else { // se o horario esta no formato correto, vai para a proxima etapa
                            break;
                        }

                    } catch (Exception e) {
                        // propositalmente vazio
                    }

                }

                String[] origensPossiveisAux = maquinaAcessada.mostrarRota().split("-");
                List<String> origensPossiveis = new ArrayList<>(origensPossiveisAux.length);

                // limpa os espacos das origens
                for(String s : origensPossiveisAux) {
                    origensPossiveis.add(s.trim());
                }

                String origem = "";

                // enquanto a origem for invalida
                while(!origensPossiveis.contains(origem)) {

                    maquinaAcessada.gerarMsgEscolherOrigem();

                    origem = leitor.next();
                }

                Tempo t = new Tempo(Integer.parseInt(horario[0]), Integer.parseInt(horario[1]));

                Boolean existeBilheteDisponivel = false;

                // se a maquina eh do tipo de maquina de aviao
                if(maquinaAcessada instanceof MaquinaAviao) {

                    // Obtem a classe do voo
                    while(true) {

                        System.out.println("\nQual classe gostaria de viajar? Responda, ou A, ou B, ou C:");
                        classeVoo = leitor.next();
                        classeVoo = classeVoo.toUpperCase();

                        if((classeVoo.equals("A") || classeVoo.equals("B") || classeVoo.equals("C")))
                            break;

                    }

                    existeBilheteDisponivel = ((MaquinaAviao)(maquinaAcessada)).verificarBilheteDisponivel(origem, t,
                            classeVoo);
                }

                else {

                    existeBilheteDisponivel = maquinaAcessada.verificarBilheteDisponivel(origem, t);
                }

                if(existeBilheteDisponivel) {

                    ABilhete bilheteComprado = maquinaAcessada.obterBilhete(origem, t);

                    if(maquinaAcessada instanceof MaquinaAviao) {
                        bilheteComprado = ((MaquinaAviao)maquinaAcessada).obterBilhete(origem, t, classeVoo);
                    }

                    maquinaAcessada.gerarMsgValorBilhete(bilheteComprado.valorBilhete);


                    Double quantia;

                    while(true) {

                        System.out.print("Por favor insira uma quantia:");

                        try {
                            quantia = Double.parseDouble(leitor.next());
                            break;
                        }
                        catch (Exception e) {
                            // propositalmente vazio
                            System.out.println("Quantia invalida!");
                        }

                    }

                    if(quantia > bilheteComprado.valorBilhete) {

                        rotinaCompraRealizadaComTroco(maquinaAcessada, origem, bilheteComprado, quantia);

                    }
                    else if(quantia.toString().equals(bilheteComprado.valorBilhete.toString())) {

                        rotinaCompraRealizadaSemTroco(maquinaAcessada, origem, bilheteComprado);
                    }
                    else { // se a quantia inserida nao foi suficiente

                        while(quantia < bilheteComprado.valorBilhete) {

                            maquinaAcessada.gerarMsgValorInsuficiente(quantia, bilheteComprado.valorBilhete);

                            while(true) {

                                System.out.print("Por favor insira uma quantia:");

                                try {
                                    quantia += Double.parseDouble(leitor.next());
                                    break;
                                }
                                catch (Exception e) {
                                    // propositalmente vazio
                                    System.out.println("Quantia invalida!");
                                }

                            }

                            if(quantia > bilheteComprado.valorBilhete) {

                                rotinaCompraRealizadaComTroco(maquinaAcessada, origem, bilheteComprado, quantia);

                            }
                            else if(quantia == bilheteComprado.valorBilhete) {

                                rotinaCompraRealizadaSemTroco(maquinaAcessada, origem, bilheteComprado);
                            }
                        }
                    }

                } else { // Se nao ha o bilhete disponivel (quer dizer que todos foram vendindo para aquele horario)
                    System.out.println("\n---------------------------------------------------------------\n" +
                            "Bilhetes esgotados para esse horario! Favor reiniciar o processo" +
                            "\n---------------------------------------------------------------\n");
                }

            }
        }
    }

    /**
     * Finaliza a compra quando nao ha troco
     * @param maquinaAcessada
     * @param origem
     * @param bilheteComprado
     */
    private static void rotinaCompraRealizadaSemTroco(IMaquina maquinaAcessada, String origem, ABilhete bilheteComprado) {
        maquinaAcessada.registrarVenda(bilheteComprado.valorBilhete);
        maquinaAcessada.gerarMsgCompraRealizada();


        System.out.println("############################################");
        System.out.println(bilheteComprado);
        System.out.println("############################################\n\n");

        maquinaAcessada.retirarBilheteVendido(origem, bilheteComprado);
    }

    /**
     * Finaliza a compra com troco
     * @param maquinaAcessada
     * @param origem
     * @param bilheteComprado
     * @param quantia
     */
    private static void rotinaCompraRealizadaComTroco(IMaquina maquinaAcessada, String origem, ABilhete bilheteComprado,
                                                      Double quantia) {

        Double troco = maquinaAcessada.calcularTroco(quantia, bilheteComprado.valorBilhete);
        maquinaAcessada.registrarVenda(bilheteComprado.valorBilhete);
        maquinaAcessada.gerarMsgCompraRealizada();
        maquinaAcessada.gerarMsgTroco(troco);

        System.out.println("############################################");
        System.out.println(bilheteComprado);
        System.out.println("############################################\n\n");

        maquinaAcessada.retirarBilheteVendido(origem, bilheteComprado);
    }

    public static void main(String[] args) {

        List<IMaquina> maquinas = new ArrayList<>();
        IMaquina maq_1, maq_2, maq_3, maq_4, maq_5;


        Map<String, List<Horario>> lista = new HashMap<>();
        lista.put("BRA", CriadorHorariosPreDefinidos.criarHorariosBRAMaq1());
        lista.put("BHZ", CriadorHorariosPreDefinidos.criarHorariosBHZMaq1());
        maq_1 = new MaquinaAviao(1, lista, 250.0, 170.0, 100.0, 15, 35, 80, "TAM Transportes Aereos");

        lista = new HashMap<>();
        lista.put("BHZ", CriadorHorariosPreDefinidos.criarHorariosBHZMaq2());
        lista.put("SPA", CriadorHorariosPreDefinidos.criarHorariosSPAMaq2());
        maq_2 = new MaquinaOnibus(2, lista, 100.0, 70, "Viacao Gontijo");

        lista = new HashMap<>();
        lista.put("SPA", CriadorHorariosPreDefinidos.criarHorariosSPAMaq3());
        lista.put("RJ", CriadorHorariosPreDefinidos.criarHorariosRJMaq3());
        maq_3 = new MaquinaOnibus(3, lista, 112.0, 70, "Viacao Sertaneja");

        lista = new HashMap<>();
        lista.put("BA", CriadorHorariosPreDefinidos.criarHorariosRand1());
        lista.put("AM", CriadorHorariosPreDefinidos.criarHorariosRand3());
        maq_4 = new MaquinaTrem(4, lista, 190.0, 120, "Trem-BaAm");

        lista = new HashMap<>();
        lista.put("AC", CriadorHorariosPreDefinidos.criarHorariosRand2());
        lista.put("RS", CriadorHorariosPreDefinidos.criarHorariosRand4());
        maq_5 = new MaquinaTrem(5, lista, 1200.0, 70, "Trem-AcRs");

        maquinas.add(maq_1);
        maquinas.add(maq_2);
        maquinas.add(maq_3);
        maquinas.add(maq_4);
        maquinas.add(maq_5);

        menu(maquinas);
    }


}
