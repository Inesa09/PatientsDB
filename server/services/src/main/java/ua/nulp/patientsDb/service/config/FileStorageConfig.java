package ua.nulp.patientsDb.service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
@Data
public class FileStorageConfig {

    private String uploadDir;
}