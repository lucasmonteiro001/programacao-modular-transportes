/**
 * Created by lucas on 5/15/16.
 */
public class BilheteAviao extends ABilhete {

    private Integer numeroVoo;
    private String classeVoo;
    private Integer numPoltrona;
    private Tempo horarioLimiteCheckin;

    public BilheteAviao(Integer numeroSerieMaquina, Integer numeroSerieBilhete, String destino,
                        Horario emissao, Horario embarque, String localEmbarque, String companhia, Double
                               valorBilhete, Integer numeroVoo, String
                                classeVoo, Integer numPoltrona, Tempo horarioLimiteCheckin) {

        super(numeroSerieMaquina, numeroSerieBilhete, "aviao", destino, emissao, embarque, localEmbarque,
                companhia, valorBilhete);


        this.numeroVoo = numeroVoo;
        this.classeVoo = classeVoo;
        this.numPoltrona = numPoltrona;
        this.horarioLimiteCheckin = horarioLimiteCheckin;

    }

    public String getClasseVoo() {
        return classeVoo;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n\tNúmero do voo\t\t" + numeroVoo +
                "\n\tNumero da poltrona\t" + numPoltrona  +
                "\n\tClasse\t\t\t\t'" + classeVoo + '\''+
                "\n\tLimite checkin\t\t" + horarioLimiteCheckin;
    }

}
