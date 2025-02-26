import java.util.ArrayList;
import java.util.Random;
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
    String verde = "\u001B[92m";
    String oro = "\u001B[33m";
    String azul = "\u001B[34m";
    String rojo = "\u001B[31m";


    //CONSTRUCTOR
    public Banco(String nombre) {
        this.usuarios = new ArrayList<>();
        this.nombre = nombre;
        this.billeteras = new ArrayList<>();
        this.transacciones = new ArrayList<>();
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
    public static String leerTextoValido(Scanner scanner, String mensaje) throws Exception {
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
                throw new Exception(rojo + negrita + "Dato inválido. Por favor, ingrese un número entero." + reset);
            }
        }
        return texto;
    }

    public static int leerEnteroValido(Scanner scanner, String mensaje) throws Exception {
        String reset = "\u001B[0m";
        String negrita = "\u001B[1m";
        String rojo = "\u001B[31m";
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                throw new Exception(rojo + negrita + "Dato inválido. Por favor, ingrese un número entero." + reset);
            }
        }
    }


    //METODO registrarUsuario
    public void registrarUsuario(String nombre, String direccion, String id, String correo, String contrasena) throws Exception {
        boolean idValido = false;
        while (!idValido) {
            if (!id.matches("//d+")) {
                throw new Exception(rojo + negrita + "Ingrese un ID válido." + reset);
            } else {
                idValido = true;
            }
        }
        boolean correoValido = false;
        while (!correoValido) {
            if (!correo.contains("@")) {
                throw new Exception(rojo + negrita + "Ingrese un correo válido" + reset);
            } else {
                correoValido = true;
            }
        }
        usuarios.add(new Usuario(nombre, direccion, id, correo, contrasena));
        System.out.println(negrita + verde + "Usuario registrado como " + reset + oro + negrita + nombre);
    }

    //METODO actualizarUsuario
    public void actualizarUsuario(ArrayList<Usuario> usuarios) throws Exception {
        if (usuarios.isEmpty()) {
            System.out.println(rojo + negrita + "No hay usuarios registrados." + reset);
            return;
        }
        System.out.println(negrita + azul + "Lista de usuarios registrados:" + reset);
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            System.out.println(negrita + azul + i + "." + reset + oro + usuario.getNombre() + reset);
        }
        int indice = leerEnteroValido(scanner, azul + negrita + "Seleccione el índice del usuario para el cual quiere actualizar los datos: " + reset);
        if (indice < 0 || indice >= usuarios.size()) {
            System.out.println(rojo + negrita + "Índice inválido." + reset);
            return;
        }
        Usuario usuarioSeleccionado = usuarios.get(indice);

        System.out.println(azul + negrita + "\nOpciones de gestión:" + reset);
        System.out.println(azul + negrita + "1." + reset + oro + "Cambiar nombre" + reset);
        System.out.println(azul + negrita + "2." + reset + oro + "Cambiar direccion" + reset);
        System.out.println(azul + negrita + "3." + reset + oro + "Cambiar numero de identificacion" + reset);
        System.out.println(azul + negrita + "4." + reset + oro + "Cambiar correo" + reset);
        System.out.println(azul + negrita + "5." + reset + oro + "Cambiar contraseña" + reset);
        System.out.print(azul + negrita + "Seleccione una opción: " + reset);
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                String nuevoNombre = leerTextoValido(scanner, azul + "Ingrese el nombre nuevo: " + reset);
                usuarioSeleccionado.setNombre(nuevoNombre);
                System.out.println(negrita + verde + "Nuevo nombre registrado con éxito!");
                break;
            case 2:
                String nuevaDireccion = leerTextoValido(scanner, azul + "Ingrese la direccion nuevo: " + reset);
                usuarioSeleccionado.setDireccion(nuevaDireccion);
                System.out.println(negrita + verde + "Nueva direccion registrada con éxito!");
                break;
            case 3:
                String nuevoId = leerTextoValido(scanner, azul + "Ingrese el id nuevo: " + reset);
                usuarioSeleccionado.setId(nuevoId);
                System.out.println(negrita + verde + "Nuevo numero de identificacion registrado con éxito!");
                break;
            case 4:
                String nuevoCorreo = leerTextoValido(scanner, azul + "Ingrese el correo nuevo: " + reset);
                usuarioSeleccionado.setCorreo(nuevoCorreo);
                System.out.println(negrita + verde + "Nuevo correo registrado con éxito!");
                break;
            case 5:
                String nuevaContrasena = leerTextoValido(scanner, azul + "Ingrese la nueva contraseña: " + reset);
                usuarioSeleccionado.setContrasena(nuevaContrasena);
                System.out.println(negrita + verde + "Nueva contraseña registrada con éxito!");
                break;
        }
    }


    //METODO eliminarUsuario
    public void eliminarUsuario(ArrayList<Usuario> usuarios) throws Exception {
        if (usuarios.isEmpty()) {
            System.out.println(rojo + negrita + "No hay usuarios registrados." + reset);
            return;
        }
        System.out.println(negrita + azul + "Lista de usuarios registrados:" + reset);
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            System.out.println(negrita + azul + i + "." + reset + oro + usuario.getNombre() + reset);
        }
        int indice = leerEnteroValido(scanner, azul + negrita + "Seleccione el índice del usuario para eliminar: " + reset);
        if (indice < 0 || indice >= usuarios.size()) {
            System.out.println(rojo + negrita + "Índice inválido." + reset);
            return;
        }
        Usuario usuarioSeleccionado = usuarios.get(indice);
        usuarios.remove(usuarioSeleccionado);
        System.out.println(verde + negrita + "Usuario eliminado con éxito!." + reset);
    }


    //TOSTRING
    @Override
    public String toString() {
        return azul + negrita + "Bienvenido al banco" + nombre + ", Seleccione el indice de la accion requerida\n" + azul + negrita + "1:" + reset + oro + negrita + "Registrar Usuario" + reset + azul + negrita +
                "\n2:" + reset + oro + negrita + "Actualizar datos de un usuario\n" + azul + negrita + "3:" + reset + oro + negrita + "Eliminar un usuario\n" + azul + negrita + "4:" + reset + oro + negrita + "Crear billetera virtual\n" + azul + negrita +
                "5:" + reset + oro + negrita + "Recargar billetera\n" + azul + negrita + "6:" + reset + oro + negrita + "Realizar transaccion\n" + reset + azul + negrita +
                "7:" + reset + oro + negrita + "Consultar saldo\n" + azul + negrita + "8:" + reset + oro + negrita + "Consultar transacciones\n" + azul + negrita +
                "9:" + reset + oro + negrita + "Obtener porcentaje de gastos e ingresos de un usuario dado el mes\n" + azul + negrita + "10:" + reset + oro + negrita + "Salir\n";
    }


    //METODO crearBilletera
    public void crearBilletera() throws Exception {
        Billetera billeteraNueva = new Billetera("", 0, null);
        String propietarioNuevo = Banco.leerTextoValido(scanner, azul + negrita + "Ingrese su nombre: " + reset);
        String idPropietario = Banco.leerTextoValido(scanner, azul + negrita + "Ingrese su id: " + reset);
        StringBuilder propietarioAsignado = new StringBuilder();
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equalsIgnoreCase(propietarioNuevo) && usuario.getId().equals(idPropietario)) {
                propietarioAsignado.append(usuario.getNombre());
                break;
            } else {
                System.out.println(rojo + negrita + "Usuario/ID No encontrado en nuestra base de datos." + reset);
                return;
            }
        }
        //billeteraNueva.setPropietario(propietarioAsignado.toString());
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(10));
        }
        billeteraNueva.setNumTarjeta(sb.toString());

        billeteras.add(billeteraNueva);

        System.out.println(verde + negrita + "Billetera virtual creada con éxito!");
        System.out.println(verde + negrita + "Propietario: " + billeteraNueva.getPropietario());
        System.out.println(verde + negrita + "Numero de tarjeta: " + billeteraNueva.getNumTarjeta());
        System.out.println(verde + negrita + "Saldo: " + billeteraNueva.getSaldo());
    }

    //METODO PARA AGREGAR LAS BILLETERAS
    public void agregarBilleteraABanco(Billetera billetera) {
        billeteras.add(billetera);
    }

    public void agregarUsuarioABanco(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void agregarTransaccionABanco(Transaccion transaccion) {
        transacciones.add(transaccion);
    }

    public ArrayList<String> consultarSaldo(String cedula, String contrasena) throws Exception {
        ArrayList<String> saldoTransacciones = new ArrayList<>();
        Usuario usuarioConsultar = null;

        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(cedula) && usuario.getContrasena().equals(contrasena)) {
                usuarioConsultar = usuario;
                break;
            }
        }

        if (usuarioConsultar == null) {
            throw new Exception("El usuario no es valido");
        }

        boolean tieneBilletera = false;
        for (Billetera billetera : billeteras) {
            if (billetera.getPropietario().equals(usuarioConsultar)) {
                tieneBilletera = true;
                saldoTransacciones.add(billetera.getTransacciones().toString());
                saldoTransacciones.add(String.valueOf(billetera.getSaldo()));
            }
        }

        if (!tieneBilletera) {
            saldoTransacciones.add("El usuario no tiene billetera asociada.");
        }

        return saldoTransacciones;
    }


    //VERSIONES NO INTERACTIVAS PARA LOS METODOS DE ELIMINAR,ACTUALIZAR,REGISTRAR USUARIO
        public void regUsuario(String nombre, String direccion, String id, String correo, String contrasena) throws Exception {
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("El nombre no puede estar vacío.");
            }
            if (direccion == null || direccion.trim().isEmpty()) {
                throw new Exception("La dirección no puede estar vacía.");
            }
            if (id == null || !id.matches("\\d+")) {
                throw new Exception("El ID debe ser un número válido.");
            }
            if (correo == null || !correo.contains("@")) {
                throw new Exception("El correo electrónico no es válido.");
            }
            if (contrasena == null || contrasena.length() < 6) {
                throw new Exception("La contraseña debe tener al menos 6 caracteres.");
            }

            usuarios.add(new Usuario(nombre, direccion, id, correo, contrasena));
        }

    public void actUsuario(int indice, String nuevoNombre, String nuevaDireccion, String nuevoId, String nuevoCorreo, String nuevaContrasena) throws Exception {
        if (indice < 0 || indice >= usuarios.size()) {
            throw new Exception("Índice de usuario inválido.");
        }

        Usuario usuario = usuarios.get(indice);

        if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
            usuario.setNombre(nuevoNombre);
        }
        if (nuevaDireccion != null && !nuevaDireccion.trim().isEmpty()) {
            usuario.setDireccion(nuevaDireccion);
        }
        if (nuevoId != null && nuevoId.matches("\\d+")) {
            usuario.setId(nuevoId);
        }
        if (nuevoCorreo != null && nuevoCorreo.contains("@")) {
            usuario.setCorreo(nuevoCorreo);
        }
        if (nuevaContrasena != null && nuevaContrasena.length() >= 6) {
            usuario.setContrasena(nuevaContrasena);
        }
    }

    public void elimUsuario(int indice) throws Exception {
        if (indice < 0 || indice >= usuarios.size()) {
            throw new Exception("Índice de usuario inválido.");
        }

        usuarios.remove(indice);
    }
}
