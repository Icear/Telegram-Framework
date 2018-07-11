package indi.icear.framework.coolqhttpapi;

import indi.icear.framework.coolqhttpapi.model.CoolQEvent;
import org.jetbrains.annotations.NotNull;

/**
 * CoolQ事件监听器接口
 */
@FunctionalInterface
public interface CoolQEventListener {
    /**
     * 当接收到CoolQ事件时回调此函数
     */
    void handleEvent(@NotNull CoolQEvent coolQEvent);
}
