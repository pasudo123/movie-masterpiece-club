package com.club.masterpiece.web.config.xss;

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SerializedString;
import io.micrometer.core.instrument.util.StringEscapeUtils;

import java.util.Arrays;
import java.util.List;

public class HtmlCharacterEscapes extends CharacterEscapes {

    private final int[] asciiEscapes;

    public HtmlCharacterEscapes(){
        // XSS 를 처리할 특수문자 지정
        this.asciiEscapes = CharacterEscapes.standardAsciiEscapesForJSON();
        final List<Character> ESCAPES_CHARACTERS = Arrays.asList('<', '>', '&', '\"', '\'', '(', ')', '#');
        ESCAPES_CHARACTERS.forEach(character -> asciiEscapes[character] = CharacterEscapes.ESCAPE_CUSTOM);
    }

    @Override
    public int[] getEscapeCodesForAscii() {
        return this.asciiEscapes;
    }

    @Override
    public SerializableString getEscapeSequence(int ch) {
        return new SerializedString(StringEscapeUtils.escapeJson(Character.toString((char)ch)));
    }
}
