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
        String rojo = "\u001B[31m";


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
        public static int leerEnteroValido(Scanner scanner, String mensaje) {
            String reset = "\u001B[0m";
            String negrita = "\u001B[1m";
            String rojo = "\u001B[31m";
            while (true) {
                try {
                    System.out.print(mensaje);
                    return Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println(rojo+negrita+"Dato inválido. Por favor, ingrese un número entero."+reset);
                }
            }
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
            if (usuarios.isEmpty()) {
                System.out.println(rojo+negrita+"No hay usuarios registrados."+reset);
                return;
            }
            System.out.println(negrita+azul+"Lista de usuarios registrados:"+reset );
            for (int i = 0; i<usuarios.size(); i++){
                Usuario usuario = usuarios.get(i);
                System.out.println(negrita+azul+i+"."+reset+oro+usuario.getNombre()+reset);
            }
            int indice = leerEnteroValido(scanner, azul+negrita+"Seleccione el índice del usuario para el cual quiere actualizar los datos: "+reset);
            if (indice < 0 || indice >= usuarios.size()) {
                System.out.println(rojo+negrita+"Índice inválido."+reset);
                return;
            }
            Usuario usuarioSeleccionado = usuarios.get(indice);

            System.out.println(azul+negrita+"\nOpciones de gestión:"+reset);
            System.out.println(azul+negrita+"1."+reset+oro+ "Cambiar nombre"+reset);
            System.out.println(oro+negrita+"2."+reset+oro+ "Cambiar direccion"+reset);
            System.out.println(oro+negrita+"3."+reset+oro+ "Cambiar numero de identificacion"+reset);
            System.out.println(oro+negrita+"4."+reset+oro+ "Cambiar correo"+reset);
            System.out.println(oro+negrita+"5."+reset+oro+ "Cambiar contraseña"+reset);
            System.out.print(azul+negrita+"Seleccione una opción: "+reset);
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    String nuevoNombre = leerTextoValido(scanner,azul+"Ingrese el nombre nuevo: "+reset);
                    usuarioSeleccionado.setNombre(nuevoNombre);
                    System.out.println(negrita+verde+"Nuevo nombre registrado con éxito!");
                    break;
                case 2:
                    String nuevaDireccion = leerTextoValido(scanner,azul+"Ingrese la direccion nuevo: "+reset);
                    usuarioSeleccionado.setDireccion(nuevaDireccion);
                    System.out.println(negrita+verde+"Nueva direccion registrada con éxito!");
                    break;
                case 3:
                    String nuevoId = leerTextoValido(scanner,azul+"Ingrese el id nuevo: "+reset);
                    usuarioSeleccionado.setId(nuevoId);
                    System.out.println(negrita+verde+"Nuevo numero de identificacion registrado con éxito!");
                    break;
                case 4:
                    String nuevoCorreo = leerTextoValido(scanner,azul+"Ingrese el correo nuevo: "+reset);
                    usuarioSeleccionado.setCorreo(nuevoCorreo);
                    System.out.println(negrita+verde+"Nuevo correo registrado con éxito!");
                    break;
                case 5:
                    String nuevaContrasena = leerTextoValido(scanner, azul+"Ingrese la nueva contraseña: "+reset);
                    usuarioSeleccionado.setContrasena(nuevaContrasena);
                    System.out.println(negrita+verde+"Nueva contraseña registrada con éxito!");
                    break;
            }
        }


    //METODO eliminarUsuario
        public void eliminarUsuario(ArrayList<Usuario> usuarios){
            if (usuarios.isEmpty()) {
                System.out.println(rojo+negrita+"No hay usuarios registrados."+reset);
                return;
            }
            System.out.println(negrita+azul+"Lista de usuarios registrados:"+reset );
            for (int i = 0; i<usuarios.size(); i++){
                Usuario usuario = usuarios.get(i);
                System.out.println(negrita+azul+i+"."+reset+oro+usuario.getNombre()+reset);
            }
            int indice = leerEnteroValido(scanner, azul+negrita+"Seleccione el índice del usuario para eliminar: "+reset);
            if (indice < 0 || indice >= usuarios.size()) {
                System.out.println(rojo+negrita+"Índice inválido."+reset);
                return;
            }
            Usuario usuarioSeleccionado = usuarios.get(indice);
            usuarios.remove(usuarioSeleccionado);
            System.out.println(verde+negrita+"Usuario eliminado con éxito!."+reset);
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
