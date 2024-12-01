package TW2.service;

import TW2.dto.AdDto;
import TW2.dto.AdsDto;
import TW2.dto.CreateOrUpdateAdDto;
import TW2.dto.ExtendedAdDto;
import org.springframework.web.multipart.MultipartFile;

public class AdService {
    public AdsDto getAllAds() {
        return null;
    }

    public AdDto addAd(MultipartFile image, CreateOrUpdateAdDto ad) {
        return null;
    }

    public ExtendedAdDto getAdById(Integer id) {
        return null;
    }

    public void updateAd(Integer id, CreateOrUpdateAdDto ad) {
    }

    public void removeAd(Integer id) {
    }

}
