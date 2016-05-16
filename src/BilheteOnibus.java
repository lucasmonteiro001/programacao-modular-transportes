/**
 * Created by lucas on 5/15/16.
 */
public class BilheteOnibus extends ABilhete {

    private Integer numeroOnibus;
    private String tipoPoltrona; // A, B, ou C

    public BilheteOnibus(Integer numeroSerieMaquina, Integer numeroSerieBilhete, String destino,
                         Horario emissao, Horario embarque, String localEmbarque, String companhia, Double
                               valorBilhete, Integer numeroOnibus, String tipoPoltrona) {

        super(numeroSerieMaquina, numeroSerieBilhete, "onibus", destino, emissao, embarque, localEmbarque,
                companhia, valorBilhete);


        this.numeroOnibus = numeroOnibus;
        this.tipoPoltrona = tipoPoltrona;

    }

    @Override
    public String toString() {
        return super.toString() +
                "\n\tNúmero do onibus\t" + numeroOnibus +
                "\n\tTipo da poltrona\t'" + tipoPoltrona + '\'';
    }


}
