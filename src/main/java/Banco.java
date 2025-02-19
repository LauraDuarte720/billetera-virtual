import java.util.ArrayList;


public class Banco {

    private String nombre;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Transaccion> transacciones;
    private ArrayList<Billetera> billeteras;


    public Banco(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(ArrayList<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

    public ArrayList<Billetera> getBilleteras() {
        return billeteras;
    }

    public void setBilleteras(ArrayList<Billetera> billeteras) {
        this.billeteras = billeteras;
    }

    @Override
    public String toString() {
        return "Banco{" +
                "nombre='" + nombre + '\'' +
                ", usuarios=" + usuarios +
                ", transacciones=" + transacciones +
                ", billeteras=" + billeteras +
                '}';
    }
}
