/*
 * Created by 2021-03-26 18:40:57 
 */
package com.demo.gateway.filter;

import java.net.InetSocketAddress;

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
 * The filter that get the host's ip.
 * @author fangang
 */
@Component
public class IpFilter implements GlobalFilter, Ordered {
	private static final Log log = LogFactory.getLog(IpFilter.class);
	@Override
	public int getOrder() {
		return 1;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
		InetSocketAddress remoteAddress = request.getRemoteAddress();
		log.info("[ ip: "+remoteAddress.getHostName()+" ]");
		return chain.filter(exchange);
	}

}
