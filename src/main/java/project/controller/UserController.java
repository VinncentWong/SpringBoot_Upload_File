package project.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import project.entities.User;
import project.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController{
    
    @Autowired
    private UserService userService;

    @PostMapping("/savefile")
    public String saveFile(@RequestParam("file") MultipartFile file) throws IOException{
        return userService.saveFile(file);
    }

    @GetMapping("/download/{filename}")
    public byte[] download(@PathVariable String file, HttpServletRequest request){
        return userService.download(file, request);
    }

    @PostMapping("/save")
    public User save(@RequestBody User user){
        return userService.save(user);
    }
}
