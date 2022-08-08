package com.logobootcamp.adminpurchaseservice.Business.Purchase;

import com.logobootcamp.commonservice.Dto.Buying.BuyingDto;
import com.logobootcamp.commonservice.Dto.Purchase.PurchaseDto;
import com.logobootcamp.adminpurchaseservice.Dao.Purchase.IPurchaseDao;
import com.logobootcamp.adminpurchaseservice.ExceptionHandling.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService implements IPurchaseService {

    /**
     * satınalma işlemleri için kullanılan servis
     *
     * bu katmandan data(repository) katmanımıza ulaşıyoruz.
     *
     */
    private final IPurchaseDao iPurchaseDao;

    @Override
    public List<BuyingDto> getAllPurchase(Long loggedUserId) throws GeneralException {
        return this.iPurchaseDao.getAllPurchase(loggedUserId);
    }

    @Override
    public BuyingDto getPurchase(Long id, Long loggedUserId) throws GeneralException {
        return this.iPurchaseDao.getPurchase(id, loggedUserId);
    }

    @Override
    public void deletePurchase(Long id, Long loggedUserId) throws GeneralException {
        this.iPurchaseDao.deletePurchase(id, loggedUserId);
    }
}
