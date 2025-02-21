import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Transaccion {
    private LocalDateTime fecha;
    private CATEGORIA categoria;
    private Billetera destinatario;
    private Billetera origen;
    private float monto;
    private String identificador;




    //CONSTRUCTOR
    public Transaccion(LocalDateTime fecha, CATEGORIA categoria, Billetera destinatario, Billetera origen, float monto, String identificador) {
        this.fecha = fecha;
        this.categoria = categoria;
        this.destinatario = destinatario;
        this.origen=origen;
        this.monto=monto;
        this.identificador=identificador;
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
        public String getIdentificador() {
            return identificador;
        }
        public void setIdentificador(String identificador) {
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


    //
}


