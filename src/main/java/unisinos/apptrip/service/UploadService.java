package unisinos.apptrip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import unisinos.apptrip.provider.UserProvider;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UploadService {

    @Autowired
    private UserProvider userProvider;

    @Value("${apptrip.uploads.path:./uploads}")
    private String uploadFolder;

    public String upload(MultipartFile file) throws IOException {

        var filePath = userProvider.getAuthenticatedUser().getId().toString() + UUID.randomUUID() + file.getOriginalFilename();

        var fullPath = Paths.get(uploadFolder).resolve(filePath);

        Files.createDirectories(fullPath.getParent());
        file.transferTo(fullPath);

        return ServletUriComponentsBuilder.fromCurrentContextPath().toUriString() + "/api/v1/uploads/" + filePath;
    }

    public UrlResource read(String path) throws IOException {

        Path baseDir = Paths.get(uploadFolder).toAbsolutePath();

        Path requested = baseDir.resolve(path).normalize();

        if (!Files.exists(requested) || !Files.isRegularFile(requested)) {
            return null;
        }

        Path realFile = requested.toRealPath();

        return new UrlResource(realFile.toUri());
    }
}

