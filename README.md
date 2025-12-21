# 📚 Literalura – Challenge con Spring Boot + JPA

Proyecto en Java utilizando Spring Boot, JPA/Hibernate y una API externa de libros.  
Permite buscar libros, registrar autores y consultar información almacenada en la base de datos.

---

## 🚀 Tecnologías utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL / MySQL / H2 (según configuración)
- API externa de libros (Gutenberg)

---

## 🧩 Funcionalidades principales

✔ Buscar un libro por nombre usando la API externa  
✔ Guardar en la base de datos solo un resultado por título  
✔ Relación Autor – Libro:

- Un autor puede tener varios libros
- Un libro pertenece a un único autor

✔ Consultar:
- libros registrados
- autores registrados
- autores vivos en un año determinado
- libros por idioma

---

## 🔗 Relación de entidades

Se utilizan solo **dos tablas en la base de datos**:

- `autor`
- `libro`

Con relación **OneToMany / ManyToOne**

---

## 🗂 Estructura básica del proyecto

```
src/main/java/com/desafio/literalura
├── principal
│ └── Principal.java
├── model
│ ├── Autor.java
│ └── Libro.java
├── repository
│ ├── AutorRepository.java
│ └── LibroRepository.java
└── LiteraluraApplication.java
```
---

## 🛢 Configuración base de datos (application.properties)

Ejemplo con PostgreSQL:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/nombre_bd
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
---

## ▶ Ejecución

Una vez levantada la aplicación, se muestra un menú en consola:

1️⃣ Buscar libro  
2️⃣ Mostrar libros registrados  
3️⃣ Mostrar autores registrados  
4️⃣ Consultar autores vivos por año  
5️⃣ Consultar libros por idioma  
0️⃣ Salir

---

## ✨ Ejemplo de salida de libros registrados

```
----------LIBRO----------
Título: Don Quijote
Autor: Cervantes Saavedra, Miguel de
Idioma: es
Número de descargas: 2599.0
-------------------------
```

---

## 📌 Consideraciones

- Se guarda solo un libro por consulta.
- Si el autor ya existe, se reutiliza.
- Si el libro ya existe, no se vuelve a registrar.

---

## 📄 Licencia

Proyecto libre para práctica y aprendizaje.
