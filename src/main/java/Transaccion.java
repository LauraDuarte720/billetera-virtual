import java.time.LocalDate;

public class Transaccion {
    private LocalDate fecha;
    private String categoria;
    private String usuarioDestinatario;



    //CONSTRUCTOR
    public Transaccion(LocalDate fecha, String categoria, String usuarioDestinatario) {
        this.fecha = fecha;
        this.categoria = categoria;
        this.usuarioDestinatario = usuarioDestinatario;
    }
    //GETTERS Y SETTERS
        public LocalDate getFecha() {
            return fecha;
        }
        public void setFecha(LocalDate fecha) {
            this.fecha = fecha;
        }
        public String getCategoria() {
            return categoria;
        }
        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }
        public String getUsuarioDestinatario() {
            return usuarioDestinatario;
        }

        public void setUsuarioDestinatario(String usuarioDestinatario) {
            this.usuarioDestinatario = usuarioDestinatario;
        }

    //
}
