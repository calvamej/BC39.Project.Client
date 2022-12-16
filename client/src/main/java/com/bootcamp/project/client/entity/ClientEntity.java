package com.bootcamp.project.client.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Client")
public class ClientEntity {
    @Id
    private String id;
    private String documentNumber;
    private String documentType;
    private String clientType;
    private String clientSubType;
    private String name;
    private String lastname;
    private String companyName;
    private String companyDescription;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date createDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date modifyDate;
}
