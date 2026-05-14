package co.saludconecta.modelo;

/**
 * Representa la entidad Paciente del sistema SaludConecta.
 * Corresponde a la tabla 'paciente' de la base de datos.
 */
public class Paciente {

    private int    idPaciente;
    private String nombres;
    private String apellidos;
    private String tipoDoc;
    private String numDoc;
    private String fechaNac;
    private String email;
    private String telefono;
    private String direccion;

    // Constructor vacío
    public Paciente() {}

    // Constructor completo
    public Paciente(int idPaciente, String nombres, String apellidos,
                    String tipoDoc, String numDoc, String fechaNac,
                    String email, String telefono, String direccion) {
        this.idPaciente = idPaciente;
        this.nombres    = nombres;
        this.apellidos  = apellidos;
        this.tipoDoc    = tipoDoc;
        this.numDoc     = numDoc;
        this.fechaNac   = fechaNac;
        this.email      = email;
        this.telefono   = telefono;
        this.direccion  = direccion;
    }

    // Getters y Setters
    public int getIdPaciente()             { return idPaciente; }
    public void setIdPaciente(int id)      { this.idPaciente = id; }

    public String getNombres()             { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos()                 { return apellidos; }
    public void setApellidos(String apellidos)   { this.apellidos = apellidos; }

    public String getTipoDoc()               { return tipoDoc; }
    public void setTipoDoc(String tipoDoc)   { this.tipoDoc = tipoDoc; }

    public String getNumDoc()              { return numDoc; }
    public void setNumDoc(String numDoc)   { this.numDoc = numDoc; }

    public String getFechaNac()                { return fechaNac; }
    public void setFechaNac(String fechaNac)   { this.fechaNac = fechaNac; }

    public String getEmail()             { return email; }
    public void setEmail(String email)   { this.email = email; }

    public String getTelefono()                { return telefono; }
    public void setTelefono(String telefono)   { this.telefono = telefono; }

    public String getDireccion()                 { return direccion; }
    public void setDireccion(String direccion)   { this.direccion = direccion; }

    @Override
    public String toString() {
        return "[" + idPaciente + "] " + nombres + " " + apellidos +
               " | Doc: " + numDoc + " | Email: " + email;
    }
}
