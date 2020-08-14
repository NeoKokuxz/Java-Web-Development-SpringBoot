package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.Mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {

    private FileService fileService;
    private UserMapper userMapper;

    public FileController(FileService fileService, UserMapper userMapper) {
        this.fileService = fileService;
        this.userMapper = userMapper;
    }

    @GetMapping("file/delete/{fileId}")
    public String deleteFile(@PathVariable Integer fileId, Model model){
        String successMessage = "Success deleted file";
        fileService.deleteFile(fileId);
        model.addAttribute("successMessage", successMessage);
        return "result";
    }

    @PostMapping("/file/upload")
    public String uploadFile(Authentication a, MultipartFile multipartFile, Model model) {
        String errorMessage = null;
        String successMessage = null;
        int rowAffected;
        try {
            if(multipartFile.isEmpty() || fileService.checkFileDuplicate(multipartFile.getOriginalFilename())){
                System.out.println("isEmpty || filename-----");
                errorMessage = "File uploaded fail : duplicate file or upload file is empty!";
                model.addAttribute("errorMessage", errorMessage);
            } else {
                rowAffected = fileService.insertFile(multipartFile, userMapper.getUser(a.getName()).getUserId());
                if(rowAffected <= 0){
                    errorMessage = "Error upload/update file!";
                    model.addAttribute("errorMessage", errorMessage);
                    return "result";
                }
                successMessage = "Success uploaded file! Continue ";
                model.addAttribute("successMessage", successMessage);
            }
            return "result";
        } catch (Exception exception){
            errorMessage = "Exception error! Try again later";
            model.addAttribute("errorMessage", errorMessage);
            return "result";
        }
    }
}
