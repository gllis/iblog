package com.gllis.iblog.controller;

import com.aliyun.oss.OSSClient;
import com.gllis.iblog.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/file/")
public class FileController {

    @Value("${aliyun.oss.default.url}")
    private String ossURL;

    @Value("${aliyun.oss.bucketName}")
    private String bucketName;

    @Autowired
    private OSSClient ossClient;

    /**
     * 上传文件
     *
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("upload")
    public Mono<Result> upload(@RequestPart("file") FilePart file) throws IOException {

        Path tempFile = Files.createTempFile("temp", file.filename());
        String filename = System.currentTimeMillis() + "_" + file.filename();

        // 保存文件到本地
        file.transferTo(tempFile.toFile());
        File tmp = tempFile.toFile();

        ossClient.putObject(bucketName, "file/" + filename, tmp);

        if (tmp.exists()) {
            tmp.delete();
        }

        return Mono.just(new Result(ossURL + filename));
    }

}
