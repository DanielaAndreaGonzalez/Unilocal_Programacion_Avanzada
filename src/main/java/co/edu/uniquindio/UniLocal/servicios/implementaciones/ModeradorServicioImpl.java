package co.edu.uniquindio.UniLocal.servicios.implementaciones;

import co.edu.uniquindio.UniLocal.documentos.Cliente;
import co.edu.uniquindio.UniLocal.documentos.HistorialModeracion;
import co.edu.uniquindio.UniLocal.documentos.Moderador;
import co.edu.uniquindio.UniLocal.documentos.Negocio;
import co.edu.uniquindio.UniLocal.dto.*;
import co.edu.uniquindio.UniLocal.enums.EstadoNegocio;
import co.edu.uniquindio.UniLocal.enums.EstadoRegistro;
import co.edu.uniquindio.UniLocal.excepciones.AutorizacionException;
import co.edu.uniquindio.UniLocal.excepciones.ResourceNotFoundException;
import co.edu.uniquindio.UniLocal.repositorio.ClienteRepo;
import co.edu.uniquindio.UniLocal.repositorio.HistorialModeracionRepo;
import co.edu.uniquindio.UniLocal.repositorio.ModeradorRepo;
import co.edu.uniquindio.UniLocal.repositorio.NegocioRepo;
import co.edu.uniquindio.UniLocal.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.UniLocal.servicios.interfaces.ModeradorServicio;
import co.edu.uniquindio.UniLocal.utils.NegocioUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ModeradorServicioImpl implements ModeradorServicio {

    private final NegocioRepo negocioRepo;
    private final EmailServicio emailServicio;
    private final HistorialModeracionRepo historialModeracionRepo;
    private final ModeradorRepo moderadorRepo;
    private final ClienteRepo clienteRepo;
    public ModeradorServicioImpl(NegocioRepo negocioRepo, EmailServicio emailServicio,HistorialModeracionRepo historialModeracionRepo,ModeradorRepo moderadorRepo
                                 ,ClienteRepo clienteRepo){
        this.negocioRepo = negocioRepo;
        this.emailServicio = emailServicio;
        this.historialModeracionRepo = historialModeracionRepo;
        this.moderadorRepo = moderadorRepo;
        this.clienteRepo = clienteRepo;
    }
    @Override
    public void eliminarCuenta(String idCuenta) throws Exception {
        Moderador moderador = obtenerModeradorPorIdCuenta(idCuenta);
        moderador.setEstado(EstadoRegistro.INACTIVO);
        moderadorRepo.save(moderador);
    }

    @Override
    public void enviarLinkRecuperacion(String email) throws Exception {
        String asunto = "Recuperar contraseña ";
        String cuerpo = "Hola! has solicitado cambiar tu contraseña, por favor ingresa  a este link  localhost:8080/api/auth/recuperar-contrasena ";
        EmailDTO correoAutorizacion = new EmailDTO(
                asunto,
                cuerpo,
                email
        );
        try {
            emailServicio.enviarCorreo(correoAutorizacion);
        } catch (Exception e) {
            throw new Exception("No se pudo restablecer la contraseña");
        }
    }

    @Override
    public void recuperarPassword(RecuperacionPasswordDTO RecuperacionPasswordDTO) {



    }

    @Override
    public void cambiarPassword(CambioPasswordDTO cambioPasswordDTO) throws Exception {

    }

    @Override
    public void autorizarNegocio(AutorizarNegocioDTO autorizarNegocioDTO) throws AutorizacionException {

        Optional<Negocio> negocioEncontrado = negocioRepo.findById(autorizarNegocioDTO.codigoNegocio());
        if(negocioEncontrado.isEmpty()){
            throw new AutorizacionException("No se encontro el negocio");
        }

        Optional<Cliente> clienteEncontrado = clienteRepo.findById(autorizarNegocioDTO.clienteId());
        if(clienteEncontrado.isEmpty()){
            throw new AutorizacionException("No se encontro el cliente");
        }

        //1. enviar correo a cliente
        String asunto = "Autorizacion Negocio ".concat(negocioEncontrado.get().getNombre());
        String cuerpo = "Su negocio ha sido aprobado con éxito para estar en la plataforma UniLocal";
        EmailDTO correoAutorizacion = new EmailDTO(
                asunto,
                cuerpo,
                clienteEncontrado.get().getEmail()
        );
        try {
            emailServicio.enviarCorreo(correoAutorizacion);
        } catch (Exception e) {
            throw new AutorizacionException("Error al enviar correo");
        }
        //2. cambiar estardo de negocio a APROBADO
        negocioEncontrado.get().setEstado(EstadoNegocio.APROBADO);
        negocioRepo.save(negocioEncontrado.get());
        //3. Guardar historial de aprobacion o aturoizacion
        HistorialModeracion historialModeracion = new HistorialModeracion(
                negocioEncontrado.get().getCodigo(),
                autorizarNegocioDTO.moderadorId(),
                autorizarNegocioDTO.fechaAccion(),
                EstadoNegocio.APROBADO,
                autorizarNegocioDTO.observacion()
        );
        historialModeracionRepo.save(historialModeracion);
    }

    @Override
    public void rechazarNegocio(AutorizarNegocioDTO autorizarNegocioDTO) throws AutorizacionException {
        Optional<Negocio> negocioEncontrado = negocioRepo.findById(autorizarNegocioDTO.codigoNegocio());
        if(negocioEncontrado.isEmpty()){
            throw new AutorizacionException("No se encontro el negocio");
        }

        Optional<Cliente> clienteEncontrado = clienteRepo.findById(autorizarNegocioDTO.clienteId());
        if(clienteEncontrado.isEmpty()){
            throw new AutorizacionException("No se encontro el cliente");
        }
        //1. enviar correo a cliente
        String asunto = "Rechazo Negocio ".concat(negocioEncontrado.get().getNombre());
        String cuerpo = "Su negocio ha sido rechazado, no puede estar en la plataforma UniLocal por las siguientes razones: \n"
                .concat(autorizarNegocioDTO.observacion())
                .concat(". Usted tiene 5 días para realizar la modificacion pertinentes y enviarlo de nuevo a revision.");
        EmailDTO correoAutorizacion = new EmailDTO(
                asunto,
                cuerpo,
                clienteEncontrado.get().getEmail()
        );
        try {
            emailServicio.enviarCorreo(correoAutorizacion);
        } catch (Exception e) {
            throw new AutorizacionException("Error al enviar correo");
        }
        //2. cambiar estardo de negocio a RECHAZADO y enviar la observacion
        negocioEncontrado.get().setEstado(EstadoNegocio.RECHAZADO);
        negocioRepo.save(negocioEncontrado.get());
        //3. Guardar historial de aprobacion o aturoizacion con razon de rechazo
        HistorialModeracion historialModeracion = new HistorialModeracion(
                negocioEncontrado.get().getCodigo(),
                autorizarNegocioDTO.moderadorId(),
                autorizarNegocioDTO.fechaAccion(),
                EstadoNegocio.RECHAZADO,
                autorizarNegocioDTO.observacion()
        );
        historialModeracionRepo.save(historialModeracion);
    }

    @Override
    public List<NegocioDTO> obtenerLugaresPendientesAutorizar() throws AutorizacionException {
        // hacer busqueda de todos los lugares en estado PENDIENTE
        List<Negocio> negociosPendientesAutorizar = negocioRepo.findByEstado(EstadoNegocio.PENDIENTE);
        if(negociosPendientesAutorizar.isEmpty()){
            throw new AutorizacionException("No hay lugares pendientes de autorizar");
        }
        return NegocioUtils.convertirListaNegocioAListaNegocioDto(negociosPendientesAutorizar);
    }
    @Override
    public List<HistorialModeracionDTO> obtenerHistoricoLugaresAutorizados(String moderadorId) throws AutorizacionException {
        // buscar en el hsitorico de moderacion todos los lugares aprobados por el moderador
        //buscar en el documento
        List<HistorialModeracion> historialModeracion = historialModeracionRepo.findByModeradorIdAndEstadoNegocio(moderadorId,EstadoNegocio.APROBADO);
        if(historialModeracion.isEmpty()){
            throw new AutorizacionException("No hay historico de lugares autorizados por el moderador");
        }
        return convertirListaHistorialAListaDTO(historialModeracion);
    }

    @Override
    public List<HistorialModeracionDTO> obtenerHistoricoLugaresRechazados(String moderadorId) throws AutorizacionException {
        // buscar en el hsitorico de moderacion todos los lugares rechazados por el moderador
        List<HistorialModeracion> historialModeracionReachazados = historialModeracionRepo.findByModeradorIdAndEstadoNegocio(moderadorId,EstadoNegocio.RECHAZADO);
        if(historialModeracionReachazados.isEmpty()){
            throw new AutorizacionException("No hay historico de lugares rechazados por el moderador");
        }
        return convertirListaHistorialAListaDTO(historialModeracionReachazados);
    }

    @Override
    public void obtenerEstadisticaNegocio(NegocioDTO negocioDTO) {

    }

    private List<HistorialModeracionDTO> convertirListaHistorialAListaDTO(List<HistorialModeracion> historicosLugaresAutorizados) {
        return historicosLugaresAutorizados.stream()
                .map(this::convertirAHistoricoDTO)
                .collect(Collectors.toList());
    }

    private HistorialModeracionDTO convertirAHistoricoDTO(HistorialModeracion historialModeracion) {
        return new HistorialModeracionDTO(
                historialModeracion.getLugarId(),
                historialModeracion.getModeradorId(),
                historialModeracion.getFechaAccion(),
                historialModeracion.getEstadoNegocio(),
                historialModeracion.getObservacion()
        );
    }

    private Moderador obtenerModeradorPorIdCuenta(String idCuenta)throws ResourceNotFoundException
    {
        Optional<Moderador> optionalModerador = moderadorRepo.findById(idCuenta);

        if(optionalModerador.isEmpty())
        {
            throw new ResourceNotFoundException("No se encontró el moderador con id: "+idCuenta);
        }
        //Obtenemos el cliente que se quiere eliminar y le asignamos el estado
        //inactivo
        return optionalModerador.get();
    }

}
