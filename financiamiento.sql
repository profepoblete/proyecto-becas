-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-01-2022 a las 19:42:48
-- Versión del servidor: 10.4.14-MariaDB
-- Versión de PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `financiamiento`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertar_alumno_solicitud` (IN `prm_rut` VARCHAR(45), IN `prm_nombre` VARCHAR(50), IN `prm_carrera` VARCHAR(50), IN `prm_telefono` VARCHAR(15), IN `prm_email` VARCHAR(40), IN `prm_fechaS` DATE, IN `prm_anioI` DECIMAL(4,0), IN `prm_semestre` VARCHAR(45), IN `prm_anioE` DECIMAL(4,0), IN `prm_r2H` VARCHAR(20), IN `prm_r3H` VARCHAR(20), IN `prm_fechaU` DATE, IN `prm_archivo` VARCHAR(45), IN `prm_idEstado` INT(3), IN `prm_idBeneficio` INT(3), IN `prm_idTipoS` INT(3))  BEGIN

/* Se declara variable donde se va a guardar el valor total*/
DECLARE TOTAL INTEGER;

-- Se declara el cursor con el select con cuyos datos se va a iterar
DECLARE insert_alumno_cursor CURSOR FOR SELECT max(usuario) FROM usuario;

-- Declaración de un manejador de error tipo NOT FOUND
DECLARE CONTINUE HANDLER FOR NOT FOUND SET @hecho = TRUE;

insert into usuario (rut_alumno,nombre,carrera,telefono,email) values (prm_rut,prm_nombre,prm_carrera,prm_telefono,prm_email);
commit;

-- Se abre el cursor. Al abrir el cursor este sitúa un puntero a la primera fila del resultado de la consulta.
OPEN insert_alumno_cursor;

-- Empieza el bucle de lectura
loop1: LOOP

 -- Se guarda el resultado en la variable, hay una variable y un campo en el SELECT de la declaración del cursor
 FETCH insert_alumno_cursor INTO TOTAL;

 -- Se sale del bucle cuando no hay elementos por recorrer
 IF @hecho THEN
 LEAVE loop1;
 END IF;

END LOOP loop1;

--  Se cierra el cursor
CLOSE insert_alumno_cursor;


-- Insertamos en una tabla con el valor del cursor entremedio de este "TOTAL"
insert into solicitud values (prm_fechaS,prm_anioI,prm_semestre,prm_anioE,prm_r2H,prm_r3H,prm_fechaU,prm_archivo,TOTAL,prm_idEstado,prm_idBeneficio,prm_idTipoS);
commit;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Insertar_Resultado` (IN `prm_idf` INT(3), IN `prm_idp` INT(3))  BEGIN

insert into resultado (solicitud_id_formulario, porcentaje_id_porcentaje) values (prm_idf, prm_idp);
commit;


END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listarReporte` ()  BEGIN
select s.id_formulario, u.rut, u.nombre, c.descripcion, b.nombreBe, p.nombreP,
 s.fecha_solicitud, s.fecha_update, f.nombreF, e.nombreE

from solicitud s, usuario u, estado e, tipo_solicitud ts, beneficio b, funcionario f, porcentaje p, resultado r, carrera c

where s.id_formulario = u.id_usuario and e.id_estado = s.estado_id_estado and ts.id_tipoS = s.tipo_solicitud_id_tipoS and 
b.id_beneficio = s.beneficio_id_beneficio and f.id_funcionario = s.funcionario_id_funcionario and r.porcentaje_id_porcentaje = p.id_porcentaje;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listarSolicitud` ()  BEGIN
	select 
		s.id_formulario as "id_formulario", 
        u.nombre_completo as "nombre", 
        c.descripcion as "carrera", 
        ts.nombreS as "nombreS", 
        b.nombreBe as "nombreBe", 
        s.fecha_solicitud as "fecha_solicitud", 
        s.fecha_update as "fecha_update", 
        e.nombreE as "nombreE",
        s.archivo as "archivo"
	from solicitud s,  
		usuario u, 
        estado e, 
        tipo_solicitud ts, 
        beneficio b, 
        carrera c
	where 
		s.id_alumno = u.id_usuario 
        and e.id_estado = s.id_estado 
        and ts.id_tipoS = s.id_tipo_solicitud 
        and b.id_beneficio = s.id_beneficio 
        and c.id_carrera = u.id_carrera
    order by 
		FIELD(e.nombreE,'En Proceso','Recepcionado','Falta Documentación', 'Rechazada', 'Aceptada'),
		s.id_formulario asc    
	;

