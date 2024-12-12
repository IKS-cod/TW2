package TW2.controller;

import TW2.dto.AdDto;
import TW2.dto.AdsDto;
import TW2.dto.CreateOrUpdateAdDto;
import TW2.dto.ExtendedAdDto;
import TW2.service.AdService;
import TW2.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

/**
 * Контроллер для работы с объявлениями.
 * <p>
 * Этот контроллер предоставляет API для создания, получения, обновления и удаления объявлений.
 * </p>
 */
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@Tag(name = "Объявления")
public class AdController {

    private static final Logger logger = LoggerFactory.getLogger(AdController.class);
    private final ImageService imageService;
    private final AdService adService;

    /**
     * Конструктор контроллера AdController.
     *
     * @param imageService Сервис для работы с изображениями.
     * @param adService    Сервис для работы с объявлениями.
     */
    public AdController(ImageService imageService, AdService adService) {
        this.imageService = imageService;
        this.adService = adService;
    }

    /**
     * Получение всех объявлений.
     *
     * @return Объект AdsDto, содержащий список всех объявлений.
     */
    @GetMapping
    @Operation(summary = "Получение всех объявлений")
    public AdsDto getAllAds() {
        logger.info("Fetching all ads");
        AdsDto ads = adService.getAllAds();
        logger.info("Successfully fetched all ads");
        return ads;
    }

    /**
     * Добавление нового объявления.
     *
     * @param ad    Данные объявления для добавления.
     * @param image Изображение объявления.
     * @return Объект AdDto, представляющий добавленное объявление.
     * @throws IOException Если произошла ошибка при обработке изображения.
     */

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Добавление объявления")
    public AdDto addAd(@RequestPart("properties") CreateOrUpdateAdDto ad,
                       @RequestPart("image") MultipartFile image) throws IOException {
        logger.info("Adding new ad with properties: {}", ad);
        AdDto addedAd = adService.addAd(ad, image);
        logger.info("Successfully added new ad with ID: {}", addedAd.getPk());
        return addedAd;
    }

    /**
     * Получение информации об объявлении по его идентификатору.
     *
     * @param id Идентификатор объявления.
     * @return Объект ExtendedAdDto, содержащий информацию об объявлении.
     */

    @GetMapping("/{id}")
    @Operation(summary = "Получение информации об объявлении")
    public ExtendedAdDto getAdById(@PathVariable Integer id) {
        logger.info("Fetching ad with ID: {}", id);
        ExtendedAdDto ad = adService.getAdById(id);
        logger.info("Successfully fetched ad with ID: {}", id);
        return ad;
    }

    /**
     * Обновление информации об объявлении по его идентификатору.
     *
     * @param id Идентификатор объявления.
     * @param ad Данные для обновления объявления.
     * @return Объект AdDto, представляющий обновленное объявление.
     */
    @PreAuthorize("@userVerification.verificationUserForAds(#id) || hasAuthority('ADMIN')")
    @PatchMapping("/{id}")
    @Operation(summary = "Обновление информации об объявлении")
    public AdDto updateAd(@PathVariable Integer id, @RequestBody CreateOrUpdateAdDto ad) {
        logger.info("Updating ad with ID: {} and properties: {}", id, ad);
        AdDto updatedAd = adService.updateAd(id, ad);
        logger.info("Successfully updated ad with ID: {}", id);
        return updatedAd;
    }

    /**
     * Удаление объявления по его идентификатору.
     *
     * @param id Идентификатор объявления для удаления.
     * @throws IOException Если произошла ошибка при удалении объявления.
     */
    @PreAuthorize("@userVerification.verificationUserForAds(#id) || hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление объявления")
    public void removeAd(@PathVariable Integer id) throws IOException {
        logger.info("Removing ad with ID: {}", id);
        adService.removeAd(id);
        logger.info("Successfully removed ad with ID: {}", id);
    }

    /**
     * Получение всех объявлений авторизованного пользователя.
     *
     * @return Объект AdsDto, содержащий список объявлений текущего пользователя.
     */
    @GetMapping("/me")
    @Operation(summary = "Получение объявлений авторизованного пользователя")
    public AdsDto getMeAllAds() {
        logger.info("Fetching all ads for the authenticated user");
        AdsDto ads = adService.getMeAllAds();
        logger.info("Successfully fetched all ads for the authenticated user");
        return ads;
    }

    /**
     * Обновление изображения объявления по его идентификатору.
     *
     * @param id Идентификатор объявления для обновления изображения.
     * @param image Новое изображение для объявления.
     * @return URL обновленного изображения.
     * @throws IOException Если произошла ошибка при обработке изображения.
     */
    @PreAuthorize("@userVerification.verificationUserForAds(#id) || hasAuthority('ADMIN')")
    @PatchMapping("/{id}/image")
    @Operation(summary = "Обновление картинки объявления")
    public String updateAdImage(@PathVariable Integer id,
                                @RequestParam("image") MultipartFile image) throws IOException {
        logger.info("Updating image for ad with ID: {}", id);
        String imageUrl = imageService.updateAdImage(id, image);
        logger.info("Successfully updated image for ad with ID: {}", id);
        return imageUrl;
    }
}
