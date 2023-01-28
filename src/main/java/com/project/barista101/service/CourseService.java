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
import com.project.barista101.payload.request.CourseRequest;
import com.project.barista101.repository.CourseRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CourseService {
    
    @Autowired
    private final CourseRepository courseRepository;

    @Transactional
    public List<Courses> getAllCourses(){
        return courseRepository.findAllByOrderByCreatedAtAsc();
    }

    @Transactional
    public Courses getCourse(UUID courseId){
        return courseRepository.findById(courseId)
            .orElseThrow(() -> new IllegalStateException("Course with current id cannot be found"));
    }

    @Transactional
    public Courses addCourse(MultipartFile file, CourseRequest courseRequest){
        Courses course = new Courses();
        course.setTitle(courseRequest.getTitle());
        course.setDescription(courseRequest.getDescription());
        course.setThumbnail(addImage(file));
        course.setCreatedAt(OffsetDateTime.now());

        return courseRepository.save(course);
    }

    @Transactional
    public Courses editCourse(MultipartFile file, UUID courseId, CourseRequest courseRequest){
        Courses course = getCourse(courseId);

        if(courseRequest.getTitle() != null){
            course.setTitle(courseRequest.getTitle());
        }

        if(courseRequest.getDescription() != null){
            course.setDescription(courseRequest.getDescription());
        }

        if(file != null){
            course.setThumbnail(addImage(file));
        }

        return courseRepository.save(course);
    }

    @Transactional
    public void deleteCourse(UUID courseId){
        Courses course = courseRepository.findById(courseId)
            .orElseThrow(() -> new IllegalStateException("Course with current id cannot be found"));

        courseRepository.delete(course);
    }

    public String addImage(MultipartFile file){
        try{
            byte[] image = new byte[0];
            
            if(file == null){
                File defaultImg = new File("src/main/resources/image/course.jpg");
                image = FileUtils.readFileToByteArray(defaultImg);
            } else {
                image = file.getBytes();
            }

            String encodedString = Base64.getEncoder().encodeToString(image);

            return encodedString;
            
        } catch (IOException exception){
            throw new IllegalStateException("Could not add the current file", exception);
        }
    }

    public byte[] getThumbnail(UUID courseId){

        Courses course = getCourse(courseId);

        byte[] decodedBytes = Base64.getDecoder().decode(course.getThumbnail());

        return decodedBytes;
    }
}
