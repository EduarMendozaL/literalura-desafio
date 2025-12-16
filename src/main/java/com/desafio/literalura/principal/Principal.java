package com.desafio.literalura.principal;

import com.desafio.literalura.model.Datos;
import com.desafio.literalura.service.ConsumoApi;
import com.desafio.literalura.service.ConvierteDatos;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private static final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvierteDatos convierteDatos = new ConvierteDatos();

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
        System.out.println("Será el json: " + json.length());
        System.out.println("lo que contiene json: " + json);
        Datos datos = convierteDatos.obtenerDatos(json, Datos.class);
//        datos.resultados().forEach(libro -> {
//            System.out.println("Título: " + libro.titulo());
//            System.out.println("Idiomas: " + libro.idiomas());
//            System.out.println("Descargas: " + libro.numeroDeDescargas());
//            System.out.println("Autores:");
//            libro.autor().forEach(autor ->
//                    System.out.println(" - " + autor.nombre())
//            );
//        });
        datos.resultados().forEach(System.out::println);
    }

    private void mostrarLibrosRegistrados() {
    }

    private void mostrarAutoresRegistrados() {
    }

    private void mostrarAutoresPorAnio() {
    }

    private void mostrarLibrosPorIdioma() {
    }

}
