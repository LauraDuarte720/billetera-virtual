import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        //INSTANCIAS NECESARIAS
            Banco banco = new Banco("banco 123");
            Billetera billetera = new Billetera("", 0, null);
            boolean salir = false;
            Usuario usermain = null;
            Scanner scanner = new Scanner(System.in);


        //CODIGOS PARA LOS COLORES
            String azul = "\u001B[34m";
            String reset = "\u001B[0m";
            String negrita = "\u001B[1m";
            String rojo = "\u001B[31m";
            String verde = "\u001B[92m";
            String oro = "\u001B[33m";
            String morado = "\u001B[35m";

        //INTERFAZ MENU PRINCIPAL DEL PROGRAMA
            while(!salir && usermain == null){
                System.out.print(banco.toString());
                /*MENU DEL SISTEMA(TOSTRING)
                1: Registrar Usuario
                2: Modificar datos de usuario
                3: Eliminar usuario
                4: Crear billetera virtual
                5: Recargar billetera virtual
                6: Realizar transaccion
                7: Consultar saldo
                8: Consultar transacciones
                9: Obtener porcentaje de gastos e ingresos de un usuario dado el mes
                10: Salir
                 */

                int opcionMenuPrincipal = Banco.leerEnteroValido(scanner, azul+negrita+"Seleccione una opción: "+reset);
                switch (opcionMenuPrincipal) {
                    case 1:
                        System.out.println(azul+negrita+"Has seleccionado registrar un usuario!"+reset);
                        String nombre = Banco.leerTextoValido(scanner,azul+"Ingrese su nombre: "+reset);
                        String direccion = Banco.leerTextoValido(scanner,azul+"Ingrese su dirección: "+reset);
                        String id = Banco.leerTextoValido(scanner,azul+"Ingrese su número de identificación: "+reset);
                        String correo = Banco.leerTextoValido(scanner,azul+"Ingrese su correo electrónico: "+reset);
                        String contrasena = Banco.leerTextoValido(scanner,azul+"Ingrese su contraseña: "+reset);
                        banco.registrarUsuario(nombre, direccion, id, correo, contrasena);
                        break;

                    case 2:
                        banco.actualizarUsuario(banco.getUsuarios());
                        break;

                    case 3:
                        banco.eliminarUsuario(banco.getUsuarios());
                        break;

                    case 4:
                        billetera.crearBilletera();
                        break;

                    case 5:








                }
            }
    }
}

