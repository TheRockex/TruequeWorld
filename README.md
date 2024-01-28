# **Público al que va dirigido la aplicación**

| [![TruequeWorld](https://github.com/TheRockex/TruequeWorld/blob/main/elegido.svg)](https://github.com/TheRockex/TruequeWorld) | La aplicación TruequeWorld está dirigida a cualquier usuario que tenga objetos a los que no de uso y quiera ganar algo por ellos, ya que ofrece la posibilidad conseguir algo que tenga más valor para esa persona intercambiándolo por algo que no utilice. La principal diferencia con otras aplicaciones de segunda mano es evitar la utilización de dinero a la hora de realizar estos “trueques”. |
| --- | --- |

*La basura de un hombre es el tesoro de otro* (autor desconocido).

<br/>
<br/>

# **Diseño:**
![Amarillo](https://github.com/TheRockex/TruequeWorld/blob/main/FFCA41.png)          ![Negro](https://github.com/TheRockex/TruequeWorld/blob/main/000000.png)

<br/>

- **Colores y sentido**: los colores principales de **TruequeWorld** son el amarillo `#FFCA41` y el negro `#000000`.
  <details>
  <summary>Leer más</summary>
  La elección del amarillo fue una decisión simple. La mayoría de aplicaciones relacionadas con la compra-venta de artículos de segunda mano utilizan derivados del verde y el azul. El rojo, el amarillo y el azul    son los 3 colores primarios; pero el rojo se asocia más frecuentemente a la comida; el azul y el verde nos parecían poco originales. Por otra parte, el amarillo, además de no estar presente como color principal   en ninguna aplicación de este tipo,  se caracteriza por transmitir sentimientos de felicidad, entusiasmo, creatividad, la divinidad y el valor; además de otros no tan positivos como la precaución y la alerta.     La felicidad por poder darle un uso a tus objetos consiguiendo otros nuevos, el entusiasmo de conseguir productos que ya no se encuentran a la venta oficialmente, el conocimiento de que lo que posees puede        tener más valor del que conoces; pero también el recordatorio de que hay que tener cuidado con los intercambios; buscamos generar todo esto mediante el uso del amarillo. El negro es un color que combina con el    resto; y al ser el color principal de tonalidad clara, resalta a la perfección los textos de toda la aplicación.
</details>
  

- **Fuentes**: se han utilizado las fuentes “*Gavora*” (para los títulos, al ser más llamativa) e “*Inter*” (para el resto de textos, similar a utilizar “*Calibri”* o “*Arial”* en un documento de texto). “*Gavora”* da a las letras una apariencia ligeramente geométrica y puntiaguda, lo que generaba armonía con el logotipo.

- **Paradigma de Material**: no se ha hecho uso de ningún tema generado por Material, pero se utilizarán estilos y strings de colores a la hora de llevar a la vida la aplicación.

<br/>
<br/>

# **Casos de uso**
Las APIs  que se plantea utilizar son las siguientes:

|API|Link|Uso|
| :-: | :-: | :-: |
|Chat|https://apilist.fun/api/channel-messaing-api|Para generar y gestionar los chats entre usuarios a tiempo real|
|Pago|https://apilist.fun/api/mastercard|Para gestionar la adición de tarjetas de crédito, utilizadas para diversas funciones como la suscripción premium o el canje de truequepuntos|
|Notificaciones|https://apilist.fun/api/yo-developers-api|Para alertar a los usuarios de diferentes acciones (como un nuevo mensaje, una interacción con su producto, actualizaciones…)|
|Localización|<https://apilist.fun/api/ip-geolocation-api>|Para establecer la ubicación de los usuarios y facilitar el intercambio de productos|
|Multiusos|https://apilist.fun/api/yclas|Para gestionar los anuncios (principal forma de ingreso); aunque proporciona más utilidades|
|Google|Credentials Manager|Para permitir a los usuarios acceder a la aplicación mediante su cuenta de Google|

> [!NOTE]
> Se intentarán utilizar todas. En caso de no poder, se aplicarán las más importantes

<br/>
<br/>

# **Actividades Android y organización**
Para una mejor explicación, la organización se va a dividir en pantallas independientes y pantallas dependientes de la barra de navegación (con sus respectivas subpantallas).

### Pantallas independientes

- **SplashScreen**: primera pantalla de la aplicación, presenta una animación de carga con el logotipo y el nombre
- **StartScreen**: ofrece la opción de iniciar sesión de 3 formas distintas
- **LoginScreen**: pantalla de inicio de sesión propia de la aplicación
- **RegisterScreen**: pantalla de registro propia de la aplicación
- **PreferencesScreen**: después de que un nuevo usuario introduzca los datos requeridos, se le solicitará información acerca de sus intereses
- **MiddleSplashScreen**: pantalla de carga intermedia entre la de preferencias y la principal

<br/>

### Pantallas dependientes de la barra de navegación:

- **MainActivityScreen**: pantalla principal de la aplicación; tanto a los nuevos usuarios como a los ya existentes se les redirige aquí después de introducir sus credenciales
    - **MainActivityScreen - See all selected** [^1]
        - MainActivityScreen - See all selected - Categories
- **SavedScreen**: pantalla que muestra los productos que el usuario ha marcado (no implica que se vayan a adquirir en el momento del marcado)
- **AddProductScreen**: pantalla que permite agregar un nuevo *TruequeProducto* al perfil del usuario
    - **CategoryScreen**: sub pantalla para seleccionar la categoría del producto
    - **BrandScreen**: sub pantalla para seleccionar la marca del producto
- **MessagesScreen**: pantalla que almacena las conversaciones entre usuarios, además de otras notificaciones (como interacciones de usuarios con los productos, actualizaciones de la aplicación...)
    - **ChatScreen**: sub pantalla individual de cada chat
- **UserScreen**: pantalla personal de cada usuario, donde se muestra su información y se ofrece la posibilidad de cambiar datos
    - **SettingsScreen**: sub pantalla para modificar los ajustes
    - **TruequePointsProScreen**: sub pantalla para suscribirse al servicio premium de *TruequeWorld*
    - **UserDetailsScreen**: sub pantalla para modificar los datos del usuario
    - **PrivacityScreen**: sub pantalla para ver los tratados de privacidad y protección de datos de la aplicación
    - **MyWalletScreen**: sub pantalla para añadir o eliminar tarjetas de crédito; indica también el saldo actual introducido en la aplicación

  <br/>
  <br/>

[^1]: Para conocer más detalles acerca de las pantallas, acceder al pdf del [Figma](https://github.com/TheRockex/TruequeWorld/blob/main/TruequeWorld.pdf)
