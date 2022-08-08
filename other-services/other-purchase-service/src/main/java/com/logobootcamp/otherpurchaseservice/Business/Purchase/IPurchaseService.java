package com.logobootcamp.otherpurchaseservice.Business.Purchase;

import com.logobootcamp.commonservice.Dto.Buying.BuyingDto;
import com.logobootcamp.commonservice.Dto.Purchase.PurchaseDto;
import com.logobootcamp.otherpurchaseservice.ExceptionHandling.GeneralException;
import com.logobootcamp.commonservice.Requests.BuyingRequest;

import java.util.List;

public interface IPurchaseService {
    List<BuyingDto> getAllPurchase(Long loggedUserId) throws GeneralException;
    BuyingDto getPurchase(Long id, Long loggedUserId) throws GeneralException;
    BuyingDto createPurchase(BuyingRequest buyingRequest, Long loggedUserId) throws GeneralException;
}
