package com.shu.cms.service.impl;

import com.shu.cms.entity.FileEntity;
import com.shu.cms.mapper.FileMapper;
import com.shu.cms.service.FileService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import com.shu.cms.config.GlobalVariable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileMapper mapper;

    @Override
    public int SaveFile(MultipartFile file) throws Exception {
        FileEntity fileEntity = new FileEntity();

        fileEntity.setSize(file.getSize());
        fileEntity.setOriginname(file.getOriginalFilename());
        fileEntity.setServerpath(GlobalVariable.FILE_DIRECTORY);

        File dest = new File(fileEntity.getServerpath(), fileEntity.getOriginname());
        if (!dest.getParentFile().exists()) {
            boolean rel = dest.getParentFile().mkdirs();
            if (!rel) {
                return 0;
            }
        }

        InputStream is = file.getInputStream();
        OutputStream os = new FileOutputStream(dest);
        try {
            byte[] buffer = new byte[8 * 1024];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            return 0;
        } finally {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }
        }
        mapper.insertEntity(fileEntity);
        return 1;
        // param file
        // return 1
        // error 0

        // filename =process> servername
        // filename =process> originname

        // global variable AS path

        // file =>local directory
        // file record =>table file
    }

    @Override
    public List<FileEntity> GetFileInfo() {
        return mapper.selectEntityWithoutPath();
    }

    @Override
    public File GetFile(String filename) {
        FileEntity fileEntity = mapper.selectEntityById(filename);
        File dest = new File(fileEntity.getServerpath(), fileEntity.getOriginname());

        if (dest.exists()) {
            return dest;
        } else
            return null;
    }

    public ResponseEntity<FileSystemResource> export(File file) {
        if (file == null) {
            return null;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=" + file.getName());
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Last-Modified", new Date().toString());
        headers.add("ETag", String.valueOf(System.currentTimeMillis()));
        return ResponseEntity.ok().headers(headers).contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream")).body(new FileSystemResource(file));
    }

    @Override
    public int DeleteFile(String filename) {
        FileEntity fileEntity = mapper.selectEntityById(filename);
        // Delete table row
        mapper.deleteEntityById(filename);
        // Delete entity of server local file
        File dest = new File(fileEntity.getServerpath(), fileEntity.getOriginname());
        if (dest.exists()) {
            dest.delete();
            return 1;
        } else
            return 0;
    }

}
