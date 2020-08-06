package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Mapper.FilesMapper;
import com.udacity.jwdnd.course1.cloudstorage.Mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class FileService {

    private final FilesMapper filesMapper;
    private final UserMapper userMapper;

    public FileService(FilesMapper filesMapper, UserMapper userMapper) {
        this.filesMapper = filesMapper;
        this.userMapper = userMapper;
    }

    //Check duplicate
    public boolean checkFileDuplicate(String filename){
        // != null - True = Duplicated
        // == null - false = No duplicate
        return filesMapper.selectFile(filename) != null;
    }

    //Get
    public List<File> getFileList(String username){
        List<File> fileList = filesMapper.selectFileList(userMapper.getUser(username).getUserId());
        if(fileList == null){
            return new ArrayList<>();
        }
        for(File file : fileList){
            file.setUrl("data" +
                    ":" + file.getContentType() + ";base64," + Base64.getEncoder().encodeToString(file.getFileData()));
        }
        return fileList;
    }

    //Post
    public Integer insertFile(MultipartFile file, Integer userId) throws IOException {
        return filesMapper.insertFile(new File(null,
                                                    file.getOriginalFilename(),
                                                    file.getContentType(),
                                                    Long.toString(file.getSize()),
                                                    userId,
                                                    file.getBytes()));

    }

//    //Put
//    public Integer updateFile(File file){
//        return filesMapper.updateFile(file);
//    }

    //Delete
    public void deleteFile(Integer fileId){
        filesMapper.deleteFile(fileId);
    }
}
