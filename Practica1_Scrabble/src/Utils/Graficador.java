/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ana_j
 */
public class Graficador {

    //Este metodo se puede usar para graficar cualquier grafo
    //debido a q solo necesita al ruta del dot y la ruta de la salida->imagen
    private void crearGrafo(String ruta_dot, String ruta_png) {
        String tParam = "-Tpng";
        String tOParam = "-o";

        String[] cmd = new String[5];

        //cmd[0]="dot"; para linux-unix
        //cmd[0]="dot.exe"; para windows
        //cmd[0]="dot"; para mac, creo
        cmd[0] = this.pametro_en_base_sistema_operativo();
        cmd[1] = tParam;
        cmd[2] = ruta_dot;
        cmd[3] = tOParam;
        cmd[4] = ruta_png;
        Runtime rt = Runtime.getRuntime();

        try {
            //Hace la llamada al sistema y ejecuta la variable cmd
            rt.exec(cmd);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //Este devuelve el parametro que va ha tener para generar la imagen
    //el parametro depende el sistema operativo
    private String pametro_en_base_sistema_operativo() {
        String OS = System.getProperty("os.name").toLowerCase();

        if (OS.contains("win")) {
            return "dot.exe";
        } else if (OS.contains("nix") || OS.contains("nux") || OS.indexOf("aix") > 0) {
            return "dot";
        } else if (OS.contains("mac")) {
            return "dot";
        } else {
            System.out.println("Saber :D");
        }

        return "dot";
    }

    public void graficar(String nombre, String contenido) {

        //Creo una carpeta en /home/usuario/SalidasDot, en donde va estar todo
        File folder = new File(System.getProperty("user.home") + File.separator + "SalidasDot");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        //Rutas para el .dot y la imagen .png
        String ruta_dot = System.getProperty("user.home") + File.separator + "SalidasDot" + File.separator + nombre + ".dot";
        String ruta_png = System.getProperty("user.home") + File.separator + "SalidasDot" + File.separator + nombre + ".png";

        //Creamos el archivo 
        creararchivo(ruta_dot, contenido);
        //Se crea el grafo
        crearGrafo(ruta_dot, ruta_png);
        //Se abre la imagen
        autoAbrir(ruta_png);
    }

    //Este metodo es generico
    //Porque crea un archivo plano en base a la ruta y el contenido que se le pase
    public synchronized void creararchivo(String pfichero, String pcontenido) {
        FileWriter archivo = null;

        try {
            archivo = new FileWriter(pfichero);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        File a = new File(pfichero);
        if (!a.exists()) {
            return;
        }

        try (PrintWriter printwriter = new PrintWriter(archivo)) {
            printwriter.print(pcontenido);
            printwriter.close();
        }
    }

    //Este metodo es generico
    //Por que abre un fichero, archivo, etc. en base a la ruta
    //Lo abre con el programa predeterminado por el sistema
    private void autoAbrir(String ruta) {
        try {
            File archivo = new File(ruta);
            if (archivo.exists()) {
                Desktop.getDesktop().open(archivo);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
