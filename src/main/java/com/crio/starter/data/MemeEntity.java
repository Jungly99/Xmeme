package com.crio.starter.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "meme")
public class MemeEntity {

    //@Id
    //ObjectId _id;  

    @Id
    private String _id;

    @NotNull
    private String name;

    @NotNull
    private String url;

    @NotNull
    private String caption;

    private LocalDateTime createdAt;

    /*public MemeEntity(@NotNull String id, @NotNull String name, @NotNull String imageUrl, @NotNull String caption) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.caption = caption;
    }*/
}