package AgregarLibro;

import Entities.Libro;
import Entities.Transaccion;
import Entities.Usuario;
import MenuPrincipal.MenuPrincipal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class AgregarLibro extends JFrame{
    private List<Usuario> usuarioLista;
    private List<Libro> libroLista;
    private List<Usuario> usuarioIniciado;
    private List<Transaccion> transaccionLista;
    private JButton agregarButton;
    private JButton volverButton;
    private JTextField isbnField;
    private JTextField nombreField;
    private JTextField autorField;
    private JTextField categoriaField;
    private JTextField paginasField;
    private JTextField stockField;
    private JPanel AgregarNuevosLibros;

    /**
     * Constructor de la clase AgregarLibro
     * @param usuarioLista lista de usuarios
     * @param libroLista lista de libros
     * @param usuarioIniciado lista del usuario que inicia sesion
     * @param transaccionLista lista de transacciones realizads por el usuario
     */
    public AgregarLibro(List<Usuario> usuarioLista, List<Libro> libroLista, List<Usuario> usuarioIniciado,List<Transaccion> transaccionLista) {
        this.usuarioLista = usuarioLista;
        this.libroLista = libroLista;
        this.usuarioIniciado = usuarioIniciado;
        this.transaccionLista = transaccionLista;

        // Se crea el panel de Iniciar sesión con sus botones, campos, labels, titulo y tamaño indicados y se muestra
        setContentPane(AgregarNuevosLibros);
        setTitle("Bibliotech - Agregar nuevos libros");
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        // Boton para Agregar un nuevo libro a la lista libroLista
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarNuevoLibro(libroLista);
            }
        });

        // Boton para volver al MenuPrincipal
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                volverMenu(usuarioLista,libroLista,usuarioIniciado);
            }
        });
    }

    /**
     * Método para agregar un nuevo libro a la lista de libros
     * @param libroLista lista de libros
     */
    private void agregarNuevoLibro(List<Libro> libroLista){
        try{
            //Guardo en un String lo que se ingrese en cada campo
            String ISBN = isbnField.getText();
            String nombre = nombreField.getText();
            String autor = autorField.getText();
            String categoria = categoriaField.getText();
            String paginas = paginasField.getText();
            String stock = stockField.getText();

            //Verificar si no hay campos vacios
            if (!ISBN.isEmpty() && !nombre.isEmpty() && !autor.isEmpty() && !categoria.isEmpty() && !paginas.isEmpty() && !stock.isEmpty()){
                int stockInt = Integer.parseInt(stock); // String a int

                for (int i = 0; i<libroLista.size() ; i++){
                    // Comparo el ISBN ingresado por campo con los ISBN de cada libro de la lista
                    if (ISBN.equalsIgnoreCase( libroLista.get(i).getCodigoIsbn() )){
                        JOptionPane.showMessageDialog(AgregarNuevosLibros,"El ISBN del libro que has ingresado ya se encuentra en el sistema.","Error",0);
                        clear();
                    }
                    // Si recorre la lista por completo y no se encuentra el ISBN en la lista, creo un Libro con los datos ingresados y lo agrego a la lista
                    else if (i==libroLista.size()-1){
                        int cantidadPaginas = Integer.parseInt(paginas);
                        int cantidadStock = Integer.parseInt(stock);
                        Libro nuevo = new Libro(ISBN,nombre,autor,categoria,cantidadPaginas,cantidadStock);
                        libroLista.add(nuevo); // Añade el nuevo libro a la lista de libros

                        JOptionPane.showMessageDialog(AgregarNuevosLibros,"Has agregado el nuevo libro con éxito","Mensaje",1);
                        clear();
                        return;
                    }
                }
            }
            // Si hay campos vacios enviar mensaje de error
            else
            {
                JOptionPane.showMessageDialog(AgregarNuevosLibros,"Por favor, complete todos los campos.","Error",0);
            }
        }
        // validacion de error al agregar un nuevo libro
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(AgregarNuevosLibros,"[!] Ha ocurrido un error [!]","Error",0);
            clear();
        }
    }

    /**
     * Método para volver al formulario MenuPrincipal y ocultar el formulario actual
     * @param usuarioLista lista de usuarios
     * @param libroLista lista de libros
     * @param usuarioIniciado lista del usuario que inicia sesion
     */
    private void volverMenu(List<Usuario> usuarioLista, List<Libro> libroLista, List<Usuario> usuarioIniciado){
        MenuPrincipal menu = new MenuPrincipal(usuarioLista,libroLista,usuarioIniciado,transaccionLista);
        setVisible(false);
    }
    /**
     * Método para limpiar todos los campos del formulario AgregarLibro
     */
    private void clear(){
        isbnField.setText("");
        nombreField.setText("");
        autorField.setText("");
        categoriaField.setText("");
        paginasField.setText("");
        stockField.setText("");
    }
}
