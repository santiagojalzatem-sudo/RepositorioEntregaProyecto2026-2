package co.uniquindio.academiaparcial.Prototype;

import co.uniquindio.academiaparcial.Model.Curso;
import co.uniquindio.academiaparcial.Model.ServicioAdicional;
import java.util.ArrayList;
import java.util.List;

public class PrototipoRegistry {

    private List<Curso> cursosBase;
    private List<ServicioAdicional> serviciosBase;

    public PrototipoRegistry() {
        cursosBase = new ArrayList<Curso>();
        serviciosBase = new ArrayList<ServicioAdicional>();
    }

    public void agregarCursoBase(Curso curso) {
        cursosBase.add(curso);
    }

    public void agregarServicioBase(ServicioAdicional servicio) {
        serviciosBase.add(servicio);
    }

    public Curso clonarCurso(String codigo) {
        for (int i = 0; i < cursosBase.size(); i++) {
            Curso c = cursosBase.get(i);
            if (c.getCodigo().equals(codigo)) {
                Curso copia = c.clonar();
                return copia;
            }
        }
        return null;
    }

    public ServicioAdicional clonarServicio(String codigo) {
        for (int i = 0; i < serviciosBase.size(); i++) {
            ServicioAdicional s = serviciosBase.get(i);
            if (s.getCodigo().equals(codigo)) {
                ServicioAdicional copia = s.clonar();
                return copia;
            }
        }
        return null;
    }
}
