package com.pratham.ecommerceportal.Controller;

import com.pratham.ecommerceportal.DTO.Response.HealthDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;

@RestController
public class HomeController {

    @GetMapping("/health")
    public ResponseEntity<HealthDTO> health(){
        try{
            return ResponseEntity.ok(new HealthDTO("Server Running", LocalDateTime.now()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HealthDTO("Server Not Running",LocalDateTime.now()));
        }
    }
}
