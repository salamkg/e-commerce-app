package com.salam.ecommerce.services;

import com.salam.ecommerce.dto.RegisterReponse;

public interface SellerService {
    RegisterReponse register(String inn, String email);
}
