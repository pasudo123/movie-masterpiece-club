package com.club.masterpiece.web.config;

import com.fasterxml.jackson.core.JsonpCharacterEscapes;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SerializedString;

import java.util.Arrays;
import java.util.List;

/**
 * reference : https://www.baeldung.com/java-apache-commons-text
 */
public class AntiXSSConfiguration{

    public AntiXSSConfiguration(){}

    public static class HTMLCharacterEscapes extends JsonpCharacterEscapes {

        private final int[] asciiEscapes;

        public HTMLCharacterEscapes() {
            // XSS 를 처리할 특수문자 지정
            this.asciiEscapes = CharacterEscapes.standardAsciiEscapesForJSON();
            final List<Character> ESCAPES_CHARACTERS = Arrays.asList('<', '>', '\"', '\'', '(', ')', '#', '&');
            ESCAPES_CHARACTERS.forEach(character -> asciiEscapes[character] = CharacterEscapes.ESCAPE_CUSTOM);
        }

        @Override
        public SerializableString getEscapeSequence(int ch) {
            switch (ch) {
                case '&' : return new SerializedString("&#38;");
                case '<' : return new SerializedString("&#60;");
                case '>' : return new SerializedString("&#62;");
                case '\"' : return new SerializedString("&#34;");
                case '\'' : return new SerializedString("&#39;");
                default : return super.getEscapeSequence(ch);
            }
        }
    }
}