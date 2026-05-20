


public class Estudiante extends Persona {

    private String carrera;

    public Estudiante(String cedula, String nombreCompleto, int edad, String carrera) {
        super(cedula, nombreCompleto, edad);
        this.carrera = carrera;
    }

    public String getCarrera() { return carrera; }
    public void setCarrera(String carrera) { this.carrera = carrera; }


    @Override
    public void mostrarDatos() {
        System.out.println("  [ESTUDIANTE]");
        System.out.println("  Cedula         : " + getCedula());
        System.out.println("  Nombre         : " + getNombreCompleto());
        System.out.println("  Edad           : " + getEdad());
        System.out.println("  Carrera        : " + carrera);
    }
}