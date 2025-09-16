package com.pratham.ecommerceportal.DTO.Response;

import com.pratham.ecommerceportal.DTO.ResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class HealthDTO implements ResponseDTO {

    private String message;
    private LocalDateTime date;
}
