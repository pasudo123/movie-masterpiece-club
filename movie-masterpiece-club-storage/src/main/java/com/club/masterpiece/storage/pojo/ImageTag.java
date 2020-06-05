//package com.club.masterpiece.storage.pojo;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//import lombok.Getter;
//import org.apache.logging.log4j.util.Strings;
//import org.springframework.util.StringUtils;
//
//import java.util.Base64;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//@Getter
//public final class ImageTag {
//    private static final Pattern IMAGE_SRC_PATTERN = Pattern.compile("src(\\s)?=\"(.*?)\"");
//    private static final Pattern IMAGE_STYLE_PATTERN = Pattern.compile("style(\\s)?=\"(.*?)\"");
//    private static final Pattern IMAGE_WIDTH_PATTERN = Pattern.compile("width(\\s)?=\"(.*?)\"");
//
//    public String imageTag;
//    private String src;
//    private String style;
//    private String width;
//
//    private ImageTag(final String imageTag){
//        this.imageTag = imageTag;
//    }
//
//    public static ImageTag create(final String imageTag) {
//        final ImageTag entity = new ImageTag(imageTag);
//        entity.setSrc();
//        entity.setStyle();
//        entity.setWidth();
//        return entity;
//    }
//
//    private void setSrc(){
//        final Matcher matcher = IMAGE_SRC_PATTERN.matcher(imageTag);
//        this.src = (matcher.find()) ? getElement(matcher.group()) : Strings.EMPTY;
//    }
//
//    private void setStyle(){
//        final Matcher matcher = IMAGE_STYLE_PATTERN.matcher(imageTag);
//        this.style = (matcher.find()) ? getElement(matcher.group()) : Strings.EMPTY;
//    }
//
//    private void setWidth(){
//        final Matcher matcher = IMAGE_WIDTH_PATTERN.matcher(imageTag);
//        this.width = (matcher.find()) ? getElement(matcher.group()) : Strings.EMPTY;
//    }
//
//    public String getPropsJson(ObjectMapper mapper) {
//        assert mapper != null;
//        ObjectNode node = mapper.createObjectNode();
//        node.put("style", this.style);
//        node.put("width", (StringUtils.isEmpty(this.width) ? "100%" : this.width));
//        return node.toString();
//    }
//
//    public byte[] decodeBase64(){
//        String imageDataBytes = this.src.substring(this.src.indexOf(",") + 1);
//        return Base64.getDecoder().decode(imageDataBytes);
//    }
//
//    private String getElement(final String line) {
//        int beginIndex = line.indexOf("\"") + 1;
//        int lastIndex = line.lastIndexOf("\"");
//        return line.substring(beginIndex, lastIndex);
//    }
//}
