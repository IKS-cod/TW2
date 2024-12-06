package TW2.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;

/**
 * Контроллер для работы с изображениями и аватарками.
 * <p>
 * Этот контроллер предоставляет API для загрузки изображений и аватарок пользователей.
 * </p>
 */
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/image")
@Tag(name = "Картинки объявлений/аватарки")
public class AvatarAndImageController {
    private static final Logger logger = LoggerFactory.getLogger(AvatarAndImageController.class);

    private final AvatarService avatarService;
    private final ImageService imageService;

    public AvatarAndImageController(AvatarService avatarService, ImageService imageService) {
        this.avatarService = avatarService;
        this.imageService = imageService;
    }

    /**
     * Загрузка картинки по указанному идентификатору.
     *
     * @param pathForEndpoint Идентификатор изображения.
     * @return ResponseEntity с изображением в байтовом формате.
     * @throws IOException Если произошла ошибка при загрузке изображения.
     */
    @GetMapping("/image/{id}")
    @Operation(summary = "Загрузка картинки")
    public ResponseEntity<byte[]> getImageFromFs(@PathVariable("id") String pathForEndpoint) throws IOException {
        logger.info("Fetching image with ID: {}", pathForEndpoint);
        Pair<byte[], String> imageData = imageService.getImageFromFs(pathForEndpoint);
        logger.info("Successfully fetched image with ID: {}", pathForEndpoint);
        return buildResponseEntity(imageData);
    }

    /**
     * Загрузка аватарки по указанному идентификатору.
     *
     * @param pathForEndpoint Идентификатор аватарки.
     * @return ResponseEntity с аватаркой в байтовом формате.
     * @throws IOException Если произошла ошибка при загрузке аватарки.
     */
    @GetMapping("/avatar/{id}")
    @Operation(summary = "Загрузка аватарки")
    public ResponseEntity<byte[]> getAvatarFromFs(@PathVariable("id") String pathForEndpoint) throws IOException {
        logger.info("Fetching avatar with ID: {}", pathForEndpoint);
        Pair<byte[], String> avatarData = avatarService.getAvatarFromFs(pathForEndpoint);
        logger.info("Successfully fetched avatar with ID: {}", pathForEndpoint);
        return buildResponseEntity(avatarData);
    }

    /**
     * Создает ResponseEntity из пары данных (байты изображения и тип контента).
     *
     * @param pair Пара, содержащая байты изображения и тип контента.
     * @return ResponseEntity с изображением.
     */
    public ResponseEntity<byte[]> buildResponseEntity(Pair<byte[], String> pair) {
        byte[] data = pair.getFirst();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentLength(data.length)
                .contentType(MediaType.parseMediaType(pair.getSecond()))
                .body(data);
    }
}