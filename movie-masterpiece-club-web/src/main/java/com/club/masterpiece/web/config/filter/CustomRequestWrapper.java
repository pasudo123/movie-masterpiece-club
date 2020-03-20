package com.club.masterpiece.web.config.filter;

import com.nhncorp.lucy.security.xss.XssFilter;
import jdk.internal.util.xml.impl.Input;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 *
 * HttpServletRequestWrapper
 *  - 서블릿 요청 및 응답 래퍼 클래스. 해당 클래스를 상속받아 사용.
 **/
@Slf4j
public class CustomRequestWrapper extends HttpServletRequestWrapper {

    public String redirectUrl;
    private byte[] bytes;

    public CustomRequestWrapper(final HttpServletRequest request) throws IOException {
        super(request);
        XssFilter xssFilter = XssFilter.getInstance("lucy-xss-servlet-filter-rule.xml", true);
        bytes = xssFilter.doFilter(getBody(request)).getBytes();
    }

    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        return new ServletInputStreamImpl(bis);
    }

    @RequiredArgsConstructor
    class ServletInputStreamImpl extends ServletInputStream {
        private final InputStream inputStream;

        @Override
        public boolean isFinished() { return false; }

        @Override
        public boolean isReady() { return false;}

        @Override
        public void setReadListener(ReadListener listener) {}

        @Override
        public int read() throws IOException {
            return inputStream.read();
        }

        public int read(byte[] bytes) throws IOException {
            return inputStream.read(bytes);
        }
    }

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public CustomRequestWrapper(final HttpServletRequest request, final String ParamUrl) {
        super(request);
        this.redirectUrl = ParamUrl + request.getRequestURI();
    }

    @Override
    public StringBuffer getRequestURL() {
        return new StringBuffer(redirectUrl);
    }

    public String getBody(HttpServletRequest request) throws IOException{
        StringBuilder builder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if(inputStream == null) {
                return Strings.EMPTY;
            }

            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            char[] charBuffer = new char[128];
            int bytesRead = -1;
            while((bytesRead = bufferedReader.read(charBuffer)) > 0){
                builder.append(charBuffer, 0, bytesRead);
            }
            return builder.toString();
        } catch (IOException e) {
            log.error("XSS Filter failed : {}", e.getMessage());
            throw e;
        } finally {
            if(bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    log.error("[Finally block] XSS Filter failed : {}", e.getMessage());
                    throw e;
                }
            }
        }
    }
}
