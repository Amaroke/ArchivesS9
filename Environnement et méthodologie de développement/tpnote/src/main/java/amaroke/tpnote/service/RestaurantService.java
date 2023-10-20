package amaroke.tpnote.service;

import java.util.List;

import org.springframework.stereotype.Service;

import amaroke.tpnote.repository.RestaurantRepository;
import amaroke.tpnote.model.entity.RestaurantEntity;
import amaroke.tpnote.model.enumeration.Tags;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final IndexService indexService;
    private final S3Service s3Service;

    public List<RestaurantEntity> getAllRestaurants() {
        return this.restaurantRepository.findAll();
    }

    public RestaurantEntity getRestaurant(Integer id) {
        return this.restaurantRepository.findById(id).orElseThrow();
    }

    public List<RestaurantEntity> searchRestaurants(String keyword, Float minNote, Float maxNote, String tag) {
        List<Integer> restaurantIds = indexService.searchRestaurantIds(keyword, minNote, maxNote, tag);
        return restaurantRepository.findAllById(restaurantIds);
    }

    public void createRestaurant(String nom, String adresse, List<Tags> tags) {
        RestaurantEntity restaurantEntity = this.restaurantRepository
                .save(RestaurantEntity.builder()
                        .nom(nom)
                        .adresse(adresse)
                        .tags(tags).build());
        this.indexService.indexRestaurant(restaurantEntity);
    }

    public void updateRestaurant(Integer id, String nom, String adresse, List<Tags> tags) {
        final RestaurantEntity restaurantEntityToUpdate = this.restaurantRepository.findById(id).orElseThrow();
        restaurantEntityToUpdate.setNom(nom);
        restaurantEntityToUpdate.setAdresse(adresse);
        restaurantEntityToUpdate.setTags(tags);
        restaurantRepository.save(restaurantEntityToUpdate);
    }

    public String getImage(Integer restaurantId) {
        return this.s3Service.getCover(restaurantId);
    }

    public String addImage(Integer restaurantId) {
        return this.s3Service.addCover(restaurantId);
    }

}
