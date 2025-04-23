package com.example.gpt.controller;

import com.example.gpt.entity.ChatMessage;
import com.example.gpt.entity.ChatSession;
import com.example.gpt.repository.ChatMessageRepository;
import com.example.gpt.repository.ChatSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="http://localhost:3000")
@Controller
@RequestMapping(path="/session")
public class ChatSessionController {
    @Autowired
    private ChatSessionRepository sessionRepository;
    //����ֿ�
    @Autowired
    private ChatMessageRepository messageRepository;
    //��ӽӿ�
    @PostMapping(path="/add")
    public @ResponseBody String addSession (@RequestBody ChatSession session) {

        sessionRepository.save(session);
        return "Saved";
    }
    //��Ϣ��ӽӿ�
    @PostMapping(path="/message/add")
    public @ResponseBody String addMessage (@RequestParam String sessionId, @RequestBody ChatMessage message) {

        ChatSession session = sessionRepository.findById(sessionId).get();
        message.setSession(session);
        messageRepository.save(message);
        return "Saved";
    }
    //ɾ���ӿ�
    @PostMapping(path="/delete") //
    public @ResponseBody String deleteSession (@RequestParam String sessionId) {

        sessionRepository.deleteById(sessionId);
        return "Deleted";
    }
    //��ȡ�ӿ�
    @GetMapping(path="/all")
    public @ResponseBody Iterable<ChatSession> getAllSessions() {

        return sessionRepository.findAll();
    }
}
