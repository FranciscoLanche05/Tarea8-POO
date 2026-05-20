
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    private static ArrayList<Persona> personas = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion = 0;


        do {
            mostrarMenu();
            opcion = leerEntero("Ingrese una opcion: ");

            switch (opcion) {
                case 1: registrarPersona();  break;
                case 2: mostrarRegistros();  break;
                case 3: actualizarRegistro(); break;
                case 4: eliminarRegistro();  break;
                case 5: System.out.println("\nSistema cerrado. Hasta luego."); break;
                default:
                    System.out.println("  Error: opcion invalida. Intente nuevamente.\n");
            }
        } while (opcion != 5);
    }


    private static void mostrarMenu() {
        System.out.println("========================================");
        System.out.println("  SISTEMA DE GESTION ACADEMICA");
        System.out.println("========================================");
        System.out.println("  1. Registrar persona");
        System.out.println("  2. Mostrar registros");
        System.out.println("  3. Actualizar registro");
        System.out.println("  4. Eliminar registro");
        System.out.println("  5. Salir");
        System.out.println("========================================");
    }


    private static void registrarPersona() {
        System.out.println("\n-- REGISTRAR PERSONA --");
        System.out.println("  Seleccione tipo:");
        System.out.println("  1. Estudiante");
        System.out.println("  2. Docente");

        int tipo = leerEntero("  Opcion: ");
        if (tipo != 1 && tipo != 2) {
            System.out.println("  Error: opcion invalida. Intente nuevamente.\n");
            return;
        }


        String cedula       = leerTexto("  Ingrese cedula: ");
        String nombre       = leerTexto("  Ingrese nombre completo: ");
        int    edad         = leerEntero("  Ingrese edad: ");


        if (existeCedula(cedula)) {
            System.out.println("  Error: ya existe una persona con esa cedula.\n");
            return;
        }

        if (tipo == 1) {

            String carrera = leerTexto("  Ingrese carrera: ");
            personas.add(new Estudiante(cedula, nombre, edad, carrera));  // ArrayList.add()
        } else {

            String asignatura = leerTexto("  Ingrese asignatura: ");
            personas.add(new Docente(cedula, nombre, edad, asignatura));  // ArrayList.add()
        }

        System.out.println("  Registro agregado correctamente.\n");

        mostrarConteo();
    }


    private static void mostrarRegistros() {
        System.out.println("\n-- LISTADO DE PERSONAS --");
        if (personas.isEmpty()) {
            System.out.println("  No hay registros almacenados.\n");
            return;
        }


        for (int i = 0; i < personas.size(); i++) {
            System.out.println("  Posicion [" + i + "]:");
            personas.get(i).mostrarDatos();
            System.out.println();
        }
    }


    private static void actualizarRegistro() {
        System.out.println("\n-- ACTUALIZAR REGISTRO --");
        if (personas.isEmpty()) {
            System.out.println("  No hay registros para actualizar.\n");
            return;
        }

        mostrarRegistros();
        int pos = leerEntero("  Ingrese la posicion a actualizar: ");


        if (pos < 0 || pos >= personas.size()) {
            System.out.println("  Error: Registro no encontrado.\n");
            return;
        }

        Persona actual = personas.get(pos);
        System.out.println("  Actualizando: " + actual.getNombreCompleto());

        String nombre = leerTexto("  Nuevo nombre completo: ");
        int    edad   = leerEntero("  Nueva edad: ");

        if (actual instanceof Estudiante) {
            String carrera = leerTexto("  Nueva carrera: ");

            personas.set(pos, new Estudiante(actual.getCedula(), nombre, edad, carrera));
        } else if (actual instanceof Docente) {
            String asignatura = leerTexto("  Nueva asignatura: ");
            personas.set(pos, new Docente(actual.getCedula(), nombre, edad, asignatura));
        }

        System.out.println("  Registro actualizado correctamente.\n");
    }


    private static void eliminarRegistro() {
        System.out.println("\n-- ELIMINAR REGISTRO --");
        if (personas.isEmpty()) {
            System.out.println("  No hay registros para eliminar.\n");
            return;
        }

        mostrarRegistros();
        int pos = leerEntero("  Ingrese la posicion a eliminar: ");

        if (pos < 0 || pos >= personas.size()) {
            System.out.println("  Error: Registro no encontrado.\n");
            return;
        }

        String nombre = personas.get(pos).getNombreCompleto();
        System.out.print("  Confirmar eliminacion de '" + nombre + "' (s/n): ");
        String confirm = scanner.nextLine().trim().toLowerCase();

        if (confirm.equals("s")) {
            personas.remove(pos);
            System.out.println("  Registro eliminado correctamente.\n");
        } else {
            System.out.println("  Eliminacion cancelada.\n");
        }
    }



    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int valor = Integer.parseInt(scanner.nextLine().trim());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("  Error: debe ingresar solo numeros.");
            }
        }
    }


    private static String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = scanner.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("  Error: campo obligatorio.");
        }
    }


    private static boolean existeCedula(String cedula) {
        for (Persona p : personas) {
            if (p.getCedula().equals(cedula)) return true;
        }
        return false;
    }

    private static void mostrarConteo() {
        int estudiantes = 0, docentes = 0;
        for (Persona p : personas) {
            if (p instanceof Estudiante) estudiantes++;
            else if (p instanceof Docente) docentes++;
        }
        System.out.println("  Total registros -> Estudiantes: " + estudiantes
                + "  |  Docentes: " + docentes + "\n");
    }
}