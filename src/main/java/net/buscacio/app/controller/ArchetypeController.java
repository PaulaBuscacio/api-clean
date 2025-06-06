package net.buscacio.app.controller;

import lombok.AllArgsConstructor;
import net.buscacio.app.dto.ArchetypeRequestDTO;
import net.buscacio.service.ArchetypeService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("/archetype")
@AllArgsConstructor
public class ArchetypeController {

    private final ArchetypeService archetypeService;

    @PostMapping
    public ResponseEntity<?> generateProject(@RequestBody ArchetypeRequestDTO requestDTO) throws IOException, InterruptedException {

        ByteArrayResource resource = archetypeService.generateProject(requestDTO.groupId(), requestDTO.artifactId());
        byte[] zipContent = resource.getByteArray();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(zipContent.length)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + requestDTO.artifactId() + ".zip\"")
                .body(resource);
    }
}
