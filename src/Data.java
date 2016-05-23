/**
 * Created by lucas on 5/15/16.
 */
public class Data {
    private Integer dia;
    private Integer mes;
    private Integer ano;

    public Data(Integer dia, Integer mes, Integer ano) {

        if(dia < 1 || dia > 31) {
            throw new IllegalArgumentException("Dia dever estar entre 1 e 31");
        }
        else if(mes < 1 || mes > 12) {
            throw new IllegalArgumentException("Mes dever estar entre 1 e 12");
        }
        else if(ano < 2000 || ano > 2100) {
            throw new IllegalArgumentException("Ano dever estar entre 2000 e 2100");
        }

        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public Integer getDia() {

        return dia;
    }

    public Integer getMes() {

        return mes;
    }

    public Integer getAno() {

        return ano;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {

        return String.format("%02d", dia) + "/" + String.format("%02d", mes) + "/" + ano;
    }
}
