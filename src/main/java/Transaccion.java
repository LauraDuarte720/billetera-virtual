import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaccion {
    private LocalDateTime fecha;
    private CATEGORIA categoria;
    private Billetera destinatario;
    private Billetera origen;
    private float monto;
    private UUID identificador;



    //CONSTRUCTOR
    public Transaccion(LocalDateTime fecha, CATEGORIA categoria, Billetera destinatario, Billetera origen, float monto) {
        this.fecha = fecha;
        this.categoria = categoria;
        this.destinatario = destinatario;
        this.origen=origen;
        this.monto=monto;
        this.identificador=generarIdentificadorDeTransaccion();

    }
    //GETTERS Y SETTERS
        public LocalDateTime getFecha() {
            return fecha;
        }
        public void setFecha(LocalDateTime fecha) {
            this.fecha = fecha;
        }
        public CATEGORIA getCategoria() {
            return categoria;
        }
        public void setCategoria(CATEGORIA categoria) {
            this.categoria = categoria;
        }
        public float getMonto() {
            return monto;
        }
        public void setMonto(float monto) {
            this.monto = monto;
        }
        public UUID getIdentificador() {
            return identificador;
        }
        public void setIdentificador(UUID identificador) {
            this.identificador = identificador;
        }
        public Billetera getDestinatario() {
            return destinatario;
        }

        public void setDestinatario(Billetera destinatario) {
            this.destinatario = destinatario;
        }

        public Billetera getOrigen() {
            return origen;
        }

        public void setOrigen(Billetera origen) {
            this.origen = origen;
        }

        public static UUID generarIdentificadorDeTransaccion(){
            return UUID.randomUUID();
        }

    @Override
    public String toString() {
        return "Transaccion{" +
                "fecha=" + fecha +
                ", categoria=" + categoria +
                ", destinatario=" + destinatario +
                ", origen=" + origen +
                ", monto=" + monto +
                ", identificador=" + identificador +
                '}';
    }
}


