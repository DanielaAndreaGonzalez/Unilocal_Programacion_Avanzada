db = connect( 'mongodb://root:example@localhost:27017/unilocal-proyecto-final?authSource=admin' );
db.clientes.insertMany([
    {
        _id: 'Cliente1',
        nickname: 'juanito',
        ciudad: 'Armenia',
        fotoPerfil: 'mi foto',
        email: 'juan@email.com',
        password: '$2a$10$m0oHiRjF5RVqJPiBi173D.RiANPzVm57IvKgJdIgK8LIuAGwgvWuy',
        nombre: 'Juan',
        estado: 'ACTIVO',
        _class: 'co.edu.uniquindio.UniLocal.documentos.Cliente'
    },
    {
        _id: 'Cliente2',
        nickname: 'maria',
        ciudad: 'Armenia',
        fotoPerfil: 'mi foto',
        email: 'maria@email.com',
        password: 'mipassword',
        nombre: 'Maria',
        estado: 'ACTIVO',
        _class: 'co.edu.uniquindio.UniLocal.documentos.Cliente'
    },
    {
        _id: 'Cliente3',
        nickname: 'pedrito',
        ciudad: 'Armenia',
        fotoPerfil: 'mi foto',
        email: 'pedro@email.com',
        password: 'mipassword',
        nombre: 'Pedro',
        estado: 'ACTIVO',
        _class: 'co.edu.uniquindio.UniLocal.documentos.Cliente'
    },
    {
        _id: 'Cliente4',
        nickname: 'Daniela',
        ciudad: 'Armenia',
        fotoPerfil: 'mi foto',
        email: 'dani@email.com',
        password: 'mipassword',
        nombre: 'daniela',
        estado: 'ACTIVO',
        _class: 'co.edu.uniquindio.UniLocal.documentos.Cliente'
    },
    {
        _id: 'Cliente5',
        nickname: 'sofia',
        ciudad: 'Armenia',
        fotoPerfil: 'mi foto',
        email: 'sofia@email.com',
        password: 'mipassword',
        nombre: 'sofia',
        estado: 'ACTIVO',
        _class: 'co.edu.uniquindio.UniLocal.documentos.Cliente'
    }
]);
db.negocios.insertMany([
    {
        _id: 'Negocio1',
        nombre: 'Restaurante Mexicano',
        descripcion: 'Restaurante de comida mexicana en Armenia',
        codigoCliente: 'Cliente1',
        ubicacion: {
            latitud: 4.540130,
            longitud: -75.665660
        },
        imagenes: ['imagen1', 'imagen2'],
        tipoNegocio: 'RESTAURANTE',
        horarios: [
            {
                dia: 'LUNES',
                horaInicio: '08:00',
                horaFin: '20:00'
            }
        ],
        telefonos: ['1234567', '7654321'],
        estado: 'ACTIVO',
        _class: 'co.edu.uniquindio.UniLocal.documentos.Negocio'
    },
    {
        _id: 'Negocio2',
        nombre: 'Cafeteria la 27',
        descripcion: 'Cafeteria y panaderia exquisita en Pereira',
        codigoCliente: 'Cliente2',
        ubicacion: {
            latitud: 5.222433,
            longitud: -45.132221
        },
        imagenes: ['imagen1', 'imagen2'],
        tipoNegocio: 'CAFE',
        horarios: [
            {
                dia: 'LUNES',
                horaInicio: '08:00',
                horaFin: '20:00'
            }
        ],
        telefonos: ['1221567', '7654321'],
        estado: 'ACTIVO',
        _class: 'co.edu.uniquindio.UniLocal.documentos.Negocio'
    },
    {
        _id: 'Negocio3',
        nombre: 'Hotel belmont',
        descripcion: 'Elegante hotel ubicado en Pereira',
        codigoCliente: 'Cliente1',
        ubicacion: {
            latitud: 3.23433,
            longitud: -87.132221
        },
        imagenes: ['imagen1', 'imagen2'],
        tipoNegocio: 'HOTEL',
        horarios: [
            {
                dia: 'LUNES',
                horaInicio: '08:00',
                horaFin: '20:00'
            }
        ],
        telefonos: ['1221567', '7654321'],
        estado: 'ACTIVO',
        _class: 'co.edu.uniquindio.UniLocal.documentos.Negocio'
    },
    {
        _id: 'Negocio4',
        nombre: 'Discoteca bar el paisa',
        descripcion: 'gran discoteca para disfrutar con tus amigos ubicada en Armenia',
        codigoCliente: 'Cliente3',
        ubicacion: {
            latitud: 9.222433,
            longitud: -85.132221
        },
        imagenes: ['imagen1', 'imagen2'],
        tipoNegocio: 'DISCOTECA',
        horarios: [
            {
                dia: 'LUNES',
                horaInicio: '19:00',
                horaFin: '23:00'
            }
        ],
        telefonos: ['1221567', '7654321'],
        estado: 'ACTIVO',
        _class: 'co.edu.uniquindio.UniLocal.documentos.Negocio'
    },
    {
        _id: 'Negocio5',
        nombre: 'Mercamax',
        descripcion: 'Supermercado ubicado en Armenia',
        codigoCliente: 'Cliente2',
        ubicacion: {
            latitud: 2.222433,
            longitud: -65.132221
        },
        imagenes: ['imagen1', 'imagen2'],
        tipoNegocio: 'SUPERMERCADO',
        horarios: [
            {
                dia: 'LUNES',
                horaInicio: '08:00',
                horaFin: '20:00'
            }
        ],
        telefonos: ['1221567', '7654321'],
        estado: 'RECHAZADO',
        _class: 'co.edu.uniquindio.UniLocal.documentos.Negocio'
    }
]);
db.comentarios.insertMany([
    {
        mensaje: "Excelente sitio, muy buena atención",
        fecha: new Date(),
        codigoCliente: 'Cliente4',
        codigoNegocio: 'Negocio1',
        calificacion: 5,
        _class: 'co.edu.uniquindio.UniLocal.documentos.Comentario'
    },
    {
        mensaje: "Agradable cafe para pasar una tarde maravillosa",
        fecha: new Date(),
        codigoCliente: 'Cliente4',
        codigoNegocio: 'Negocio2',
        calificacion: 5,
        _class: 'co.edu.uniquindio.UniLocal.documentos.Comentario'
    },
    {
        mensaje: "lugar muy bonito, pero muy poco personal para atender solicitudes",
        fecha: new Date(),
        codigoCliente: 'Cliente5',
        codigoNegocio: 'Negocio3',
        calificacion: 4,
        _class: 'co.edu.uniquindio.UniLocal.documentos.Comentario'
    },
    {
        mensaje: "Muy buen ambiente, me gusto mucho",
        fecha: new Date(),
        codigoCliente: 'Cliente4',
        codigoNegocio: 'Negocio4',
        calificacion: 5,
        _class: 'co.edu.uniquindio.UniLocal.documentos.Comentario'
    },
    {
        mensaje: "pesima atencion, nunca habian los productos requeridos",
        fecha: new Date(),
        codigoCliente: 'Cliente5',
        codigoNegocio: 'Negocio2',
        calificacion: 2,
        _class: 'co.edu.uniquindio.UniLocal.documentos.Comentario'
    },

]);

