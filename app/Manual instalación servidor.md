# Como instalar el servidor en tu ordenador

## Requisitos

* Sistema operativo **Windows 10** o **Ubuntu 18.04**. Probado únicamente en estos dos.
* Servidor **[Apache Tomcat 9.0.31](https://tomcat.apache.org/download-90.cgi)** 
* **[XAMPP 7.4.2](https://www.apachefriends.org/es/index.html)**
* Fichero **.war**.
* Fichero **.sql**.

## Pasos previos
* Configurar la **IP** del equipo. **192.168.1.71 / 24**. *Una futura actualización nos permitirá seleccionar la IP desde el cliente para no tener que cambiar la IP del servidor.*
* Los dispositivos que se van a conectar al servidor deberán estar en el mismo rango de IP. **192.168.1.X / 24**.

## Insalación de la base de datos
1. Ejecutar MySQL.
2. Pulsar sobre **Nueva**.
3. **Importar**.
4. Pulsamos el botón de **seleccionar archivo** y buscamos el fichero **.sql** descargado.
5. **Continuar**.

Ya tenemos la base de datos instalada.

## Instalación del fichero .war en Tomcat.
1. Vamos a la carpeta donde hemos descargado Tomcat en su versión 9.0.31.
2. Abrimos la carpeta **conf**.
3. Editamos el fichero **tomcat-users.xml** y lo guardamos.


```
<tomcat-users>
    <user password="pass" roles="manager-gui" username="user" />
</tomcat-users>
```


4. Abrimos la carpeta **bin**.
5. Si estamos en Windows abrimos el fichero **startup.bat** o si estamos en Ubuntu ejecutamos el **startup.sh**.
6. Vamos a nuestro navegador y escribimos la dirección **[localhost:8080](localhost:8080)**.
7. **Manager App**.
8. Escribimos el **usuario** y la **contraseña** que hemos añadido en el paso 3.
9. Vamos a la sección de **Archivo WAR a desplegar**.
10. Seleccionamos el fichero .war que descargamos.
11. Pulsamos sobre el botón desplegar.

Ya tenemos el servidor preparado para poder empezar a utilizar el juego.
