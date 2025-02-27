import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class BancoTest {


    //TESTS DE REGISTRAR USUARIO
        @Test
        void regUsuarioTest() throws Exception {
            Banco banco = new Banco("Banco123");
            banco.regUsuario("pacho", "asdsad", "123456", "pacho@mggail.com", "contraseña2123");

            assertEquals(1, banco.getUsuarios().size());
            assertEquals("pacho", banco.getUsuarios().get(0).getNombre());
        }

        @Test
        void correoValidoTest() throws Exception {
            Banco banco = new Banco("Banco123");
            Exception exception = assertThrows(Exception.class, () ->
                    banco.regUsuario("hermenegildo", "Calle 789", "67890", "asdgmail.com", "125125")
            );
            assertEquals("El correo electrónico no es válido.", exception.getMessage());
        }

        @Test
        void testRegistrarUsuarioConContrasenaCorta() {
            Banco banco = new Banco("Banco123");
            Exception exception = assertThrows(Exception.class, () ->
                    banco.regUsuario("pahco", "Avenida 10", "54321", "pacho@gmail.com", "123")
            );
            assertEquals("La contraseña debe tener al menos 6 caracteres.", exception.getMessage());
        }

    //TEST ACTUALIZAR USUARIO

        @Test
        void testActualizarUsuarioExitoso() throws Exception {
            Banco banco = new Banco("Banco123");

            Usuario usuario=new Usuario("Pedro","Calle 15","55555","pacho@gmail.com","123");
            banco.agregarUsuarioABanco(usuario);
            banco.actUsuario("55555", "pachito", "Calle 99", "66666", "pedro@newmail.com", "nuevaClave");

            assertEquals("pachito", usuario.getNombre());
            assertEquals("Calle 99", usuario.getDireccion());
            assertEquals("66666", usuario.getId());
            assertEquals("pedro@newmail.com", usuario.getCorreo());
            assertEquals("nuevaClave", usuario.getContrasena());
        }


    //TEST ELIMINAR USUARIO
        @Test
        void testEliminarUsuarioExitoso() throws Exception {
            Banco banco = new Banco("Banco123");
            banco.regUsuario("pacho123", "Calle 50", "98765", "pacho@gmail.com", "superclave");
            banco.elimUsuario("98765");
            assertEquals(0, banco.getUsuarios().size());
        }

    //TEST CONSULTAR SALDO
    @Test
    public void consultarSaldoTest() {

        Banco banco = new Banco("banco 123");

        Usuario usuario1 = new Usuario("Juan Pérez", "Calle 123", "1001", "juan@example.com", "123");
        Usuario usuario2 = new Usuario("María Gómez", "Avenida 456", "1002", "maria@example.com", "securepass");
        Usuario usuario3 = new Usuario("Carlos López", "Carrera 789", "1003", "carlos@example.com", "mypassword");

        Billetera billetera1 = new Billetera( 5000.0, usuario1);
        Billetera billetera2 = new Billetera( 10000.0, usuario2);
        Billetera billetera3 = new Billetera( 7500.0, usuario3);


        Transaccion transaccion1 = new Transaccion(LocalDateTime.now(), CATEGORIA.VIAJES, billetera3, billetera1, 500);
        Transaccion transaccion2 = new Transaccion(LocalDateTime.now(), CATEGORIA.FACTURA, billetera3, billetera1, 1200);
        Transaccion transaccion3 = new Transaccion(LocalDateTime.now(), CATEGORIA.ROPA, billetera2, billetera1, 300);

        banco.agregarUsuarioABanco(usuario1);
        banco.agregarUsuarioABanco(usuario2);
        banco.agregarUsuarioABanco(usuario3);

        banco.agregarBilleteraABanco(billetera1);
        banco.agregarBilleteraABanco(billetera2);
        banco.agregarBilleteraABanco(billetera3);


        billetera1.agregarTransaccion(transaccion1);
        billetera1.agregarTransaccion(transaccion2);
        billetera1.agregarTransaccion(transaccion3);

        assertDoesNotThrow(()->{
            SaldoTransacciones saldoTransacciones = banco.consultarSaldo("1001","123");
            assertEquals(3, saldoTransacciones.getTransaccions().size());

        });
    }


    @Test
    public void testCrearBilleteraExitoso() throws Exception {
        Banco banco = new Banco("Banco123");

        banco.regUsuario("Juan Perez", "Calle 123", "12345",
                "juan@test.com", "password123");

        Billetera billetera = banco.crearBilletera("12345", "Juan Perez");

        assertEquals("Juan Perez", billetera.getPropietario().getNombre());
        assertEquals(0.0, billetera.getSaldo());
        assertEquals(10, billetera.getNumTarjeta().length());
        assertTrue(billetera.getNumTarjeta().matches("\\d{10}"));
        assertEquals(1, banco.getBilleteras().size());
    }

    @Test
    public void testNumerosTarjetaUnicos() throws Exception {
        Banco banco = new Banco("Banco123");

        banco.regUsuario("Juan Perez", "Calle 123", "12345",
                "juan@test.com", "password123");
        int numeroBilleteras = 10; // Testing with 10 wallets (adjustable)
        Billetera[] billeteras = new Billetera[numeroBilleteras];

        for (int i = 0; i < numeroBilleteras; i++) {
            billeteras[i] = banco.crearBilletera("12345", "Juan Perez");

            assertEquals(10, billeteras[i].getNumTarjeta().length());
            assertTrue(billeteras[i].getNumTarjeta().matches("\\d{10}"));
        }

        for (int i = 0; i < numeroBilleteras; i++) {
            String numeroActual = billeteras[i].getNumTarjeta();
            for (int j = 0; j < numeroBilleteras; j++) {
                if (i != j) {
                    String numeroComparar = billeteras[j].getNumTarjeta();
                    if (numeroActual.equals(numeroComparar)) {
                        fail("Se encontró un número de tarjeta duplicado: " + numeroActual +
                                " en las posiciones " + i + " y " + j);
                    }
                }
            }
        }

        assertEquals(numeroBilleteras, banco.getBilleteras().size());

        for (int i = 0; i < banco.getBilleteras().size(); i++) {
            String numeroActual = banco.getBilleteras().get(i).getNumTarjeta();
            for (int j = 0; j < banco.getBilleteras().size(); j++) {
                if (i != j) {
                    String numeroComparar = banco.getBilleteras().get(j).getNumTarjeta();
                    if (numeroActual.equals(numeroComparar)) {
                        fail("Se encontró un duplicado en la lista del banco: " + numeroActual +
                                " en las posiciones " + i + " y " + j);
                    }
                }
            }
        }

    }




}
