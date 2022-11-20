package org.example.jedis;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 *
 * @author wxz
 * @date 21:43 2022/11/20
 */
public class MetaspaceDemo {

    static class OOMTest {}

    public static void main(String[] args) {
        int i = 0;
        try {
           while (true) {
               i++;
               Enhancer enhancer = new Enhancer();
               enhancer.setSuperclass(OOMTest.class);
               enhancer.setUseCache(false);
               enhancer.setCallback(new MethodInterceptor() {
                   @Override
                   public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                       return methodProxy.invokeSuper(o, args);
                   }
               });
               enhancer.create();
           }
        } catch (Throwable e) {
            System.out.println("多少次之后发生了异常: " + i);
            e.printStackTrace();
        }
    }
}
