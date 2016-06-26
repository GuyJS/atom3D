import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GreeterTest {

    @Test
    public void shouldReturnGreeting(){
        Greeter greeter = new Greeter();
        String result = greeter.sayHello();
        assertThat( result, is("Hello, World!"));
    }
}
