package com.pratham.ecommerceportal.DTO.Response;

import com.pratham.ecommerceportal.DTO.ResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO implements ResponseDTO {
    private String message;
}
