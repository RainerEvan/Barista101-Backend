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

import com.project.barista101.model.course.Courses;
import com.project.barista101.model.course.Modules;
import com.project.barista101.payload.request.ModuleRequest;
import com.project.barista101.repository.CourseRepository;
import com.project.barista101.repository.ModuleRepository;
import com.project.barista101.utils.ProfileImageUtils;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ModuleService {
    
    @Autowired
    private final ModuleRepository moduleRepository;
    @Autowired
    private final CourseRepository courseRepository;

    @Transactional
    public List<Modules> getAllModulesForCourse(UUID courseId){
        Courses course = courseRepository.findById(courseId)
            .orElseThrow(() -> new IllegalStateException("Course with current id cannot be found"));

        return moduleRepository.findAllByCourseOrderByCreatedAtAsc(course);
    }

    @Transactional
    public int countModulesForCourse(UUID courseId){
        Courses course = courseRepository.findById(courseId)
            .orElseThrow(() -> new IllegalStateException("Course with current id cannot be found"));

        return moduleRepository.countByCourse(course);
    }

    @Transactional
    public Modules getModule(UUID moduleId){
        return moduleRepository.findById(moduleId)
            .orElseThrow(() -> new IllegalStateException("Module with current id cannot be found"));
    }

    @Transactional
    public Modules addModule(MultipartFile file, ModuleRequest moduleRequest){
        Courses course = courseRepository.findById(moduleRequest.getCourseId())
            .orElseThrow(() -> new IllegalStateException("Course with current id cannot be found"));

        Modules module = new Modules();
        module.setCourse(course);
        module.setTitle(moduleRequest.getTitle());
        module.setThumbnail(addImage(file));
        module.setCreatedAt(OffsetDateTime.now());

        return moduleRepository.save(module);
    }

    @Transactional
    public Modules editModule(MultipartFile file, UUID moduleId, ModuleRequest moduleRequest){
        Modules module = getModule(moduleId);

        if(moduleRequest.getTitle() != null){
            module.setTitle(moduleRequest.getTitle());
        }

        if(file != null){
            module.setThumbnail(addImage(file));
        }

        return moduleRepository.save(module);
    }

    @Transactional
    public void deleteModule(UUID moduleId){
        Modules module = moduleRepository.findById(moduleId)
            .orElseThrow(() -> new IllegalStateException("Module with current id cannot be found"));

        moduleRepository.delete(module);
    }

    public String addImage(MultipartFile file){
        try{
            byte[] image = new byte[0];
            
            if(file == null){
                File defaultImg = new File("src/main/resources/image/module.jpg");
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

    public byte[] getThumbnail(UUID moduleId){

        Modules module = getModule(moduleId);

        byte[] decodedBytes = Base64.getDecoder().decode(module.getThumbnail());

        return decodedBytes;
    }
}
