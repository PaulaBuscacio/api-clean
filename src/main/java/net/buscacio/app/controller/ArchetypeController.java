package net.buscacio.app.controller;

import jakarta.annotation.Resource;
import net.buscacio.app.dto.ArchetypeRequestDTO;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("/archetype")
public class ArchetypeController {

    @PostMapping
    public ResponseEntity<?> generateProject(@RequestBody ArchetypeRequestDTO requestDTO) throws IOException, InterruptedException {
        List<String>  commands = Arrays.asList("C:\\maven\\apache-maven-3.9.9\\bin\\mvn.cmd", "archetype:generate",
                "-DarchetypeCatalog=local",
                "-DarchetypeGroupId=net.buscacio",
                "-DarchetypeArtifactId=clean-archetype-archetype",
                "-DarchetypeVersion=1.0.0",
                "-DgroupId=" + requestDTO.groupId(),
                "-DartifactId=" + requestDTO.artifactId(),
                "-DinteractiveMode=false");

        ProcessBuilder pb = new ProcessBuilder(commands); //("C:\\maven\\apache-maven-3.9.9\\bin\\mvn.cmd", "-v");
        pb.directory(new File("D:\\Documentos\\pos\\tcc")); // opcional
        pb.redirectErrorStream(true);
        Process process = pb.start();
        java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) System.out.println(line);
        process.waitFor();
        Path zipPath = Path.of("D:\\Documentos\\pos\\tcc\\"+requestDTO.artifactId()+".zip");
        zipDirectory(
                Path.of("D:\\Documentos\\pos\\tcc\\"+requestDTO.artifactId()),  zipPath);
        ProcessBuilder processBuilder = new ProcessBuilder("C:\\Program Files\\Git\\bin\\bash.exe","rm", "-rf","D:\\Documentos\\pos\\tcc\\"+requestDTO.artifactId());
        Process p = processBuilder.start();
        p.waitFor();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/zip"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + requestDTO.artifactId() + ".zip\"")
                .body("Projeto gerado com sucesso");

    }

    public void zipDirectory(Path sourceDirPath, Path zipFilePath) throws IOException {
        try (ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(zipFilePath))) {
            Files.walk(sourceDirPath)
                    .filter(path -> !Files.isDirectory(path))
                    .forEach(path -> {
                        ZipEntry zipEntry = new ZipEntry(sourceDirPath.relativize(path).toString());
                        try {
                            zs.putNextEntry(zipEntry);
                            Files.copy(path, zs);
                            zs.closeEntry();
                        } catch (IOException e) {
                            System.err.println(e);
                        }
                    });
        }
    }
}
