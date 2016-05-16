/**
 * Created by lucas on 5/15/16.
 */
public class Tempo {

    private Integer hora;
    private Integer minutos;

    public Tempo(Integer hora, Integer minutos) {

        if(hora < 0 || hora > 23) {
            throw new IllegalArgumentException("Hora deve estar entre 0 e 23");
        }

        if(minutos < 0 || minutos > 59) {
            throw new IllegalArgumentException("Minutos deve estar entre 0 e 59");
        }

        this.hora = hora;
        this.minutos = minutos;
    }

    public Integer getHora() {
        return hora;
    }

    public Integer getMinutos() {

        return minutos;
    }

    @Override
    public String toString() {

        return hora + ":" + String.format("%02d", minutos);
    }
}
