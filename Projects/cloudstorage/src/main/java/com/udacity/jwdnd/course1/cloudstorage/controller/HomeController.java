package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private CredentialService credentialService;
    private NoteService noteService;
    private FileService fileService;

    public HomeController(CredentialService credentialService, NoteService noteService, FileService fileService){
        this.credentialService = credentialService;
        this.noteService = noteService;
        this.fileService = fileService;
    }

    //Return Home Page View
    @GetMapping()
    public String getHomeView(Authentication a, Model model){
        //Get data when refresh or get this home view
        List<File> fileList = fileService.getFileList(a.getName());
        List<Note> noteList = noteService.getNoteList(a.getName());
        List<Credential> credentialList = credentialService.getCredentialList(a.getName());
        model.addAttribute("name", a.getName());
        model.addAttribute("fs", fileList);
        model.addAttribute("ns", noteList);
        model.addAttribute("cs", credentialList);

        return "home";
    }



}
