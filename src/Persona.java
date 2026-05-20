

public abstract class Persona {

    private String cedula;
    private String nombreCompleto;
    private int edad;


    public Persona(String cedula, String nombreCompleto, int edad) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
    }


    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }


    public abstract void mostrarDatos();
}