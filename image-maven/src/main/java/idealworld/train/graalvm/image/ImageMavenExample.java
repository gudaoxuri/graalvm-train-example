package idealworld.train.graalvm.image;

import java.lang.reflect.InvocationTargetException;

/**
 * native Image Maven示例.
 *
 * @author gudaoxuri
 */
public class ImageMavenExample {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        var user1 = new UserDTO();
        var user2 = (UserDTO) Class.forName("idealworld.train.graalvm.image.UserDTO").getDeclaredConstructor().newInstance();
        user1.setName("测试用户");
        user2.setName("测试用户");
        System.out.println(user1.getName());
        System.out.println(user2.getName());
    }

}
