package com.salam.ecommerce.product;

import com.salam.ecommerce.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/products/{id}")
    ProductResponse getProduct(@PathVariable Long id);

    @PutMapping("/products/{id}/decrease-stock")
    void decreaseStock(@PathVariable Long id, @RequestParam int quantity);

//    @Value("${application.config.product-url}")
//    private String productUrl;
//    private final RestTemplate restTemplate;
//
//    public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> requestBody) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.set(CONTENT_TYPE, APPLICATION_JSON_VALUE);
//
//        HttpEntity<List<PurchaseRequest>> requestEntity = new HttpEntity<>(requestBody, headers);
//        ParameterizedTypeReference<List<PurchaseResponse>> responseType = new ParameterizedTypeReference<>() {};
//        ResponseEntity<List<PurchaseResponse>> responseEntity = restTemplate.exchange(
//                productUrl + "/purchase",
//                POST,
//                requestEntity,
//                responseType
//        );
//        if (responseEntity.getStatusCode().isError()) {
//            throw new BusinessException("Error while retrieving purchases " + responseEntity.getStatusCode());
//        }
//        return responseEntity.getBody();
//    }
}
