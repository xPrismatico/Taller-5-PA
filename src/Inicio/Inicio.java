package Inicio;
import Entities.Libro;
import Entities.Transaccion;
import Entities.Usuario;
import MenuPrincipal.MenuPrincipal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Inicio extends JFrame{
    private List<Usuario> usuarioLista;
    private List<Libro> libroLista;
    private List<Usuario> usuarioIniciado;
    private List<Transaccion> transaccionLista;

    private JTextField rutField;
    private JTextField contraseniaField;
    private JButton iniciarButton;
    private JPanel IniciarSesion;
    private JButton cerrarButton;

    /**
     * Constructor de la clase Inicio
     * @param usuarioLista lista de usuarios
     * @param libroLista lista de libros
     * @param usuarioIniciado lista del usuario que inicia sesion
     * @param transaccionLista lista de transacciones realizadas por el usuario
     */
    public Inicio(List<Usuario> usuarioLista,List<Libro> libroLista,List<Usuario> usuarioIniciado,List<Transaccion> transaccionLista){
        this.usuarioLista = usuarioLista;
        this.libroLista = libroLista;
        this.usuarioIniciado = usuarioIniciado;
        this.transaccionLista = transaccionLista;

        // Se crea el panel de Iniciar sesión con sus botones, campos, labels, titulo y tamaño indicados y se muestra
        setContentPane(IniciarSesion);
        setTitle("Bibliotech - Iniciar sesión");
        setSize(800, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        // Boton de Iniciar Sesión
        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Iniciar(usuarioLista,libroLista,usuarioIniciado);
            }
        });

        // Boton de Cerrar
        cerrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarRegistro(transaccionLista);
                escribirArchivoLibros(libroLista);
                cerrar();
            }
        });
    }

    /**
     * Metodo que valida que los datos del usuaro esten en la lista y llama al menu principal
     * @param usuarioLista lista de usuarios
     * @param libroLista lista de libros
     * @param usuarioIniciado lista del usuario que inicia sesion
     */
    private void Iniciar(List<Usuario> usuarioLista, List<Libro> libroLista, List<Usuario> usuarioIniciado){

        try{
            // Recorrer lista y revisar por usuario
            String rutIngresado = rutField.getText();
            String contraseniaIngresada = contraseniaField.getText();

            // Si los campos rut y contrasenia no están vacios
            if (!rutIngresado.isEmpty() && !contraseniaIngresada.isEmpty() ){

                for (int i=0 ; i < usuarioLista.size(); i++){
                    // Se recorre la lista de usuarios para comparar si el rut y contrasenia ingresados son iguales
                    if (usuarioLista.get(i).getRut().equalsIgnoreCase(rutIngresado) && usuarioLista.get(i).getContrasenia().equalsIgnoreCase(contraseniaIngresada)){
                        usuarioIniciado.add(usuarioLista.get(i)); // Se agrega al usuario que inicia sesión a la lista usuarioIniciado (es solo para acceder al usuario que hace transacciones mas facil)

                        //Se abre el formulario MenuPrincipal y se oculta el formulario Inicio
                        MenuPrincipal menuPrincipal = new MenuPrincipal(usuarioLista,libroLista,usuarioIniciado,transaccionLista);
                        setVisible(false);
                        menuPrincipal.setVisible(true);
                        return;
                    }
                    // Si el rut o contrasenia ingresados en los campos no coinciden con la lista de usuarios registrados en "usuarios.txt" enviar mensaje de error
                    else if (i == usuarioLista.size()-1){
                        JOptionPane.showMessageDialog(IniciarSesion,"El rut o contraseña ingresados no existe.","Error de inicio de sesión",0);
                        clear();
                    }
                }
                clear();
            }
            // Si los campos rut y contrasenia están vacíos enviar mensaje de error
            else{
                JOptionPane.showMessageDialog(IniciarSesion,"Por favor, complete todos los campos.","Error de inicio de sesión",0);
            }

        }
        // validacion de error al iniciar sesion
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(IniciarSesion, "[!] Ha ocurrido un error [!]","Error de inicio de sesión",0);
            clear();
        }
    }

    /**
     * Método que genera un archivo "registro.txt" donde se guardan las transacciones realizadas por el usuario
     * @param transaccionLista lista de transacciones del usuario
     */
    public static void generarRegistro(List<Transaccion> transaccionLista) {
        // Escribir el archivo "registro.txt"
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("registro.txt"));
            // Para cada Transaccion en la lista, se crea un String que tenga los atributos de la transaccion actual separado por "," y se escribe en registro.txt en una linea
            for (Transaccion transaccion : transaccionLista) {
                String linea = transaccion.getRut() + "," + transaccion.getNombreVendedor() + "," + transaccion.getApellido() + "," + transaccion.getIsbn() + "," + transaccion.getNombreLibro() + "," + transaccion.getTipoTransaccion();
                writer.write(linea);
                writer.newLine();
            }

            writer.close();
        }
        catch (IOException e){
            // validacion de error al escribir el archivo
            System.out.println("[!] Ha ocurrido un error al crear el archivo [!]");
            e.printStackTrace();

        }
    }

    /**
     * Método para escribir en un archivo "libros.txt" con los datos de la lista de libros
     * @param libroLista lista de libros que gestiona el usuario en el sistema
     */
    public static void escribirArchivoLibros(List<Libro> libroLista) {
        // Escribir el archivo "libros.txt"
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("libros.txt"));
            // Para cada Libro en la lista, se crea un String que tenga los atributos del libro actual separado por "," y se escribe en libros.txt en una linea
            for (Libro libro : libroLista) {
                String linea = libro.getCodigoIsbn() + "," + libro.getNombre() + "," + libro.getAutor() + "," + libro.getCategoria() + "," + libro.getCantidadPaginas() + "," + libro.getStock();
                writer.write(linea);
                writer.newLine();
            }

            writer.close();
        }
        catch (IOException e){
            // validacion de error al escribir el archivo
            System.out.println("[!] Ha ocurrido un error al crear el archivo [!]");
            e.printStackTrace();

        }
    }

    /**
     * Método para limpiar todos los campos del formulario Inicio
     */
    private void clear(){
        contraseniaField.setText("");
        rutField.setText("");
    }

    /**
     * Método para cerrar el programa
     */
    private void cerrar(){
        System.exit(0);
    }
}
