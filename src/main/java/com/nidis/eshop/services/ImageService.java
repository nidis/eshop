package com.nidis.eshop.services;

import com.nidis.eshop.models.Image;
import com.nidis.eshop.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    private ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public List<Image> findAll() {
        return imageRepository.findAll();
    }
}
