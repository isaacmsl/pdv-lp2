package br.ufrn.imd.pdvlp2.product.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.imd.pdvlp2.core.controller.AbstractController;
import br.ufrn.imd.pdvlp2.product.model.ProductModel;
import br.ufrn.imd.pdvlp2.product.service.ProductService;

@RestController
@RequestMapping("products")
public class ProductController extends AbstractController<ProductModel, ProductService> {
    @GetMapping
    public ResponseEntity<ProductModel> findByName(@RequestParam String name) {
        Optional<ProductModel> product = service.findByName(name);

        if (!product.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(product.get());
    }

    @GetMapping("/verifyIsAvailable")
    public Boolean verifyIsAvailable(@RequestParam String name, @RequestParam int requestedQuantity) {
        Optional<ProductModel> product = service.findByName(name);
        ProductModel productItem = product.get();

        if (!product.isPresent()) {
            return false;
        }

        return productItem.getQuantity() >= requestedQuantity;
    }
    
    @GetMapping("/barcode")
    public ResponseEntity<ProductModel> findByBarcode(@RequestParam String barcode) {
        Optional<ProductModel> product = service.findByBarcode(barcode);

        if (!product.isPresent()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(product.get());
    }

    @GetMapping("/regex")
    public ResponseEntity<List<ProductModel>> findByNameRegex(@RequestParam String name) {
        return ResponseEntity.ok().body(service.findByNameRegex(name));
    }
}
