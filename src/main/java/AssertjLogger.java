import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.UUID;

import static io.qameta.allure.util.ResultsUtils.getStatus;
import static io.qameta.allure.util.ResultsUtils.getStatusDetails;

@Aspect
public class AssertjLogger {
    private static AllureLifecycle lifecycle;

    public static AllureLifecycle getLifecycle() {
        if (lifecycle == null) lifecycle = Allure.getLifecycle();
        return lifecycle;
    }

    @Pointcut("call(* org.assertj.core.api.*.extracting(..))")
    public void extract() {
    }

    @Around("extract()")
    public Object extractingStep(ProceedingJoinPoint joinPoint) throws Throwable {
        return step(joinPoint, "Every element of list ");
    }

    Object step(ProceedingJoinPoint joinPoint, String name) throws Throwable {
        String uuid = UUID.randomUUID().toString();
        getLifecycle().startStep(uuid, new StepResult().setName(name));

        try {
            Object proceed = joinPoint.proceed();
            getLifecycle().updateStep(uuid, s -> s.setStatus(Status.PASSED));
            return proceed;
        } catch (Throwable e) {
            getLifecycle().updateStep(uuid, s -> s
                    .setStatus(getStatus(e)
                            .orElse(Status.BROKEN))
                    .setStatusDetails(getStatusDetails(e)
                            .orElse(null)));
            throw e;
        } finally {
            getLifecycle().stopStep(uuid);
        }
    }
}
