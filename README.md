# Ejercicio Tecnico "Operación Fuego de Quasar"

### Para poder correr la aplicacion en local es necesario primero crear la base de datos corriengo 
### el siguiente comando de docker
```
    docker-compose up --build
```

### Una vez que la base de datos este activa se debera correr el siguiente comando en otra consola

```
    mvn spring-boot:run -Dspring-boot.run.profiles=dev
```
## Los endpoints expuestos por la API son:

### Top Secret

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

### Top Secret Split

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
NOTA: En caso de que se carguen mas de una vez datos para un mismo satelite, se considerara como 
valida la ultima.

## Algoritmos

### GetLocation

Para resolver su funcionalidad se utilizo la trilateración, que consiste en proyectar un circulo 
sobre cada uno de los puntos y encontrar un punto donde los 3 circulos se intersecten.

### GetMessage

En este caso se toma el arreglo mas corto, considerandolo el mas cercano al largo real del mensaje 
y se completan los espacios en blanco a partir de los otros mensajes, para que el desfasaje no 
afecte el mensaje los arreglos son recorridos de atras para adelante.