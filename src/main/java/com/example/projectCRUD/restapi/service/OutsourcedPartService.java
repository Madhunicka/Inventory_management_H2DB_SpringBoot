//package com.example.projectCRUD.restapi.service;
//
//import com.example.projectCRUD.restapi.entity.OutsourcedPart;
//import com.example.projectCRUD.restapi.repository.OutsourcedPartRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class OutsourcedPartService {
//
//    @Autowired
//    private OutsourcedPartRepository outsourcedPartRepository;
//
//    public OutsourcedPart saveOutsourcedPart(OutsourcedPart outsourcedPart) {
//        return outsourcedPartRepository.save(outsourcedPart);
//    }
//
//    public List<OutsourcedPart> getAllOutsourcedParts() {
//        return outsourcedPartRepository.findAll();
//    }
//
//    public OutsourcedPart getOutsourcedPartById(Long id) {
//        return outsourcedPartRepository.findById(id).orElse(null);
//    }
//
//    public OutsourcedPart updateOutsourcedPart(Long id, OutsourcedPart outsourcedPartDetails) {
//        OutsourcedPart outsourcedPart = outsourcedPartRepository.findById(id).orElse(null);
//        if (outsourcedPart != null) {
//            outsourcedPart.setName(outsourcedPartDetails.getName());
//            outsourcedPart.setPrice(outsourcedPartDetails.getPrice());
//            outsourcedPart.setInv(outsourcedPartDetails.getInv());
//            outsourcedPart.setCompanyName(outsourcedPartDetails.getCompanyName());
//            outsourcedPart.setProducts(outsourcedPartDetails.getProducts());
//            return outsourcedPartRepository.save(outsourcedPart);
//        }
//        return null;
//    }
//
//    public void deleteOutsourcedPart(Long id) {
//        outsourcedPartRepository.deleteById(id);
//    }
//}