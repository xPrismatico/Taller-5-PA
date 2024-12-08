import Entities.Libro;
import Entities.Transaccion;
import Entities.Usuario;
import Inicio.Inicio;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SistemaImpl implements Sistema{
    private Inicio inicio;
    private static List<Usuario> usuarioIniciado;
    private static List<Libro> libroLista;
    private static List<Usuario> usuarioLista;
    private static List<Transaccion> transaccionLista;

    /**
     * Constructor de la clase SistemaImpl
     */

    public SistemaImpl() {
        this.usuarioLista = new LinkedList<>();
        this.libroLista = new ArrayList<>();
        this.usuarioIniciado = new ArrayList<>();
        this.transaccionLista = new ArrayList<>();
        leerArchivoUsuarios();
        leerArchivoLibros();
    }

    /**
     * Método para iniciar el programa y mostrar el formulario Inicio
     */
    @Override
    public void startProgram(){
        this.inicio = new Inicio(usuarioLista,libroLista,usuarioIniciado,transaccionLista);
    }

    /**
     * Método para leer archivo "libros.txt" y guardar los libros en una lista libroLista
     */
    public static void leerArchivoLibros() {

        // Leer el archivo "libros.txt"
        try (BufferedReader br = new BufferedReader(new FileReader("libros.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Separar una linea en "," y obtener los datos entre cada separacion en un String
                String[] chain = line.split(",");
                String isbn = chain[0];
                String title = chain[1];
                String author = chain[2];
                String category = chain[3];
                int copies = Integer.parseInt(chain[4]);
                int stock = Integer.parseInt(chain[5]);
                // crear un libro de tipo Libro con los atributos del archivo y añadirlos a la lista libroLista
                Libro libro = new Libro(isbn,title,author,category,copies,stock);
                libroLista.add(libro);

            }
        }
        // validacion de error al leer el archivo
        catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    /**
     * Método para leer archivo "usuarios.txt" y guardar los usuarios en una lista usuarioLista
     */
    public static void leerArchivoUsuarios() {

        // Leer el archivo "usuarios.txt"
        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Separar una linea en "," y obtener los datos entre cada separacion en un String
                String[] chain = line.split(",");
                String rut = chain[0];
                String name = chain[1];
                String lastname = chain[2];
                String password = chain[3];
                // crear un usuario de tipo Usuario con los atributos del archivo y añadirlos a la lista usuarioLista
                Usuario usuario = new Usuario(rut,name, lastname, password);
                usuarioLista.add(usuario);

            }
        }
        // validacion de error al leer el archivo
        catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

}
