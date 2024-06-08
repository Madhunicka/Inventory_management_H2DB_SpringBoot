package com.example.projectCRUD.restapi.controller;

import com.example.projectCRUD.restapi.entity.InhousePart;
import com.example.projectCRUD.restapi.entity.OutsourcedPart;
import com.example.projectCRUD.restapi.entity.Parts;
import com.example.projectCRUD.restapi.repository.InhousePartRepository;
import com.example.projectCRUD.restapi.repository.OutsourcedPartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin

@RestController

public class OutsourcedPartController {
    @Autowired
    OutsourcedPartRepository repo;

    @GetMapping("/outsourcedparts")
    public List<OutsourcedPart> getOutsourcedParts(){
        List<OutsourcedPart>  outparts = repo.findAll();
        return outparts;
    }

    @GetMapping("/outsourcedparts/{id}")
    public OutsourcedPart getoutsourcedpart(@PathVariable int id){
        OutsourcedPart outsourcedPart= repo.findById((long) id).get();
        return outsourcedPart;
    }

    @PostMapping("/outsourcedparts/add/{company}")
    public void createOutsourcedPart(@RequestBody Parts parts, @PathVariable("company") String company) {
        System.out.println(company);
        System.out.println(parts);

        OutsourcedPart outsourcedPart = new OutsourcedPart();
        outsourcedPart.setCompanyName(company);
        outsourcedPart.setName(parts.getName());
        outsourcedPart.setInv(parts.getInv());
        outsourcedPart.setPrice(parts.getPrice());

        repo.save(outsourcedPart);
    }
    @PutMapping("/outsourcedparts/update/{id}")
    public OutsourcedPart updateOutsourcedparts(@PathVariable int id){
        OutsourcedPart outsourcedPart = repo.findById((long) id).get();
        outsourcedPart.setCompanyName("comp1");
        repo.save(outsourcedPart);
        return outsourcedPart;

    }

    @DeleteMapping("/outsourcedparts/delete/{id}")
    public void removeOutsourcedpart(@PathVariable int id){
        OutsourcedPart outsourcedPart = repo.findById((long) id).get();
        repo.delete(outsourcedPart);

    }




}