package com.pratham.ecommerceportal.DTO.Response;

import com.pratham.ecommerceportal.DTO.ResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllDTO implements ResponseDTO {
    List<GetProductDTO> products;
}
