package com.ParkingSystem.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    @Value("${analytics.output.path:../Analytics/outputs}")
    private String analyticsOutputPath;

    @Value("${analytics.script.path:../Analytics/main.py}")
    private String scriptPath;

    @Value("${analytics.script.timeout:60}")
    private int scriptTimeout;

    @Value("${python.executable.path:../Analytics/venv/Scripts/python.exe}")
    private String pythonExecutable;

    private static final Set<String> ALLOWED_EXTENSIONS = Set.of("png", "jpg", "jpeg", "html", "pdf");

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        try {
            String ext = filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();
            if (!ALLOWED_EXTENSIONS.contains(ext)) {
                return ResponseEntity.badRequest().build();
            }

            Path basePath = Paths.get(analyticsOutputPath).normalize().toRealPath();
            Path filePath = basePath.resolve(filename).normalize();

            if (!filePath.startsWith(basePath)) {
                return ResponseEntity.badRequest().build();
            }

            Resource resource = new FileSystemResource(filePath.toFile());
            if (!resource.exists() || !resource.isReadable()) {
                return ResponseEntity.notFound().build();
            }

            String contentType = switch (ext) {
                case "png" -> MediaType.IMAGE_PNG_VALUE;
                case "jpg", "jpeg" -> MediaType.IMAGE_JPEG_VALUE;
                case "html" -> MediaType.TEXT_HTML_VALUE;
                case "pdf" -> MediaType.APPLICATION_PDF_VALUE;
                default -> MediaType.APPLICATION_OCTET_STREAM_VALUE;
            };

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<String> refreshAnalytics() {
        try {
            // Verificar que el script existe
            File scriptFile = new File(scriptPath);
            if (!scriptFile.exists()) {
                return ResponseEntity.status(500).body("No se encuentra el script: " + scriptPath);
            }

            ProcessBuilder pb = new ProcessBuilder(pythonExecutable, scriptPath);
            pb.directory(scriptFile.getParentFile()); // Establece el directorio del script
            pb.redirectErrorStream(true);
            Process process = pb.start();

            // Leer la salida para depuración
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            boolean finished = process.waitFor(scriptTimeout, TimeUnit.SECONDS);
            if (finished && process.exitValue() == 0) {
                return ResponseEntity.ok("Gráficos actualizados correctamente");
            } else {
                return ResponseEntity.status(500).body("Error al ejecutar script: " + output.toString());
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Excepción: " + e.getMessage());
        }
    }
}