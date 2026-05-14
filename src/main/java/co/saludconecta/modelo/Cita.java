package co.saludconecta.modelo;

/**
 * Representa la entidad Cita médica del sistema SaludConecta.
 * Corresponde a la tabla 'cita' de la base de datos.
 */
public class Cita {

    private int    idCita;
    private String fechaCita;
    private String horaCita;
    private String estado;
    private int    idPaciente;
    private int    idMedico;

    public Cita() {}

    public Cita(int idCita, String fechaCita, String horaCita,
                String estado, int idPaciente, int idMedico) {
        this.idCita     = idCita;
        this.fechaCita  = fechaCita;
        this.horaCita   = horaCita;
        this.estado     = estado;
        this.idPaciente = idPaciente;
        this.idMedico   = idMedico;
    }

    public int    getIdCita()                { return idCita; }
    public void   setIdCita(int idCita)      { this.idCita = idCita; }

    public String getFechaCita()                   { return fechaCita; }
    public void   setFechaCita(String fechaCita)   { this.fechaCita = fechaCita; }

    public String getHoraCita()                  { return horaCita; }
    public void   setHoraCita(String horaCita)   { this.horaCita = horaCita; }

    public String getEstado()                { return estado; }
    public void   setEstado(String estado)   { this.estado = estado; }

    public int  getIdPaciente()                  { return idPaciente; }
    public void setIdPaciente(int idPaciente)    { this.idPaciente = idPaciente; }

    public int  getIdMedico()                { return idMedico; }
    public void setIdMedico(int idMedico)    { this.idMedico = idMedico; }

    @Override
    public String toString() {
        return "[Cita " + idCita + "] Fecha: " + fechaCita +
               " " + horaCita + " | Estado: " + estado +
               " | Paciente ID: " + idPaciente +
               " | Médico ID: " + idMedico;
    }
}
