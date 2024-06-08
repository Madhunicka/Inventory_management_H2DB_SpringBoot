package com.example.projectCRUD.restapi.controller;

import com.example.projectCRUD.restapi.entity.InhousePart;
import com.example.projectCRUD.restapi.entity.Parts;
import com.example.projectCRUD.restapi.repository.InhousePartRepository;
import com.example.projectCRUD.restapi.repository.PartsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(InhousePartController.class)
public class InhousePartTestController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InhousePartRepository repo;

    @MockBean
    private PartsRepository partsRepository;

    private InhousePart inhousePart;
    private Parts part;

    @BeforeEach
    void setUp() {
        inhousePart = new InhousePart();
        inhousePart.setId(1L);
        inhousePart.setPartId(1);
        inhousePart.setName("Part Name");
        inhousePart.setInv(100);
        inhousePart.setPrice(10.0);

        part = new Parts();
        part.setId(1L);
        part.setName("Part Name");
        part.setInv(100);
        part.setPrice(10.0);
    }

    @Test
    void testGetInhouseParts() throws Exception {
        when(repo.findAll()).thenReturn(Arrays.asList(inhousePart));

        mockMvc.perform(get("/inhouseparts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(inhousePart.getId()))
                .andExpect(jsonPath("$[0].name").value(inhousePart.getName()));
    }

    @Test
    void testGetInhousePart() throws Exception {
        when(repo.findById(1L)).thenReturn(Optional.of(inhousePart));

        mockMvc.perform(get("/inhouseparts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(inhousePart.getId()))
                .andExpect(jsonPath("$.name").value(inhousePart.getName()));
    }

    @Test
    void testCreateInhousePart() throws Exception {
        when(partsRepository.save(any(Parts.class))).thenReturn(part);
        when(repo.save(any(InhousePart.class))).thenReturn(inhousePart);

        mockMvc.perform(post("/inhouseparts/add/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Part Name\",\"inv\":100,\"price\":10.0}"))
                .andExpect(status().isOk());

        verify(repo, times(1)).save(any(InhousePart.class));
    }

    @Test
    void testUpdateInhousePart() throws Exception {
        when(repo.findById(1L)).thenReturn(Optional.of(inhousePart));
        when(repo.save(any(InhousePart.class))).thenReturn(inhousePart);

        mockMvc.perform(put("/inhouseparts/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"partId\":5,\"name\":\"Updated Part Name\",\"inv\":200,\"price\":20.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.partId").value(5));

        verify(repo, times(1)).save(any(InhousePart.class));
    }

    @Test
    void testDeleteInhousePart() throws Exception {
        when(repo.findById(1L)).thenReturn(Optional.of(inhousePart));

        mockMvc.perform(delete("/inhouseparts/delete/1"))
                .andExpect(status().isOk());

        verify(repo, times(1)).delete(any(InhousePart.class));
    }
}
