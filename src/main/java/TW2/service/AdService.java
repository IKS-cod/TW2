package TW2.service;

import TW2.dto.AdDto;
import TW2.dto.AdsDto;
import TW2.dto.CreateOrUpdateAdDto;
import TW2.dto.ExtendedAdDto;
import TW2.exception.AdNotFoundException;
import TW2.mapper.Mappers;
import TW2.model.Ads;
import TW2.model.Images;
import TW2.model.Users;
import TW2.repository.AdsRepository;
import TW2.repository.ImagesRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Сервис для управления объявлениями.
 * <p>
 * Этот класс предоставляет методы для создания, обновления, удаления и получения объявлений,
 * а также для работы с изображениями, связанными с объявлениями.
 * </p>
 */
@Service
public class AdService {
    private static final Logger logger = LoggerFactory.getLogger(AdService.class);

    private final CommentService commentService;
    private final UserContextService userContextService;
    private final ImageService imageService;
    private final ImagesRepository imagesRepository;
    private final Mappers mappers;
    private final AdsRepository adsRepository;

    public AdService(CommentService commentService, UserContextService userContextService,
                     ImageService imageService,
                     ImagesRepository imagesRepository,
                     AdsRepository adsRepository,
                     Mappers mappers) {
        this.commentService = commentService;
        this.userContextService = userContextService;
        this.imageService = imageService;
        this.imagesRepository = imagesRepository;
        this.adsRepository = adsRepository;
        this.mappers = mappers;
    }

    /**
     * Получает все объявления.
     *
     * @return объект AdsDto, содержащий список всех объявлений и их количество.
     */
    public AdsDto getAllAds() {
        List<Ads> adsList = adsRepository.findAll();
        AdsDto adsDto = new AdsDto();
        adsDto.setCount(adsList.size());
        List<AdDto> adDtoList = getAdDtoList(adsList);
        adsDto.setResults(adDtoList);
        logger.info("Retrieved {} ads", adsList.size());
        return adsDto;
    }

    /**
     * Добавляет новое объявление.
     *
     * @param adDto данные объявления для создания.
     * @param image изображение для объявления.
     * @return объект AdDto, представляющий добавленное объявление.
     * @throws IOException если произошла ошибка при сохранении изображения.
     */
    public AdDto addAd(CreateOrUpdateAdDto adDto, MultipartFile image) throws IOException {
        Ads ads = mappers.toAds(adDto);
        Users users = userContextService.getCurrentUserFromDb();
        ads.setUsers(users);
        Ads adsFromDb = adsRepository.save(ads);

        imageService.saveImage(image, ads);
        AdDto adDtoFromDb = mappers.toAdDto(adsFromDb);
        adDtoFromDb.setImage(imagesRepository.findByAdsPk(adsFromDb.getPk()).getFilePath());

        logger.info("Added ad ID: {}", adsFromDb.getPk());

        return adDtoFromDb;
    }

    /**
     * Получает объявление по его идентификатору.
     *
     * @param id идентификатор объявления.
     * @return объект ExtendedAdDto, представляющий найденное объявление.
     */
    public ExtendedAdDto getAdById(Integer id) {
        Ads adsFromDb = adsRepository.findById(id)
                .orElseThrow(() -> new AdNotFoundException("Ad not found with ID: " + id));

        ExtendedAdDto extendedAdDto = mappers.toExtendedAdDto(adsFromDb);

        Images image = imagesRepository.findByAdsPk(adsFromDb.getPk());
        if (image != null) {
            String str = image.getPathForEndpoint() + image.getId();
            extendedAdDto.setImage(str);
            logger.info("Retrieved ad ID: {} with image", id);
        } else {
            logger.warn("No image found for ad ID: {}", id);
            extendedAdDto.setImage("default/image/path"); // Установите путь по умолчанию
        }

        return extendedAdDto;
    }

    /**
     * Обновляет существующее объявление.
     *
     * @param id идентификатор объявления для обновления.
     * @param adDto новые данные объявления.
     * @return объект AdDto, представляющий обновленное объявление.
     */
    public AdDto updateAd(Integer id, CreateOrUpdateAdDto adDto) {
        Ads ads = adsRepository.findById(id)
                .orElseThrow(() -> new AdNotFoundException("Ad not found with ID: " + id));

        ads.setTitle(adDto.getTitle());
        ads.setPrice(adDto.getPrice());
        ads.setDescription(adDto.getDescription());

        Ads updatedAds = adsRepository.save(ads);

        AdDto adDtoFromDb = mappers.toAdDto(updatedAds);

        Images image = imagesRepository.findByAdsPk(updatedAds.getPk());
        if (image != null) {
            adDtoFromDb.setImage(image.getFilePath());
            logger.info("Updated ad ID: {} with new data", updatedAds.getPk());
        } else {
            logger.warn("No image found for updated ad ID: {}", updatedAds.getPk());
            adDtoFromDb.setImage("default/image/path"); // Установите путь по умолчанию
        }

        return adDtoFromDb;
    }

    /**
     * Удаляет объявление по его идентификатору.
     *
     * @param id идентификатор объявления для удаления.
     * @throws IOException если произошла ошибка при удалении изображения.
     */
    @Transactional
    public void removeAd(Integer id) throws IOException {
        commentService.deleteCommentForIdAds(id);
        imageService.deleteImageForIdAds(id);

        adsRepository.deleteById(id);

        logger.info("Deleted ad ID: {}", id);
    }

    /**
     * Получает все объявления текущего пользователя.
     *
     * @return объект AdsDto, содержащий список всех объявлений текущего пользователя и их количество.
     */
    public AdsDto getMeAllAds() {
        Users users = userContextService.getCurrentUserFromDb();

        List<Ads> ads = adsRepository.findByUsersId(users.getId());

        AdsDto adsDto = new AdsDto();
        adsDto.setCount(ads.size());

        List<AdDto> adDtoList = getAdDtoList(ads);

        adsDto.setResults(adDtoList);

        logger.info("Retrieved {} ads for user ID: {}", ads.size(), users.getId());

        return adsDto;
    }

    /**
     * Преобразует список объявлений в список DTO объектов для передачи клиенту.
     *
     * @param ads список объявлений для преобразования.
     * @return список объектов AdDto.
     */
    private List<AdDto> getAdDtoList(List<Ads> ads) {
        List<Images> images = imagesRepository.findAllByAdsPkIn(
                ads.stream().map(Ads::getPk).collect(Collectors.toList())
        );

        Map<Integer, String> imagesMap = images.stream()
                .collect(Collectors.toMap(image -> image.getAds().getPk(),
                        image -> image.getPathForEndpoint() + image.getId()));

        List<AdDto> adDtoList = ads.stream().map(ad -> {
            String imagePath = imagesMap.get(ad.getPk());
            return new AdDto(
                    ad.getUsers().getId(),
                    imagePath != null ? imagePath : "default/image/path", // Путь по умолчанию, если изображение отсутствует
                    ad.getPk(),
                    ad.getPrice(),
                    ad.getTitle()
            );
        }).collect(Collectors.toList());

        logger.info("Converted {} ads to AdDtos", adDtoList.size());

        return adDtoList;
    }
}
