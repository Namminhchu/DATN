package com.migi.migi_project.service.admin;

import com.migi.migi_project.model.dto.ServiceWorkerDTO;
import com.migi.migi_project.model.response.ResponseNormal;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ManageServiceWorkerService {
    List<ServiceWorkerDTO> findAll();
    ResponseNormal save(ServiceWorkerDTO dto, MultipartFile file);
    ResponseNormal delete(Integer id);
}
