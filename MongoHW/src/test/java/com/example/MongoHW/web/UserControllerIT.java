package com.example.MongoHW.web;

import com.example.MongoHW.documents.UserDocument;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class UserControllerIT {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeEach
    public void setup() {
        mongoTemplate.getDb().drop();
        UserDocument UserDocument1 = UserDocument.builder().id(1L).firstName("Petro").lastName("Yatsenyshyn").email("email@1").age(26).isMarried(true).build();
        UserDocument UserDocument2 = UserDocument.builder().id(2L).firstName("Andriy").lastName("Yatsenyshyn").email("email@2").age(37).isMarried(false).build();
        mongoTemplate.save(UserDocument1);
        mongoTemplate.save(UserDocument2);
    }

    @Test
    public void createUserDocumentTest() throws Exception {
        UserDocument userDocument = UserDocument.builder().id(3L).firstName("Ivan").lastName("Stron").email("email@3").age(23).isMarried(true).build();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonUserDocument = objectMapper.writeValueAsString(userDocument);

        mockMvc.perform(post("/api/users/create")
                        .content(jsonUserDocument)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value(userDocument.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(userDocument.getLastName()))
                .andExpect(jsonPath("$.email").value(userDocument.getEmail()))
                .andExpect(jsonPath("$.age").value(userDocument.getAge()))
                .andExpect(jsonPath("$.isMarried").value(userDocument.getIsMarried()));
    }

    @Test
    public void getUserDocumentByFirstNameTest() throws Exception {
        mockMvc.perform(get("/api/users/firstName/Petro"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Petro"));
    }

    @Test
    public void getUserDocumentByLastNameTest() throws Exception {
        mockMvc.perform(get("/api/users/lastName/Yatsenyshyn"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].lastName").value("Yatsenyshyn"));
    }

    @Test
    public void getByAgeGreaterThanTest() throws Exception {
        mockMvc.perform(get("/api/users/age/30"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].age").value(37));
    }

    @Test
    public void getUserDocumentByEmailTest() throws Exception {
        mockMvc.perform(get("/api/users/email/email@2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("email@2"));
    }

    @Test
    public void getUserDocumentByIsMarriedTest() throws Exception {
        mockMvc.perform(get("/api/users/married/true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].isMarried").value("true"));
    }

    @Test
    public void deleteUserDocumentTest() throws Exception {
        mockMvc.perform(delete("/api/users/delete/1"))
                .andExpect(status().isOk());

        assertNull(mongoTemplate.findById(1L,UserDocument.class));
    }
}
