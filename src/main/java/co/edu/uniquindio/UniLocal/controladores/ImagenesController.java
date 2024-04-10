package co.edu.uniquindio.UniLocal.controladores;


import co.edu.uniquindio.UniLocal.servicios.interfaces.ImagenesServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/imagenes")
@RequiredArgsConstructor
public class ImagenesController {

    private final ImagenesServicio imagenesServicio;



}
