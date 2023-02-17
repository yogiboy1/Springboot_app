package com.yohan.myfirstapp.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.enumfields.Criteria;
import com.yohan.myfirstapp.model.Document;
import com.yohan.myfirstapp.model.UploadFileResponse;
import com.yohan.myfirstapp.service.FileService;

@RestController
@RequestMapping("/document")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("Criteria") Criteria criteria) {
        Document dbFile = fileService.newDocument(file, criteria);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/document/downloadFile/")
                .path(dbFile.getId())
                .toUriString();
        
        System.out.println(fileDownloadUri);
        
        return new UploadFileResponse(dbFile.getDocname(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files,@RequestParam("criteriaorder") List<Criteria> criterias) {
       // Map<String,Criteria> map = Arrays.asList(files).stream().collect(Collectors.toMap(file->file.getName(), null))
        
        List<MultipartFile> file2 = Arrays.asList(files);
    	return Arrays.asList(files).stream().map(file -> uploadFile(file,criterias.get(file2.indexOf(file)))).collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
        // Load file from database
        Document dbFile = fileService.geDocument(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getDocname() + "\"")
                .body(new ByteArrayResource(dbFile.getFile()));
    }

}
