package com.bootcamp.project.client.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Client")
public class ClientEntity {
    @Id
    private ObjectId id;
    private String documentNumber;
    private String name;
    private String lastname;
    private String type;
    private Date createDate;
    private Date modifyDate;
}
