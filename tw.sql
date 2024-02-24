-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 24-02-2024 a las 01:15:02
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
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
-- Estructura de tabla para la tabla `chats`
--

CREATE TABLE `chats` (
  `id` int(11) NOT NULL,
  `id_producto` int(11) DEFAULT NULL,
  `id_interesado` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `chats`
--

INSERT INTO `chats` (`id`, `id_producto`, `id_interesado`) VALUES
(1, 2, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `favoritos`
--

CREATE TABLE `favoritos` (
  `id` int(11) NOT NULL,
  `Productoid` int(11) DEFAULT NULL,
  `Usuarioid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `favoritos`
--

INSERT INTO `favoritos` (`id`, `Productoid`, `Usuarioid`) VALUES
(1, 2, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensajes`
--

CREATE TABLE `mensajes` (
  `id` int(11) NOT NULL,
  `id_chat` int(11) DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `texto` varchar(200) DEFAULT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `mensajes`
--

INSERT INTO `mensajes` (`id`, `id_chat`, `id_usuario`, `texto`, `fecha`) VALUES
(2, 1, 2, 'holaaaaaaaaaaa', '2024-02-19 10:41:51');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `descripcion` text NOT NULL,
  `valor_tp` int(11) DEFAULT NULL,
  `estado` varchar(10) NOT NULL,
  `usuarioid` int(11) DEFAULT NULL,
  `img` varchar(29) DEFAULT NULL,
  `categoria` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id`, `nombre`, `descripcion`, `valor_tp`, `estado`, `usuarioid`, `img`, `categoria`) VALUES
(2, 'p', 'p', 6, 'p', 2, 'imgs\\ankyan.jpg', 'p'),
(12, 'liyuu', 'liyuu', 0, 'liyuu', 2, 'imgs\\liyuu.jpg', 'liyuu'),
(13, 'ankyan', 'yohane', 0, 'yoshiko', 2, 'imgs\\ankyan.jpg', 'anime'),
(14, 'gigachad', 'musedeidad', 0, 'godines', 2, 'imgs\\gigachad.jpg', 'anime'),
(15, 'shukita', 'shukita toda preciosa', 0, 'shukita', 2, 'imgs\\shukita.jpg', 'mi novia'),
(16, 'agurisssssssssssssss', 'mi flaquita', 0, 'casada ', 2, 'imgs\\agurisssssssssssssss.jpg', 'sip');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `truques_productos`
--

CREATE TABLE `truques_productos` (
  `idtp` int(11) NOT NULL,
  `idProductoVender` int(11) NOT NULL,
  `idProductoComprar` int(11) NOT NULL,
  `idUsuarioInteresado` int(11) NOT NULL,
  `textoComprador` text DEFAULT NULL,
  `textoVendedor` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `dni` varchar(9) DEFAULT NULL,
  `nombre` varchar(15) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `email` varchar(20) DEFAULT NULL,
  `contrasena` varchar(12) NOT NULL,
  `img` longtext DEFAULT NULL,
  `truquepoints` int(11) NOT NULL,
  `preferencias` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `dni`, `nombre`, `apellidos`, `email`, `contrasena`, `img`, `truquepoints`, `preferencias`) VALUES
(1, '12345678A', 'Juan', 'Pérez', 'juan@example.com', 'contraseña12', 'imgs\\ankyan.jpg', 100, NULL),
(2, 'a', 'a', 'a', 'a', 'a', 'imgs\\ankyan.jpg', 0, NULL),
(3, 'b', 'b', 'b', 'b', 'c', 'imgs\\ankyan.jpg', 0, NULL),
(4, 'c', 'c', 'c', 'c', 'c', 'imgs\\ankyan.jpg', 0, NULL),
(5, 'r', 'r', 'r', 'r', 'r', 'imgs\\ankyan.jpg', 0, 'Entretenimiento,Escolar,'),
(6, 'h', 'h', 'h', 'h', 'h', 'imgs\\ankyan.jpg', 0, 'Vehiculos,Escolar,'),
(7, 'p', 'p', 'p', 'p', 'p', 'imgs\\ankyan.jpg', 0, NULL),
(8, 'q', 'q', 'q', 'q', 'q', 'imgs\\ankyan.jpg', 0, 'Juegos,Infantil,'),
(9, 't', 't', 't', 't', 't', 'imgs\\ankyan.jpg', 0, 'Tecnología,Juegos,');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `chats`
--
ALTER TABLE `chats`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_producto` (`id_producto`),
  ADD KEY `id_interesado` (`id_interesado`);

--
-- Indices de la tabla `favoritos`
--
ALTER TABLE `favoritos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idProductoFav` (`Productoid`),
  ADD KEY `idUsuarioFav` (`Usuarioid`);

--
-- Indices de la tabla `mensajes`
--
ALTER TABLE `mensajes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_chat` (`id_chat`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idUsuario` (`usuarioid`);

--
-- Indices de la tabla `truques_productos`
--
ALTER TABLE `truques_productos`
  ADD PRIMARY KEY (`idtp`),
  ADD KEY `idProductoVender` (`idProductoVender`),
  ADD KEY `idUsuarioInteresado` (`idUsuarioInteresado`),
  ADD KEY `idProductoComprar` (`idProductoComprar`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `dni` (`dni`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `chats`
--
ALTER TABLE `chats`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `mensajes`
--
ALTER TABLE `mensajes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `truques_productos`
--
ALTER TABLE `truques_productos`
  MODIFY `idtp` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `chats`
--
ALTER TABLE `chats`
  ADD CONSTRAINT `chats_ibfk_1` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id`),
  ADD CONSTRAINT `chats_ibfk_2` FOREIGN KEY (`id_interesado`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `favoritos`
--
ALTER TABLE `favoritos`
  ADD CONSTRAINT `favoritos_ibfk_1` FOREIGN KEY (`Productoid`) REFERENCES `productos` (`id`),
  ADD CONSTRAINT `favoritos_ibfk_2` FOREIGN KEY (`Usuarioid`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `mensajes`
--
ALTER TABLE `mensajes`
  ADD CONSTRAINT `mensajes_ibfk_1` FOREIGN KEY (`id_chat`) REFERENCES `chats` (`id`),
  ADD CONSTRAINT `mensajes_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`usuarioid`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `truques_productos`
--
ALTER TABLE `truques_productos`
  ADD CONSTRAINT `truques_productos_ibfk_1` FOREIGN KEY (`idProductoVender`) REFERENCES `productos` (`id`),
  ADD CONSTRAINT `truques_productos_ibfk_2` FOREIGN KEY (`idUsuarioInteresado`) REFERENCES `usuarios` (`id`),
  ADD CONSTRAINT `truques_productos_ibfk_3` FOREIGN KEY (`idProductoComprar`) REFERENCES `productos` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
