package com.fj.gateway.util;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

// 鉴权
@Component
public class TokenFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {


        // 获取参数中的token
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        if (StringUtils.isEmpty(token)){
            System.out.println("拦截了。。。。");
            // 如果token为空则拦截
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);

            ServerHttpResponse response = exchange.getResponse();

            return exchange.getResponse().setComplete();
        }
        // 有权限，放行
        return chain.filter(exchange);
    }

    // 设置优先级，数字小，优先级越大
    @Override
    public int getOrder() {
        return 0;
    }
}
