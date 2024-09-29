import org.junit.jupiter.api.*;

public class MainTest {
    @Test
    @Timeout(value = 22)
    @Disabled
    void main() {
        try {
            Main.main(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
