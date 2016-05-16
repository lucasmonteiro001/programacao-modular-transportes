/**
 * Created by lucas on 5/15/16.
 */
public class Horario {

    private Tempo tempo;
    private Data data;
    private Boolean diariamente;

    public Horario(Tempo tempo, Data data, Boolean diariamente) {
        this.tempo = tempo;
        this.data = data;
        this.diariamente = diariamente;
    }

    public Data getData() {

        return data;
    }

    public Tempo getTempo() {

        return tempo;
    }

    public Boolean getDiariamente() {

        return diariamente;
    }

    @Override
    public String toString() {

        return data + ", " + tempo;
    }
}
