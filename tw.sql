-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 07-02-2024 a las 10:57:35
-- Versión del servidor: 10.4.11-MariaDB
-- Versión de PHP: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tw`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `negociacion_trueque`
--

CREATE TABLE `negociacion_trueque` (
  `idtrueque` int(11) NOT NULL,
  `idproducto` int(11) NOT NULL,
  `idinteresado` int(11) NOT NULL,
  `textoVendedor` text DEFAULT NULL,
  `textoComprador` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `negociacion_trueque`
--

INSERT INTO `negociacion_trueque` (`idtrueque`, `idproducto`, `idinteresado`, `textoVendedor`, `textoComprador`) VALUES
(1, 6, 4, 'Wenas, me ha interesado tu boli, me lo puedes cambiar??', 'Buenas, lo siento, ya está reserevado'),
(2, 8, 1, 'Buenos días, te cambio mi figura por tu lata de monster', 'Buenas tardes, acepto el trato'),
(3, 4, 3, 'Hola, me estoy iniciando en el lol y me interesa tu cuenta', 'Wenas, qué me puedes ofrecer??'),
(4, 3, 2, 'Hola, quiero tu lote de ropa', 'Buenas noches, ¿por qué me lo cambias?');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `descripcion` varchar(200) NOT NULL,
  `valorenTP` int(11) DEFAULT NULL,
  `estado` varchar(10) NOT NULL,
  `usuarioid` int(11) DEFAULT NULL,
  `categoria` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id`, `nombre`, `descripcion`, `valorenTP`, `estado`, `usuarioid`, `categoria`) VALUES
(1, 'Figura de anime', 'Esta en perfectas condiciones', NULL, 'disponible', 1, ''),
(2, 'Sandwich del VIPS', 'Caduca en 1 semana', 20, 'reservado', 2, ''),
(3, 'Lote ropa invierno', 'Lote de 100 prendas aleatorias', 200, 'truequeado', 3, ''),
(4, 'Cuenta del lol', 'He dejado el lol y quiero truequear mi cuenta', 100, 'disponible', 4, ''),
(5, 'Sudadera DragonBall', 'Me queda pequeña', 40, 'truequeado', 1, ''),
(6, 'Boli Bic azul', 'Está casi gastado, pero aún funciona', NULL, 'reservado', 2, ''),
(7, 'Tablet Lenovo', 'No la uso apenas', NULL, 'disponible', 3, ''),
(8, 'Lata Monster', 'La tengo repetida', NULL, 'reservado', 4, '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tarjeta`
--

CREATE TABLE `tarjeta` (
  `id` int(11) NOT NULL,
  `numTarjeta` int(11) DEFAULT NULL,
  `CVV` int(11) NOT NULL,
  `fechaCad` varchar(10) NOT NULL,
  `idUsuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tarjeta`
--

INSERT INTO `tarjeta` (`id`, `numTarjeta`, `CVV`, `fechaCad`, `idUsuario`) VALUES
(1, 999999991, 111, '01/2025', 1),
(2, 999999992, 222, '02/2026', 2),
(3, 999999993, 333, '04/2027', 3),
(4, 999999994, 444, '08/2028', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `truques_productos`
--

CREATE TABLE `truques_productos` (
  `idtp` int(11) NOT NULL,
  `idproductovender` int(11) NOT NULL,
  `idproductocomprar` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `truques_productos`
--

INSERT INTO `truques_productos` (`idtp`, `idproductovender`, `idproductocomprar`) VALUES
(1, 7, 6),
(2, 8, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `truque_tp`
--

CREATE TABLE `truque_tp` (
  `idttp` int(11) NOT NULL,
  `idcomprador` int(11) NOT NULL,
  `idvendedor` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `truque_tp`
--

INSERT INTO `truque_tp` (`idttp`, `idcomprador`, `idvendedor`) VALUES
(1, 1, 4),
(2, 2, 3),
(3, 3, 5),
(4, 4, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `DNI` varchar(9) DEFAULT NULL,
  `nombre` varchar(15) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `email` varchar(20) DEFAULT NULL,
  `contrasena` varchar(12) NOT NULL,
  `TP` int(11) NOT NULL,
  `preferencias` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `DNI`, `nombre`, `apellidos`, `email`, `contrasena`, `TP`, `preferencias`) VALUES
(1, '66666666W', 'Alex', 'Martinez', 'alexmartinez@ifp', '1234abcd', 0, NULL),
(2, '66666666X', 'Johan', 'Benitez', 'johanbenitez@ifp', '5678efgh', 5, NULL),
(3, '66666666Y', 'Luca', 'Jin', 'lucajin@ifp', '9090ijkl', 10, NULL),
(4, '66666666Z', 'Sergio', 'Navarro', 'sergionavarro@ifp', '0909mnop', 15, NULL);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `negociacion_trueque`
--
ALTER TABLE `negociacion_trueque`
  ADD PRIMARY KEY (`idtrueque`),
  ADD KEY `idproducto` (`idproducto`),
  ADD KEY `idinteresado` (`idinteresado`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idUsuario` (`usuarioid`);

--
-- Indices de la tabla `tarjeta`
--
ALTER TABLE `tarjeta`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `numTarjeta` (`numTarjeta`),
  ADD KEY `idUsuario` (`idUsuario`);

--
-- Indices de la tabla `truques_productos`
--
ALTER TABLE `truques_productos`
  ADD PRIMARY KEY (`idtp`),
  ADD KEY `idproductovender` (`idproductovender`),
  ADD KEY `idproductocomprar` (`idproductocomprar`);

--
-- Indices de la tabla `truque_tp`
--
ALTER TABLE `truque_tp`
  ADD PRIMARY KEY (`idttp`),
  ADD KEY `idcomprador` (`idcomprador`),
  ADD KEY `idvendedor` (`idvendedor`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `DNI` (`DNI`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `negociacion_trueque`
--
ALTER TABLE `negociacion_trueque`
  MODIFY `idtrueque` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `tarjeta`
--
ALTER TABLE `tarjeta`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `truques_productos`
--
ALTER TABLE `truques_productos`
  MODIFY `idtp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `truque_tp`
--
ALTER TABLE `truque_tp`
  MODIFY `idttp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `negociacion_trueque`
--
ALTER TABLE `negociacion_trueque`
  ADD CONSTRAINT `negociacion_trueque_ibfk_1` FOREIGN KEY (`idproducto`) REFERENCES `productos` (`id`),
  ADD CONSTRAINT `negociacion_trueque_ibfk_2` FOREIGN KEY (`idinteresado`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`usuarioid`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `tarjeta`
--
ALTER TABLE `tarjeta`
  ADD CONSTRAINT `tarjeta_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `truques_productos`
--
ALTER TABLE `truques_productos`
  ADD CONSTRAINT `truques_productos_ibfk_1` FOREIGN KEY (`idproductovender`) REFERENCES `productos` (`id`),
  ADD CONSTRAINT `truques_productos_ibfk_2` FOREIGN KEY (`idproductocomprar`) REFERENCES `productos` (`id`);

--
-- Filtros para la tabla `truque_tp`
--
ALTER TABLE `truque_tp`
  ADD CONSTRAINT `truque_tp_ibfk_1` FOREIGN KEY (`idcomprador`) REFERENCES `usuarios` (`id`),
  ADD CONSTRAINT `truque_tp_ibfk_2` FOREIGN KEY (`idvendedor`) REFERENCES `productos` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
