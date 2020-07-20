package com.udacity.jwdnd.c1.review.controller;

import com.udacity.jwdnd.c1.review.model.ChatForm;
import com.udacity.jwdnd.c1.review.service.MessageService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private MessageService ms;

    public ChatController(MessageService ms){
        this.ms = ms;
    }

    @GetMapping
    public String getChatPAge(ChatForm cf, Model model){
        //Register the attribute to get chat history
        model.addAttribute("chatMessages", this.ms.getChatMessage() );
        //return chat.html when access Get /chat
        return "chat";
    }

    @PostMapping
    public String postChatMessage(Authentication a, ChatForm chatform, Model model){
        chatform.setUsername(a.getName());
        //chatform.setUsername("test");
        this.ms.addMessage(chatform);
        chatform.setMessageText("");
        model.addAttribute("chatMessages", this.ms.getChatMessage() );
        System.out.println(chatform.getMessageText());
        return "chat";
    }

    @ModelAttribute("allMessageTypes")
    public String[] allMessageType(){
        return new String[]{"Say", "Shout", "Whisper"};
    }

}
