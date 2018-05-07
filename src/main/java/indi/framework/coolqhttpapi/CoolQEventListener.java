package indi.framework.coolqhttpapi;

import indi.framework.coolqhttpapi.model.CoolQEvent;

/**
 * CoolQ事件监听器接口
 */
@FunctionalInterface
public interface CoolQEventListener {
    /**
     * 当接收到CoolQ事件时回调此函数
     */
    void handleEvent(CoolQEvent coolQEvent);
}
