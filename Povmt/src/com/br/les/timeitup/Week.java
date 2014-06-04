
package com.br.les.timeitup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Week {

    private final int num;
    private final Map<String, ArrayList<ActivityTI>> mapDiaTi;
    private List<String> dias;

    public Week(int num) {
        this.num = num;
        dias = new ArrayList<String>();
        createDias();

        mapDiaTi = new HashMap<String, ArrayList<ActivityTI>>();

        for (String dia : dias) {
            mapDiaTi.put(dia, new ArrayList<ActivityTI>());
        }
    }

    private void createDias() {
        dias = new ArrayList<String>();
        dias.add("Domingo");
        dias.add("Segunda");
        dias.add("Terça");
        dias.add("Quarta");
        dias.add("Quinta");
        dias.add("Sexta");

    }

    public int getNum() {
        return num;
    }

    public String calculateTimeOfTiOnWeek(String name) {
        int saida = 0;
        for (String dia : dias) {
            List<ActivityTI> list = mapDiaTi.get(dia);
            for (ActivityTI activityTI : list) {
                saida += activityTI.getTime();
            }
        }
        return String.valueOf(saida);
    }

    public void addTI(ActivityTI my_activity_ti) {
        String dia = retornarDiaSemana(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
        mapDiaTi.get(dia).add(my_activity_ti);

    }

    // retorna o dia da semana dada uma data
    public String retornarDiaSemana(int ano, int mes, int dia)
    {
        Calendar calendario = new GregorianCalendar(ano, mes - 1, dia);
        int diaSemana = calendario.get(Calendar.DAY_OF_WEEK);

        return pesquisarDiaSemana(diaSemana);
    }

    // faz a pesquisa, dado um inteiro de 1 a 7
    private String pesquisarDiaSemana(int _diaSemana)
    {
        String diaSemana = null;

        switch (_diaSemana)
        {

            case 1: {
                diaSemana = "Domingo";
                break;
            }
            case 2: {
                diaSemana = "Segunda";
                break;
            }
            case 3: {
                diaSemana = "Terça";
                break;
            }
            case 4: {
                diaSemana = "Quarta";
                break;
            }
            case 5: {
                diaSemana = "Quinta";
                break;
            }
            case 6: {
                diaSemana = "Sexta";
                break;
            }
            case 7: {
                diaSemana = "Sábado";
                break;
            }

        }
        return diaSemana;

    }

}
