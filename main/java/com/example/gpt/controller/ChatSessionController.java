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
    //引入仓库
    @Autowired
    private ChatMessageRepository messageRepository;
    //添加接口
    @PostMapping(path="/add")
    public @ResponseBody String addSession (@RequestBody ChatSession session) {

        sessionRepository.save(session);
        return "Saved";
    }
    //消息添加接口
    @PostMapping(path="/message/add")
    public @ResponseBody String addMessage (@RequestParam String sessionId, @RequestBody ChatMessage message) {

        ChatSession session = sessionRepository.findById(sessionId).get();
        message.setSession(session);
        messageRepository.save(message);
        return "Saved";
    }
    //删除接口
    @PostMapping(path="/delete") //
    public @ResponseBody String deleteSession (@RequestParam String sessionId) {

        sessionRepository.deleteById(sessionId);
        return "Deleted";
    }
    //获取接口
    @GetMapping(path="/all")
    public @ResponseBody Iterable<ChatSession> getAllSessions() {

        return sessionRepository.findAll();
    }
}
