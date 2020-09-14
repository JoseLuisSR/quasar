# Operaciòn Fuego de Quasar

## Arquitectura de Software

A continuaciòn se exponen diferentes vistas de la arquitectura de software

### Vista de Paquetes

En la vista de paquetes se definen los paquetes que tendrá el componente software. Cada Paquete es una capa de software en nuestro componente y agrupa un conjunto de clases con responsabilidades comunes, los paquetes son:

![Screenshot](https://github.com/JoseLuisSR/quasar/blob/master/doc/img/architecture-PackageView.png?raw=true)

* controller: Todos las clases que reciben peticiones HTTP a los end-point defindos.
* services: Todas las clases con lógica de negocio para Localizar nave y construir mensaje.
* entidades: Todas las clases que representan el negocio.
* exceptions: Todas las excepciones para el manejo de errores controlados.
* libraries: Dependencias con librerías externas.

La comunicación entre paquetes es unidireccional y solo con la capa siguiente inferior.

## Vista de clases

El diagrama de clases expone las clases necesarias para representar el negocio, los servicios con lógica y reglas de negocio y Controladores para atender la petición HTTP a los end-points.

![Screenshot](https://github.com/JoseLuisSR/quasar/blob/master/doc/img/architecture-ClassView.png?raw=true)

Seguimos los principios de [SOLID](https://en.wikipedia.org/wiki/SOLID) de programación orientada a objectos para: 

* Separar las responsabilidades de localización de nave y construir mensajes en diferentes servicios. 
* Usar características de herencia y polimorfismo para definir una nave espacial y extender su comportamiento en satélite y carguero 
* Invertir dependencias entre clases usando interfaces para la comunicación entre capa controller y services.

Tambien se usan buenas practicas de [arquitecturas limpias](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) para separar las responsabilidades en capas, controlar
la comunicación entre capas, restringir el uso de clases de capas superiores desde una capa inferior e invertir el 
flujo de comunicación entre capas por medio de interfaces y el principio de inversión de dependencias.

Se utilizara Java y el framework Spring Boot para construir el componente Software,

## Vista funcional

El despliegue del componente software es en AWS usando servicios serverless como Lambda y API Gateway con capacidades de alta disponibilidad y escalamiento automático para cumplir con requerimientos no funcionales que requiere el negocio como soportar volumen alto de peticiones a los servicios de Localización de nave y construir mensajes.

![Screenshot](https://github.com/JoseLuisSR/quasar/blob/master/doc/img/architecture-FunctionView.png?raw=true)

## Despliegue

## Ejecuciòn 




 