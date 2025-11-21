package unisinos.tripverse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import unisinos.tripverse.configuration.UserProvider;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UploadService {

    @Autowired
    private UserProvider userProvider;

    private final String UPLOAD_FOLDER = "./uploads/";

    public String upload(MultipartFile file) throws IOException {

        var filePath = userProvider.getAuthenticatedUser().getId().toString() + UUID.randomUUID() + file.getOriginalFilename();

        var fullPath = UPLOAD_FOLDER + filePath;

        Path copyPath = Paths.get(fullPath);

        Files.createDirectories(copyPath.getParent());

        file.transferTo(copyPath);

        return ServletUriComponentsBuilder.fromCurrentContextPath().toUriString() + "/api/v1/uploads/" + filePath;
    }

    public UrlResource read(String path) throws IOException {

        Path baseDir = Paths.get(UPLOAD_FOLDER).toAbsolutePath();

        Path requested = baseDir.resolve(path).normalize();

        if (!Files.exists(requested) || !Files.isRegularFile(requested)) {
            return null;
        }

        Path realFile = requested.toRealPath();

        return new UrlResource(realFile.toUri());
    }
}
