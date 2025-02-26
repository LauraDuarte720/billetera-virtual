import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class BilleteraTest {

    @Test

    public void realizarTransaccionTest(){
        Banco banco = new Banco("banco 123");

        Billetera billetera1 = new Billetera("123456789", 5000.0, null);
        Billetera billetera2 = new Billetera("987654321", 10000.0, null);
        Billetera billetera3 = new Billetera("456789123", 7500.0, null);

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

    @Test

    public void cambioSaldoValidoTest(){

        Banco banco = new Banco("banco 123");

        Billetera billetera1 = new Billetera("123456789", 5000.0, null);
        Billetera billetera2 = new Billetera("987654321", 10000.0, null);
        Billetera billetera3 = new Billetera("456789123", 7500.0, null);

        banco.agregarBilleteraABanco(billetera1);
        banco.agregarBilleteraABanco(billetera2);
        banco.agregarBilleteraABanco(billetera3);

        assertDoesNotThrow(()->{
        Transaccion transaccionRealizada=billetera1.realizarTransaccion(banco,3000,CATEGORIA.VIAJES,billetera1,billetera2);

        });

        assertEquals(13000,billetera2.getSaldo());
        assertEquals(1800,billetera1.getSaldo());

    }

    @Test
    public void recargarBilleteraTest(){

        Billetera billetera1 = new Billetera("123456789", 5000.0, null);

        assertDoesNotThrow(()->{
            billetera1.recargarBilletera(5000);
        });

        assertEquals(10000,billetera1.getSaldo());
        }

    @Test
    public void consultarTransaccionesDadoTiempoTest(){
        Banco banco = new Banco("banco 123");

        Billetera billetera1 = new Billetera("123456789", 5000.0, null);
        Billetera billetera2 = new Billetera("987654321", 10000.0, null);
        Billetera billetera3 = new Billetera("456789123", 7500.0, null);

        banco.agregarBilleteraABanco(billetera1);
        banco.agregarBilleteraABanco(billetera2);
        banco.agregarBilleteraABanco(billetera3);

        assertDoesNotThrow(()->{
            Transaccion transaccionRealizada=billetera1.realizarTransaccion(banco,300,CATEGORIA.VIAJES,billetera1,billetera2);
            assertEquals(billetera1.consultarTransaccionesTiempo(LocalDateTime.of(2025,1,24,12,0,0), LocalDateTime.of(2025,3,24,12,0,0)).size(), 1);
        });
    }

    @Test
    public void porcentajeGastosTest(){
        Banco banco = new Banco("banco 123");

        Billetera billetera1 = new Billetera("123456789", 5000.0, null);
        Billetera billetera2 = new Billetera("987654321", 10000.0, null);
        Billetera billetera3 = new Billetera("456789123", 7500.0, null);

        banco.agregarBilleteraABanco(billetera1);
        banco.agregarBilleteraABanco(billetera2);
        banco.agregarBilleteraABanco(billetera3);


        assertDoesNotThrow(()->{
            billetera1.realizarTransaccion(banco,500, CATEGORIA.ROPA, billetera1, billetera2);
            billetera1.realizarTransaccion(banco,1000, CATEGORIA.ROPA, billetera1, billetera3);
            double porcentajeEsperado = ((1900.0/5000.0)*100.0);
            assertEquals(porcentajeEsperado, billetera1.porcentajeGastos(billetera1, LocalDateTime.of(2025,1,1,12,12,12), LocalDateTime.of(2025,4,1,12,12,12), CATEGORIA.ROPA));
        });

    }

    @Test
    public void calcularPorcentajeGastosIngresosTotalesTest() throws Exception{
        Billetera billetera1 = new Billetera("123456789", 5000.0, null);
        Billetera billetera2 = new Billetera("987654321", 10000.0, null);

        Transaccion transaccion1 = new Transaccion(LocalDateTime.now(), CATEGORIA.VIAJES, billetera2, billetera1, 500);
        Transaccion transaccion2 = new Transaccion(LocalDateTime.now(), CATEGORIA.FACTURA, billetera2, billetera1, 1200);
        Transaccion transaccion3 = new Transaccion(LocalDateTime.now(), CATEGORIA.ROPA, billetera1, billetera2, 300);

        billetera1.agregarTransaccion(transaccion1);
        billetera1.agregarTransaccion(transaccion2);
        billetera1.agregarTransaccion(transaccion3);
        billetera2.agregarTransaccion(transaccion1);
        billetera2.agregarTransaccion(transaccion2);
        billetera2.agregarTransaccion(transaccion3);

        double valorEsperadoGastos = (1700.0/2000.0)*100.0;
        assertEquals(valorEsperadoGastos, billetera1.calcularPorcentajeGastosIngresosTotales(LocalDateTime.of(2000, 12, 8, 1,2,2), LocalDateTime.of(2030, 12, 8, 1,2,2), true));

        double valorEsperadoIngresos = (300/2000.0)*100.0;
        assertEquals(valorEsperadoIngresos, billetera1.calcularPorcentajeGastosIngresosTotales(LocalDateTime.of(2000, 12, 8, 1,2,2), LocalDateTime.of(2030, 12, 8, 1,2,2), false));

    }

    @Test
    public void calcularPorcentajeGastosIngresosTotalesErrorTest() throws Exception{
        Billetera billetera1 = new Billetera("123456789", 5000.0, null);

        assertThrows(Throwable.class, () -> billetera1.calcularPorcentajeGastosIngresosTotales(LocalDateTime.of(2030, 12, 8, 1,2,2), LocalDateTime.of(2000, 12, 8, 1,2,2), false));

    }
}






