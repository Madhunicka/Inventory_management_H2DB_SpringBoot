package com.example.projectCRUD.restapi.controller;

import com.example.projectCRUD.restapi.entity.InhousePart;
//import com.example.projectCRUD.restapi.service.InhousePartService;
import com.example.projectCRUD.restapi.entity.Parts;
import com.example.projectCRUD.restapi.repository.InhousePartRepository;
import com.example.projectCRUD.restapi.repository.PartsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin

@RestController
//@RequestMapping("/inhouse-parts")
public class InhousePartController {
    @Autowired
    InhousePartRepository repo;

    @Autowired
    PartsRepository partsRepository;

    @GetMapping("/inhouseparts")
    public List<InhousePart> getInhouseParts(){
    List<InhousePart>  inparts = repo.findAll();
        return inparts;
    }

    @GetMapping("/inhouseparts/{id}")
    public InhousePart getinhousepart(@PathVariable int id){
        InhousePart inhousePart= repo.findById((long) id).get();
        return inhousePart;
    }

    @PostMapping("/inhouseparts/add/{part_id}")
    public void createInhousepart(@RequestBody Parts parts, @PathVariable("part_id") int part_id){
        System.out.println(part_id);
        System.out.println(parts);

        InhousePart inhousePart = new InhousePart();
//        inhousePart.setId(parts.getId());
        inhousePart.setPartId(part_id);

//
//      Parts newP =  partsRepository.save(parts);
//
////        Parts newP = repo.save(inhousePart);
//        inhousePart.setId(newP.getId());
        inhousePart.setName(parts.getName());
        inhousePart.setInv(parts.getInv());
        inhousePart.setPrice(parts.getPrice());
//        inhousePart.

        repo.save(inhousePart);

//    repo.save(inhousePart);
    }
    @PutMapping("/inhouseparts/update/{id}")
    public InhousePart upddateInhouseparts(@PathVariable int id){
        InhousePart inhousePart = repo.findById((long) id).get();
        inhousePart.setPartId(5);
        repo.save(inhousePart);
        return inhousePart;

    }

    @DeleteMapping("/inhouseparts/delete/{id}")
    public void removeInhousepart(@PathVariable int id){
        InhousePart inhousePart = repo.findById((long) id).get();
        repo.delete(inhousePart);

    }




}