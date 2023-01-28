package com.project.barista101.service;

import java.io.File;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.barista101.model.brew.Brews;
import com.project.barista101.payload.request.BrewRequest;
import com.project.barista101.repository.BrewRepository;
import com.project.barista101.utils.ProfileImageUtils;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BrewService {
    
    @Autowired
    private final BrewRepository brewRepository;

    @Transactional
    public List<Brews> getAllBrews(){
        return brewRepository.findAllByOrderByCreatedAtAsc();
    }

    @Transactional
    public Brews getBrew(UUID brewId){
        return brewRepository.findById(brewId)
            .orElseThrow(() -> new IllegalStateException("Brew with current id cannot be found"));
    }

    @Transactional
    public Brews addBrew(MultipartFile file, BrewRequest brewRequest){
        Brews brew = new Brews();
        brew.setTitle(brewRequest.getTitle());
        brew.setDescription(brewRequest.getDescription());
        brew.setCoffee(brewRequest.getCoffee());
        brew.setWater(brewRequest.getWater());
        brew.setRatio(brewRequest.getRatio());
        brew.setTime(brewRequest.getTime());
        brew.setGrindSize(brewRequest.getGrindSize());
        brew.setPreparations(brewRequest.getPreparations());
        brew.setSteps(brewRequest.getSteps());
        brew.setThumbnail(addImage(file));
        brew.setCreatedAt(OffsetDateTime.now());

        return brewRepository.save(brew);
    }

    @Transactional
    public Brews editBrew(MultipartFile file, UUID brewId, BrewRequest brewRequest){
        Brews brew = getBrew(brewId);

        if(brewRequest.getTitle() != null){
            brew.setTitle(brewRequest.getTitle());
        }

        if(brewRequest.getDescription() != null){
            brew.setDescription(brewRequest.getDescription());
        }

        if(brewRequest.getCoffee() != null){
            brew.setCoffee(brewRequest.getCoffee());
        }

        if(brewRequest.getWater() != null){
            brew.setWater(brewRequest.getWater());
        }

        if(brewRequest.getRatio() != null){
            brew.setRatio(brewRequest.getRatio());
        }

        if(brewRequest.getTime() != null){
            brew.setTime(brewRequest.getTime());
        }

        if(brewRequest.getGrindSize() != null){
            brew.setGrindSize(brewRequest.getGrindSize());
        }

        if(brewRequest.getPreparations() != null){
            brew.setPreparations(brewRequest.getPreparations());
        }

        if(brewRequest.getSteps() != null){
            brew.setSteps(brewRequest.getSteps());
        }

        if(file != null){
            brew.setThumbnail(addImage(file));
        }

        return brewRepository.save(brew);
    }

    @Transactional
    public void deleteBrew(UUID brewId){
        Brews brew = brewRepository.findById(brewId)
            .orElseThrow(() -> new IllegalStateException("Brew with current id cannot be found"));

        brewRepository.delete(brew);
    }

    public String addImage(MultipartFile file){
        try{
            byte[] image = new byte[0];
            
            if(file == null){
                File defaultImg = new File("src/main/resources/image/brew.png");
                image = FileUtils.readFileToByteArray(defaultImg);
            } else {
                image = ProfileImageUtils.cropImageSquare(file);
            }

            String encodedString = Base64.getEncoder().encodeToString(image);

            return encodedString;
            
        } catch (IOException exception){
            throw new IllegalStateException("Could not add the current file", exception);
        }
    }

    public byte[] getThumbnail(UUID brewId){

        Brews brew = getBrew(brewId);

        byte[] decodedBytes = Base64.getDecoder().decode(brew.getThumbnail());

        return decodedBytes;
    }
}
