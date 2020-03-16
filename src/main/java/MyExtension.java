import io.qameta.atlas.core.Atlas;
import io.qameta.atlas.core.api.Extension;
import io.qameta.atlas.core.api.MethodExtension;
import io.qameta.atlas.core.internal.Configuration;
import io.qameta.atlas.core.util.MethodInfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyExtension implements Extension, InvocationHandler, MethodExtension {
    private MainPage page;

    MyExtension(MainPage page) {
        this.page=page;
    }
    @Override
    public boolean test(final Method method) {
        return method.isAnnotationPresent(Selector.class);
    }
    @Override
    public String invoke(final Object proxy, Method method, Object [] args) {
        return method.getAnnotation(Selector.class).xpath();

    }

    @Override
    public Object invoke(Object o, MethodInfo methodInfo, Configuration configuration) throws Throwable {
        return null;
    }
}
