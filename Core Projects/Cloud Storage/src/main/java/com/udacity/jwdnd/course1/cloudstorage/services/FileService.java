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

/**
 * This class contains all method for File services
 *
 * @Author Neo Chen
 *
 */
@Service
public class FileService {

    private final FilesMapper filesMapper;
    private final UserMapper userMapper;

    public FileService(FilesMapper filesMapper, UserMapper userMapper) {
        this.filesMapper = filesMapper;
        this.userMapper = userMapper;
    }

    /**
     * The checkFileDuplicate method checks for duplicate filename in the fileMapper
     *
     * @param filename
     * @return true when duplicate found, false when no duplicate found
     */
    public boolean checkFileDuplicate(String filename){
        return filesMapper.selectFile(filename) != null;
    }

    /**
     * The getFileList method gets list from fileMapper by username
     *
     * @param username
     * @return list of files or empty array list
     */
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

    /**
     * The insertFile method insert multipart file and userId into fileMapper
     *
     * @param file
     * @param userId
     * @return int value
     * @throws IOException
     */
    public Integer insertFile(MultipartFile file, Integer userId) throws IOException {
        return filesMapper.insertFile(new File(null,
                                                    file.getOriginalFilename(),
                                                    file.getContentType(),
                                                    Long.toString(file.getSize()),
                                                    userId,
                                                    file.getBytes()));

    }


    /**
     * The deleteFile method deletes file in fileMapper by fileId
     *
     * @param fileId
     */
    public void deleteFile(Integer fileId){
        filesMapper.deleteFile(fileId);
    }
}
