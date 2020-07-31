package com.greedystar.generator.invoker.base;

/**
 * @author GreedyStar
 * @since 1.0.0, 2018/9/5
 */
public abstract class AbstractBuilder {

    public abstract Invoker build();

    public boolean isParamtersValid() {
        try {
            checkBeforeBuild();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public abstract void checkBeforeBuild() throws Exception;

}
