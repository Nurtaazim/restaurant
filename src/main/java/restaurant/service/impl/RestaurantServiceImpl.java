package restaurant.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import restaurant.repository.RestaurantRepository;
import restaurant.service.RestaurantService;
@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
}
