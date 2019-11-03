package at.technikum.rh.Interfaces;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public interface Request {
    /**
     * @return Returns true if the request is valid. A request is valid, if
     *         method and url could be parsed. A header is not necessary.
     */
    boolean isValid();

    /**
     * @return Returns the request method in UPPERCASE. get -> GET
     */
    String getMethod();

    /**
     * @return Returns a URL object of the request. Never returns null.
     */
    Url getUrl();

    /**
     * @return
     */
    Map<String, String> getHeaders();

    /**
     * @return Returns the number of header or 0, if no header where found.
     */
    int getHeaderCount();

    /**
     * @return Returns the user agent from the request header
     */
    String getUserAgent();

    /**
     * @return Returns the parsed content length request header. Never returns
     *         null.
     */
    int getContentLength();

    /**
     * @return Returns the parsed content type request header. Never returns
     *         null.
     */
    String getContentType();

    /**
     * @return Returns the request content (body) stream or null if there is no
     *         content stream.
     */
    InputStream getContentStream();

    /**
     * @return Returns the request content (body) as string or null if there is
     *         no content.
     */
    String getContentString() throws IOException;

    /**
     * @return Returns the request content (body) as byte[] or null if there is
     *         no content.
     *
     */
    byte[] getContentBytes() throws IOException;
}
//als Stream kommt rein, das ist ein network stream
//Response
//Was bekomme ich als Response zurück
//conten als String, strem oder array gesetzt werden
//ansonst alles private
//Add Header nachschaauen ob das drinnen ist: foo und bar