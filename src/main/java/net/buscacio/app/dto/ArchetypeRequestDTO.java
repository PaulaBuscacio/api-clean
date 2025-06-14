package net.buscacio.app.dto;

public record ArchetypeRequestDTO(String groupId, String artifactId) {
    public ArchetypeRequestDTO {
        if (groupId == null || groupId.isBlank()) {
            throw new IllegalArgumentException("Group ID must not be null or empty");
        }
        if (artifactId == null || artifactId.isBlank()) {
            throw new IllegalArgumentException("Artifact ID must not be null or empty");
        }
    }

}
