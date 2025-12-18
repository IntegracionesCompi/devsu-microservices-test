# Prueba Técnica – Microservicios

Implementación de la prueba técnica solicitada utilizando dos microservicios y comunicación asíncrona con RabbitMQ.

---

## Servicios
- **cliente-service**: gestión de clientes y personas.
- **cuenta-service**: gestión de cuentas, movimientos y reportes.

---

## Tecnologías
- Java / Spring Boot  
- JPA  
- RabbitMQ  
- PostgreSQL  
- Docker / Docker Compose  
- Postman  

---

## Levantar el proyecto

Desde la raíz del proyecto:

```bash
docker-compose up -d --build
```
 INSTRUCCIONES PARA LEVANTAR EL PROYECTO

Descomprimir el proyecto o clonar el repositorio.

Ubicarse en la raíz del proyecto, donde se encuentra el archivo docker-compose.yml.

Ejecutar el siguiente comando:

   docker-compose up -d --build

Verificar que los contenedores estén levantados con:

    docker-compose ps


_______________________________________________________________________________________________
USO DE LA COLECCIÓN DE POSTMAN

    Importar el archivo:

    postman-devsu-test.json

Con esta colección se pueden realizar las siguientes acciones:

        GESTIÓN DE CLIENTES

                Crear cliente
                POST /clientes

                Consultar clientes
                GET /clientes
                GET /clientes/{id}

                Actualizar cliente
                PUT /clientes/{id}
                PATCH /clientes/{id}

                Eliminar o desactivar cliente
                DELETE /clientes/{id}

        GESTIÓN DE CUENTAS

                Crear cuenta
                POST /cuentas

                Consultar cuentas
                GET /cuentas
                GET /cuentas/{id}

        MOVIMIENTOS

                Registrar movimiento (depósito o retiro)
                POST /movimientos

                Consultar movimientos
                GET /movimientos

                    Si se intenta realizar un retiro sin saldo suficiente, el sistema responde con el mensaje:

                    Saldo no disponible

       _____________________________________________________________________________________________________    

        REPORTES

        Obtener estado de cuenta por cliente y rango de fechas:

        GET /reportes?clienteId={id}&fechaInicio=YYYY-MM-DD&fechaFin=YYYY-MM-DD

        El reporte retorna las cuentas asociadas al cliente y el detalle de movimientos dentro del rango indicado.

        _________________________________________________________________________________________________

        COMUNICACIÓN ASÍNCRONA

        Al registrar un movimiento:

        El cuenta-service publica un evento en RabbitMQ.

        El cliente-service consume el evento y almacena la información del movimiento.

        La comunicación entre microservicios se realiza de forma asíncrona.
