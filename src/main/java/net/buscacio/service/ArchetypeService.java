package net.buscacio.service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class ArchetypeService {

    public ByteArrayResource generateProject(String groupId, String artifactId) throws IOException, InterruptedException {
        List<String> commands = Arrays.asList("C:\\maven\\apache-maven-3.9.9\\bin\\mvn.cmd", "archetype:generate",
                "-DarchetypeCatalog=local",
                "-DarchetypeGroupId=net.buscacio",
                "-DarchetypeArtifactId=clean-archetype-archetype",
                "-DarchetypeVersion=1.0.0",
                "-DgroupId=" + groupId,
                "-DartifactId=" + artifactId,
                "-DinteractiveMode=false");

        ProcessBuilder pb = new ProcessBuilder(commands);
        Path tempDir = Files.createTempDirectory("project");
        pb.directory(tempDir.toFile());
        pb.redirectErrorStream(true);
        pb.redirectErrorStream(true);
        Process process = pb.start();
        java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) System.out.println(line);
        process.waitFor();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Path projectPath = tempDir.resolve(artifactId);
        zipDirectory(projectPath, baos);
        Files.walk(tempDir)
                .sorted(java.util.Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);

        byte[] zipContent = baos.toByteArray();
        return new ByteArrayResource(zipContent) {
            @Override
            public String getFilename() {
                return artifactId + ".zip";
            }
        };
    }

    private void zipDirectory(Path sourceDirPath, ByteArrayOutputStream baos) throws IOException {
        try (ZipOutputStream zs = new ZipOutputStream(baos)) {
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
