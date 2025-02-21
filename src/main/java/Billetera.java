import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Billetera {

    //INSTANCIAS NECESARIAS
        private String numTarjeta;
        private double saldo;
        private String propietario;
        private ArrayList<Transaccion> transacciones;
        private ArrayList<Usuario> usuarios;
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
            this.usuarios = new ArrayList<>();
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
        public String getPropietario() {
            return propietario;
        }
        public void setPropietario(String propietario) {
            this.propietario = propietario;
        }

    //METODO crearBilletera
        public void crearBilletera(){
            numTarjeta = "";
            saldo = 0;
            String propietarioNuevo = Banco.leerTextoValido(scanner, azul+negrita+"Ingrese su nombre: "+reset);
            String idPropietario = Banco.leerTextoValido(scanner, azul+negrita+"Ingrese su id: "+reset);
            StringBuilder propietarioAsignado = new StringBuilder();
            for(Usuario usuario : usuarios){
                if(usuario.getNombre().equalsIgnoreCase(propietarioNuevo) && usuario.getId().equals(idPropietario)){
                    propietarioAsignado.append(usuario.getNombre());
                    break;
                }else {
                    System.out.println(rojo + negrita + "Usuario/ID No encontrado en nuestra base de datos." + reset);
                    return;
                }
            }
            propietario = propietarioAsignado.toString();
            StringBuilder sb = new StringBuilder();
            Random random = new Random();
            for (int i = 0; i < 16; i++) {
                sb.append(random.nextInt(10));
            }
            numTarjeta = sb.toString();
            Billetera billeteraNueva = new Billetera(numTarjeta, saldo, propietario);
            System.out.println(verde+negrita+"Billetera virtual creada con éxito!");
            System.out.println(verde+negrita+"Propietario: "+propietario);
            System.out.println(verde+negrita+"Numero de tarjeta: "+numTarjeta);
            System.out.println(verde+negrita+"Saldo: "+saldo);
        }
}
