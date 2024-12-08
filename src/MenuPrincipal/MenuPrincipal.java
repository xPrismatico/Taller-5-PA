package MenuPrincipal;

import Entities.Libro;
import Entities.Transaccion;
import Entities.Usuario;
import Inicio.Inicio;
import PrestarLibro.PrestarLibro;
import DevolverLibro.DevolverLibro;
import AgregarLibro.AgregarLibro;
import BuscarLibro.BuscarLibro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MenuPrincipal extends JFrame {
    private List<Usuario> usuarioLista;
    private List<Libro> libroLista;
    private List<Usuario> usuarioIniciado;
    private List<Transaccion> transaccionLista;
    private JPanel MenuPrincipal;
    private JButton buscarLibroButton;
    private JButton prestarLibroButton;
    private JButton agregarLibroButton;
    private JButton devolverLibroButton;
    private JButton cerrarSesionButton;

    public MenuPrincipal(List<Usuario> usuarioLista,List<Libro> libroLista,List<Usuario> usuarioIniciado,List<Transaccion> transaccionLista) {
        this.usuarioLista = usuarioLista;
        this.libroLista = libroLista;
        this.usuarioIniciado = usuarioIniciado;
        this.transaccionLista = transaccionLista;

        setContentPane(MenuPrincipal);
        setTitle("Bibliotech - Menú del sistema");
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        buscarLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                buscarLibro(usuarioLista,libroLista,usuarioIniciado);

            }
        });
        prestarLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                prestarLibro(usuarioLista,libroLista,usuarioIniciado);

            }
        });
        agregarLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                agregarLibro(usuarioLista,libroLista,usuarioIniciado);

            }
        });
        devolverLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                devolverLibro(usuarioLista,libroLista,usuarioIniciado);

            }
        });
        cerrarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarSesion(usuarioLista,libroLista,usuarioIniciado);
            }
        });
    }

    /**
     *
     * @param usuarioLista
     * @param libroLista
     * @param usuarioIniciado
     */
    private void cerrarSesion(List<Usuario> usuarioLista, List<Libro> libroLista, List<Usuario> usuarioIniciado){
        Inicio inicio = new Inicio(usuarioLista,libroLista,usuarioIniciado,transaccionLista);
        setVisible(false);
    }

    /**
     * Metodo que llama al formulario de buscar libro
     * @param usuarioLista lista que contiene a los susuarios
     * @param libroLista lista que contiene los libros
     * @param usuarioIniciado lista ....
     */
    private void buscarLibro(List<Usuario> usuarioLista, List<Libro> libroLista, List<Usuario> usuarioIniciado){
        BuscarLibro buscar = new BuscarLibro(usuarioLista,libroLista,usuarioIniciado,transaccionLista);
        setVisible(false);
    }

    /**
     * Metodo que llama al formulario de prestar libro
     * @param usuarioLista
     * @param libroLista
     * @param usuarioIniciado
     */
    private void prestarLibro(List<Usuario> usuarioLista, List<Libro> libroLista, List<Usuario> usuarioIniciado){
        PrestarLibro prestar = new PrestarLibro(usuarioLista,libroLista,usuarioIniciado,transaccionLista);
        setVisible(false);
    }

    private void agregarLibro(List<Usuario> usuarioLista, List<Libro> libroLista, List<Usuario> usuarioIniciado){
        AgregarLibro agregar = new AgregarLibro(usuarioLista,libroLista,usuarioIniciado,transaccionLista);
        setVisible(false);
    }

    private void devolverLibro(List<Usuario> usuarioLista, List<Libro> libroLista, List<Usuario> usuarioIniciado){
        DevolverLibro devolver = new DevolverLibro(usuarioLista,libroLista,usuarioIniciado,transaccionLista);
        setVisible(false);
    }



}
