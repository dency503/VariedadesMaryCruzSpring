package com.Rosita.store.Controller;


import com.Rosita.store.Service.ProductoServiceImpl;
import com.Rosita.store.models.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;
import java.util.Optional;

@Controller
public class SearchController {

    @Autowired
    private ProductoServiceImpl productServiceImpl;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity< List<Producto>> searchProducts(
            @RequestParam(name = "q", required = false) String name,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "minPrice", required = false) Optional<Double> minPrice,
            @RequestParam(name = "maxPrice", required = false) Optional<Double> maxPrice,
            @RequestParam(name = "brand", required = false) String brand,
            @RequestParam(name = "material", required = false) String material,
            Model model) {
        List<Producto> products = productServiceImpl.search(name, description, minPrice, maxPrice, brand, material);
        model.addAttribute("products", products);

        return ResponseEntity.ok(products);
    }
}