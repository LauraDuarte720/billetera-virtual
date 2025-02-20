import java.util.ArrayList;
import java.util.Scanner;


public class Banco {

    //INSTANCIAS NECESARIAS
        private String nombre;
        private ArrayList<Usuario> usuarios;
        private ArrayList<Transaccion> transacciones;
        private ArrayList<Billetera> billeteras;
        Scanner scanner = new Scanner(System.in);
        String negrita = "\u001B[1m";
        String reset = "\u001B[0m";
        String morado = "\u001B[35m";
        String verde = "\u001B[92m";
        String oro = "\u001B[33m";
        String azul = "\u001B[34m";


    //CONSTRUCTOR
        public Banco(String nombre) {
            this.nombre = nombre;
        }

    //GETTERS Y SETTERS
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


    //METODOS PARA LA OPTIMIZACION DEL CODIGO
        public static String leerTextoValido(Scanner scanner, String mensaje) {
            String texto;
            String reset = "\u001B[0m";
            String negrita = "\u001B[1m";
            String rojo = "\u001B[31m";
            while (true) {
                System.out.print(mensaje);
                texto = scanner.nextLine().trim();
                if (!texto.isEmpty()) {
                    break;
                } else {
                    System.out.println(rojo+negrita+"Dato inválido. Por favor, ingrese un valor."+reset);
                }
            }
            return texto;
        }

        


    //METODO registrarUsuario
        public void registrarUsuario(String nombre, String direccion, String id, String correo, String contrasena){
            Usuario usuario = new Usuario("", "", "", "", "");
            nombre = leerTextoValido(scanner,azul+"Ingrese su nombre: "+reset);
            direccion = leerTextoValido(scanner,azul+"Ingrese su dirección: "+reset);
            id = leerTextoValido(scanner,azul+"Ingrese su número de identificación: "+reset);
            correo = leerTextoValido(scanner,azul+"Ingrese su correo electrónico: "+reset);
            contrasena = leerTextoValido(scanner,azul+"Ingrese su contraseña: "+reset);
            usuarios.add(new Usuario(nombre, direccion, id, correo, contrasena));
            System.out.println(negrita+verde+"Usuario registrado como "+reset+oro+negrita + nombre);
        }

    //METODO actualizarUsuario
        public void actualizarUsuario(ArrayList<Usuario> usuarios){
            System.out.println(negrita+azul+"Lista de usuarios registrados:"+reset );
            for (int i = 0; i<usuarios.size(); i++){
                Usuario usuario = usuarios.get(i);
                System.out.println(negrita+azul+i+"."+reset+oro+usuario.getNombre()+reset);
            }
            System.out.println(azul+negrita+"Seleccione el índice del usuario para el cual quiere actualizar los datos: "+reset);
            int indice =
        }



    //TOSTRING
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
