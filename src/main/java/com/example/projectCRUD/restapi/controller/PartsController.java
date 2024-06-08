package com.example.projectCRUD.restapi.controller;

import com.example.projectCRUD.restapi.entity.Parts;
import com.example.projectCRUD.restapi.entity.Product;
import com.example.projectCRUD.restapi.repository.PartsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/parts")
public class PartsController {
    @Autowired
    PartsRepository partsRepository;

    @GetMapping
    public List<Parts> getAllParts() {
        return partsRepository.findAll();
    }

    @GetMapping("/{id}")
    public Parts getPartById(@PathVariable Long id) {
        return partsRepository.findById(id).orElse(null);
    }

    @PostMapping("/add")
    public Parts createOrUpdatePart(@RequestBody Parts newPart) {
        Long id = newPart.getId();
        Parts existingPart = partsRepository.findById(id).orElse(null);

//        Product[] products = newPart.getProducts().toArray(new Product[0]);

        if (existingPart != null) {
            // Update existing part with new price and quantity
            existingPart.setPrice(newPart.getPrice());
            existingPart.setInv(existingPart.getInv() + newPart.getInv());
            partsRepository.save(existingPart);
            return existingPart;
        } else {
            // Create new part
            return partsRepository.save(newPart);
        }
    }


    @PutMapping("/update/{id}")
    public Parts updatePart(@PathVariable Long id, @RequestBody Parts updatedPart) {
        Parts existingPart = partsRepository.findById(id).orElse(null);
        if (existingPart != null) {
            existingPart.setName(updatedPart.getName());
            existingPart.setPrice(updatedPart.getPrice());
            existingPart.setInv(updatedPart.getInv());
            // You may need to handle relationships (if any) here
            partsRepository.save(existingPart);
        }
        return existingPart;
    }

    @DeleteMapping("/delete/{id}")
    public void deletePart(@PathVariable Long id) {
        partsRepository.deleteById(id);
    }



//    @GetMapping("/{id}/products")
}