END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `arancel`
--

CREATE TABLE `arancel` (
  `id_arancel` int(10) NOT NULL,
  `valor_matricula` int(5) NOT NULL,
  `valor_arancel` int(5) NOT NULL,
  `annio_ingreso` int(5) NOT NULL,
  `id_semestre` int(2) NOT NULL,
  `id_carrera` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `arancel`
--

INSERT INTO `arancel` (`id_arancel`, `valor_matricula`, `valor_arancel`, `annio_ingreso`, `id_semestre`, `id_carrera`) VALUES
(1, 97000, 2250000, 2020, 1, 1),
(2, 127000, 2210000, 2020, 1, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `beneficio`
--

CREATE TABLE `beneficio` (
  `id_beneficio` int(3) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `id_tipo_beneficio` int(3) NOT NULL,
  `vigente` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `beneficio`
--

INSERT INTO `beneficio` (`id_beneficio`, `nombre`, `id_tipo_beneficio`, `vigente`) VALUES
(1, 'CONVENIO ATENTO CHILE S.A', 2, 1),
(2, 'CONVENIO CENCOSUD', 2, 1),
(3, 'CONVENIO UNIVERSIDAD CATOLICA', 2, 1),
(4, 'CONVENIO GRUPO SANTANDER', 2, 1),
(5, 'CONVENIO ARMADA', 2, 1),
(6, 'CONVENIO BIENESTAR FUERZA AÉREA CHILE', 2, 1),
(7, 'CONVENIO EJERCITO DE CHILE', 2, 1),
(8, 'BECA SEGUNDO HERMANO', 1, 1),
(9, 'BECA TERCER HERMANO', 1, 1),
(10, 'BECA LICEO POLITECNICO ANDES', 1, 1),
(12, 'BECA DEPORTIVA', 1, 1),
(13, 'BECA SUMATE', 1, 1),
(14, 'BECA SEDE', 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carrera`
--

CREATE TABLE `carrera` (
  `id_carrera` int(2) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `id_departamento` int(2) NOT NULL,
  `id_tipo_carrera` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `carrera`
--

INSERT INTO `carrera` (`id_carrera`, `descripcion`, `id_departamento`, `id_tipo_carrera`) VALUES
(1, 'Analista programador computacional', 1, 1),
(2, 'Auditoria', 2, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `departamento`
--

CREATE TABLE `departamento` (
  `id_departamento` int(2) NOT NULL,
  `departamento` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `departamento`
--

INSERT INTO `departamento` (`id_departamento`, `departamento`) VALUES
(1, 'Informatica y telecomunicaciones'),
(2, 'Administracion y negocios');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado`
--

CREATE TABLE `estado` (
  `id_estado` int(3) NOT NULL,
  `estado` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `estado`
--

INSERT INTO `estado` (`id_estado`, `estado`) VALUES
(1, 'Recepcionado'),
(2, 'En Proceso'),
(3, 'Aceptada'),
(4, 'Rechazada'),
(5, 'Falta Documentación');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `log`
--

CREATE TABLE `log` (
  `id_log` int(6) NOT NULL,
  `fecha_accion` datetime DEFAULT current_timestamp(),
  `tipo_usuario` varchar(50) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `email` varchar(40) NOT NULL,
  `accion` varchar(30) NOT NULL,
  `id_formulario` int(6) NOT NULL,
  `id_usuario` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `log`
--

INSERT INTO `log` (`id_log`, `fecha_accion`, `tipo_usuario`, `nombre`, `email`, `accion`, `id_formulario`, `id_usuario`) VALUES
(1, '2021-04-13 21:25:16', 'Alumno', 'FAVIO ALEXANDER CORNEJO CABA', 'f.cornejoc@alumno.duoc.cl', 'Ingreso de solicitud', 2, 1),
(2, '2021-04-13 21:26:22', 'Financiamento', 'Fernanda DÃ­az', 'fdiaz@duoc.cl', 'Modificó una solicitud', 2, 2),
(3, '2021-04-13 21:30:01', 'Alumno', 'FAVIO ALEXANDER CORNEJO CABA', 'f.cornejoc@alumno.duoc.cl', 'Ingreso de solicitud', 3, 1),
(4, '2021-04-13 21:37:09', 'Financiamento', 'Fernanda DÃ­az', 'fdiaz@duoc.cl', 'Modificó una solicitud', 3, 2),
(5, '2021-04-13 21:43:17', 'Alumno', 'Cesar Poblete Sepulveda', 'cesar.poblete.sep@gmail.com', 'Ingreso de solicitud', 4, 4),
(6, '2021-04-13 21:43:46', 'Financiamento', 'Fernanda DÃ­az', 'fdiaz@duoc.cl', 'Modificó una solicitud', 4, 2),
(7, '2021-04-13 21:50:52', 'Financiamento', 'Fernanda DÃ­az', 'fdiaz@duoc.cl', 'Modificó la solicitud', 1, 2),
(8, '2021-04-13 22:21:40', 'Financiamento', 'Fernanda DÃ­az', 'fdiaz@duoc.cl', 'Modificó la solicitud', 1, 2),
(9, '2021-04-13 22:22:43', 'Alumno', 'Cesar Poblete Sepulveda', 'cesar.poblete.sep@gmail.com', 'Ingreso de solicitud', 5, 4),
(10, '2021-04-13 22:23:01', 'Financiamento', 'Fernanda DÃ­az', 'fdiaz@duoc.cl', 'Modificó la solicitud', 5, 2),
(11, '2021-04-13 22:23:22', 'Financiamento', 'Fernanda DÃ­az', 'fdiaz@duoc.cl', 'Modificó una solicitud', 5, 2),
(12, '2021-04-13 22:23:53', 'Financiamento', 'Fernanda DÃ­az', 'fdiaz@duoc.cl', 'Modificó una solicitud', 2, 2),
(13, '2021-04-13 22:24:48', 'Alumno', 'Cesar Poblete Sepulveda', 'cesar.poblete.sep@gmail.com', 'Ingreso de solicitud', 6, 4),
(14, '2021-04-13 22:25:12', 'Financiamento', 'Fernanda DÃ­az', 'fdiaz@duoc.cl', 'Modificó una solicitud', 6, 2),
(15, '2021-04-13 22:25:52', 'Alumno', 'Cesar Poblete Sepulveda', 'cesar.poblete.sep@gmail.com', 'Ingreso de solicitud', 7, 4),
(16, '2021-04-13 22:26:41', 'Alumno', 'Cesar Poblete Sepulveda', 'cesar.poblete.sep@gmail.com', 'Ingreso de solicitud', 8, 4),
(17, '2021-04-13 22:27:08', 'Alumno', 'Cesar Poblete Sepulveda', 'cesar.poblete.sep@gmail.com', 'Ingreso de solicitud', 9, 4),
(18, '2021-04-13 22:27:59', 'Alumno', 'Cesar Poblete Sepulveda', 'cesar.poblete.sep@gmail.com', 'Ingreso de solicitud', 10, 4),
(19, '2021-04-13 22:29:03', 'Alumno', 'Cesar Poblete Sepulveda', 'cesar.poblete.sep@gmail.com', 'Ingreso de solicitud', 11, 4),
(20, '2021-04-13 22:29:27', 'Financiamento', 'Fernanda DÃ­az', 'fdiaz@duoc.cl', 'Modificó una solicitud', 7, 2),
(21, '2021-04-13 22:29:45', 'Financiamento', 'Fernanda DÃ­az', 'fdiaz@duoc.cl', 'Modificó una solicitud', 8, 2),
(22, '2021-04-13 22:29:58', 'Financiamento', 'Fernanda DÃ­az', 'fdiaz@duoc.cl', 'Modificó una solicitud', 9, 2),
(23, '2021-04-13 22:30:08', 'Financiamento', 'Fernanda DÃ­az', 'fdiaz@duoc.cl', 'Modificó una solicitud', 10, 2),
(24, '2021-04-13 22:30:18', 'Financiamento', 'Fernanda DÃ­az', 'fdiaz@duoc.cl', 'Modificó una solicitud', 11, 2),
(25, '2021-04-14 20:28:48', 'Financiamiento', 'Cesar Poblete Sepulveda', 'cesar.poblete.sep@gmail.com', 'Ingreso de solicitud', 12, 2),
(26, '2021-04-14 20:31:16', 'Financiamiento', 'Cesar Poblete Sepulveda', 'cesar.poblete.sep@gmail.com', 'Ingreso de solicitud', 13, 2),
(27, '2021-04-14 20:32:17', 'Financiamiento', 'Cesar Poblete Sepulveda', 'cesar.poblete.sep@gmail.com', 'Ingreso de solicitud', 14, 2),
(28, '2021-04-14 20:32:56', 'Financiamento', 'Fernanda DÃ­az', 'fdiaz@duoc.cl', 'Modificó una solicitud', 18, 2),
(29, '2021-04-14 20:33:08', 'Financiamento', 'Fernanda DÃ­az', 'fdiaz@duoc.cl', 'Modificó una solicitud', 17, 2),
(30, '2021-04-14 20:33:20', 'Financiamento', 'Fernanda DÃ­az', 'fdiaz@duoc.cl', 'Modificó una solicitud', 16, 2),
(31, '2021-04-14 20:33:27', 'Financiamento', 'Fernanda DÃ­az', 'fdiaz@duoc.cl', 'Modificó la solicitud', 1, 2),
(32, '2021-04-15 16:34:31', 'Alumno', 'Cesar Poblete Sepulveda', 'cesar.poblete.sep@gmail.com', 'Ingreso de solicitud', 15, 4),
(33, '2021-04-15 16:35:03', 'Financiamento', 'Fernanda DÃ­az', 'fdiaz@duoc.cl', 'Modificó una solicitud', 19, 2),
(34, '2021-04-15 16:36:27', 'Financiamiento', 'Cesar Poblete Sepulveda', 'cesar.poblete.sep@gmail.com', 'Ingreso de solicitud', 16, 2),
(35, '2021-04-15 16:36:55', 'Financiamento', 'Fernanda DÃ­az', 'fdiaz@duoc.cl', 'Modificó una solicitud', 20, 2),
(36, '2021-04-15 17:02:57', 'Alumno', 'Cesar Poblete Sepulveda', 'cesar.poblete.sep@gmail.com', 'Ingreso de solicitud', 17, 4),
(37, '2021-04-15 17:05:12', 'Financiamento', 'Fernanda DÃ­az', 'fdiaz@duoc.cl', 'Modificó una solicitud', 21, 2),
(38, '2021-04-15 17:06:47', 'Financiamiento', 'Cesar Poblete Sepulveda', 'cesar.poblete.sep@gmail.com', 'Ingreso de solicitud', 18, 2),
(39, '2021-04-15 17:08:05', 'Financiamento', 'Fernanda DÃ­az', 'fdiaz@duoc.cl', 'Modificó una solicitud', 22, 2),
(40, '2021-06-13 20:41:22', 'Financiamento', 'Fernanda DÃ­az', 'fdiaz@duoc.cl', 'Modificó la solicitud', 1, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `porcentaje`
--

CREATE TABLE `porcentaje` (
  `id_porcentaje` int(3) NOT NULL,
  `descripcion` varchar(50) NOT NULL,
  `vigente` tinyint(1) NOT NULL DEFAULT 1,
  `porcentaje` int(2) DEFAULT NULL,
  `id_beneficio` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `porcentaje`
--

INSERT INTO `porcentaje` (`id_porcentaje`, `descripcion`, `vigente`, `porcentaje`, `id_beneficio`) VALUES
(2, 'CONVENIO CENCOSUD 10%', 1, NULL, 2),
(3, 'CONVENIO UNIVERSIDAD CATOLICA 10%', 1, NULL, 3),
(4, 'CONVENIO GRUPO SANTANDER 10%', 1, NULL, 4),
(5, 'CONVENIO ARMADA 10%', 1, NULL, 5),
(6, 'CONVENIO BIENESTAR FUERZA AÉREA CHILE 10%', 1, NULL, 6),
(7, 'CONVENIO EJÉRCITO DE CHILE 10%', 1, NULL, 7),
(8, 'BECA SEGUNDO HERMANO 20%', 1, 20, 8),
(9, 'BECA TERCER HERMANO 50%', 1, 50, 9),
(12, 'BECA Deportiva 20%', 1, NULL, 12),
(13, 'BECA Deportiva 40%', 1, NULL, 12),
(14, 'BECA Deportiva 80%', 1, NULL, 12),
(15, 'BECA Deportiva 100%', 1, NULL, 12),
(16, 'BECA SUMATE 25%', 1, NULL, 13),
(17, 'BECA SEDE 5%', 1, NULL, 14),
(18, 'BECA SEDE 10%', 1, NULL, 14),
(19, 'BECA SEDE 15%', 1, NULL, 14),
(20, 'BECA SEDE 20%', 1, NULL, 14),
(21, 'BECA SEDE 25%', 1, NULL, 14),
(22, 'BECA SEDE 30%', 1, NULL, 14),
(23, 'BECA SEDE 35%', 1, NULL, 14),
(24, 'BECA SEDE 40%', 1, NULL, 14),
(25, 'BECA SEDE 50%', 1, NULL, 14),
(26, 'BECA SEDE 75%', 1, NULL, 14),
(27, 'BECA SEDE 100%', 1, NULL, 14),
(32, 'bbb', 1, 26, 1),
(33, 'aaa', 1, 13, 1),
(34, 'abc', 1, 69, 1),
(35, 'yyy', 1, 22, 1),
(36, 'yyy', 1, 22, 1),
(37, 'pico q lee', 1, 99, 1),
(38, 'yao cabrera', 1, 55, 1),
(39, 'trolleador cara', 1, 33, 1),
(40, 'trolleador cara', 1, 33, 1),
(41, 'yayayayai', 1, 77, 1),
(42, 'klk', 1, 42, 1),
(43, 'trevor', 0, 18, 1),
(44, 'alejeke', 0, 8, 1),
(45, 'alejeke', 0, 8, 1),
(46, 'Alejalbo', 1, 69, 1),
(62, 'SEDE 77%', 1, 70, 14),
(63, 'CENCOSUD 50%', 1, 50, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sede`
--

CREATE TABLE `sede` (
  `id_sede` int(2) NOT NULL,
  `sede` varchar(100) CHARACTER SET utf8mb4 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `sede`
--

INSERT INTO `sede` (`id_sede`, `sede`) VALUES
(1, 'Maipu'),
(2, 'Alameda');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `solicitud`
--

CREATE TABLE `solicitud` (
  `id_formulario` int(3) NOT NULL,
  `fecha_solicitud` date NOT NULL,
  `anio_ingreso` int(5) NOT NULL,
  `semestre` int(45) DEFAULT NULL,
  `anio_egreso` int(5) DEFAULT NULL,
  `r2_hermano` varchar(20) DEFAULT NULL,
  `r3_hermano` varchar(20) DEFAULT NULL,
  `fecha_update` datetime DEFAULT NULL,
  `archivo` mediumblob DEFAULT NULL,
  `nombre_archivo` varchar(150) DEFAULT NULL,
  `id_alumno` int(6) NOT NULL,
  `id_estado` int(3) NOT NULL,
  `id_tipo_solicitud` int(3) NOT NULL,
  `id_funcionario` int(6) DEFAULT NULL,
  `id_porcentaje` int(3) DEFAULT NULL,
  `id_beneficio` int(3) NOT NULL,
  `monto_beneficio` bigint(20) DEFAULT NULL,
  `id_carrera` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_beneficio`
--

CREATE TABLE `tipo_beneficio` (
  `id_tipo` int(3) NOT NULL,
  `nombre` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tipo_beneficio`
--

INSERT INTO `tipo_beneficio` (`id_tipo`, `nombre`) VALUES
(1, 'Beca'),
(2, 'Convenio');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_carrera`
--

CREATE TABLE `tipo_carrera` (
  `id_tipo_carrera` int(2) NOT NULL,
  `tipo_carrera` varchar(25) CHARACTER SET utf8mb4 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tipo_carrera`
--

INSERT INTO `tipo_carrera` (`id_tipo_carrera`, `tipo_carrera`) VALUES
(1, 'Tecnica'),
(2, 'Profesional');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_solicitud`
--

CREATE TABLE `tipo_solicitud` (
  `id_tipo_solicitud` int(3) NOT NULL,
  `tipo_solicitud` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tipo_solicitud`
--

INSERT INTO `tipo_solicitud` (`id_tipo_solicitud`, `tipo_solicitud`) VALUES
(1, 'Nueva'),
(2, 'Renovacion');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_usuario`
--

CREATE TABLE `tipo_usuario` (
  `id_tipo_usuario` int(2) NOT NULL,
  `descripcion` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tipo_usuario`
--

INSERT INTO `tipo_usuario` (`id_tipo_usuario`, `descripcion`) VALUES
(1, 'Alumno'),
(2, 'Funcionario');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` int(6) NOT NULL,
  `rut` varchar(45) NOT NULL,
  `nombre_completo` varchar(50) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `email` varchar(40) NOT NULL,
  `pass` varchar(40) NOT NULL,
  `estado` char(1) NOT NULL,
  `id_carrera` int(4) DEFAULT NULL,
  `id_tipo_usuario` int(2) NOT NULL,
  `id_sede` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `rut`, `nombre_completo`, `telefono`, `email`, `pass`, `estado`, `id_carrera`, `id_tipo_usuario`, `id_sede`) VALUES
(1, '20043844-2', 'Favio Alexander Cornejo Caballero', '+56955204260', 'f.cornejoc@alumno.duoc.cl', '123', '1', 1, 1, 1),
(2, '10484900-8', 'Fernanda Díaz', '+56998761234', 'fdiaz@duoc.cl', '123', '1', NULL, 2, 1),
(3, '15845655-9', 'Cesar Poblete', '+56983224679', 'cesar.poblete.sep@gmail.com', '123', '1', 1, 1, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `arancel`
--
ALTER TABLE `arancel`
  ADD PRIMARY KEY (`id_arancel`);

--
-- Indices de la tabla `beneficio`
--
ALTER TABLE `beneficio`
  ADD PRIMARY KEY (`id_beneficio`),
  ADD KEY `fk_beneficio_tipo_beneficio_idx` (`id_tipo_beneficio`);

--
-- Indices de la tabla `carrera`
--
ALTER TABLE `carrera`
  ADD PRIMARY KEY (`id_carrera`),
  ADD KEY `fk_id_departamento_idx` (`id_departamento`) USING BTREE,
  ADD KEY `fk_id_tipo_carrera_idx` (`id_tipo_carrera`) USING BTREE;

--
-- Indices de la tabla `departamento`
--
ALTER TABLE `departamento`
  ADD PRIMARY KEY (`id_departamento`);

--
-- Indices de la tabla `estado`
--
ALTER TABLE `estado`
  ADD PRIMARY KEY (`id_estado`);

--
-- Indices de la tabla `log`
--
ALTER TABLE `log`
  ADD PRIMARY KEY (`id_log`);

--
-- Indices de la tabla `porcentaje`
--
ALTER TABLE `porcentaje`
  ADD PRIMARY KEY (`id_porcentaje`),
  ADD KEY `fk_porcentaje_beneficio` (`id_beneficio`) USING BTREE;

--
-- Indices de la tabla `sede`
--
ALTER TABLE `sede`
  ADD PRIMARY KEY (`id_sede`);

--
-- Indices de la tabla `solicitud`
--
ALTER TABLE `solicitud`
  ADD PRIMARY KEY (`id_formulario`),
  ADD KEY `fk_solicitud_alumno` (`id_alumno`),
  ADD KEY `fk_solicitud_estado` (`id_estado`),
  ADD KEY `fk_solicitud_porcentaje` (`id_porcentaje`),
  ADD KEY `fk_solicitud_tipo_solicitud` (`id_tipo_solicitud`),
  ADD KEY `fk_solicitud_funcionario` (`id_funcionario`),
  ADD KEY `fk_solicitud_beneficio` (`id_beneficio`);

--
-- Indices de la tabla `tipo_beneficio`
--
ALTER TABLE `tipo_beneficio`
  ADD PRIMARY KEY (`id_tipo`);

--
-- Indices de la tabla `tipo_carrera`
--
ALTER TABLE `tipo_carrera`
  ADD PRIMARY KEY (`id_tipo_carrera`);

--
-- Indices de la tabla `tipo_solicitud`
--
ALTER TABLE `tipo_solicitud`
  ADD PRIMARY KEY (`id_tipo_solicitud`);

--
-- Indices de la tabla `tipo_usuario`
--
ALTER TABLE `tipo_usuario`
  ADD PRIMARY KEY (`id_tipo_usuario`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`),
  ADD KEY `fk_id_carrera_idx` (`id_carrera`),
  ADD KEY `fk_id_tipo_usuario_idx` (`id_tipo_usuario`),
  ADD KEY `fk_id_sede_idx` (`id_sede`) USING BTREE;

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `arancel`
--
ALTER TABLE `arancel`
  MODIFY `id_arancel` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `beneficio`
--
ALTER TABLE `beneficio`
  MODIFY `id_beneficio` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT de la tabla `carrera`
--
ALTER TABLE `carrera`
  MODIFY `id_carrera` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `departamento`
--
ALTER TABLE `departamento`
  MODIFY `id_departamento` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `estado`
--
ALTER TABLE `estado`
  MODIFY `id_estado` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `log`
--
ALTER TABLE `log`
  MODIFY `id_log` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT de la tabla `porcentaje`
--
ALTER TABLE `porcentaje`
  MODIFY `id_porcentaje` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- AUTO_INCREMENT de la tabla `sede`
--
ALTER TABLE `sede`
  MODIFY `id_sede` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `solicitud`
--
ALTER TABLE `solicitud`
  MODIFY `id_formulario` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT de la tabla `tipo_beneficio`
--
ALTER TABLE `tipo_beneficio`
  MODIFY `id_tipo` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tipo_carrera`
--
ALTER TABLE `tipo_carrera`
  MODIFY `id_tipo_carrera` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tipo_solicitud`
--
ALTER TABLE `tipo_solicitud`
  MODIFY `id_tipo_solicitud` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tipo_usuario`
--
ALTER TABLE `tipo_usuario`
  MODIFY `id_tipo_usuario` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `beneficio`
--
ALTER TABLE `beneficio`
  ADD CONSTRAINT `fk_beneficio_tipo_beneficio` FOREIGN KEY (`id_tipo_beneficio`) REFERENCES `tipo_beneficio` (`id_tipo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `carrera`
--
ALTER TABLE `carrera`
  ADD CONSTRAINT `carrera_ibfk_1` FOREIGN KEY (`id_departamento`) REFERENCES `departamento` (`id_departamento`) ON UPDATE CASCADE,
  ADD CONSTRAINT `carrera_ibfk_2` FOREIGN KEY (`id_tipo_carrera`) REFERENCES `tipo_carrera` (`id_tipo_carrera`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `porcentaje`
--
ALTER TABLE `porcentaje`
  ADD CONSTRAINT `porcentaje_ibfk_1` FOREIGN KEY (`id_beneficio`) REFERENCES `beneficio` (`id_beneficio`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `solicitud`
--
ALTER TABLE `solicitud`
  ADD CONSTRAINT `fk_solicitud_alumno` FOREIGN KEY (`id_alumno`) REFERENCES `usuario` (`id_usuario`),
  ADD CONSTRAINT `fk_solicitud_estado` FOREIGN KEY (`id_estado`) REFERENCES `estado` (`id_estado`),
  ADD CONSTRAINT `fk_solicitud_tipo_solicitud` FOREIGN KEY (`id_tipo_solicitud`) REFERENCES `tipo_solicitud` (`id_tipo_solicitud`),
  ADD CONSTRAINT `solicitud_ibfk_1` FOREIGN KEY (`id_beneficio`) REFERENCES `beneficio` (`id_beneficio`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `fk_id_carrera` FOREIGN KEY (`id_carrera`) REFERENCES `carrera` (`id_carrera`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_id_sede` FOREIGN KEY (`id_sede`) REFERENCES `sede` (`id_sede`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_id_tipo_usuario` FOREIGN KEY (`id_tipo_usuario`) REFERENCES `tipo_usuario` (`id_tipo_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
