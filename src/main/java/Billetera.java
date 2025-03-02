import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Random;

public class Billetera {

    //ATRIBUTOS
    private String numTarjeta;
    private double saldo;
    private Usuario propietario;
    private ArrayList<Transaccion> transacciones;
    public static  final float COSTO = 200;

    //SCANNER PARA EL CASO DE QUE HUBIERA APP
    Scanner scanner = new Scanner(System.in);
    String negrita = "\u001B[1m";
    String reset = "\u001B[0m";
    String verde = "\u001B[92m";
    String oro = "\u001B[33m";
    String azul = "\u001B[34m";
    String rojo = "\u001B[31m";


    //CONSTRUCTOR
    public Billetera( double saldo, Usuario propietario) {
        this.numTarjeta = crearNumeroUnicoBilletera();
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

    public Usuario getPropietario() {
        return propietario;
    }

    public void setPropietario(Usuario propietario) {
        this.propietario = propietario;
    }

    //METODO PARA REALIZAR UNA TRANSACCION
    public Transaccion realizarTransaccion(float saldoTransferir, CATEGORIA categoria,Billetera origen,  Billetera destino) throws Exception{

        Transaccion transaccion=new Transaccion(LocalDateTime.now(), categoria,destino, origen, saldoTransferir);

        origen.restarSaldo(saldoTransferir,origen);
        destino.sumarMonto(saldoTransferir,destino);

        origen.agregarTransaccion(transaccion);

        return transaccion;
    }

    //METODO PARA AGREGAR UNA TRANSACCION A LA LISTA
    public void agregarTransaccion(Transaccion transaccion){
        transacciones.add(transaccion);
    }

    //METODO PARA SUMAR MONTO A LA BILLETERA DESTINO
    public void sumarMonto(double saldoTransferencia, Billetera billetera){
        double nuevoSaldoDestino= saldoTransferencia+ billetera.getSaldo();
        billetera.setSaldo( nuevoSaldoDestino);
    }

    //METODO PARA RESTAR SALDO A LA BILLETERA ORIGEN
    public void restarSaldo(double saldoTransferencia, Billetera billetera){
        double nuevoSaldoOrigen=billetera.getSaldo() - saldoTransferencia-COSTO;
        billetera.setSaldo(nuevoSaldoOrigen);
    }

    //METODO PARA RECARGAR BILLETERA
    public void recargarBilletera(double saldoARecargar) throws Exception{
        boolean recargaValida = false;
        while(!recargaValida){
            if (saldoARecargar <= 0) {
                throw new Exception("Seleccione un saldo correcto para recargar!");
            } else {
                recargaValida = true;
            }
        }
        if (recargaValida){
            double saldoNuevo=saldo + saldoARecargar;
            setSaldo(saldoNuevo);
        }
    }

    //METODO CONSULTAR TRANSACCIONES DADO TIEMPO
    public ArrayList<Transaccion> consultarTransaccionesTiempo(LocalDateTime fechaInicio, LocalDateTime fechaFinal) throws Exception {
        ArrayList<Transaccion>listaTransacciones=new ArrayList<>();
        if(fechaInicio.isAfter(fechaFinal)){
            throw new Exception("La segunda fecha tiene que ser despues de la primera");
        }
        boolean tiempoValido=false;
        while(!tiempoValido){
            for (Transaccion transaccion : transacciones) {
                if (transaccion.getFecha().isAfter(fechaInicio) && transaccion.getFecha().isBefore(fechaFinal)) {
                    listaTransacciones.add(transaccion);
                    tiempoValido=true;
                } else {
                    throw new Exception("No hay transacciones realizadas por esta billetera en ese intervalo de tiempo.");
                }
            }
        }
        return listaTransacciones;
    }

    //METODO PARA CONSULTAR PORCENTAJE GASTOS-INGRESOS SEGUN UN PERIODO DE TIEMPO
    public double calcularPorcentajeGastosIngresosTotales(LocalDateTime fechaInicio, LocalDateTime fechaFinal, boolean gastos) throws Exception {

        if(fechaInicio.isAfter(fechaFinal)){
            throw new Exception("La fecha de inicio debe de ser antes de la fecha final");
        }
        ArrayList<Transaccion> transaccionesFilatradas = obtenerTransaccionesFiltradas(fechaInicio, fechaFinal);
        double ingresosTotales = 0;
        double gastosTotales = 0;

        for (Transaccion transaccion : transaccionesFilatradas) {
            if(this.equals(transaccion.getOrigen())){
                gastosTotales+=transaccion.getMonto();
            }
            else if(this.equals(transaccion.getDestinatario())){
                ingresosTotales+=transaccion.getMonto();
            }
        }

        double total = ingresosTotales+gastosTotales;

        if(gastos){
            return (gastosTotales/total)*100;
        }
        return (ingresosTotales/total)*100;

    }

    //METODO PARA CONSULTAR PORCENTAJE GASTOS-INGRESOS SEGUN UN PERIODO DE TIEMPO Y CATEGORIA
    public double calcularPorcentajeGastosIngresosCategoria (LocalDateTime fechaInicio, LocalDateTime fechaFinal, CATEGORIA categoria,boolean gastos) throws Exception{

        if(fechaInicio.isAfter(fechaFinal)){
            throw new Exception("La fecha de inicio debe de ser antes de la fecha final");
        }
        ArrayList<Transaccion> transaccionesFilatradas = obtenerTransaccionesFiltradasCategoria(fechaInicio, fechaFinal, categoria);
        double ingresosTotales = 0;
        double gastosTotales = 0;

        for (Transaccion transaccion : transaccionesFilatradas) {
            if(this.equals(transaccion.getOrigen())){
                gastosTotales+=transaccion.getMonto();
            }
            else if(this.equals(transaccion.getDestinatario())){
                ingresosTotales+=transaccion.getMonto();
            }
        }

        double total = ingresosTotales+gastosTotales;

        if(gastos){
            return (gastosTotales/total)*100;
        }
        return (ingresosTotales/total)*100;


    }

    //METODO PARA OBTENER TRANSACCIONES FILTRADAS SEGUN UN PERIODO DE TIEMPO
    public ArrayList<Transaccion> obtenerTransaccionesFiltradas(LocalDateTime fechaInicio, LocalDateTime fechaFinal){
        ArrayList<Transaccion> transaccionesFilatradas = new ArrayList<>();
        for(Transaccion transaccion : transacciones) {
            if(transaccion.getFecha().isAfter(fechaInicio) && transaccion.getFecha().isBefore(fechaFinal)){
                transaccionesFilatradas.add(transaccion);
            }
        }
        return transaccionesFilatradas;
    }

    //METODO PARA OBTENER TRANSACCIONES FILTRADAS SEGUN UN PERIODO DE TIEMPO Y CATEGORIA
    public ArrayList<Transaccion> obtenerTransaccionesFiltradasCategoria(LocalDateTime fechaInicio, LocalDateTime fechaFinal, CATEGORIA categoria){
        ArrayList<Transaccion> transaccionesFiltradas = obtenerTransaccionesFiltradas(fechaInicio, fechaFinal);
        ArrayList<Transaccion> transaccionesCategorias = new ArrayList<>();
        for(Transaccion transaccion: transaccionesFiltradas){
            if(transaccion.getCategoria().equals(categoria)){
                transaccionesCategorias.add(transaccion);
            }
        }
        return transaccionesCategorias;
    }

    //METODO PARA CREAR UN NUMERO UNICO DE BILLETERA
    public String crearNumeroUnicoBilletera() {

        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }

}


