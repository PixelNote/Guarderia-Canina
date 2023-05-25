# Bebitos Felices - Guarderia Canina, Proyecto Final

## Integrantes
* Daniela Alejandra Puerto
* Yeyson Esteban Pulido
* Andrés Felipe Jiménez 

## Requerimientos
* El sistema permite registrar clientes, almacenando atributos como nombre, documento y dirección de residencia.
* El sistema permite registrar una mascota, para posteriormente asociarla a un cliente, el cuál no puede tener más de 2 mascotas asociadas.
* El cliente puede reservar el espacio para su mascota con su número de documento y el día en que desea el servicio, siempre y cuando no tenga otra mascota anteriormente reservada para ese día.
* La guardería acepta máximo 20 reservas por día.
* Se deben poder consultar los perros que estan programados para un día determinado.
* Debe ser posible consultar el historial de reservas que ha tenido un cliente y las mascotas que tiene asociadas.
* Un sistema de notificaciones asícnronas debe avisar a los clientes cuando sus mascotas están listas para ser recogidas.
* Los usuarios reciben el mensaje en tiempo real a través de correo electrónico.
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

## Vista de contexto
![Diagramas - Página 1 (1)](https://github.com/PixelNote/Guarderia-Canina/assets/101272542/a245297f-dd07-4ef1-b349-70d782c6b982)

## Vista de contenedores
![Diagramas - Página 1](https://github.com/PixelNote/Guarderia-Canina/assets/101272542/14946699-290a-4679-b62f-8e5fdf04a032)

## Vista de componente
![Diagramas 2 (1)](https://github.com/PixelNote/Guarderia-Canina/assets/101272542/6d6d3e30-6920-47ea-8e76-132028a37576)

## Diagrama de paquetes
![Diagrama en blanco](https://github.com/PixelNote/Guarderia-Canina/assets/101272542/961b6d5f-2914-4cce-b0bf-321afb4edd8b)

## Diagrama de despliegue

## Diagrama de secuencia
![Diagrama de secuencia básico](https://github.com/PixelNote/Guarderia-Canina/assets/101272542/baf4722b-d8ce-423b-a3e1-6673e5778e20)

## Coverage
Usando Jacoco se verificó el coverage dispuesto por las pruebas unitarias y de integración realizadas a los servcios y controladores de la guardería.
![WhatsApp Image 2023-05-25 at 3 09 52 AM](https://github.com/PixelNote/Guarderia-Canina/assets/101272542/6bc81e09-b26b-4a93-b207-160725f5b7f8)
