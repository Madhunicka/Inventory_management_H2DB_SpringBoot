//package com.example.projectCRUD.restapi.service;
//
//import com.example.projectCRUD.restapi.entity.InhousePart;
//import com.example.projectCRUD.restapi.repository.InhousePartRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class InhousePartService {
//
//    @Autowired
//    private InhousePartRepository inhousePartRepository;
//
//    public InhousePart saveInhousePart(InhousePart inhousePart) {
//        return inhousePartRepository.save(inhousePart);
//    }
//
//    public List<InhousePart> getAllInhouseParts() {
//        return inhousePartRepository.findAll();
//    }
//
//    public InhousePart getInhousePartById(Long id) {
//        return inhousePartRepository.findById(id).orElse(null);
//    }
//
//    public InhousePart updateInhousePart(Long id, InhousePart inhousePartDetails) {
//        InhousePart inhousePart = inhousePartRepository.findById(id).orElse(null);
//        if (inhousePart != null) {
//            inhousePart.setName(inhousePartDetails.getName());
//            inhousePart.setPrice(inhousePartDetails.getPrice());
//            inhousePart.setInv(inhousePartDetails.getInv());
//            inhousePart.setPartId(inhousePartDetails.getPartId());
//            inhousePart.setProducts(inhousePartDetails.getProducts());
//            return inhousePartRepository.save(inhousePart);
//        }
//        return null;
//    }
//
//    public void deleteInhousePart(Long id) {
//        inhousePartRepository.deleteById(id);
//    }
//}