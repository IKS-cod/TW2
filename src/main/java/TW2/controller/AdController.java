package TW2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import TW2.dto.Ad;
import TW2.dto.Ads;
import TW2.dto.CreateOrUpdateAd;
import TW2.dto.ExtendedAd;
import TW2.service.AdService;

@RestController
@RequestMapping("/ads")
@Tag(name = "Объявления")
public class AdController {

    @Autowired
    private AdService adService; // Сервис для работы с объявлениями

    @GetMapping
    @Operation(summary = "Получение всех объявлений")
    public Ads getAllAds() {
        // Логика получения всех объявлений
        return adService.getAllAds();
    }

    @PostMapping
    @Operation(summary = "Добавление объявления")
    public Ad addAd(@RequestParam("image") MultipartFile image, @RequestBody CreateOrUpdateAd ad) {
        // Логика добавления объявления
        return adService.addAd(image, ad);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение информации об объявлении")
    public ExtendedAd getAdById(@PathVariable Integer id) {
        // Логика получения объявления по ID
        return adService.getAdById(id);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Обновление информации об объявлении")
    public String updateAd(@PathVariable Integer id, @RequestBody CreateOrUpdateAd ad) {
        // Логика обновления объявления
        adService.updateAd(id, ad);
        return "Объявление успешно обновлено";
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление объявления")
    public String removeAd(@PathVariable Integer id) {
        // Логика удаления объявления
        adService.removeAd(id);
        return "Объявление успешно удалено";
    }

    @GetMapping("/me")
    @Operation(summary = "Получение объявлений авторизованного пользователя")
    public Ads getUserAds() {
        return null;
    }

    @PatchMapping("/{id}/image")
    @Operation(summary = "Обновление картинки объявления")
    public String updateUserImage(@PathVariable Integer id, @RequestBody CreateOrUpdateAd ad) {
        return null;
    }
}
