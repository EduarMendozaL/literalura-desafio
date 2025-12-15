package com.desafio.literalura.principal;

import com.desafio.literalura.service.ConsumoApi;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoApi consumoApi = new ConsumoApi();


    public void muestraMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
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
        System.out.println("Ingrese el nombre o parte del título");
        var titulo = teclado.nextLine();

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
