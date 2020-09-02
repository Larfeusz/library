package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Book {


    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;



    private String title;

    @JsonProperty("autor")
    private String author;

    @JsonIgnore
    private String isbn;


}
