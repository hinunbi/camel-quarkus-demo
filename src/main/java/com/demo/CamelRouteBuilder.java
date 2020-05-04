package com.demo;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.model.rest.RestBindingMode;

import java.util.concurrent.atomic.AtomicInteger;


public class CamelRouteBuilder extends RouteBuilder {

  static AtomicInteger readCount = new AtomicInteger();

  public void configure() {

    BindyCsvDataFormat biindy = new BindyCsvDataFormat(com.demo.UserInfo.class);

    restConfiguration()
        .host("localhost")
        .port(8080)
        .jsonDataFormat("json-gson")
        .bindingMode(RestBindingMode.json);

    from("timer://csv?period=5s")
        .routeId("csvProducerRoute")
        .setBody(simple("Gildong,Hong,${date:now}\nWoochi,Jun,${date:now}"))
        .to("file://input?fileName=userinfo.csv")
        .log("New userinfo.csv saved.");

    from("file://input?fileName=userinfo.csv&delete=true")
        .routeId("csvConsumerRoute")
        .log("Loaded userinfo.csv records\n${body}")
        .unmarshal(biindy)
        .split(body())
        .parallelProcessing()
        .process(new Processor() {
          @Override
          public void process(Exchange exchange) throws Exception {
            UserInfo userInfo = exchange.getIn().getBody(UserInfo.class);
            userInfo.setReadCount(readCount.incrementAndGet());
          }
        })
        .log("Call Rest API using ${body} ...")
        .to("rest:post:userinfo");
  }
}