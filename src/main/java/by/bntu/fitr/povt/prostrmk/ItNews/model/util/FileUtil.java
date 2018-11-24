package by.bntu.fitr.povt.prostrmk.ItNews.model.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {

    public static String saveFile(MultipartFile file){
        String filePath = "src/main/webapp/resources/pics/";
        File javaFile = new File(filePath + file.getOriginalFilename());
        try {
            byte[] bytes = file.getBytes();
            javaFile = new File(javaFile.getAbsolutePath());
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(javaFile));
            stream.write(bytes);
            stream.flush();
            stream.close();
            return "/resources/pics/" + file.getOriginalFilename();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
