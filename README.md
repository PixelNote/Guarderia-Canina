# API Rest Guarderia Canina

## Integrantes
* Daniela Alejandra Puerto
* Yeyson Esteban Pulido
* Andrés Felipe Jiménez 

##  Requerimientos
•	Permitir registrar clientes con los siguientes atributos: Nombre, documento, Dirección de residencia, nombre mascota.
•	El cliente debe poder reservar el espacio para su mascota a través de su número de documento y el día en que desea el servicio, el sistema debe validar que no se supere la capacidad permitida de la guardería por día que es de 20 perros.
•	El sistema debe permitir consultar los perros que estan programados para un día determinado
•	El sistema debe permitir consultar las reservas que ha tenido un cliente en toda la historia
•	Ahora, es necesario separar la creación del cliente y el de la mascota para permitir crear un cliente y posterior asociar mascotas que queden asociadas a ese cliente.
•	Un cliente no podrá tener más de 2 mascotas y al momento de reservar se debe verificar que el usuario no tenga una mascota anteriormente reservada para ese día.
•	Debe haber un endpoint que permita consultar a través de un número de documento las mascotas asociadas a un cliete.
Para ejecutar:
•	Se requiere Java 19.
Rutas:
• Para ver lista de clientes:
/clientes
• Para agregar cliente:
/cliente
• Para agregar mascota:
/mascota
• Para ver lista de mascotas:
/mascotas/todas
• Para ver mascota específica de un dueño:
/mascotas
• Para agregar reserva:
/reserva
• Para ver reservas por fecha:
/fecha
• Para ver historial de reservas de un cliente:
/documento		
			
			
			
			

## Para ejecutar:

* Se requiere Java 19.

* Usar en puerto http://localhost:8088

## Rutas:

•	Para ver lista de clientes: 


http://localhost:8088/clientes

•	Para agregar cliente: 


http://localhost:8088/cliente

•	Para agregar reserva: 


http://localhost:8088/reserva

•	Para ver reservas por fecha: 


http://localhost:8088/fecha/{fecha}

•	Para ver historial de reservas de un cliente: 


http://localhost:8088/documento/{documento}

## Diagrama de base de datos
![Diagrama de base de datos](https://raw.githubusercontent.com/PixelNote/Guarderia-Canina/main/src/main/resources/Diagrama.jpeg)
