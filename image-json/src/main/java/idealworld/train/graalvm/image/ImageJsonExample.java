package idealworld.train.graalvm.image;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * native Image Json示例.
 *
 * @author gudaoxuri
 */
public class ImageJsonExample {

    private static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws JsonProcessingException, ClassNotFoundException {
        var obj = mapper.readValue(args[0],Class.forName(args[1]));
        System.out.println(mapper.writeValueAsString(obj));
    }

}
