# challengeFuegoQuasar

API para determinar la distancia, ubicación y contenido de un mensaje de auxilio de los satélites de la fuerza.

# Requerimientos

- JDK 1.8 o superior.
- IDE Eclipse con plugin para AWS.
- Una cuenta en la consola de AWS para desplegar y probar las Lambdas.

# Arquitectura de la Aplicación

La aplicación es desarrollada para que funcione en la arquitectura Serverless  o  relacionada  formalmente con el término FaaS (Function as a Service), para esto me apoye en servicios propios de AWS Como lambdas, Api Gateway y S3, a continuación una imagen donde se describe mejor lo anteriormente expuesto: 

![Arquitectura Meli](https://github.com/JorgeTamara22/challengeFuegoQuasar/blob/master/Arquitectura%20challenge%20MELI.png)

# Servicios REST

Para exponer los servicios REST al cliente se utilizó Api Gateway de AWS, los cuales se describen a continuación:


