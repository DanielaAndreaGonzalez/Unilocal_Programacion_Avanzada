package co.edu.uniquindio.UniLocal.servicios.implementaciones;

import co.edu.uniquindio.UniLocal.servicios.interfaces.ImagenesServicio;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class ImagenesServicioImpl implements ImagenesServicio {

    private final Cloudinary cloudinary;

    public ImagenesServicioImpl(){
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dmwjxulch");
        config.put("api_key","888141527252764");
        config.put("api_secret","8_C-vJO3npDM2qOSapjOgi03d_E");

        cloudinary = new Cloudinary(config);
    }

    @Override
    public Map subirImagen(MultipartFile imagen) throws Exception {
        File file = convertir(imagen);
        return cloudinary.uploader().upload(file,ObjectUtils.asMap("folder","UNILOCAL_imgs"));
    }

    @Override
    public Map eliminarImagen(String idImagen) throws Exception {
        return cloudinary.uploader().destroy(idImagen,ObjectUtils.emptyMap());
    }

    private File convertir(MultipartFile imagen) throws IOException{
        File file = File.createTempFile(imagen.getOriginalFilename(),null);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagen.getBytes());
        fos.close();
        return file;
    }
}
