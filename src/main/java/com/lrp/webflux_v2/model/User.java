package com.lrp.webflux_v2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user_details")
public final class User {
    //TODO: Add auto generated id later
    //Make immutable and optional
    @Id
    private Long id;
    private String name;
    private String email;
}
