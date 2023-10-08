package com.backengtest.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectWorkingDto {
    private Long id;
    @NotBlank(message = "Title is required")
    private String title;
    private String description;
    private double payment;
    private Boolean active;
    private Date date;
    private Long projectDomainId;
    private Date accdate;
    private int progress;
    private Boolean finished;
    private String userName;
}
