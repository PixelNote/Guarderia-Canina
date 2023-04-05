# API Rest Guarderia Canina

## Integrantes
* Daniela Alejandra Puerto
* Yeyson Esteban Pulido
* Andrés Felipe Jiménez 

## Requerimientos
*	Permitir registrar clientes con los siguientes atributos: Nombre, documento, Dirección de residencia, nombre mascota.
*	El cliente debe poder reservar el espacio para su mascota a través de su número de documento y el día en que desea el servicio, el sistema debe validar que no se supere la capacidad permitida de la guardería por día que es de 20 perros.
* El sistema debe permitir consultar los perros que estan programados para un día determinado
*	El sistema debe permitir consultar las reservas que ha tenido un cliente en toda la historia
*	Ahora, es necesario separar la creación del cliente y el de la mascota para permitir crear un cliente y posterior asociar mascotas que queden asociadas a ese cliente.
*	Un cliente no podrá tener más de 2 mascotas y al momento de reservar se debe verificar que el usuario no tenga una mascota anteriormente reservada para ese día.
*	Debe haber un endpoint que permita consultar a través de un número de documento las mascotas asociadas a un cliete.
## Para ejecutar:
*	Se requiere Java 19.
## Rutas:
*	Para ver lista de clientes:
/clientes
*	Para agregar cliente:
/cliente
*	Para agregar mascota:
/mascota
*	Para ver lista de mascotas:
/mascotas/todas
*	Para ver mascota específica de un dueño:
/mascotas
*	Para agregar reserva:
/reserva
*	Para ver reservas por fecha:
/fecha
*	Para ver historial de reservas de un cliente:
/documento		

## Diagrama de base de datos
![Diagrama de base de datos](https://raw.githubusercontent.com/PixelNote/Guarderia-Canina/main/src/main/resources/Diagrama.jpeg)

## Coverage
Usando Jacoco se verificó el coverage dispuesto por las pruebas de unitarias y de integración realizadas a los servcios y controladores de la guardería.

![image](https://user-images.githubusercontent.com/101272542/230238944-687ae9ff-125b-4594-9fd3-76f9fc411406.png)

![image](https://user-images.githubusercontent.com/101272542/230238955-2308647b-a7a1-4151-9a2c-4efd092fa9d2.png)

