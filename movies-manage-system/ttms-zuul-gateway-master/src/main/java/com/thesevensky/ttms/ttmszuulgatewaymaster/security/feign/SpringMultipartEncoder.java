package com.thesevensky.ttms.ttmszuulgatewaymaster.security.feign;


import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import feign.form.ContentType;
import feign.form.FormEncoder;
import feign.form.MultipartFormContentProcessor;
import feign.form.spring.SpringManyMultipartFilesWriter;
import feign.form.spring.SpringSingleMultipartFileWriter;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Map;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/5 14:50
 * @Version 1.0
 */
//public class SpringMultipartEncoder extends FormEncoder {
//
//    /**
//     * Constructor with the default Feign's encoder as a delegate.
//     */
//    public SpringMultipartEncoder() {
//        this(new Default());
//    }
//
//
//    /**
//     * Constructor with specified delegate encoder.
//     *
//     * @param delegate delegate encoder, if this encoder couldn't encode object.
//     */
//    public SpringMultipartEncoder(Encoder delegate) {
//        super(delegate);
//
//        MultipartFormContentProcessor processor = (MultipartFormContentProcessor) getContentProcessor(ContentType.MULTIPART);
//        processor.addWriter(new SpringSingleMultipartFileWriter());
//        processor.addWriter(new SpringManyMultipartFilesWriter());
//    }
//
//
//    @Override
//    public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
//        // 单MultipartFile判断
//        if (bodyType.equals(MultipartFile.class)) {
//            MultipartFile file = (MultipartFile) object;
//            Map data = Collections.singletonMap(file.getName(), object);
//            super.encode(data, MAP_STRING_WILDCARD, template);
//            return;
//        } else if (bodyType.equals(MultipartFile[].class)) {
//            // MultipartFile数组处理
//            MultipartFile[] file = (MultipartFile[]) object;
//            if (file != null) {
//                for (MultipartFile file1 : file) {
//                    System.out.println("zhe li -- " + file1.getOriginalFilename());
//                }
//                Map data = Collections.singletonMap(file.length == 0 ? "" : file[0].getName(), object);
//                System.out.println( "data" + data);
//
//                super.encode(data, MAP_STRING_WILDCARD, template);
//                return;
//            }
//        }
//        // 其他类型调用父类默认处理方法
//        super.encode(object, bodyType, template);
//    }
//
//
//}