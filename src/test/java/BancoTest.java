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
}
