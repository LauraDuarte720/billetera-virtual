import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class BancoTest {

    @Test

    public void consultarSaldoTest() {

        Banco banco = new Banco("banco 123");

        Usuario usuario1 = new Usuario("Juan Pérez", "Calle 123", "1001", "juan@example.com", "123");
        Usuario usuario2 = new Usuario("María Gómez", "Avenida 456", "1002", "maria@example.com", "securepass");
        Usuario usuario3 = new Usuario("Carlos López", "Carrera 789", "1003", "carlos@example.com", "mypassword");

        Billetera billetera1 = new Billetera("123456789", 5000.0, usuario1);
        Billetera billetera2 = new Billetera("987654321", 10000.0, usuario2);
        Billetera billetera3 = new Billetera("456789123", 7500.0, usuario3);


        Transaccion transaccion1 = new Transaccion(LocalDateTime.now(), CATEGORIA.VIAJES, billetera3, billetera1, 500);
        Transaccion transaccion2 = new Transaccion(LocalDateTime.now(), CATEGORIA.FACTURA, billetera3, billetera1, 1200);
        Transaccion transaccion3 = new Transaccion(LocalDateTime.now(), CATEGORIA.ROPA, billetera2, billetera1, 300);

        banco.agregarUsuarioABanco(usuario1);
        banco.agregarUsuarioABanco(usuario2);
        banco.agregarUsuarioABanco(usuario3);

        banco.agregarBilleteraABanco(billetera1);
        banco.agregarBilleteraABanco(billetera2);
        banco.agregarBilleteraABanco(billetera3);

        banco.agregarTransaccionABanco(transaccion1);
        banco.agregarTransaccionABanco(transaccion2);
        banco.agregarTransaccionABanco(transaccion3);

        billetera1.agregarTransaccion(transaccion1);
        billetera1.agregarTransaccion(transaccion2);
        billetera1.agregarTransaccion(transaccion3);

        assertDoesNotThrow(()->{
            ArrayList<String> listaSaldoTransacciones=banco.consultarSaldo("1001","123");

        });
    }


}