db.moderador.insertMany([
    {
        _id: 'moderador1',
        nombre: 'Carlos',
        cedula: "115195033",
        nickname: 'Moderador 1',
        email: 'danielaandreagonzalezhenao@gmail.com',
        password: '123456',
        estado: 'ACTIVO',
        fotoPerfil: 'fotomoderador1.png',
        telefono: ['312121211'],
        _class: 'co.edu.uniquindio.UniLocal.documentos.Moderador'
    },
    {
        _id: 'moderador2',
        nombre: 'Luisa',
        cedula: "1003818314",
        nickname: 'Moderador 2',
        email: 'luisacahe5@gmail.com',
        password: '123456',
        estado: 'ACTIVO',
        fotoPerfil: 'fotomoderador2.png',
        telefono: ['313144223','065454'],
        _class: 'co.edu.uniquindio.UniLocal.documentos.Moderador'
    },
    {   _id: 'moderador3',
        nombre: 'Leidy',
        cedula: "51992345",
        nickname: 'Moderador 3',
        email: 'leidy@email.com',
        password: '123456',
        estado: 'ACTIVO',
        fotoPerfil: 'fotomoderador3.png',
        telefono: ['3145213456'],
        _class: 'co.edu.uniquindio.UniLocal.documentos.Moderador'
    },
    {
        _id: 'moderador4',
        nombre: 'Santiago',
        cedula: "1002342334",
        nickname: 'Moderador 4',
        email: 'santiago@gmail.com',
        password: '123456',
        estado: 'ACTIVO',
        fotoPerfil: 'fotomoderador4.png',
        telefono: ['3154435678'],
        _class: 'co.edu.uniquindio.UniLocal.documentos.Moderador'
    },
    {   _id: 'moderador5',
        nombre: 'Luis',
        cedula: "52456231",
        nickname: 'Moderador 5',
        email: 'luis@gmail.com',
        password: '123456',
        estado: 'ACTIVO',
        fotoPerfil: 'fotomoderador5.png',
        telefono: ['3002345687'],
        _class: 'co.edu.uniquindio.UniLocal.documentos.Moderador'
    }
]);

db.historialModeracion.insertMany([
    {
        lugarId: "negocio1",
        moderadorId: 'moderador1',
        fechaAccion: new Date(),
        estadoNegocio: 'ACTIVO',
        observacion: 'se acepta negocio',
        _class: 'co.edu.uniquindio.UniLocal.documentos.HistorialModeracion'
    },
    {
        lugarId: "negocio2",
        moderadorId: 'moderador5',
        fechaAccion: new Date(),
        estadoNegocio: 'ACTIVO',
        observacion: 'se acepta negocio',
        _class: 'co.edu.uniquindio.UniLocal.documentos.HistorialModeracion'
    },
    {
        lugarId: "negocio3",
        moderadorId: 'moderador4',
        fechaAccion: new Date(),
        estadoNegocio: 'ACTIVO',
        observacion: 'se acepta negocio',
        _class: 'co.edu.uniquindio.UniLocal.documentos.HistorialModeracion'
    },
    {
        lugarId: "negocio4",
        moderadorId: 'moderador3',
        fechaAccion: new Date(),
        estadoNegocio: 'ACTIVO',
        observacion: 'se acepta negocio',
        _class: 'co.edu.uniquindio.UniLocal.documentos.HistorialModeracion'
    },
    {
        lugarId: "negocio5",
        moderadorId: 'moderador2',
        fechaAccion: new Date(),
        estadoNegocio: 'RECHAZADO',
        observacion: 'se rechaza negocio por incumplir reglas',
        _class: 'co.edu.uniquindio.UniLocal.documentos.HistorialModeracion'
    },
]);