# challengeFuegoQuasar

API para determinar la distancia, ubicación y contenido de un mensaje de auxilio de los satélites de la fuerza.

# Requerimientos

- JDK 1.8 o superior.
- IDE Eclipse con plugin para AWS.
- Una cuenta en la consola de AWS para desplegar y probar las Lambdas.
- SoapUI para probar los servicios REST

# Arquitectura de la Aplicación

La aplicación es desarrollada para que funcione en la arquitectura Serverless  o  relacionada  formalmente con el término FaaS (Function as a Service), para esto me apoye en servicios propios de AWS Como lambdas, Api Gateway y S3, a continuación una imagen donde se describe mejor lo anteriormente expuesto: 

![Arquitectura Meli](https://github.com/JorgeTamara22/challengeFuegoQuasar/blob/master/post2.png)

# Nivel 1

Se crea un proyecto AWS lambda Java para implementar las fuciones GetLocation y GetMessage

![Arquitectura Meli](https://github.com/JorgeTamara22/challengeFuegoQuasar/blob/master/lambdas.png)

# Servicios REST

Para exponer los servicios REST al cliente se utilizó Api Gateway de AWS, los cuales se describen a continuación:

![Arquitectura Meli](https://github.com/JorgeTamara22/challengeFuegoQuasar/blob/master/api.png)

## topsecret (Nivel 2)
[https://u5fhoiwlve.execute-api.us-east-2.amazonaws.com/prod/topsecret](https://u5fhoiwlve.execute-api.us-east-2.amazonaws.com/prod/topsecret)

- Metodo: POST
- Request: Este metodo tiene como entrada una lista de satelites con la siguiente estructura en JSON:

{ "satelites": [ { "name": "kenobi", "distance": 485.7, "message": ["este", "", "", "mensaje", ""] }, { "name": "skywalker", "distance": 266.1, "message": ["", "es", "", "", "secreto"] }, { "name": "sato", "distance": 600.5, "message": ["este", "", "un", "", ""] } ] }

- Response: La respuesta del metodo tiene la siguiente estructura:
{
   "position":    {
      "x": -100.00688339798238,
      "y": 75.51770663789418
   },
   "message": "este es un mensaje secreto"
}

## topsecret (Nivel 3)
[https://u5fhoiwlve.execute-api.us-east-2.amazonaws.com/prod/topsecret-split?satellite_name=XXXX](https://u5fhoiwlve.execute-api.us-east-2.amazonaws.com/prod/topsecret-split?satellite_name=XXXX)
- Metodo: POST
- Request: Este metodo tiene como entrada un objeto satelites y recibe un parametro llamado satellite_name con la siguiente estructura en JSON:
 
{ "distance": 600.5, "message": ["este", "", "un", "", ""] }

- Response: La respuesta del metodo tiene la siguiente estructura:
{
   "position":    {
      "x": -100.00688339798238,
      "y": 75.51770663789418
   },
   "message": "este es un mensaje secreto"
}

## topsecret (Nivel 3)
[https://u5fhoiwlve.execute-api.us-east-2.amazonaws.com/prod/topsecret-split](https://u5fhoiwlve.execute-api.us-east-2.amazonaws.com/prod/topsecret-split)
- Metodo: GET
- Request: No tiene entrada
- Response: La respuesta del metodo tiene la siguiente estructura:
{
   "position":    {
      "x": -100.00688339798238,
      "y": 75.51770663789418
   },
   "message": "este es un mensaje secreto"
}

# Test

Para ejecutar los tests correr el programa con JUnit Test
