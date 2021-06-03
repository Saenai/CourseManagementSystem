package com.shu.cms.service;

import java.io.File;
import java.util.List;

import com.shu.cms.entity.FileEntity;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    int SaveFile(MultipartFile file) throws Exception;

    List<FileEntity> GetFileInfo();

    File GetFile(String filename);

    int DeleteFile(String filename);
}
