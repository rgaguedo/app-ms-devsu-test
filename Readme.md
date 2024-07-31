# Prueba Técnica Micro Servicios Devsu - Backend Java

## Iniciar el proyecto
1. Realizar el build de los micro servicios:
```
maven package -f pom.xml
```
2. Una vez generado los artefactos se debe usar `docker-compose` para construir la imagen con los contenedores de los micro servicios:

```
docker-compose up --build
```

Se adjunta el archivo `Microservices API.postman_colletion.json` el cual servirá para importar a POSTMAN y realizar las transacciones respectivas.

## Realización de pruebas
Para realizar las pruebas unitarias ejecutando el siguiente comando:
```
maven test -f pom.xml
```

**Observación:** Los comandos deberán ejecutarse en la raíz del proyecto.