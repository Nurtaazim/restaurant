package restaurant.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import restaurant.dto.response.ChequeResponse;
import restaurant.service.ChequeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cheques")
public class ChequeAPI {
    private final ChequeService chequeService;
    @PostMapping
    ChequeResponse create (@RequestParam List<Integer> menuItemsId){
        return chequeService.create(menuItemsId);
    }
}
