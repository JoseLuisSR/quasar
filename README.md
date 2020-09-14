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

### Vista de clases

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

### Vista funcional

El despliegue del componente software es en AWS usando servicios serverless como Lambda y API Gateway con capacidades de alta disponibilidad y escalamiento automático para cumplir con requerimientos no funcionales que requiere el negocio como soportar volumen alto de peticiones a los servicios de Localización de nave y construir mensajes.

![Screenshot](https://github.com/JoseLuisSR/quasar/blob/master/doc/img/architecture-FunctionView.png?raw=true)

La función lambda tendrá el contexto de ejecución de Java 8 y 512 de memoria.

Lambda permite configurar reserva de concurrencia y aprovisionamiento de concurrencia para el despliegue de más instancias de la función lambda.
Por cuenta de AWS y región se tiene un máximo de 1000 instancias compartidas entre todas las funciones lambda, se puede escalar a 20.000 abriendo
caso de soporte con AWS.

En API Gateway se especifican los recursos (end-points), operaciones HTTP permitidas y la integración con la función lambda.
Los recursos deben corresponde con los creados en el proyecto Spring Boot, son:

* /api/v1/topsecret POST

### Vista de Despliegue

Por medio del servicio CloudFormation de infraestructura como código se despliega el componente software en la función lambda y 
se crea un API Gateway para exponer lo servicios web REST/JSON.

![Screenshot](https://github.com/JoseLuisSR/quasar/blob/master/doc/img/architecture-DeployView.png?raw=true)

Se utilizan Stacks anidados para tener plantilla por recurso AWS para Lambda y API Gateway, dividiendo las responsabilidades y creando plantillas especializadas por recurso de AWS.

Se carga las plantillas y el ejecutable del proyecto Spring Boot (.zip/.jar) a un bucket de S3 y posterior crea el NestStack en CloudFormation ingresando los siguientes parámetros:

* LambdaFunctionName: Nombre a elecciòn para la funciòn lambda.
* LambdaHandler: Packete y nombre de la clase que contiene la logica para recibir evento del API Gateway.
* LambdaRuntime: Java 8, lenguaje de programaciòn usa para construir el componente software.
* LambdaCodeS3Bucket: Nombre del bucket donde se encuentra el ejecutable Java (.zip, .jar).
* LambdaCodeS3Key: Nombre del ejecutable Java (.zip, .jar).
* TemplateURLambda: URL del template lambda.yaml en S3 bucket.
* TemplateURLApiGateway: URL del template apigateway.yaml en S3 bucket.

![Screenshot](https://github.com/JoseLuisSR/quasar/blob/master/doc/img/CloudFormationParameters.png?raw=true)

## Ejecuciòn 

Las siguiente es la URL del servicio publicado en AWS con API Gateway y Funciòn Lambda:

* Nivel 1: https://bnr3cdugnc.execute-api.us-east-2.amazonaws.com/ist/api/v1/topsecret

En el repositorio encuentra script de ejecuiòn de pruebas HTTP Pots con JMeter el cual tiene la URL configurada del servicio.Limitar la ejecuciòn de las pruebas para no generar cobros.

![Screenshot](https://github.com/JoseLuisSR/quasar/blob/master/doc/img/JMeterRequest.png?raw=true)

![Screenshot](https://github.com/JoseLuisSR/quasar/blob/master/doc/img/JMeterResponse.png?raw=true)




 