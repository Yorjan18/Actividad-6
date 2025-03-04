package Archivos;

import java.io.*;
import java.util.*;

public class Eventos {

    private File archivo;
    private boolean Error;

    public Eventos() {
        archivo = new File("Contactos.txt");
    }

    public void Agregar_Texto(String texto) {
        try (FileWriter escritor = new FileWriter(archivo, true)) {
            escritor.write(texto + "\n");
            Error = false;
        } catch (IOException e) {
            Error = true;
        }
    }

    public void Eliminar_Dato(String nombre) {
        List<String> lineas = new ArrayList<>();
        boolean encontrado = false;

        archivo = new File("Contactos.txt");

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split("!");
                String nombreArchivo = partes[0];

                if (!nombreArchivo.equalsIgnoreCase(nombre)) {
                    lineas.add(linea);  
                } else {
                    encontrado = true;
                }
            }
        } catch (IOException e) {
            Error = true;
            return;
        }

        if (!encontrado) {
            Error = true;
            return;
        }

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo))) {
            for (String linea : lineas) {
                escritor.write(linea);
                escritor.newLine();
            }
            Error = false;
        } catch (IOException e) {
            Error = true;
        }
    }


    public String Obtener_Celular(String nombre) {
        archivo = new File("Contactos.txt");

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split("!");
                if (partes.length == 2) {
                    String nombreArchivo = partes[0];
                    String celular = partes[1];

                    if (nombreArchivo.equalsIgnoreCase(nombre)) {
                        return celular;  
                    }
                }
            }
        } catch (IOException e) {
            Error = true;
        }

        return null;  
    }

    public void Editar_Dato(String nombre, String nuevoCelular) {
        archivo = new File("Contactos.txt");
        List<String> lineas = new ArrayList<>();
        boolean encontrado = false;

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split("!");
                if (partes.length == 2) {
                    String nombreArchivo = partes[0];

                    if (nombreArchivo.equalsIgnoreCase(nombre)) {
                        lineas.add(nombre + "!" + nuevoCelular); 
                        encontrado = true;
                    } else {
                        lineas.add(linea); 
                    }
                }
            }
        } catch (IOException e) {
            Error = true;
            return;
        }

        if (!encontrado) {
            Error = true;
            return;
        }

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo))) {
            for (String linea : lineas) {
                escritor.write(linea);
                escritor.newLine();
            }
            Error = false;
        } catch (IOException e) {
            Error = true;
        }
    }

    public void Verificar_Dato(String nombre, String A) {
        boolean encontrado = false;
        archivo = new File("Contactos.txt");

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split("!");  
                String nombreArchivo = partes[0];    
                System.out.println(nombre);

                if (nombreArchivo.equalsIgnoreCase(nombre)) {
                    System.out.println("Jo");
                    encontrado = true;
                    break;  
                }
            }
        } catch (IOException e) {
            Error = true;
            return;
        }
        System.out.println(encontrado);
        
        if (A.equals("Crear")) {
            Error = encontrado;  
        } else if (A.equals("Eliminar")) {
            Error = !encontrado; 
        }
    }


    public boolean getError() {
        return Error;
    }
}
