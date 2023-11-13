package cphbusiness.noinputs.main.service;

import cphbusiness.noinputs.main.dto.RestaurantDTO;
import cphbusiness.noinputs.main.model.Restaurant;
import cphbusiness.noinputs.main.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<RestaurantDTO> getAllRestaurants() {
        // TODO: Limit this? (Maybe 20 per page)
        List<Restaurant> restaurantsEntities = restaurantRepository.findAll();
        List<RestaurantDTO> restaurantsDTOs = new ArrayList<>();

        for (Restaurant restaurant : restaurantsEntities) {
            restaurantsDTOs.add(new RestaurantDTO(restaurant.getId(), restaurant.getName()));
        }

        return restaurantsDTOs;
    }
}
