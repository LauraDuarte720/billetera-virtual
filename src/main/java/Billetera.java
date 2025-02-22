import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Billetera {

    //INSTANCIAS NECESARIAS
        private String numTarjeta;
        private double saldo;
        private String propietario;
        private ArrayList<Transaccion> transacciones;
        float COSTO = 200;
        Scanner scanner = new Scanner(System.in);
        String negrita = "\u001B[1m";
        String reset = "\u001B[0m";
        String verde = "\u001B[92m";
        String oro = "\u001B[33m";
        String azul = "\u001B[34m";
        String rojo = "\u001B[31m";


    //CONSTRUCTOR
        public Billetera(String numTarjeta, double saldo, String propietario) {
            this.numTarjeta = numTarjeta;
            this.saldo = saldo;
            this.propietario = propietario;
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
        public String getPropietario() {
            return propietario;
        }
        public void setPropietario(String propietario) {
            this.propietario = propietario;
        }




}
