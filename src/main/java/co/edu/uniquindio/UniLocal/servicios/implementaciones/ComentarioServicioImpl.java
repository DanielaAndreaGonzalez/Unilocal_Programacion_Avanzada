package co.edu.uniquindio.UniLocal.servicios.implementaciones;

import co.edu.uniquindio.UniLocal.documentos.Cliente;
import co.edu.uniquindio.UniLocal.documentos.Comentario;
import co.edu.uniquindio.UniLocal.documentos.Negocio;
import co.edu.uniquindio.UniLocal.dto.EmailDTO;
import co.edu.uniquindio.UniLocal.dto.ItemComentarioDTO;
import co.edu.uniquindio.UniLocal.dto.RegistroComentarioDTO;
import co.edu.uniquindio.UniLocal.dto.RespuestaComentarioDTO;
import co.edu.uniquindio.UniLocal.repositorio.ClienteRepo;
import co.edu.uniquindio.UniLocal.repositorio.ComentarioRepo;
import co.edu.uniquindio.UniLocal.repositorio.NegocioRepo;
import co.edu.uniquindio.UniLocal.servicios.interfaces.ComentarioServicio;
import co.edu.uniquindio.UniLocal.servicios.interfaces.EmailServicio;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComentarioServicioImpl implements ComentarioServicio {

    private final ComentarioRepo comentarioRepo;
    private final NegocioRepo negocioRepo;
    private final ClienteRepo clienteRepo;
    private final EmailServicio emailServicio;
    //contruc
    public ComentarioServicioImpl(ComentarioRepo comentarioRepo, NegocioRepo negocioRepo, ClienteRepo clienteRepo, EmailServicio emailServicio) {
        this.comentarioRepo = comentarioRepo;
        this.negocioRepo = negocioRepo;
        this.clienteRepo = clienteRepo;
        this.emailServicio = emailServicio;
    }

    @Override
    public String crearComentario(RegistroComentarioDTO registroComentarioDTO) throws Exception{
        Comentario comentarioGuardado = comentarioRepo.save(crearComentarioDesdeItemComentarioDto(registroComentarioDTO));
        Negocio negocioEncontrado = negocioRepo.findById(registroComentarioDTO.codigoNegocio()).orElse(null);


        if(negocioEncontrado == null)throw new Exception("El negocio no existe");
        if(negocioEncontrado.getComentarios() == null){
            negocioEncontrado.setComentarios(new ArrayList<>());
        }
        negocioEncontrado.getComentarios().add(comentarioGuardado);

        Cliente clienteNegocioEncontrado = clienteRepo.findById(negocioEncontrado.getCodigoCliente()).orElse(null);
        if(clienteNegocioEncontrado == null)throw new RuntimeException("El cliente no existe");
        EmailDTO email = new EmailDTO(
                "Su negocio ha recibido un nuevo comentario!!!",
                registroComentarioDTO.mensaje(),
                clienteNegocioEncontrado.getEmail()
        );
        emailServicio.enviarCorreo(email);

        negocioRepo.save(negocioEncontrado);
        return comentarioGuardado.getCodigo();
    }

    private Comentario crearComentarioDesdeItemComentarioDto(RegistroComentarioDTO registroComentarioDTO) {
        Comentario comentario = new Comentario();
        comentario.setMensaje(registroComentarioDTO.mensaje());
        comentario.setRespuesta(registroComentarioDTO.respuesta());
        comentario.setCodigoCliente(registroComentarioDTO.codigoCliente());
        comentario.setCodigoNegocio(registroComentarioDTO.codigoNegocio());
        comentario.setFecha(registroComentarioDTO.fechaRegistro());
        comentario.setCalificacion(registroComentarioDTO.calificacion());
        return comentario;
    }

    @Override
    public List<ItemComentarioDTO> listarComentariosNegocio(String negocioId) {
        return comentarioRepo.findComentariosByNegocioId(negocioId);
    }

    @Override
    public void responderComentario(RespuestaComentarioDTO respuestaComentarioDTO) {
        Comentario comentarioEncontrado = comentarioRepo.findById(respuestaComentarioDTO.codigoComentario()).orElse(null);

        if(comentarioEncontrado == null)throw new RuntimeException("El comentario no existe");
        comentarioEncontrado.setRespuesta(respuestaComentarioDTO.respuesta());
        Cliente clienteComentario = clienteRepo.findById(comentarioEncontrado.getCodigoCliente()).orElse(null);
        if(clienteComentario == null)throw new RuntimeException("El cliente no existe");
        EmailDTO email = new EmailDTO(
                "Han respondido tu comentario..!!",
                respuestaComentarioDTO.respuesta(),
                clienteComentario.getEmail()
        );
        try {
            emailServicio.enviarCorreo(email);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        comentarioRepo.save(comentarioEncontrado);
    }

    @Override
    public void calcularPromedioCalificaciones() {

    }
}
