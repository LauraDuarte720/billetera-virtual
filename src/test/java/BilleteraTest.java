import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class BilleteraTest {

    @Test

    public void realizarTransaccion(){
        Banco banco = new Banco("banco 123");

        Billetera billetera1 = new Billetera("123456789", 5000.0, "Juan Pérez");
        Billetera billetera2 = new Billetera("987654321", 10000.0, "María Gómez");
        Billetera billetera3 = new Billetera("456789123", 7500.0, "Carlos Rodríguez");

        banco.agregarBilleteraABanco(billetera1);
        banco.agregarBilleteraABanco(billetera2);
        banco.agregarBilleteraABanco(billetera3);

        assertDoesNotThrow(()->{
            Transaccion transaccionRealizada=billetera1.realizarTransaccion(banco,300,CATEGORIA.VIAJES,billetera1,billetera2);

            Transaccion transaccionPrueba = new Transaccion(
                    transaccionRealizada.getFecha(),  // Usamos la misma fecha de la transacción real
                    CATEGORIA.VIAJES,
                    billetera2,
                    billetera1,
                    300
            );

            assertEquals(transaccionPrueba.getCategoria(), transaccionRealizada.getCategoria());
            assertEquals(transaccionPrueba.getOrigen(), transaccionRealizada.getOrigen());
            assertEquals(transaccionPrueba.getDestinatario(), transaccionRealizada.getDestinatario());
            assertEquals(transaccionPrueba.getMonto(), transaccionRealizada.getMonto());
        });



    }



}
