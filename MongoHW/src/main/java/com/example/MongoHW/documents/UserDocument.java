package com.example.MongoHW.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class UserDocument {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
    private Boolean isMarried;
}
