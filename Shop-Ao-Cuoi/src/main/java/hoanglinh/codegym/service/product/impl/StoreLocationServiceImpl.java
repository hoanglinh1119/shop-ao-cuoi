package hoanglinh.codegym.service.product.impl;

import hoanglinh.codegym.model.product.ProductProperties.StoreLocation;
import hoanglinh.codegym.repositories.product.IStoreLocationRepository;
import hoanglinh.codegym.service.product.IStoreLocationService;
import org.springframework.beans.factory.annotation.Autowired;

public class StoreLocationServiceImpl implements IStoreLocationService {
    @Autowired
    IStoreLocationRepository iStoreLocationRepository;
    @Override
    public Iterable<StoreLocation> findAll() {
        return iStoreLocationRepository.findAll();
    }

    @Override
    public StoreLocation findById(Long id) {
        return iStoreLocationRepository.findOne(id);
    }

    @Override
    public void save(StoreLocation storeLocation) {
      iStoreLocationRepository.save(storeLocation);
    }

    @Override
    public void remove(Long id) {
     iStoreLocationRepository.delete(id);
    }
}
