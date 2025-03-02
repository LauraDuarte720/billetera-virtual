import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Banco {

    //ATRIBUTOS
    private String nombre;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Billetera> billeteras;

    //SCANNER PARA EL APP
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

    public ArrayList<Billetera> getBilleteras() {
        return billeteras;
    }

    public void setBilleteras(ArrayList<Billetera> billeteras) {
        this.billeteras = billeteras;
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

    public void actUsuario(String idViejo, String nuevoNombre, String nuevaDireccion, String nuevoId, String nuevoCorreo, String nuevaContrasena) throws Exception {

        Usuario usuarioConsultar= null;
            for(Usuario usuario:usuarios){
                if(usuario.getId().equals(idViejo)){
                    usuarioConsultar= usuario;
                    break;
                }
            }

        if (usuarioConsultar == null) {
            throw new Exception("El usuario no existe");
        }
        if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
            usuarioConsultar.setNombre(nuevoNombre);
        }
        if (nuevaDireccion != null && !nuevaDireccion.trim().isEmpty()) {
            usuarioConsultar.setDireccion(nuevaDireccion);
        }
        if (nuevoId != null && nuevoId.matches("\\d+")) {
            usuarioConsultar.setId(nuevoId);
        }
        if (nuevoCorreo != null && nuevoCorreo.contains("@")) {
            usuarioConsultar.setCorreo(nuevoCorreo);
        }
        if (nuevaContrasena != null && nuevaContrasena.length() >= 6) {
            usuarioConsultar.setContrasena(nuevaContrasena);
        }
    }

    public void elimUsuario(String idEliminar) throws Exception {
        Usuario usuarioEliminar = null;
        for(Usuario usuario: usuarios){
            if(usuario.getId().equals(idEliminar)){
                usuarioEliminar = usuario;
            }
        }
        usuarios.remove(usuarioEliminar);
    }

    //PARTE DEL METODO PARA REALIZAR LA TRANSACCION
    public Transaccion realizarTransaccion(float saldoTransferir, CATEGORIA categoria,String numBilleteraOrigen, String numBilleteraDestino) throws Exception{
        boolean origenValido=false;
        boolean destinoValido=false;
        boolean transaccionValida=false;
        Billetera billeteraOrigen = null;
        Billetera billeteraDestino = null;

        while (!transaccionValida){
            for (Billetera billetera: billeteras){
                if( billetera.getNumTarjeta().equals(numBilleteraOrigen)){
                    origenValido=true;
                    billeteraOrigen = billetera;
                }else if (billetera.getNumTarjeta().equals(numBilleteraDestino)){
                    destinoValido=true;
                    billeteraDestino = billetera;
                }
            }
            if (origenValido && destinoValido) {
                transaccionValida = true;
            }else {
                throw new Exception("La billetera destino o billetera origen no estan registradas en el banco");
            }
        }

        if (saldoTransferir+Billetera.COSTO>billeteraOrigen.getSaldo()){
            throw  new Exception("No hay saldo suficiente en la billetera ");
        }else if(saldoTransferir<=0) {
            throw new Exception("No se permite transferir un saldo menor a cero");
        }

        return billeteraOrigen.realizarTransaccion(saldoTransferir, categoria, billeteraOrigen, billeteraDestino );
    }


    //METODOS PARA AGREGAR LAS BILLETERAS, USUARIOS
    public void agregarBilleteraABanco(Billetera billetera) {
        billeteras.add(billetera);
    }

    public void agregarUsuarioABanco(Usuario usuario) {
        usuarios.add(usuario);
    }


    //METODO PARA CONSULTAR SALDO Y TRANSACCIONES
    public SaldoTransacciones consultarSaldo(String cedula, String contrasena) throws Exception {
        SaldoTransacciones saldoTransacciones = new SaldoTransacciones();
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
                saldoTransacciones.setTransaccions(billetera.getTransacciones());
                saldoTransacciones.setSaldo(billetera.getSaldo());
            }
        }

        return saldoTransacciones;
    }

    //METOD PARA CREAR LA BILLETERA

        public Billetera crearBilletera(String idPropietario, String nombrePropietario) throws Exception {
            if (idPropietario == null || idPropietario.trim().isEmpty()) {
                throw new Exception("El ID del propietario no puede estar vacío");
            }
            if (nombrePropietario == null || nombrePropietario.trim().isEmpty()) {
                throw new Exception("El nombre del propietario no puede estar vacío");
            }

            Usuario propietarioEncontrado = null;
            for (Usuario usuario : usuarios) {
                if (usuario.getNombre().equalsIgnoreCase(nombrePropietario) &&
                        usuario.getId().equals(idPropietario)) {
                    propietarioEncontrado = usuario;
                    break;
                }
            }
            if (propietarioEncontrado == null) {
                throw new Exception("Usuario/ID no encontrado en nuestra base de datos");
            }

            Billetera billeteraNueva = new Billetera(0, propietarioEncontrado);
            String numeroUnico;
            boolean numeroDuplicado;

            do {
                numeroUnico = billeteraNueva.crearNumeroUnicoBilletera();
                numeroDuplicado = false;

                for (Billetera billetera : billeteras) {
                    if (billetera.getNumTarjeta().equals(numeroUnico)) {
                        numeroDuplicado = true;
                        break;
                    }
                }

                if (numeroDuplicado) {
                    throw new Exception("Se generó un número de tarjeta duplicado. Intentando generar uno nuevo...");
                }
            } while (numeroDuplicado);

            billeteraNueva.setNumTarjeta(numeroUnico);
            billeteras.add(billeteraNueva);
            return billeteraNueva;
        }



    //METODOS PARA LA OPTIMIZACION DEL CODIGO (SI SE HACEN CON APP)
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
    public void crearBilletera(String idPropietario) throws Exception {
        Billetera billeteraNueva = new Billetera( 0, null);
        String propietarioNuevo = Banco.leerTextoValido(scanner, azul + negrita + "Ingrese su nombre: " + reset);
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




}
