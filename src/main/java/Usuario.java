public class Usuario {
    private String nombre;
    private String direccion;
    private String id;
    private String correo;
    private String contrasena;


    //CONSTRUCTOR
        public Usuario(String nombre, String direccion, String id, String correo, String contrasena) {
            this.nombre = nombre;
            this.direccion = direccion;
            this.id = id;
            this.correo = correo;
            this.contrasena = contrasena;
        }

    //GETTERS Y SETTERS
        public String getNombre() {
            return nombre;
        }
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
        public String getDireccion() {
            return direccion;
        }
        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getCorreo() {
            return correo;
        }
        public void setCorreo(String correo) {
            this.correo = correo;
        }
        public String getContrasena() {
            return contrasena;
        }
        public void setContrasena(String contrasena) {
            this.contrasena = contrasena;
        }

}
