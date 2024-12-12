package TW2.service;

import TW2.exception.ImageNotFoundException;
import TW2.model.Ads;
import TW2.model.Images;
import TW2.repository.ImagesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

/**
 * Сервис для управления изображениями объявлений.
 * <p>
 * Этот класс предоставляет методы для сохранения, получения, обновления и удаления изображений,
 * связанных с объявлениями.
 * </p>
 */
@Service
public class ImageService {
    private static final Logger logger = LoggerFactory.getLogger(ImageService.class);

    private final Path path; // Путь к директории для хранения изображений
    private final ImagesRepository imagesRepository; // Репозиторий для работы с изображениями

    public ImageService(ImagesRepository imagesRepository,
                        @Value("${application.image-dir-name}") String avatarsDirName) {
        this.imagesRepository = imagesRepository;
        this.path = Path.of(avatarsDirName); // Инициализация пути к директории
    }

    /**
     * Сохраняет изображение в файловой системе и в базе данных.
     *
     * @param image объект MultipartFile, представляющий изображение.
     * @param ads   объект Ads, к которому принадлежит изображение.
     * @throws IOException если возникает ошибка при записи файла.
     */
    public void saveImage(MultipartFile image, Ads ads) throws IOException {
        Images imageForDb = createImageEntity(image, ads);
        Path getFilePathFromDb = Paths.get(imageForDb.getFilePath());
        Files.write(getFilePathFromDb, image.getBytes());
        imagesRepository.save(imageForDb);
        logger.info("Image saved for ad ID: {}", ads.getPk());
    }

    /**
     * Получает изображение из файловой системы по идентификатору.
     *
     * @param pathForEndpoint путь для доступа к изображению.
     * @return пара, содержащая байты изображения и его тип медиа.
     * @throws IOException если возникает ошибка при чтении файла.
     * @throws ImageNotFoundException если изображение не найдено по указанному ID.
     */
    public Pair<byte[], String> getImageFromFs(String pathForEndpoint) throws IOException {
        Integer imageId = Integer.parseInt(pathForEndpoint);
        Images image = imagesRepository.findById(imageId)
                .orElseThrow(() -> new ImageNotFoundException("Image not found with ID: " + imageId));

        byte[] imageData = Files.readAllBytes(Paths.get(image.getFilePath()));
        logger.info("Retrieved image data for ID: {}", imageId);
        return Pair.of(imageData, image.getMediaType());
    }

    /**
     * Обновляет изображение объявления по его идентификатору.
     *
     * @param adId уникальный идентификатор объявления.
     * @param image объект MultipartFile, представляющий новое изображение.
     * @return путь к обновленному изображению.
     * @throws IOException если возникает ошибка при записи файла или удалении старого изображения.
     * @throws ImageNotFoundException если изображение не найдено для указанного объявления.
     */
    public String updateAdImage(Integer adId, MultipartFile image) throws IOException {
        Images imageFromDb = Optional.ofNullable(imagesRepository.findByAdsPk(adId))
                .orElseThrow(() -> new ImageNotFoundException("Image not found for ad ID: " + adId));

        deleteExistingImage(imageFromDb);
        Images updatedImage = createImageEntity(image, imageFromDb.getAds());
        Path getFilePathFromDb = Paths.get(updatedImage.getFilePath());
        Files.write(getFilePathFromDb, image.getBytes());
        imagesRepository.save(updatedImage);

        logger.info("Updated image for ad ID: {}", adId);
        return updatedImage.getFilePath();
    }

    /**
     * Удаляет изображение по идентификатору объявления.
     *
     * @param adId уникальный идентификатор объявления.
     * @throws IOException если возникает ошибка при удалении файла.
     * @throws ImageNotFoundException если изображение не найдено для указанного объявления.
     */
    @Transactional
    public void deleteImageForIdAds(Integer adId) throws IOException {
        Images imageFromDb = Optional.ofNullable(imagesRepository.findByAdsPk(adId))
                .orElseThrow(() -> new ImageNotFoundException("Image not found for ad ID: " + adId));

        Files.delete(Paths.get(imageFromDb.getFilePath()));
        imagesRepository.deleteByAdsPk(adId);

        logger.info("Deleted image for ad ID: {}", adId);
    }

    /**
     * Создает объект Images на основе загруженного изображения и объявления.
     *
     * @param image объект MultipartFile, представляющий загружаемое изображение.
     * @param ads   объект Ads, к которому принадлежит изображение.
     * @return созданный объект Images с установленными полями.
     * @throws IOException если возникает ошибка при получении данных изображения.
     */
    private Images createImageEntity(MultipartFile image, Ads ads) throws IOException {
        String extension = StringUtils.getFilenameExtension(image.getOriginalFilename());
        Path avatarsPath = path.resolve(UUID.randomUUID() + "." + extension);

        Images imageForDb = new Images();
        imageForDb.setAds(ads);
        imageForDb.setPathForEndpoint("/image/image/");
        imageForDb.setFilePath(avatarsPath.toString());
        imageForDb.setMediaType(image.getContentType());

        return imageForDb;
    }

    /**
     * Удаляет существующее изображение из файловой системы.
     *
     * @param existingImage объект Images, представляющий текущее изображение.
     * @throws IOException если возникает ошибка при удалении файла.
     */
    private void deleteExistingImage(Images existingImage) throws IOException {
        Path existingPath = Paths.get(existingImage.getFilePath());
        if (Files.exists(existingPath)) {
            Files.delete(existingPath);
            logger.info("Deleted existing image file at path: {}", existingPath);
        } else {
            logger.warn("Attempted to delete non-existing image file at path: {}", existingPath);
        }
    }
}