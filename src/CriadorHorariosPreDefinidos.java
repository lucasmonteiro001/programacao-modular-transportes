import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lucas on 5/23/16.
 * Classe que cria os horarios pre definidos
 */
public class CriadorHorariosPreDefinidos {

    public static List<Horario> criarHorariosRJMaq3() {

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

    public static List<Horario> criarHorariosSPAMaq3() {

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

    public static List<Horario> criarHorariosSPAMaq2() {

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

    public static List<Horario> criarHorariosBHZMaq2() {

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

    public static List<Horario> criarHorariosBRAMaq1() {

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

    public static List<Horario> criarHorariosRand1() {

        List<Horario> horarios = new LinkedList<>();

        adicionarNovoHorario(horarios, new Tempo(6, 45));
        adicionarNovoHorario(horarios, new Tempo(10, 30));
        adicionarNovoHorario(horarios, new Tempo(16, 0));
        adicionarNovoHorario(horarios, new Tempo(21, 0));

        return horarios;
    }

    public static List<Horario> criarHorariosRand2() {

        List<Horario> horarios = new LinkedList<>();

        adicionarNovoHorario(horarios, new Tempo(3, 45));
        adicionarNovoHorario(horarios, new Tempo(5, 40));
        adicionarNovoHorario(horarios, new Tempo(6, 0));
        adicionarNovoHorario(horarios, new Tempo(12, 0));
        adicionarNovoHorario(horarios, new Tempo(23, 45));
        adicionarNovoHorario(horarios, new Tempo(23, 59));

        return horarios;
    }

    public static List<Horario> criarHorariosRand3() {

        List<Horario> horarios = new LinkedList<>();

        adicionarNovoHorario(horarios, new Tempo(2, 45));
        adicionarNovoHorario(horarios, new Tempo(3, 45));
        adicionarNovoHorario(horarios, new Tempo(4, 45));
        adicionarNovoHorario(horarios, new Tempo(5, 45));
        adicionarNovoHorario(horarios, new Tempo(14, 45));
        adicionarNovoHorario(horarios, new Tempo(15, 45));
        adicionarNovoHorario(horarios, new Tempo(16, 45));
        adicionarNovoHorario(horarios, new Tempo(17, 45));

        return horarios;
    }

    public static List<Horario> criarHorariosRand4() {

        List<Horario> horarios = new LinkedList<>();

        adicionarNovoHorario(horarios, new Tempo(8, 15));
        adicionarNovoHorario(horarios, new Tempo(9, 15));
        adicionarNovoHorario(horarios, new Tempo(10, 15));
        adicionarNovoHorario(horarios, new Tempo(21, 10));
        adicionarNovoHorario(horarios, new Tempo(22, 10));
        adicionarNovoHorario(horarios, new Tempo(23, 10));


        return horarios;
    }



    public static void adicionarNovoHorario(List<Horario> horarios, Tempo tempo) {

        Calendar calobj = Calendar.getInstance();

        Integer mes = calobj.get(Calendar.MONTH) + 1,
                dia = calobj.get(Calendar.DAY_OF_MONTH),
                ano = calobj.get(Calendar.YEAR);

        Data data = new Data(dia,mes,ano);
        Horario horario = new Horario(tempo, data, true);
        horarios.add(horario);
    }

}
