package com.project.barista101.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.barista101.model.account.Accounts;
import com.project.barista101.model.course.Courses;
import com.project.barista101.model.course.Enrollments;
import com.project.barista101.model.course.Modules;
import com.project.barista101.payload.request.EnrollmentRequest;
import com.project.barista101.repository.AccountRepository;
import com.project.barista101.repository.CourseRepository;
import com.project.barista101.repository.EnrollmentRepository;
import com.project.barista101.repository.ModuleRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EnrollmentService {
    
    @Autowired
    private final EnrollmentRepository enrollmentRepository;
    @Autowired
    private final AccountRepository accountRepository;
    @Autowired
    private final CourseRepository courseRepository;
    @Autowired
    private final ModuleRepository moduleRepository;

    @Transactional
    public List<Enrollments> getAllEnrollmentsForAccount(UUID accountId){
        Accounts account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalStateException("Account with current id cannot be found"));

        return enrollmentRepository.findAllByAccount(account);
    }

    @Transactional
    public Enrollments getEnrollmentForCourseAndAccount(UUID courseId, UUID accountId){
        Courses course = courseRepository.findById(courseId)
            .orElseThrow(() -> new IllegalStateException("Course with current id cannot be found"));

        Accounts account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalStateException("Account with current id cannot be found"));

        return enrollmentRepository.findByCourseAndAccount(course, account);
    }

    @Transactional
    public Enrollments getEnrollment(UUID enrollmentId){
        return enrollmentRepository.findById(enrollmentId)
            .orElseThrow(() -> new IllegalStateException("Enrollment with current id cannot be found"));
    }

    @Transactional
    public Enrollments addEnrollment(EnrollmentRequest enrollmentRequest){
        Courses course = courseRepository.findById(enrollmentRequest.getCourseId())
            .orElseThrow(() -> new IllegalStateException("Course with current id cannot be found"));

        Accounts account = accountRepository.findById(enrollmentRequest.getAccountId())
            .orElseThrow(() -> new IllegalStateException("Account with current id cannot be found"));

        Enrollments enrollment = new Enrollments();
        enrollment.setAccount(account);
        enrollment.setCourse(course);
        enrollment.setStartDate(OffsetDateTime.now());

        List<Modules> moduleList = moduleRepository.findAllByCourseOrderByCreatedAtAsc(course);

        JSONArray moduleStatus = new JSONArray();
        
        moduleList.stream().forEach(
            (moduleObject) -> {
                JSONObject module = new JSONObject();
                module.put("moduleId", moduleObject.getId());
                module.put("done", false);
                moduleStatus.put(module);
            }
        );

        enrollment.setModuleStatus(moduleStatus.toString());

        return enrollmentRepository.save(enrollment);
    }

    @Transactional
    public Enrollments finishModule(UUID enrollmentId, UUID moduleId){
        Enrollments enrollment = getEnrollment(enrollmentId);

        JSONArray moduleStatus = new JSONArray(enrollment.getModuleStatus());

        for(int i=0;i<moduleStatus.length();i++){
            JSONObject module = moduleStatus.getJSONObject(i);
            if(module.getString("moduleId").equals(moduleId.toString())){
                if(!module.getBoolean("done")){
                    module.put("done", true);
                    moduleStatus.put(i,module);
                    enrollment.setModuleStatus(moduleStatus.toString());
                    return enrollmentRepository.save(enrollment);
                }
            }
        }

        return null;
    }

    @Transactional
    public double calculateProgress(UUID enrollmentId){
        Enrollments enrollment = getEnrollment(enrollmentId);

        double totalDone = 0;
        
        JSONArray moduleStatus = new JSONArray(enrollment.getModuleStatus());

        for(int i=0;i<moduleStatus.length();i++){
            JSONObject module = moduleStatus.getJSONObject(i);
            if(module.getBoolean("done")){
                totalDone += 1;
            }
        }

        double percentage = (totalDone / moduleStatus.length()) * 100;

        BigDecimal progress = new BigDecimal(Double.toString(percentage));
        progress = progress.setScale(0, RoundingMode.HALF_UP);

        return progress.doubleValue();
    }
}
