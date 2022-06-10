package project.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import project.entities.User;
import project.repository.UserRepository;

@Service
public class UserService {

    private Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;
    
    public String saveFile(MultipartFile file) throws IOException{
        log.info("Nama original file = " + file.getOriginalFilename());
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        log.info("Nama file yang baru = " + fileName);
        User user = new User(1L, "Cent", fileName, fileName.getBytes());
        userRepository.save(user);

        String url = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/download")
                    .path(fileName)
                    .toUriString();
        return "Link Download = " + url;
    }

    public byte[] download(String fileName, HttpServletRequest request){
        User user = userRepository.findByFileName(fileName);
        String mimeType = request.getServletContext().getMimeType(fileName);
        log.info(mimeType);
        return user.getDoc();
    }
    public User save(User user){
        log.info("User adalah " + user.getName());
        log.info("User repository = " + userRepository);
        return userRepository.save(user);
    }
}
