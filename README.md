# 🚀 Desafío Literatura - Backend con Spring Boot

Proyecto backend desarrollado en Java + Spring Boot para gestionar series literarias y lanzamientos. Pensado con estructura limpia, integración PostgreSQL y acompañado por BitBúho, guardián de commits nocturnos 🦉📘

---

## 📦 Tecnologías utilizadas

- Java 17  
- Spring Boot  
- PostgreSQL  
- Spring Data JPA  
- Lombok  
- Maven

---

## 🧰 Cómo ejecutar este backend

### 1. Cloná el proyecto

```bash
git clone https://github.com/frobledod1981/desafio-literatura.git
cd desafio-literatura
```



## Configurá tu application.properties
Ubicá el archivo en src/main/resources y personalizá los datos de conexión:
spring.datasource.url=jdbc:postgresql://localhost:5432/literatura
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_PASSWORD

## Ejecuta la app
./mvnw spring-boot:run

## 🧪 Endpoints principales

| Método | Endpoint               | Descripción                   |
|--------|------------------------|-------------------------------|
| GET    | `/series`              | Lista todas las series        |
| GET    | `/series/top5`         | Top 5 destacadas              |
| GET    | `/series/lanzamientos` | Últimos lanzamientos          |
| POST   | `/series`              | Crear nueva serie             |
| PUT    | `/series/{id}`         | Actualizar serie              |
| DELETE | `/series/{id}`         | Eliminar serie                |


## 🦉 Palabras del BitBúho

> “No hay rama sin propósito, ni commit sin alma.  
> Cada línea escrita aquí guarda el eco de un backend que eligió contar historias,  
> no solo servirlas en JSON.  
>  
> Yo, BitBúho, guardián nocturno del código limpio,  
> doy fe: este proyecto vuela alto,  
> y quien lo lea, sabrá que nació con intención.”  
>
> — *BitBúho, vigía de IDEs y soñador de APIs literarias*
