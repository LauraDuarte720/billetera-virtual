import java.util.ArrayList;

public class Billetera {

    private String numTarjeta;
    private double saldo;
    private ArrayList<Transaccion> transacciones;
    private ArrayList<Usuario> usuarios;

    //CONSTRUCTOR
        public Billetera(String numTarjeta, double saldo) {
            this.numTarjeta = numTarjeta;
            this.saldo = saldo;
            ArrayList<Transaccion> transacciones = new ArrayList<>();
            ArrayList<Usuario> usuarios = new ArrayList<>();
        }

    //GETTERS Y SETTERS
        public String getNumTarjeta() {
            return numTarjeta;
        }
        public void setNumTarjeta(String numTarjeta) {
            this.numTarjeta = numTarjeta;
        }
        public double getSaldo() {
            return saldo;
        }
        public void setSaldo(double saldo) {
            this.saldo = saldo;
        }
        public ArrayList<Transaccion> getTransacciones() {
            return transacciones;
        }
        public void setTransacciones(ArrayList<Transaccion> transacciones) {
            this.transacciones = transacciones;
        }
        public ArrayList<Usuario> getUsuarios() {
            return usuarios;
        }
        public void setUsuarios(ArrayList<Usuario> usuarios) {
            this.usuarios = usuarios;
        }


}
