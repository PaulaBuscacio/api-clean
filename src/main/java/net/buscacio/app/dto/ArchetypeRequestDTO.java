package net.buscacio.app.dto;

public record ArchetypeRequestDTO(String groupId, String artifactId) {
    public ArchetypeRequestDTO {
        if (groupId == null || groupId.isBlank()) {
            throw new IllegalArgumentException("Por favor, informe o Group Id");
        }
        if (artifactId == null || artifactId.isBlank()) {
            throw new IllegalArgumentException("Por favor, informe o Artifact Id");
        }
    }

}
