package com.desafio.literalura.principal;

import com.desafio.literalura.model.Autor;
import com.desafio.literalura.model.Datos;
import com.desafio.literalura.model.DatosLibro;
import com.desafio.literalura.model.Libro;
import com.desafio.literalura.repository.AutorRepository;
import com.desafio.literalura.repository.LibroRepository;
import com.desafio.literalura.service.ConsumoApi;
import com.desafio.literalura.service.ConvierteDatos;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Component
public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private static final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvierteDatos convierteDatos = new ConvierteDatos();

    private LibroRepository libroRepository;
    private AutorRepository autorRepository;

    public Principal(LibroRepository libroRepository,
                     AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void muestraMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    \n*****   MENU LITERALURA   *****
                    
                    1 - Buscar libro por título
                    2 - Mostrar libros registrados
                    3 - Mostrar autores registrados
                    4 - Mostrar autores vivos en un determinado año
                    5 - Mostrar libros por idioma
                    
                    0 - Salir
                    """;
            System.out.println(menu);
            try {
                opcion = teclado.nextInt();
                teclado.nextLine();

                switch (opcion) {
                    case 1:
                        buscarLibroPorTitulo();
                        break;

                    case 2:
                        mostrarLibrosRegistrados();
                        break;

                    case 3:
                        mostrarAutoresRegistrados();
                        break;

                    case 4:
                        mostrarAutoresPorAnio();
                        break;

                    case 5:
                        mostrarLibrosPorIdioma();
                        break;

                    case 0:
                        System.out.println("Cerrando la aplicación...");
                        break;

                    default:
                        System.out.println("Opción inválida\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingresar solo número\n");
                teclado.nextLine();
            }
        }
    }

    private void buscarLibroPorTitulo() {
        System.out.println("Ingrese el nombre o parte del título a buscar");
        var titulo = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + titulo.replace(" ", "+"));
        Datos datos = convierteDatos.obtenerDatos(json, Datos.class);
        var resultados = datos.resultados();

        if (resultados == null || resultados.isEmpty()) {
            System.out.println("No se encontraron libros con ese título");
            return;
        }

        DatosLibro datoLibro = resultados.get(0);

        if (libroRepository.existsByTitulo(datoLibro.titulo())) {
            System.out.println("El libro ya está ingresado: " + datoLibro.titulo());
            return;
        }

        Libro libro = new Libro(datoLibro);

        List<Autor> autores = datoLibro.autor().stream()
                .map(datosAutor -> autorRepository
                        .findByNombre(datosAutor.nombre())
                        .orElseGet(() -> autorRepository.save(new Autor(datosAutor))))
                .toList();

        libro.setAutores(autores);
        libroRepository.save(libro);

        System.out.println("Libro guardado: " + libro.getTitulo());
    }

    private void mostrarLibrosRegistrados() {
        var libros = libroRepository.findAll();

        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados");
            return;
        }

        libros.forEach(libro -> {
            System.out.println("Título: " + libro.getTitulo());
            System.out.println("Autor: ");
            libro.getAutores().forEach(autor -> System.out.println(autor.getNombre()));
            System.out.println("Idioma: " + libro.getIdioma());
            System.out.println("Número de Descargas: " + libro.getNumeroDeDescargas());
            System.out.println("---------------------------");
        });
    }

    private void mostrarAutoresRegistrados() {
        var autores = autorRepository.findAll();

        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados");
            return;
        }

        autores.forEach(autor -> {
            System.out.println("Autor: " + autor.getNombre());
            System.out.println("Año de Nacimiento: " + autor.getAnioNacimiento());
            System.out.println("Año de Fallecimiento: " + autor.getAnioFallecimiento());
            System.out.println("---------------------------");
        });
    }

    private void mostrarAutoresPorAnio() {
    }

    private void mostrarLibrosPorIdioma() {
    }

}
