INSERT INTO `estudiante` (`noIdentificacion`, `tipoIdentificacion`, `nombres`, `apellidos`, `fechaIngreso`, `correo`) VALUES ('1111', 'CC', 'Juan', 'perez', '2022-11-09 00:00:00','juan@gmail.com');
INSERT INTO `estudiante` (`idPersona`, `apellidos`, `noIdentificacion`, `nombres`, `tipoIdentificacion`, `fechaIngreso`, `correo`) VALUES (NULL, 'Solarte', '1061813423', 'Juan', 'CC', '2022-11-28 01:51:17.000000','solarte@gmail.com');
INSERT INTO `estudiante` (`idPersona`, `apellidos`, `noIdentificacion`, `nombres`, `tipoIdentificacion`, `fechaIngreso`, `correo`) VALUES (NULL, 'Marino', '1061818923', 'Clouse', 'CC', '2022-11-28 01:51:17.000000','marino@gmail.com');
INSERT INTO `estudiante` (`idPersona`, `apellidos`, `noIdentificacion`, `nombres`, `tipoIdentificacion`, `fechaIngreso`, `correo`) VALUES (NULL, 'Melina', '10618143923', 'Andrea', 'CC', '2022-11-28 01:51:17.000000','melina@gmail.com');
INSERT INTO `estudiante` (`idPersona`, `apellidos`, `noIdentificacion`, `nombres`, `tipoIdentificacion`, `fechaIngreso`, `correo`) VALUES (NULL, 'Medina', '10618134323', 'Jiliana', 'CC', '2022-11-28 01:51:17.000000','medina@gmail.com');
INSERT INTO `estudiante` (`idPersona`, `apellidos`, `noIdentificacion`, `nombres`, `tipoIdentificacion`, `fechaIngreso`, `correo`) VALUES (NULL, 'Andrade', '4324234444', 'Melissa', 'CC', '2022-11-28 01:51:17.000000','andrade@gmail.com');
INSERT INTO `estudiante` (`idPersona`, `apellidos`, `noIdentificacion`, `nombres`, `tipoIdentificacion`, `fechaIngreso`, `correo`) VALUES (NULL, 'Diaz', '1061814323', 'Luis', 'CC', '2022-11-28 01:51:17.000000','diaz@gmail.com');
INSERT INTO `estudiante` (`idPersona`, `apellidos`, `noIdentificacion`, `nombres`, `tipoIdentificacion`, `fechaIngreso`, `correo`) VALUES (NULL, 'Andrade', '432424433', 'Melissa', 'CC', '2022-11-28 01:51:17.000000','andrade@gmail.com');



INSERT INTO `direccion` (`calle`, `localidad`, `idPersona`) VALUES ('calle 34 # 5', 'Popayán', 1);
INSERT INTO `direccion` (`calle`, `localidad`, `idPersona`) VALUES ('calle 4 # 5', 'Popayán', 2);
INSERT INTO `direccion` (`calle`, `localidad`, `idPersona`) VALUES ('cra 9 # 5', 'Popayán', 3);
INSERT INTO `direccion` (`calle`, `localidad`, `idPersona`) VALUES ('cra 4 # 59', 'Popayán', 4);
INSERT INTO `direccion` (`calle`, `localidad`, `idPersona`) VALUES ('calle 9 # 45', 'Popayán', 5);
INSERT INTO `direccion` (`calle`, `localidad`, `idPersona`) VALUES ('cra 4 # 59', 'Popayán', 6);
INSERT INTO `direccion` (`calle`, `localidad`, `idPersona`) VALUES ('calle 89 # 10', 'Popayán', 7);
INSERT INTO `direccion` (`calle`, `localidad`, `idPersona`) VALUES ('calle 45 # 5', 'Popayán', 8);


INSERT INTO `telefono` (`tipo`, `numero`, `idPersona`) VALUES ('Celular', '3207719034', 1);
INSERT INTO `telefono` (`tipo`, `numero`, `idPersona`) VALUES ('Celular', '123456789', 1);


INSERT INTO `docente` (`idPersona`, `apellidos`, `noIdentificacion`, `nombres`, `tipoIdentificacion`, `salario`, `tipoDocente`, `universidad`) VALUES (null, 'Rojas', '10801080123', 'Pedro', 'CC', 2500000, 'Planta', 'Unicauca');
INSERT INTO `docente` (`idPersona`, `apellidos`, `noIdentificacion`, `nombres`, `tipoIdentificacion`, `universidad`, `tipoDocente`, `salario`) VALUES (NULL, 'Buitron', '8012346', 'Sandra', 'CC', 'Universidad Del Cauca','planta','7000000');



INSERT INTO `asignatura`(`idAsignatura`, `nombre`) VALUES(null, 'Calculo III');
INSERT INTO `asignatura` (`idAsignatura`, `nombre`) VALUES (null, 'Ingeniería de Requisitos'); 
INSERT INTO `docente_asignatura`(`idPersona`, `idAsignatura`) VALUES(1, 1);
INSERT INTO `docente_asignatura` (`idPersona`, `idAsignatura`) VALUES (2, 2); 

INSERT INTO `curso` (`idCurso`, `nombre`, `periodo`, `idAsignatura`) VALUES (null, 'B', 1,1); 
INSERT INTO `curso` (`idCurso`, `nombre`, `periodo`, `idAsignatura`) VALUES (null, 'A', 2,2); 


