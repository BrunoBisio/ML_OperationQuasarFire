# Ejercicio Tecnico "Operación Fuego de Quasar"

## Para poder correr la aplicacion en local es necesario primero crear la base de datos corriengo el siguiente comando de docker
```
    docker-compose up --build
```

## Una vez que la base de datos este activa se debera correr el siguiente comando en otra consola 

```
    mvn spring-boot:run
```
## Los endpoints expuestos por la API son:

## Top Secret

| Tipo | URL |
| --- | --- |
| POST | https://localhost:8080/topsecret |

### La llamada POST debe tener un cuerpo con el siguiente formato:

```
    {
        "satellites": [
            {
                "name": "kenobi",
                "distance": 100.0,
                "message": [ "este", "", "", "mensaje", "" ]
            },
            {
                "name": "skywalker",
                "distance": 115.5,
                "message": [ "", "es", "", "", "secreto" ]
            },
            {
                "name": "sato",
                "distance": 142.7,
                "message": [ "este", "", "un", "", "" ]
            }
        ]
    }

```
NOTA: Por favor respetar el orden de los satelites. En caso contrario, la ubicacion obtenida podria ser erronea.

## Top Secret Split

| Tipo | URL |
| --- | --- |
| GET | https://localhost:8080/topsecret_split |
| POST | https://localhost:8080/topsecret_split/{satellite_name} |

### La llamada POST debe tener un cuerpo con el siguiente formato:

```
    {
        "distance": 100.0,
        "message": ["este", "", "", "mensaje", ""]
    }
```
NOTA: En caso de que se carguen mas de una vez datos para un mismo satelite, se considerara como valida la ultima.