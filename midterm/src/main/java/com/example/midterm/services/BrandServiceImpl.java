package com.example.midterm.services;

import com.example.midterm.models.Brand;
import com.example.midterm.repositories.BrandRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BrandServiceImpl implements BrandService{
    @Autowired
    private BrandRepository brandRepository;
    @Override
    public List<Brand> getAllBrands(){
        return this.brandRepository.findAll();
    }
    @Override
    public Brand addBrand(Brand brand){
        return this.brandRepository.save(brand);
    }
    @Override
    public Optional<Brand> getBrandById(int id){
        return this.brandRepository.findById(id);
    }
    @Override
    public void deleteBrand(int id){
        this.brandRepository.deleteById(id);
    }
    @Override
    public Brand updateBrand(int id, Brand brand){
        Optional<Brand> update = this.brandRepository.findById(id);
        if(update.isPresent()){
            brand.setBrandId(id);
            return this.brandRepository.save(brand);
        }
        else{
            return null;
        }
    }
}
