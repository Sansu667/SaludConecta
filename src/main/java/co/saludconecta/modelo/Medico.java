package co.saludconecta.modelo;

public class Medico {

    private int    idMedico;
    private String nombres;
    private String apellidos;
    private String numRegistro;
    private String email;
    private String telefono;
    private int    idEspecialidad;

    public Medico() {}

    public Medico(int idMedico, String nombres, String apellidos,
                  String numRegistro, String email, String telefono,
                  int idEspecialidad) {
        this.idMedico       = idMedico;
        this.nombres        = nombres;
        this.apellidos      = apellidos;
        this.numRegistro    = numRegistro;
        this.email          = email;
        this.telefono       = telefono;
        this.idEspecialidad = idEspecialidad;
    }

    public int    getIdMedico()                   { return idMedico; }
    public void   setIdMedico(int idMedico)       { this.idMedico = idMedico; }

    public String getNombres()                    { return nombres; }
    public void   setNombres(String nombres)      { this.nombres = nombres; }

    public String getApellidos()                  { return apellidos; }
    public void   setApellidos(String apellidos)  { this.apellidos = apellidos; }

    public String getNumRegistro()                      { return numRegistro; }
    public void   setNumRegistro(String numRegistro)    { this.numRegistro = numRegistro; }

    public String getEmail()                  { return email; }
    public void   setEmail(String email)      { this.email = email; }

    public String getTelefono()                   { return telefono; }
    public void   setTelefono(String telefono)    { this.telefono = telefono; }

    public int  getIdEspecialidad()                     { return idEspecialidad; }
    public void setIdEspecialidad(int idEspecialidad)   { this.idEspecialidad = idEspecialidad; }

    @Override
    public String toString() {
        return "[Médico " + idMedico + "] " + nombres + " " + apellidos +
               " | Reg: " + numRegistro + " | Especialidad ID: " + idEspecialidad;
    }
}
