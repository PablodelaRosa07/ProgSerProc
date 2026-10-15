package Models;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Exception.GestionFicherosException;
import Models.EstadoComparacion;

public class DiffFolder {
    private static final Logger logger = LogManager.getLogger(DiffFolder.class);
    private File folder1;
    private File folder2;

    public void setFolders(File folder1, File folder2) throws GestionFicherosException {
        if (folder1 == null || !folder1.exists() || !folder1.isDirectory() || 
            folder2 == null || !folder2.exists() || !folder2.isDirectory()) {
            throw new GestionFicherosException("Error: Ambos elementos deben existir y ser directorios válidos.");
        }
        
        this.folder1 = folder1;
        this.folder2 = folder2;
    }

    public File getFolder1() {
        return folder1;
    }

    public File getFolder2() {
        return folder2;
    }


    private Set<String> obtenerNombresArchivos(File carpeta) {
        Set<String> nombres = new HashSet<>();
        File[] contenido = carpeta.listFiles();
        
        if (contenido != null) {
            for (File file : contenido) {
                if (file.isFile()) { 
                    nombres.add(file.getName());
                }
            }
        }
        return nombres;
    }
    

    public Collection<ResultadoComparacion> comparame() throws GestionFicherosException {
        if (folder1 == null || folder2 == null) {
            throw new GestionFicherosException("Error: Los directorios no han sido inicializados.");
        }

        Set<String> archivos1 = obtenerNombresArchivos(folder1);
        Set<String> archivos2 = obtenerNombresArchivos(folder2);

        Collection<ResultadoComparacion> resultados = new ArrayList<>();

        Set<String> principal;
        Set<String> secundario;
        File carpetaPrincipal;
        File carpetaSecundaria;
        boolean esDirectorio1Principal;

        if (archivos1.size() >= archivos2.size()) {
            principal = archivos1;
            secundario = archivos2;
            carpetaPrincipal = folder1;
            carpetaSecundaria = folder2;
            esDirectorio1Principal = true;
            logger.info("El directorio 1 (" + folder1.getName() + ") tiene más o igual número de elementos. Se procesará en primer lugar.");
        } else {
            principal = archivos2;
            secundario = archivos1;
            carpetaPrincipal = folder2;
            carpetaSecundaria = folder1;
            esDirectorio1Principal = false;
            logger.info("El directorio 2 (" + folder2.getName() + ") tiene más elementos. Se procesará en primer lugar.");
        }

        for (String nombre : principal) {
            File archivoPrincipal = new File(carpetaPrincipal, nombre);

            if (secundario.contains(nombre)) {
                File archivoSecundario = new File(carpetaSecundaria, nombre);
                double tiempo1 = archivoPrincipal.lastModified();
                double tiempo2 = archivoSecundario.lastModified();

                if (tiempo1 == tiempo2) {
                    resultados.add(new ResultadoComparacion(nombre, EstadoComparacion.IGUALES));
                } else {
                    if (esDirectorio1Principal) {
                        if (tiempo1 > tiempo2) {
                            resultados.add(new ResultadoComparacion(nombre, EstadoComparacion.MENOS_NUEVO_EN_2));
                        } else {
                            resultados.add(new ResultadoComparacion(nombre, EstadoComparacion.MENOS_NUEVO_EN_1));
                        }
                    } else {
                        if (tiempo2 > tiempo1) {
                            resultados.add(new ResultadoComparacion(nombre, EstadoComparacion.MENOS_NUEVO_EN_1));
                        } else {
                            resultados.add(new ResultadoComparacion(nombre, EstadoComparacion.MENOS_NUEVO_EN_2));
                        }
                    }
                }
            } else {
                if (esDirectorio1Principal) {
                    resultados.add(new ResultadoComparacion(nombre, EstadoComparacion.FALTA_EN_2));
                } else {
                    resultados.add(new ResultadoComparacion(nombre, EstadoComparacion.FALTA_EN_1));
                }
            }
        }

        for (String nombre : secundario) {
            if (!principal.contains(nombre)) {
                if (esDirectorio1Principal) {
                    resultados.add(new ResultadoComparacion(nombre, EstadoComparacion.FALTA_EN_1));
                } else {
                    resultados.add(new ResultadoComparacion(nombre, EstadoComparacion.FALTA_EN_2));
                }
            }
        }

        return resultados;
    }

    public void compararArchivos() throws GestionFicherosException {
        Collection<ResultadoComparacion> resultado = comparame();
        for (ResultadoComparacion r : resultado) {
            logger.info(r.toString());
        }
    }
}