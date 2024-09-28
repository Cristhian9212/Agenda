# Agenda de contactos

Esta es una aplicación desarrollada en **Kotlin** utilizando **Jetpack Compose** para gestionar contactos. La app permite agregar, visualizar, editar y eliminar contactos, presentando una interfaz moderna con degradados suaves, íconos circulares y tarjetas interactivas.

## Características

- **Agregar contactos**: Los usuarios pueden ingresar nombres, apellidos, teléfono y dirección en un formulario interactivo en la pantalla A.
- **Visualización de contactos**: Los contactos ingresados se listan automáticamente en la pantalla B. Cada contacto tiene su propio `Card` que muestra un ícono con la inicial del nombre, nombre completo, y más detalles cuando se expande.
- **Eliminar contacto**: Cada contacto tiene un botón de eliminar que permite borrar el contacto seleccionado.
- **Editar contacto**: Cada contacto tiene un botón de editar para modificar los datos del contacto.
- **Navegación entre pantallas**: Los usuarios pueden navegar entre la pantalla A (agregar contacto) y la pantalla B (visualizar contactos).
- **Interfaz estética**: Se ha implementado un diseño moderno con degradados de colores suaves y botones flotantes redondos.

## Estructura de la Aplicación

- **Pantalla A**: Permite al usuario ingresar los datos de un contacto y cuenta con dos botones flotantes:
  - **Botón Guardar**: Guarda el contacto y lo añade a la lista.
  - **Botón Agenda**: Navega hacia la pantalla B para ver los contactos guardados.
  
- **Pantalla B**: Muestra una lista de contactos en formato `LazyColumn`, donde cada contacto se presenta en una tarjeta (`ContactCard`). Cada tarjeta incluye:
  - Un ícono circular con la inicial del nombre.
  - Nombre del contacto y detalles (al hacer clic en la tarjeta se expande para mostrar apellidos, teléfono y dirección).
  - Botones de editar y eliminar para cada contacto.

## Componentes Principales

### Pantalla A (`screenA.kt`)

- **Formulario**: Incluye campos para ingresar nombres, apellidos, teléfono y dirección.
- **Botones flotantes**: El diseño de los botones flotantes (Guardar y Agenda) es circular con un degradado suave, similar al botón de regreso en la pantalla B.

### Pantalla B (`screenB.kt`)

- **Lista de Contactos**: Se utiliza un `LazyColumn` para mostrar todos los contactos guardados.
- **Tarjeta de Contacto**: Cada contacto tiene una `ContactCard`, la cual incluye:
  - Un ícono circular con la inicial del nombre.
  - Nombre del contacto y detalles que se muestran al hacer clic en la tarjeta.
  - Botón para **Editar** y **Eliminar** el contacto.

## Funcionamiento

1. El usuario puede ingresar los datos de un contacto en la pantalla A y guardarlos usando el botón flotante de Guardar.
2. Luego, puede navegar a la pantalla B para ver la lista de contactos.
3. En la pantalla B, el usuario puede editar o eliminar contactos directamente desde la lista.
4. La navegación entre pantallas se realiza utilizando `NavController`.

## Requisitos

- **Android Studio** versión Koala.
- **Kotlin** como lenguaje principal.
- **Jetpack Compose** como framework para la interfaz de usuario.

## Instalación

1. Clonar el repositorio.
2. Abrir el proyecto en Android Studio.
3. Ejecutar la aplicación en un emulador o dispositivo físico.

## Créditos

Desarrollado por **Cristhian Camilo Fernandez Castro**.
