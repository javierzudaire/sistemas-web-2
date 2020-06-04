/*
 *  Javier Zudaire
 */
package principal;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import static java.lang.System.exit;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;
import objetos.Producto;
import objetos.Tienda;

/**
 *
 * @author javierzudaire
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        ClienteAPI api = new ClienteAPI();
        Tienda mitienda = new Tienda();
        Scanner sc = new Scanner(System.in);

        System.out.println("\n==========================");
        System.out.println("|       BIENVENIDO!      |");
        System.out.println("==========================");
        System.out.println("| Opciones:              |");
        System.out.println("|     1. Login           |");
        System.out.println("|     2. Crear cuenta    |");
        System.out.println("|     3. XML -> HTML     |");
        System.out.println("==========================");
        System.out.print("\n-> Introduce opción: ");
        String bienvenido = sc.nextLine();

        try {
            Integer.parseInt(bienvenido);
        } catch (Exception e) {
            System.out.println("\n-- Error: opción inválida --\n");
            System.out.print("\n-> Introduce opción de nuevo (1, 2 ó 3): ");
            bienvenido = sc.nextLine();
        }

        while (Integer.parseInt(bienvenido) != 1 && Integer.parseInt(bienvenido) != 2 && Integer.parseInt(bienvenido) != 3) {
            System.out.println("\n-- Error: opción inválida --\n");
            System.out.print("\n-> Introduce opción de nuevo (1, 2 ó 3): ");
            bienvenido = sc.nextLine();
        }

        String response = null;

        if (Integer.parseInt(bienvenido) == 1) {

            System.out.print("\n-- Login --");
            System.out.print("\nEmail: ");
            String email = sc.nextLine();
            System.out.print("Contraseña: ");
            String pass = sc.nextLine();

            response = api.login(email, pass);

            while (response.equals("Usuario/contraseña incorrectos")) {
                System.out.println("\n-> " + response);
                System.out.println("\nIntente de nuevo");
                System.out.print("Email: ");
                email = sc.nextLine();
                System.out.print("Contraseña: ");
                pass = sc.nextLine();
                response = api.login(email, pass);
            }

        } else if (Integer.parseInt(bienvenido) == 2) {

            System.out.print("\n-- Crear cuenta --");
            System.out.print("\nNombre: ");
            String nombre = sc.nextLine();
            System.out.print("Apellido: ");
            String apellido = sc.nextLine();
            System.out.print("Email: ");
            String email = sc.nextLine();
            System.out.print("Contraseña: ");
            String pass = sc.nextLine();
            System.out.println("");

            response = api.signup(nombre, apellido, email, pass);

        } else if (Integer.parseInt(bienvenido) == 3) {

            System.out.print("\n-- Convertir XML a HTML --");
            System.out.println("\n1. Tienda");
            System.out.println("2. Producto");
            System.out.print("\nIntroduce opción: ");
            String conversion = sc.next();

            try {
                Integer.parseInt(conversion);
            } catch (Exception e) {
                System.out.println("\n-- Error: opción inválida --");
                System.out.print("\n-> Introduce opción de nuevo (1 ó 2): ");
                conversion = sc.next();
                System.out.println("");
            }

            while (Integer.parseInt(conversion) != 1 && Integer.parseInt(conversion) != 2 && Integer.parseInt(conversion) != 3) {
                System.out.println("\n-- Error: opción inválida --");
                System.out.print("\n-> Introduce opción de nuevo (1 ó 2): ");
                conversion = sc.next();
                System.out.println("");
            }

            if (Integer.parseInt(conversion) == 1) {

                System.out.print("\nIntroduce nombre de fichero: ");
                String ficheroTienda = sc.next();
                File objetoXML = new File("data/" + ficheroTienda);
                byte[] bytes = Base64.getEncoder().encode(Files.readAllBytes(objetoXML.toPath()));
                String encodedTienda = new String(bytes);

                String html = api.convertTienda(encodedTienda);

                byte[] bytes2 = Base64.getDecoder().decode(html);
                InputStream stream = new ByteArrayInputStream(bytes2);
                String result = CharStreams.toString(new InputStreamReader(stream, Charsets.UTF_8));
                System.out.println(result);

                exit(0);

            } else if (Integer.parseInt(conversion) == 2) {

                System.out.print("\nIntroduce nombre de fichero: ");
                String ficheroProducto = sc.next();
                File objetoXML = new File("data/" + ficheroProducto);
                byte[] bytes = Base64.getEncoder().encode(Files.readAllBytes(objetoXML.toPath()));
                String encodedProducto = new String(bytes);

                String html = api.convertProducto(encodedProducto);

                byte[] bytes2 = Base64.getDecoder().decode(html);
                InputStream stream = new ByteArrayInputStream(bytes2);
                String result = CharStreams.toString(new InputStreamReader(stream, Charsets.UTF_8));
                System.out.println(result);

                exit(0);

            }

        }

        Token.setToken(response);

        if (api.getTienda() != null) {
            System.out.println("\nBienvenido! Existe la tienda " + api.getTienda().getNombre());
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

                System.out.println("\n--- CREACIÓN TIENDA ---");
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

                api.setTienda(mitienda);
            }
        } else {
            System.out.println("\n--- CREACIÓN TIENDA ---");
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

            api.setTienda(mitienda);
        }

        System.out.println("\n==================================");
        System.out.println("|              MENU              |");
        System.out.println("==================================");
        System.out.println("| Opciones:                      |");
        System.out.println("|     1. Añadir producto         |");
        System.out.println("|     2. Listar productos        |");
        System.out.println("|     3. Obtener producto        |");
        System.out.println("|     4. Borrar  producto        |");
        System.out.println("|     5. Actualizar producto     |");
        System.out.println("|     6. Exportar tienda         |");
        System.out.println("|     7. Importar tienda         |");
        System.out.println("|     8. Exportar producto       |");
        System.out.println("|     9. Importar producto       |");
        System.out.println("|     10. Información tienda     |");
        System.out.println("|     11. Nueva tienda           |");
        System.out.println("|     12. Salir                  |");
        System.out.println("==================================\n");

        int opcionNum = 0;

        while (opcionNum != 12) {

            System.out.print("Seleccionar opción (* para mostrar menu): ");
            String opcionn = sc.next();

            if (opcionn.equals("*")) {

                System.out.println("\n==================================");
                System.out.println("|              MENU              |");
                System.out.println("==================================");
                System.out.println("| Opciones:                      |");
                System.out.println("|     1. Añadir producto         |");
                System.out.println("|     2. Listar productos        |");
                System.out.println("|     3. Obtener producto        |");
                System.out.println("|     4. Borrar  producto        |");
                System.out.println("|     5. Actualizar producto     |");
                System.out.println("|     6. Exportar tienda         |");
                System.out.println("|     7. Importar tienda         |");
                System.out.println("|     8. Exportar producto       |");
                System.out.println("|     9. Importar producto       |");
                System.out.println("|     10. Información tienda     |");
                System.out.println("|     11. Nueva tienda           |");
                System.out.println("|     12. Salir                  |");
                System.out.println("==================================\n");

                System.out.print("Seleccionar opción: ");
                opcionn = sc.next();
                System.out.println();
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
                        System.out.println("\n-- Error: precio no válido --");
                        System.out.println("-- Formato: X.XX --\n");
                        System.out.print("Introduce precio: ");
                        precio = sc.next();
                    }

                    Producto x = new Producto();
                    x.setEAN(EAN);
                    x.setNombre(nombreProducto);
                    x.setDescripcion(descripcion);
                    x.setPrecio(Double.parseDouble(precio));

                    api.setProducto(x);

                    System.out.println();

                    break;

                case 2:
                    System.out.println("\n--- LISTA DE PRODUCTOS --- ");
                    ArrayList productos = api.getTienda().getProductos();

                    if (productos.isEmpty()) {
                        System.out.println("\nTienda vacía, añade productos a la tienda\n");
                        break;
                    }

                    int i = 0;
                    for (Object p : productos) {
                        i += 1;
                        System.out.println("\n* Producto " + i + " *");
                        Producto producto = (Producto) p;
                        StringBuilder sb = new StringBuilder();
                        sb.append("- EAN: ").append(producto.getEAN()).append("\n")
                                .append("- Nombre: ").append(producto.getNombre()).append("\n")
                                .append("- Descripción: ").append(producto.getDescripcion()).append("\n")
                                .append("- Precio: ").append(producto.getPrecio());
                        System.out.println(sb.toString());
                        System.out.println();

                    }
                    break;

                case 3:
                    System.out.println("\n--- OBTENER PRODUCTO --- ");
                    ArrayList productos2 = api.getTienda().getProductos();

                    if (productos2.isEmpty()) {
                        System.out.println("\nTienda vacía, añade productos a la tienda\n");
                        break;
                    }

                    System.out.print("\nIntroduce el EAN del producto que deseas obtener: ");
                    String EAN2 = sc.next();

                    Producto producto = api.getProducto(EAN2);

                    if (producto.getNombre() == null) {
                        System.out.println("\n* Producto no encontrado, intente de nuevo *\n");
                        break;
                    }

                    System.out.println("\n* Producto encontrado *");
                    StringBuilder sb = new StringBuilder();
                    sb.append("- EAN: ").append(producto.getEAN()).append("\n")
                            .append("- Nombre: ").append(producto.getNombre()).append("\n")
                            .append("- Descripción: ").append(producto.getDescripcion()).append("\n")
                            .append("- Precio: ").append(producto.getPrecio());
                    System.out.println(sb.toString());
                    System.out.println();

                    break;

                case 4:
                    System.out.println("\n--- BORRAR PRODUCTO --- ");
                    ArrayList productos3 = api.getTienda().getProductos();

                    if (productos3.isEmpty()) {
                        System.out.println("\nTienda vacía, añade productos a la tienda\n");
                        break;
                    }

                    System.out.print("\nIntroduce el EAN del producto que deseas borrar: ");
                    String EAN3 = sc.next();

                    api.deleteProducto(EAN3);
                    System.out.println("");

                    break;

                case 5:
                    System.out.println("\n--- ACTUALIZAR PRODUCTO --- ");
                    ArrayList productos4 = api.getTienda().getProductos();

                    if (productos4.isEmpty()) {
                        System.out.println("\nTienda vacía, añade productos a la tienda\n");
                        break;
                    }

                    int i2 = 0;
                    for (Object p : productos4) {
                        i2 += 1;
                        System.out.println("\n* Producto " + i2 + " *");
                        Producto producto2 = (Producto) p;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("- EAN: ").append(producto2.getEAN()).append("\n")
                                .append("- Nombre: ").append(producto2.getNombre()).append("\n")
                                .append("- Descripción: ").append(producto2.getDescripcion()).append("\n")
                                .append("- Precio: ").append(producto2.getPrecio());
                        System.out.println(sb2.toString());
                        System.out.println();

                    }

                    System.out.print("\nIntroduce el EAN del producto que deseas actualizar: ");
                    String EAN4 = sc.next();

                    System.out.println("===================================");
                    System.out.println("| Opciones:                       |");
                    System.out.println("|     1. Modificar nombre         |");
                    System.out.println("|     2. Modificar descripción    |");
                    System.out.println("|     3. Modificar precio         |");
                    System.out.println("===================================\n");
                    System.out.print("-> Introduce opción: ");
                    String modificar = sc.next();

                    try {
                        Integer.parseInt(modificar);
                    } catch (Exception e) {
                        System.out.println("\n-- Error: opción inválida --\n");
                        System.out.print("\n-> Introduce opción: ");
                        modificar = sc.next();
                        System.out.println("");
                    }

                    while (Integer.parseInt(modificar) != 1 && Integer.parseInt(modificar) != 2 && Integer.parseInt(modificar) != 3) {
                        System.out.println("\n-- Error: opción inválida --\n");
                        System.out.print("\n-> Introduce opción: ");
                        modificar = sc.nextLine();
                        System.out.println("");
                    }

                    String valor = null;

                    if (Integer.parseInt(modificar) == 1) {
                        System.out.print("\nIntroduce nuevo nombre: ");
                        valor = sc.next();
                    } else if (Integer.parseInt(modificar) == 2) {
                        System.out.print("\nIntroduce nueva descripción: ");
                        valor = sc.next();
                    } else if (Integer.parseInt(modificar) == 3) {
                        System.out.print("\nIntroduce nuevo precio: ");
                        valor = sc.next();

                        try {
                            Double.parseDouble(valor);
                        } catch (Exception e) {
                            System.out.println("\n-- Error: precio no válido --");
                            System.out.println("-- Formato: X.XX --\n");
                            System.out.print("Introduce precio: ");
                            valor = sc.next();
                        }

                    }

                    api.updateProducto(EAN4, modificar, valor);
                    System.out.println("");

                    break;

                case 6:
                    System.out.println("\n--- EXPORTAR TIENDA --- ");
                    System.out.print("\nIntroducir nombre de fichero: ");
                    String filename = sc.next();

                    String resultado = api.exportTienda();
                    byte[] decoded = Base64.getDecoder().decode(resultado);
                    try (FileOutputStream stream = new FileOutputStream("data/" + filename)) {
                        stream.write(decoded);
                    }

                    System.out.println();
                    break;

                case 7:
                    System.out.println("\n--- IMPORTAR TIENDA --- ");
                    System.out.print("\nIntroduce nombre de fichero: ");
                    String ficheroTienda = sc.next();
                    File tiendaXML = new File("data/" + ficheroTienda);
                    byte[] bytes = Base64.getEncoder().encode(Files.readAllBytes(tiendaXML.toPath()));
                    String encodedTienda = new String(bytes);
                    api.importTienda(encodedTienda);
                    System.out.println();
                    break;

                case 8:
                    System.out.println("\n--- EXPORTAR PRODUCTO --- ");
                    System.out.print("\nIntroducir nombre de fichero: ");
                    String filename2 = sc.next();

                    ArrayList productos5 = api.getTienda().getProductos();

                    if (productos5.isEmpty()) {
                        System.out.println("\nTienda vacía, añade productos a la tienda\n");
                        break;
                    }

                    int i3 = 0;
                    for (Object p : productos5) {
                        i3 += 1;
                        System.out.println("\n* Producto " + i3 + " *");
                        Producto producto2 = (Producto) p;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("- EAN: ").append(producto2.getEAN()).append("\n")
                                .append("- Nombre: ").append(producto2.getNombre()).append("\n")
                                .append("- Descripción: ").append(producto2.getDescripcion()).append("\n")
                                .append("- Precio: ").append(producto2.getPrecio());
                        System.out.println(sb2.toString());
                        System.out.println();

                    }

                    System.out.print("Introduce EAN del producto a exportar: ");
                    String productoExport = sc.next();
                    String resultado2 = api.exportProducto(productoExport);
                    System.out.println();
                    byte[] decoded2 = Base64.getDecoder().decode(resultado2);
                    try (FileOutputStream stream = new FileOutputStream("data/" + filename2)) {
                        stream.write(decoded2);
                    }
                    break;

                case 9:
                    System.out.println("\n--- IMPORTAR PRODUCTO --- ");
                    System.out.print("\nIntroduce nombre de fichero: ");
                    String ficheroProducto = sc.next();
                    File productoXML = new File("data/" + ficheroProducto);
                    byte[] bytes2 = Base64.getEncoder().encode(Files.readAllBytes(productoXML.toPath()));
                    String encodedProducto = new String(bytes2);
                    api.importProducto(encodedProducto);
                    System.out.println();
                    break;

                case 10:
                    Tienda t = api.getTienda();
                    System.out.println("\n--- INFORMACIÓN TIENDA --- ");
                    System.out.println("\n- Nombre: " + t.getNombre());
                    System.out.println("- Dirección: " + t.getDireccion());
                    System.out.println("- Teléfono: " + t.getTelefono());
                    System.out.println();
                    break;

                case 11:
                    System.out.println("\n--- CREACIÓN TIENDA ---");
                    System.out.print("\nIntroduce nombre: ");
                    String nombre = sc.next();
                    System.out.print("Introduce dirección: ");
                    String direccion = sc.next();
                    System.out.print("Introduce teléfono: ");
                    String telefono = sc.next();
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

                    api.setTienda(mitienda);
                    System.out.println();
                    break;

                case 12:
                    System.out.println("\nSaliendo...");
                    break;

            }

        }

    }

}
