package helpers;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class TestStatus implements AfterTestExecutionCallback {
    public boolean isFailed;
    public boolean isFailed(){
        return isFailed;
    }
    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        isFailed = context.getExecutionException().isPresent();
    }
}
