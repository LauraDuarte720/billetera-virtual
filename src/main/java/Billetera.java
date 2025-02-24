import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Billetera {

    //INSTANCIAS NECESARIAS
    private String numTarjeta;
    private double saldo;
    private String propietario;
    private ArrayList<Transaccion> transacciones;
    private static  final float COSTO = 200;

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
        this.transacciones = new ArrayList<>();
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

    // Metodo Consultar Saldo

    public double consultarSaldo() {
        return saldo;
    }

    public ArrayList<Transaccion> consultarTransacciones() {
        return transacciones;
    }

    public Transaccion realizarTransaccion(Banco banco,float saldoTransferir, CATEGORIA categoria,Billetera origen,  Billetera destino) throws Exception{

        ArrayList<Billetera>billeteras=banco.getBilleteras();
        boolean origenValido=false;
        boolean destinoValido=false;
        boolean transaccionValida=false;
        if (saldoTransferir+COSTO>saldo){
            throw  new Exception("No hay saldo suficiente en la billetera ");
        }else if(saldoTransferir<=0) {
            throw new Exception("No se permite transferir un saldo menor a cero");
        }
        while (!transaccionValida){
            for (Billetera billetera: billeteras){
                if( billetera.getNumTarjeta().equals(origen.getNumTarjeta())){
                    origenValido=true;
                }else if (billetera.getNumTarjeta().equals(destino.getNumTarjeta())){
                    destinoValido=true;
                }
            }
            if (origenValido && destinoValido) {
                transaccionValida = true;
            }else {
                throw new Exception("La billetera destino o billetera origen no estan registradas en el banco");
            }
        }


        Transaccion transaccion=new Transaccion(LocalDateTime.now(), categoria,destino, origen, saldoTransferir);
        return transaccion;
    }

    public void agregarTransaccion(Transaccion transaccion){
        transacciones.add(transaccion);
    }

    public double sumarMonto(Transaccion transaccion, Billetera billetera){
        return transaccion.getMonto()+ billetera.getSaldo();
    }
}


