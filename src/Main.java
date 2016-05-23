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
                maquinaAcessada.mostrarRota();

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

                        System.out.println("Qual classe gostaria de viajar? Responda, ou A, ou B, ou C:");
                        classeVoo = leitor.next();
                        classeVoo = classeVoo.toLowerCase();

                        if((classeVoo.equals("a") || classeVoo.equals("b") || classeVoo.equals("c")))
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
                    else if(quantia == bilheteComprado.valorBilhete) {

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
                    System.out.println("\nBilhetes esgotados para esse horario! Favor reiniciar o processo\n");
                }

            }

            System.out.println("--------------");
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
        System.out.println(bilheteComprado);
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
        System.out.println(bilheteComprado);
        maquinaAcessada.retirarBilheteVendido(origem, bilheteComprado);
    }

    public static void main(String[] args) {

        List<IMaquina> maquinas = new ArrayList<>();
        IMaquina maq_1, maq_2, maq_3, maq_4, maq5;


        Map<String, List<Horario>> lista = new HashMap<>();
        lista.put("BRA", criarHorariosBRAMaq1());
        lista.put("BHZ", criarHorariosBHZMaq1());
        maq_1 = new MaquinaAviao(1, lista, 250.0, 170.0, 100.0, 15, 35, 80, "TAM");

        lista = new HashMap<>();
        lista.put("BHZ", criarHorariosBHZMaq2());
        lista.put("SPA", criarHorariosSPAMaq2());
        maq_2 = new MaquinaOnibus(1, lista, 100.0, 50, "Gontijo");

        lista = new HashMap<>();
        lista.put("SPA", criarHorariosSPAMaq3());
        lista.put("RJ", criarHorariosRJMaq3());
        maq_3 = new MaquinaOnibus(1, lista, 100.0, 50, "Sertaneja");

        maquinas.add(maq_1);
        maquinas.add(maq_2);
        maquinas.add(maq_3);

        menu(maquinas);
    }

    private static List<Horario> criarHorariosRJMaq3() {

        List<Horario> horarios = new LinkedList<>();

        adicionarNovoHorario(horarios, new Tempo(5, 30));
        adicionarNovoHorario(horarios, new Tempo(6, 0));
        adicionarNovoHorario(horarios, new Tempo(7, 0));
        adicionarNovoHorario(horarios, new Tempo(8, 30));
        adicionarNovoHorario(horarios, new Tempo(12, 15));
        adicionarNovoHorario(horarios, new Tempo(14, 0));
        adicionarNovoHorario(horarios, new Tempo(17, 0));
        adicionarNovoHorario(horarios, new Tempo(18, 0));
        adicionarNovoHorario(horarios, new Tempo(19, 30));
        adicionarNovoHorario(horarios, new Tempo(22, 00));

        return horarios;
    }

    private static List<Horario> criarHorariosSPAMaq3() {

        List<Horario> horarios = new LinkedList<>();

        adicionarNovoHorario(horarios, new Tempo(6, 0));
        adicionarNovoHorario(horarios, new Tempo(7, 0));
        adicionarNovoHorario(horarios, new Tempo(8, 0));
        adicionarNovoHorario(horarios, new Tempo(9, 0));
        adicionarNovoHorario(horarios, new Tempo(10, 0));
        adicionarNovoHorario(horarios, new Tempo(12, 0));
        adicionarNovoHorario(horarios, new Tempo(13, 30));
        adicionarNovoHorario(horarios, new Tempo(14, 30));
        adicionarNovoHorario(horarios, new Tempo(15, 30));
        adicionarNovoHorario(horarios, new Tempo(16, 30));
        adicionarNovoHorario(horarios, new Tempo(18, 0));
        adicionarNovoHorario(horarios, new Tempo(19, 0));
        adicionarNovoHorario(horarios, new Tempo(21, 30));
        adicionarNovoHorario(horarios, new Tempo(22, 0));
        adicionarNovoHorario(horarios, new Tempo(22, 30));
        adicionarNovoHorario(horarios, new Tempo(23, 0));
        adicionarNovoHorario(horarios, new Tempo(23, 30));
        adicionarNovoHorario(horarios, new Tempo(23, 59));


        return horarios;
    }

    private static List<Horario> criarHorariosSPAMaq2() {

        List<Horario> horarios = new LinkedList<>();

        adicionarNovoHorario(horarios, new Tempo(5, 30));
        adicionarNovoHorario(horarios, new Tempo(6, 0));
        adicionarNovoHorario(horarios, new Tempo(7, 0));
        adicionarNovoHorario(horarios, new Tempo(8, 30));
        adicionarNovoHorario(horarios, new Tempo(12, 15));
        adicionarNovoHorario(horarios, new Tempo(14, 0));
        adicionarNovoHorario(horarios, new Tempo(17, 0));
        adicionarNovoHorario(horarios, new Tempo(18, 0));
        adicionarNovoHorario(horarios, new Tempo(19, 30));
        adicionarNovoHorario(horarios, new Tempo(22, 0));

        return horarios;
    }

    private static List<Horario> criarHorariosBHZMaq2() {

        List<Horario> horarios = new LinkedList<>();

        adicionarNovoHorario(horarios, new Tempo(6, 0));
        adicionarNovoHorario(horarios, new Tempo(7, 0));
        adicionarNovoHorario(horarios, new Tempo(10, 0));
        adicionarNovoHorario(horarios, new Tempo(15, 0));
        adicionarNovoHorario(horarios, new Tempo(17, 30));
        adicionarNovoHorario(horarios, new Tempo(21, 0));
        adicionarNovoHorario(horarios, new Tempo(22, 30));
        adicionarNovoHorario(horarios, new Tempo(23, 59));

        return horarios;

    }

    private static List<Horario> criarHorariosBRAMaq1() {

        List<Horario> horarios = new LinkedList<>();

        adicionarNovoHorario(horarios, new Tempo(7, 30));
        adicionarNovoHorario(horarios, new Tempo(10, 45));
        adicionarNovoHorario(horarios, new Tempo(13, 0));
        adicionarNovoHorario(horarios, new Tempo(14, 45));
        adicionarNovoHorario(horarios, new Tempo(21, 15));
        adicionarNovoHorario(horarios, new Tempo(22, 0));

        return horarios;
    }

    public static List<Horario> criarHorariosBHZMaq1() {

        List<Horario> horarios = new LinkedList<>();

        adicionarNovoHorario(horarios, new Tempo(7, 45));
        adicionarNovoHorario(horarios, new Tempo(10, 0));
        adicionarNovoHorario(horarios, new Tempo(15, 0));
        adicionarNovoHorario(horarios, new Tempo(17, 45));
        adicionarNovoHorario(horarios, new Tempo(21, 0));

        return horarios;
    }

    private static void adicionarNovoHorario(List<Horario> horarios, Tempo tempo) {

        Calendar calobj = Calendar.getInstance();

        Integer mes = calobj.get(Calendar.MONTH) + 1,
                dia = calobj.get(Calendar.DAY_OF_MONTH),
                ano = calobj.get(Calendar.YEAR);

        Data data = new Data(dia,mes,ano);
        Horario horario = new Horario(tempo, data, true);
        horarios.add(horario);
    }
}
