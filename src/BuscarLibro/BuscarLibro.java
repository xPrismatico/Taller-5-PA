package BuscarLibro;

import Entities.Libro;
import Entities.Transaccion;
import Entities.Usuario;
import Inicio.Inicio;
import MenuPrincipal.MenuPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BuscarLibro extends JFrame{
    private List<Usuario> usuarioLista;
    private List<Libro> libroLista;
    private List<Usuario> usuarioIniciado;
    private List<Transaccion> transaccionLista;
    private JTextArea textArea1;
    private JTextField isbnField;
    private JPanel BuscadorLibro;
    private JButton buscarButton;
    private JButton volverButton;

    /**
     * Constructor de la clase BuscarLibro
     * @param usuarioLista lista de usuarios
     * @param libroLista lista de libros
     * @param usuarioIniciado lista del usuario que inicia sesion
     * @param transaccionLista lista de transacciones realizads por el usuario
     */
    public BuscarLibro(List<Usuario> usuarioLista, List<Libro> libroLista, List<Usuario> usuarioIniciado,List<Transaccion> transaccionLista) {
        this.usuarioLista = usuarioLista;
        this.libroLista = libroLista;
        this.usuarioIniciado = usuarioIniciado;
        this.transaccionLista = transaccionLista;

        // Se crea el panel de BuscadorLibro con sus botones, campos, labels, titulo y tamaño indicados y se muestra
        setContentPane(BuscadorLibro);
        setTitle("Bibliotech - Buscador de libros");
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        // Boton de volver al MenuPrincipal
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                volverMenu(usuarioLista,libroLista,usuarioIniciado);
            }
        });
        // Boton de Buscar un libro ingresado
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verDatosLibro(libroLista);
                clear();
            }
        });
    }

    /**
     * Método para mostrar en el formulario los datos del libro que corresponde al ISBN ingresado
     * @param libroLista lista de libros
     */
    private void verDatosLibro(List<Libro> libroLista){
        try{
            // Se guarda en un String lo ingresado en el campo
            String ISBN = isbnField.getText();

            // Si hay algo escrito en el campo
            if (!ISBN.isEmpty()){
                textArea1.setText(""); // Se vacian los datos que estén escritos en el textArea

                //Recorro la lista de libros
                for (int i = 0; i<libroLista.size() ; i++){
                    // Si el libro actual tiene el mismo ISBN que el libro ingresado por campo
                    if (ISBN.equalsIgnoreCase( libroLista.get(i).getCodigoIsbn() )){
                        // Se muestran los datos del libro actual en el textArea con el metodo toString()
                        textArea1.append( libroLista.get(i).toString() );
                        clear();
                        return;
                    }
                    // Si el ISBN ingrsado no se encuentra en la lista de libros enviar mensaje de error
                    else if (i==libroLista.size()-1){
                        JOptionPane.showMessageDialog(BuscadorLibro,"El libro que has ingresado no se encuentra en el sistema","Error",0);
                        clear();
                    }
                }
            }
            // Si no hay nada escrito en el campo enviar mensaje de error
            else
            {
                JOptionPane.showMessageDialog(BuscadorLibro,"Por favor, complete todos los campos.","Error",0);
            }
        }
        // validacion de error al intentar mostrar los datos de un libro
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(BuscadorLibro,"[!] Ha ocurrido un error [!]","Error",0);
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

