/*
 *  Javier Zudaire
 */
package principal;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Scanner;
import servidortienda.GestionTienda;
import servidortienda.GestionTienda_Service;
import servidortienda.Producto;
import servidortienda.Tienda;

/**
 *
 * @author javierzudaire
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        GestionTienda_Service service = new GestionTienda_Service();
        GestionTienda tienda = service.getGestionTiendaPort();

        Tienda mitienda = new Tienda();
        Scanner sc = new Scanner(System.in);

        if (tienda.getTienda().getNombre() != null) {
            System.out.println("Bienvenido! Existe la tienda " + tienda.getTienda().getNombre());
            System.out.print("\nIntroduzca: ");
            System.out.println("\n1 -> Para continuar con la misma tienda ");
            System.out.println("2 -> Para crear una tienda nueva ");
            String op = sc.nextLine();

            if (!op.equals("1") && !op.equals("2")) {
                System.out.println(op);
                System.out.println("\n-- Error: opción incorrecta --\n");
                System.out.print("Introduce opción de nuevo (1 ó 2): ");
                op = sc.nextLine();
                System.out.println("");

            } else if (op.equals("2")) {

                System.out.println("--- CREACIÓN TIENDA ---");
                System.out.print("\nIntroduce nombre: ");
                String nombre = sc.nextLine();
                System.out.print("Introduce dirección: ");
                String direccion = sc.nextLine();
                System.out.print("Introduce teléfono: ");
                String telefono = sc.nextLine();
                try {
                    Integer.parseInt(telefono);
                } catch (Exception e) {
                    System.out.println("\n-- Error: teléfono no válido --\n");
                    System.out.print("Introduce teléfono: ");
                    telefono = sc.nextLine();
                    System.out.println("");
                }

                mitienda.setNombre(nombre);
                mitienda.setDireccion(direccion);
                mitienda.setTelefono(Integer.parseInt(telefono));

                tienda.setTienda(mitienda);

            }
        } else {
            System.out.println("--- CREACIÓN TIENDA ---");
            System.out.print("\nIntroduce nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Introduce dirección: ");
            String direccion = sc.nextLine();
            System.out.print("Introduce teléfono: ");
            String telefono = sc.nextLine();
            try {
                Integer.parseInt(telefono);
            } catch (Exception e) {
                System.out.println("\n-- Error: teléfono no válido --\n");
                System.out.print("Introduce teléfono: ");
                telefono = sc.nextLine();
                System.out.println("");
            }

            mitienda.setNombre(nombre);
            mitienda.setDireccion(direccion);
            mitienda.setTelefono(Integer.parseInt(telefono));

            tienda.setTienda(mitienda);

        }

        System.out.println("===============================");
        System.out.println("|             MENU            |");
        System.out.println("===============================");
        System.out.println("| Opciones:                   |");
        System.out.println("|     1. Añadir producto      |");
        System.out.println("|     2. Listar productos     |");
        System.out.println("|     3. Exportar tienda      |");
        System.out.println("|     4. Importar tienda      |");
        System.out.println("|     5. Exportar producto    |");
        System.out.println("|     6. Importar producto    |");
        System.out.println("|     7. Salir                |");
        System.out.println("===============================\n");

        int opcionNum = 0;

        while (opcionNum != 7) {

            System.out.print("Seleccionar opción (* para mostrar menu): ");
            String opcionn = sc.next();

            if (opcionn.equals("*")) {

                System.out.println("\n===============================");
                System.out.println("|             MENU            |");
                System.out.println("===============================");
                System.out.println("| Opciones:                   |");
                System.out.println("|     1. Añadir producto      |");
                System.out.println("|     2. Listar productos     |");
                System.out.println("|     3. Exportar tienda      |");
                System.out.println("|     4. Importar tienda      |");
                System.out.println("|     5. Exportar producto    |");
                System.out.println("|     6. Importar producto    |");
                System.out.println("|     7. Salir                |");
                System.out.println("===============================\n");

                System.out.print("Seleccionar opción: ");
                opcionn = sc.next();
                System.out.print("Recibida " + opcionn);
            }

            try {
                opcionNum = Integer.parseInt(opcionn);
            } catch (Exception e) {
                System.out.println("Opcion inválida");
            }

            switch (opcionNum) {
                case 1:

                    System.out.println("\n--- AÑADIR PRODUCTO ---");
                    System.out.print("\nIntroduce EAN: ");
                    sc.nextLine();
                    String EAN = sc.nextLine();
                    System.out.print("Introduce nombre: ");
                    String nombreProducto = sc.nextLine();
                    System.out.print("Introduce descripción: ");
                    String descripcion = sc.nextLine();
                    System.out.print("Introduce precio: ");
                    String precio = sc.next();
                    try {
                        Double.parseDouble(precio);
                    } catch (Exception e) {
                        System.out.println("\n-- Error: precio no válido --\n");
                        System.out.println("\n-- Formato: X.XX --\n");
                        System.out.print("Introduce precio: ");
                        precio = sc.next();
                    }

                    Producto x = new Producto();
                    x.setEan(EAN);
                    x.setNombre(nombreProducto);
                    x.setDescripcion(descripcion);
                    x.setPrecio(Double.parseDouble(precio));

                    tienda.setProducto(x);

                    System.out.println();

                    break;

                case 2:
                    System.out.println("\n--- LISTA DE PRODUCTOS --- ");
                    servidortienda.Tienda t = tienda.getTienda();
                    Tienda.Productos productos = t.getProductos();

                    if (productos.getProducto().isEmpty()) {
                        System.out.println("\nTienda vacía, añade productos a la tienda\n");
                    }

                    int i = 0;
                    for (Object p : productos.getProducto()) {
                        i += 1;
                        System.out.println("\n* Producto " + i + " *");
                        Producto producto = (Producto) p;
                        StringBuilder sb = new StringBuilder();
                        sb.append("- EAN: ").append(producto.getEan()).append("\n")
                                .append("- Nombre: ").append(producto.getNombre()).append("\n")
                                .append("- Descripción: ").append(producto.getDescripcion()).append("\n")
                                .append("- Precio: ").append(producto.getPrecio());
                        System.out.println(sb.toString());
                        System.out.println();

                    }
                    break;

                case 3:
                    System.out.println("\n--- EXPORTAR TIENDA --- ");
                    System.out.print("\nIntroducir nombre de fichero: ");
                    String filename = sc.next();

                    String resultado = tienda.exportTienda();
                    byte[] decoded = Base64.getDecoder().decode(resultado);
                    try (FileOutputStream stream = new FileOutputStream("data/" + filename)) {
                        stream.write(decoded);
                    }

                    System.out.println();
                    break;

                case 4:
                    System.out.println("\n--- IMPORTAR TIENDA --- ");
                    System.out.print("\nIntroduce nombre de fichero: ");
                    String ficheroTienda = sc.next();
                    File tiendaXML = new File("data/" + ficheroTienda);
                    byte[] bytes = Base64.getEncoder().encode(Files.readAllBytes(tiendaXML.toPath()));
                    String encodedTienda = new String(bytes);
                    tienda.importTienda(encodedTienda);
                    System.out.println();
                    break;

                case 5:
                    System.out.println("\n--- EXPORTAR PRODUCTO --- ");
                    System.out.print("\nIntroducir nombre de fichero: ");
                    String filename2 = sc.next();

                    servidortienda.Tienda t2 = tienda.getTienda();
                    Tienda.Productos productos2 = t2.getProductos();

                    int ii = 0;
                    for (Object p : productos2.getProducto()) {
                        ii += 1;
                        System.out.println("\n* Producto " + ii + " *");
                        Producto producto = (Producto) p;
                        StringBuilder sb = new StringBuilder();
                        sb.append("- EAN: ").append(producto.getEan()).append("\n")
                                .append("- Nombre: ").append(producto.getNombre()).append("\n")
                                .append("- Descripción: ").append(producto.getDescripcion()).append("\n")
                                .append("- Precio: ").append(producto.getPrecio());
                        System.out.println(sb.toString());
                        System.out.println();

                    }

                    System.out.print("Introduce EAN del producto a exportar: ");
                    String productoExport = sc.next();
                    String resultado2 = tienda.exportProducto(productoExport);
                    System.out.println();
                    byte[] decoded2 = Base64.getDecoder().decode(resultado2);
                    try (FileOutputStream stream = new FileOutputStream("data/" + filename2)) {
                        stream.write(decoded2);
                    }

                    System.out.println();
                    break;

                case 6:
                    System.out.println("\n--- IMPORTAR PRODUCTO --- ");
                    System.out.print("\nIntroduce nombre de fichero: ");
                    String ficheroProducto = sc.next();
                    File productoXML = new File("data/" + ficheroProducto);
                    byte[] bytes2 = Base64.getEncoder().encode(Files.readAllBytes(productoXML.toPath()));
                    String encodedProducto = new String(bytes2);
                    tienda.importProducto(encodedProducto);
                    System.out.println();
                    break;
                case 7:
                    System.out.println("\nSaliendo...");
                    break;
            }
        }

    }

}
