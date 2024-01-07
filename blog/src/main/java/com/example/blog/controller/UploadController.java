package com.example.blog.controller;

import lombok.AllArgsConstructor;;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/file")
@AllArgsConstructor
public class UploadController {

    @CrossOrigin
    @PostMapping("/upload")
    public Map importData(MultipartFile file, HttpServletRequest req) throws IOException {
        Map map = new HashMap();
        String format = new SimpleDateFormat("/yyyy/MM/dd/").format(new Date());
        String realPath = "D:/images";
        File folder = new File(realPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String oldName = file.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
        file.transferTo(new File(folder,newName));
        String url = "images/" + newName;
        System.out.println(url);
        map.put("url",url);
        return map;
    }

}
