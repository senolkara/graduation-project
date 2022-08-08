package com.logobootcamp.otherpurchaseservice.Business.Purchase;

import com.logobootcamp.commonservice.Dto.Buying.BuyingDto;
import com.logobootcamp.commonservice.Dto.Purchase.PurchaseDto;
import com.logobootcamp.otherpurchaseservice.ExceptionHandling.GeneralException;
import com.logobootcamp.commonservice.Requests.BuyingRequest;
import com.logobootcamp.otherpurchaseservice.Dao.Purchase.IPurchaseDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService implements IPurchaseService {

    /**
     * bireysel veya kurumsal kullanıcıların satınalma işlemleri için kullanıldığı servis.
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
    public BuyingDto createPurchase(BuyingRequest buyingRequest, Long loggedUserId) throws GeneralException {
        return this.iPurchaseDao.createPurchase(buyingRequest, loggedUserId);
    }

}
