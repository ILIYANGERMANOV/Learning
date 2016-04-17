import annotations.Test;
import annotations.TesterInfo;
import sun.rmi.runtime.Log;

@TesterInfo(createdBy = "Iliyan",
        priority = TesterInfo.Priority.HIGH,
        tags = {"ui, backend", "system"}
)
public class TestExample {
    @Test(enabled = false)
    void uiTest() {
        throw new RuntimeException("This test always failed");
    }

    @Test
    void backendTest() {
        if(1 < 2) {
            //this test is always passed
        }
    }

    @Test
    void systemTest() {
        throw new RuntimeException("This test always failed");
    }

    void uselessMethod(String name) {
        System.out.println("Useless method");
    }

}
