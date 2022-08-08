package com.logobootcamp.adminpurchaseservice.Dao.Purchase;

import com.logobootcamp.commonservice.Dto.Buying.BuyingDto;
import com.logobootcamp.adminpurchaseservice.ExceptionHandling.GeneralException;

import java.util.List;

public interface IPurchaseDao {
    List<BuyingDto> getAllPurchase(Long loggedUserId) throws GeneralException;
    BuyingDto getPurchase(Long id, Long loggedUserId) throws GeneralException;
    void deletePurchase(Long id, Long loggedUserId) throws GeneralException;
}
