package com.thesevensky.ttms.ttmsproviderusermaster.commons.mail;

import com.thesevensky.starter.properties.MasterProperties;
import com.thesevensky.ttms.ttmsproviderusermaster.commons.Generator;

import java.util.Random;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/8 19:44
 * @Version 1.0
 */
public class EmailCodeGenerator implements Generator<String> {

    private MasterProperties masterProperties = null;

    private final int codeLength = 6;

    public EmailCodeGenerator() {
    }

    public EmailCodeGenerator(MasterProperties masterProperties) {
        this.masterProperties = masterProperties;
    }

    @Override
    public String generate() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < ((masterProperties == null)?
                codeLength : masterProperties.getEmail().getCodeLength()); i++) {
            stringBuilder.append(EmailCodeGeneratorHolder.getChar());
        }
        return stringBuilder.toString();
    }

    private static class EmailCodeGeneratorHolder{
        private static final Random random = new Random();
        private static char getChar() {
            int abs = Math.abs(random.nextInt());
            int mod = abs % 3;
            switch (mod) {
                case 0 : return (char) (Math.abs(random.nextInt())% 26 + 65);
                case 1 : return (char) (Math.abs(random.nextInt())% 10 + 48);
                default: return (char) (Math.abs(random.nextInt())% 26 + 97);
            }
        }
    }
}
