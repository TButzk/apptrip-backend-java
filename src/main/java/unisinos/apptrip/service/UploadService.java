package unisinos.apptrip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import unisinos.apptrip.dto.UploadDto;
import unisinos.apptrip.exception.ApiException;
import unisinos.apptrip.model.MediaType;

import java.io.IOException;
import java.nio.file.*;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UploadService {
    private static final Set<String> IMAGE_TYPES = Set.of("image/jpeg", "image/png", "image/webp");
    private static final Set<String> GIF_TYPES = Set.of("image/gif");
    private static final Set<String> AUDIO_TYPES = Set.of("audio/mpeg", "audio/mp4", "audio/aac", "audio/wav", "audio/x-wav", "audio/ogg", "audio/webm");
    private static final Set<String> VIDEO_TYPES = Set.of("video/mp4", "video/webm", "video/quicktime");
    private static final Map<String, String> EXTENSIONS = Map.ofEntries(
            Map.entry("image/jpeg", ".jpg"), Map.entry("image/png", ".png"),
            Map.entry("image/webp", ".webp"), Map.entry("image/gif", ".gif"),
            Map.entry("audio/mpeg", ".mp3"), Map.entry("audio/mp4", ".m4a"),
            Map.entry("audio/aac", ".aac"), Map.entry("audio/wav", ".wav"),
            Map.entry("audio/x-wav", ".wav"), Map.entry("audio/ogg", ".ogg"),
            Map.entry("audio/webm", ".webm"), Map.entry("video/mp4", ".mp4"),
            Map.entry("video/webm", ".webm"), Map.entry("video/quicktime", ".mov")
    );

    private final AuthorizationService authorizationService;

    @Value("${apptrip.uploads.path:./uploads}")
    private String uploadFolder;
    @Value("${apptrip.uploads.image-max-bytes:10485760}")
    private long imageMaxBytes;
    @Value("${apptrip.uploads.audio-max-bytes:26214400}")
    private long audioMaxBytes;
    @Value("${apptrip.uploads.video-max-bytes:104857600}")
    private long videoMaxBytes;

    public UploadDto upload(MultipartFile file) throws IOException {
        authorizationService.currentUser();
        if (file == null || file.isEmpty()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Selecione um arquivo.");
        }
        String contentType = file.getContentType() == null ?"" : file.getContentType().toLowerCase(Locale.ROOT);
        MediaType type = inferType(contentType);
        long max = switch (type) {
            case Photo, Gif -> imageMaxBytes;
            case Audio -> audioMaxBytes;
            case Video -> videoMaxBytes;
        };
        if (file.getSize() > max) {
            throw new ApiException(HttpStatus.PAYLOAD_TOO_LARGE, "O arquivo excede o limite permitido para " + type + ".");
        }
        String filename = UUID.randomUUID() + EXTENSIONS.get(contentType);
        Path base = baseDirectory();
        Path fullPath = base.resolve(filename).normalize();
        Files.createDirectories(base);
        try (var input = file.getInputStream()) {
            Files.copy(input, fullPath, StandardCopyOption.REPLACE_EXISTING);
        }
        String url = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString()
                + "/api/v1/uploads/" + filename;
        return UploadDto.builder()
                .url(url)
                .filename(filename)
                .originalName(file.getOriginalFilename())
                .contentType(contentType)
                .sizeBytes(file.getSize())
                .type(type)
                .build();
    }

    public UrlResource read(String filename) throws IOException {
        Path requested = baseDirectory().resolve(filename).normalize();
        if (!requested.startsWith(baseDirectory()) || !Files.isRegularFile(requested)) {
            return null;
        }
        return new UrlResource(requested.toUri());
    }

    public void delete(String filename) {
        try {
            Path requested = baseDirectory().resolve(filename).normalize();
            if (requested.startsWith(baseDirectory())) {
                Files.deleteIfExists(requested);
            }
        } catch (IOException ignored) {
            // The database operation remains authoritative; orphan cleanup can be retried later.
        }
    }

    private Path baseDirectory() {
        return Paths.get(uploadFolder).toAbsolutePath().normalize();
    }

    private MediaType inferType(String contentType) {
        if (GIF_TYPES.contains(contentType)) return MediaType.Gif;
        if (IMAGE_TYPES.contains(contentType)) return MediaType.Photo;
        if (AUDIO_TYPES.contains(contentType)) return MediaType.Audio;
        if (VIDEO_TYPES.contains(contentType)) return MediaType.Video;
        throw new ApiException(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Formato de arquivo não permitido.");
    }
}
