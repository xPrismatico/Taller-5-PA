package PrestarLibro;

import Entities.Libro;
import Entities.Transaccion;
import Entities.Usuario;
import MenuPrincipal.MenuPrincipal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PrestarLibro extends JFrame{
    private List<Usuario> usuarioLista;
    private List<Libro> libroLista;
    private List<Usuario> usuarioIniciado;

    private List<Transaccion> transaccionLista;
    private JPanel ArrendarLibro;
    private JTextField isbnField;
    private JButton volverButton;
    private JButton prestarButton;

    /**
     * Constructor de la clase PrestarLibro
     * @param usuarioLista lista de usuarios
     * @param libroLista lista de libros
     * @param usuarioIniciado lista del usuario que inicia sesion
     * @param transaccionLista lista de transacciones realizads por el usuario
     */
    public PrestarLibro(List<Usuario> usuarioLista, List<Libro> libroLista, List<Usuario> usuarioIniciado, List<Transaccion> transaccionLista) {
        this.usuarioLista = usuarioLista;
        this.libroLista = libroLista;
        this.usuarioIniciado = usuarioIniciado;
        this.transaccionLista = transaccionLista;

        // Se crea el panel de Arrendar libro con sus botones, campos, labels, titulo y tamaño indicados y se muestra
        setContentPane(ArrendarLibro);
        setTitle("Bibliotech - Prestación de libros");
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        // Boton para prestar un libro
        prestarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                arrendarLibro(libroLista,usuarioIniciado,transaccionLista);
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
     * Método que resta uno al stock del libro en la lista de libros que corresponde al ISBN ingresado
     * @param libroLista lista de libros
     * @param usuarioIniciado lista del usuario que inicia sesion
     * @param transaccionLista lista de transacciones realizads por el usuario
     */
    private void arrendarLibro(List<Libro> libroLista,List<Usuario> usuarioIniciado,List<Transaccion> transaccionLista){
        try{
            // Se guarda en un String lo ingresado en el campo
            String ISBN = isbnField.getText();
            // Si no esta vacio el campo
            if (!ISBN.isEmpty()){

                for (int i = 0; i<libroLista.size() ; i++){
                    // Si el ISBN entregado por el campo Coincide con el ISB del libro y hay Stock, entonces se puede prestar
                    if (ISBN.equalsIgnoreCase( libroLista.get(i).getCodigoIsbn() ) && libroLista.get(i).getStock() > 0){
                        int stockActual = libroLista.get(i).getStock()-1;
                        libroLista.get(i).setStock(stockActual); // Restar -1 al stock actual del libro

                        // Crear datos para una transaccion
                        String rut = usuarioIniciado.get(0).getRut();
                        String nombreVendedor = usuarioIniciado.get(0).getNombre();
                        String apellido = usuarioIniciado.get(0).getApellido();

                        String isbn = libroLista.get(i).getCodigoIsbn();
                        String nombreLibro = libroLista.get(i).getNombre();
                        String tipoTransaccion = "prestamo";

                        // Crear nueva transaccion y añadirla a la lista
                        Transaccion nueva = new Transaccion(rut,nombreVendedor,apellido,isbn,nombreLibro,tipoTransaccion);
                        transaccionLista.add(nueva);

                        JOptionPane.showMessageDialog(ArrendarLibro,"Has prestado el libro con éxito","Mensaje",1);
                        clear();
                        return;
                    }
                    // Si no hay stock del libro correspondiente al ISBN ingresado por el campo enviar mensaje de error
                    else if (libroLista.get(i).getStock() <= 0){
                        JOptionPane.showMessageDialog(ArrendarLibro,"No hay stock disponible del libro con ese ISBN","Error",0);
                        clear();
                        return;
                    }
                    // Si el ISBN ingresado por campo no se encuentra en ningun libro de la lista libros enviar mensaje de error
                    else if (i==libroLista.size()-1){
                        JOptionPane.showMessageDialog(ArrendarLibro,"El ISBN del libro que has ingresado no se encuentra en el sistema","Error",0);
                        clear();
                    }
                }
            }
            // Si los campos estan vacios enviar mensaje de error
            else
            {
                JOptionPane.showMessageDialog(ArrendarLibro,"Por favor, complete todos los campos.","Error",0);
            }
        }
        // Validacion de error al intentar prestar un libro recorriendo lista de libros y creando una nueva transaccion
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(ArrendarLibro,"[!] Ha ocurrido un error [!]","Error",0);
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
    }

}
