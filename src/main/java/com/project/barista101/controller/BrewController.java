package com.project.barista101.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.barista101.model.brew.Brews;
import com.project.barista101.payload.request.BrewRequest;
import com.project.barista101.service.BrewService;
import com.project.barista101.utils.ResponseHandler;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/brew")
@AllArgsConstructor
public class BrewController {

    @Autowired
    private final BrewService brewService;

    @PostMapping(path = "/add")
    public ResponseEntity<Object> addBrew(@RequestPart(name = "image",required = false) MultipartFile image, @RequestPart("brew") BrewRequest brewRequest){
        try{
            Brews brew = brewService.addBrew(image, brewRequest);

            return ResponseHandler.generateResponse("Brew has been added successfully!", HttpStatus.OK, brew);

        } catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @PutMapping(path = "/edit")
    public ResponseEntity<Object> editBrew(@RequestPart(name = "image",required = false) MultipartFile image, @RequestPart("brewId") UUID brewId, @RequestPart("brew") BrewRequest brewRequest){
        try{
            Brews brew = brewService.editBrew(image, brewId, brewRequest);

            return ResponseHandler.generateResponse("Brew has been updated successfully!", HttpStatus.OK, brew);

        } catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<Object> deleteBrew(@RequestParam("brewId") UUID brewId){
        try{
            brewService.deleteBrew(brewId);

            return ResponseHandler.generateResponse("Brew has been deleted successfully!", HttpStatus.OK, null);

        } catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @GetMapping(path = "/thumbnail/{brewId}")
    public byte[] getThumbnail(@PathVariable("brewId") UUID brewId){
        return brewService.getThumbnail(brewId);
    }
}
