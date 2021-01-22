package idealworld.train.graalvm.image.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import idealworld.train.graalvm.image.ImageJsonExample;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 用于生成Native Image.
 *
 * @author gudaoxuri
 */
public class JsonTest {

    @Test
    public void testAll() throws JsonProcessingException, ClassNotFoundException {
        ImageJsonExample.main(new String[]{"{\"name\":\"测试\"}", "idealworld.train.graalvm.image.UserDTO"});
        Assertions.assertTrue(true);
    }

}
