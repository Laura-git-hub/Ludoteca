# Proyecto SpringBoot - Aplicación Web para una Ludoteca

Este proyecto es una aplicación Web que sirva de catálogo de juegos.

## Estructura del Proyecto

En este proyecto nos dedicaremos a crear el backend donde se desarrollarán los endpoints que interactuarán más adelante con el frontend, la lógica del negocio y la capa de datos.

## Tecnologías Utilizadas

- Java
- IntelliJ
- SpringBoot
- Spring Web API
- H2 Hibernate
- Maven
- Postman

## Flujo de la Aplicación

- El usuario abre el navegador y solicita una web mediante una URL
- El servidor frontend, le sirve los recursos (páginas web, javascript, imágenes, ...) y se cargan en el navegador
- El navegador renderiza las páginas web, ejecuta los procesos javascript y realiza las navegaciones
- Si en algún momento se requiere invocar una operación, el navegador lanzará una petición contra una URL del backend
- El backend estará escuchando las peticiones y las ejecutará en el momento que le invoquen devulviendo un resultado al navegador
- Si hiciera falta leer o guardar datos, el backend lo realizará lanzando consultas SQL contra la BBDD

## Estructura de la Aplicación Web

- Los datos que vienen y van al cliente están en formato json
- Al entrar en un Controller esos datos json se transforman en un DTO.
- Al salir del Controller hacia el cliente, esos DTOs se transforman en formato json. 
- Cuando un Controller ejecuta una llamada a un Service le pasa sus datos en DTO, y el Service se encarga de transformar esto a una Entity.
- A la inversa, cuando un Service responde a un Controller, él responde con una Entity y el Controller ya se encargará de transformarlo a DTO.
- Los Repository, siempre se trabaja de entrada y salida con objetos tipo Entity

## Instrucciones de Uso 

- Clona el siguiente repositorio:
https://github.com/Laura-git-hub/Ludoteca.git
- Abre el proyecto en IntelliJ.
- Ejecuta la aplicación desde IntelliJ.


