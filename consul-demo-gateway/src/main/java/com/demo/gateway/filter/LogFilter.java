/*
 * Created by 2021-03-26 18:49:51 
 */
package com.demo.gateway.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**
 * @author fangang
 */
@Component
public class LogFilter implements GlobalFilter, Ordered {
	private static final Log log = LogFactory.getLog(LogFilter.class);
	@Override
	public int getOrder() {
		return 1;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
	     log.info(
	                String.format("send %s[method] request to %s[url]",
	                		request.getMethod(),
	                		request.getURI()));
		return chain.filter(exchange);
	}
}
