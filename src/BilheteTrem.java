/**
 * Created by lucas on 5/15/16.
 */
public class BilheteTrem extends ABilhete {

    private Integer numeroTrem;
    private String tipoPoltrona; // A, B, ou C

    public BilheteTrem(Integer numeroSerieMaquina, Integer numeroSerieBilhete, String destino,
                       Horario emissao, Horario embarque, String localEmbarque, String companhia, Double
                               valorBilhete, Integer numeroTrem, String tipoPoltrona) {

        super(numeroSerieMaquina, numeroSerieBilhete, "Trem", destino, emissao, embarque, localEmbarque,
                companhia, valorBilhete);


        this.numeroTrem = numeroTrem;
        this.tipoPoltrona = tipoPoltrona;

    }

    @Override
    public String toString() {
        return super.toString() +
                "\n\tNúmero do trem\t\t" + numeroTrem +
                "\n\tTipo da poltrona\t'" + tipoPoltrona + '\'';
    }
}
