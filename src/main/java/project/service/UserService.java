package project.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import project.entities.User;
import project.repository.UserRepository;

@Service
public class UserService {

    private Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;
    
    public ResponseEntity<String> saveFile(MultipartFile file) throws IOException{
        log.info("Nama file = " + file.getOriginalFilename());
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        log.info("Setelah clean path, nama file jadi = " + fileName);
        User user = new User();
        user.setName("Coba");
        user.setFileName(fileName);
        User tempUser = userRepository.save(user);
        String uploadDir = "/dir" + tempUser.getId();
        return null;
    }

    public User save(User user){
        log.info("User adalah " + user.getName());
        log.info("User repository = " + userRepository);
        return userRepository.save(user);
    }
}
