package restaurant.service;

import restaurant.dto.response.ChequeResponse;

import java.util.List;

public interface ChequeService {
    ChequeResponse create(List<Integer> menuItemsId);
}
