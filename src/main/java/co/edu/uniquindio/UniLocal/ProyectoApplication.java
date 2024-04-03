package co.edu.uniquindio.UniLocal;

import co.edu.uniquindio.UniLocal.dto.EmailDTO;
import co.edu.uniquindio.UniLocal.servicios.implementaciones.EmailServicioImpl;
import co.edu.uniquindio.UniLocal.servicios.interfaces.emailServicio;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@SpringBootApplication
public class ProyectoApplication {

public static void main(String[] args) {

    //SpringApplication.run(ProyectoApplication.class, args);

    ConfigurableApplicationContext context = SpringApplication.run(ProyectoApplication.class, args);

    // Obtenemos la instancia de EmailServicioImpl desde el contexto de Spring
    EmailServicioImpl emailServicio = context.getBean(EmailServicioImpl.class);

    // Ahora puedes usar el servicio de correo electrónico
    EmailDTO emailDTO = new EmailDTO("Asunto de prueba", "Hola, este es un mensaje de prueba", "destinatario@example.com");
    try {
        emailServicio.enviarCorreo(emailDTO);
    } catch (Exception e) {
        e.printStackTrace();
    }
}



}