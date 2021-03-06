/*
 * JBoss, Home of Professional Open Source Copyright 2009, Red Hat Middleware
 * LLC, and individual contributors by the @authors tag. See the copyright.txt
 * in the distribution for a full listing of individual contributors.
 * 
 * This is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * 
 * This software is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this software; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA, or see the FSF
 * site: http://www.fsf.org.
 */
package org.switchyard.component.camel;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.xml.namespace.QName;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.junit.Before;
import org.junit.Test;
import org.switchyard.HandlerException;
import org.switchyard.Message;
import org.switchyard.Service;
import org.switchyard.ServiceReference;
import org.switchyard.component.camel.composer.CamelBindingData;
import org.switchyard.component.camel.composer.CamelComposition;
import org.switchyard.component.common.composer.MessageComposer;
import org.switchyard.metadata.ExchangeContract;
import org.switchyard.metadata.ServiceOperation;
import org.switchyard.metadata.java.JavaService;

/**
 * Unit test for {@link CamelResponseHandler}
 * 
 * @author Daniel Bevenius
 *
 */
public class CamelResponseHandlerTest {

    private static final QName JAVA_STRING_QNAME = new QName("java:java.lang.String");
    private static final QName SERVICE_QNAME = new QName("java:" + MockService.class.getName());

    private MessageComposer<CamelBindingData> _messageComposer;

    @Before
    public void before() {
        _messageComposer = CamelComposition.getMessageComposer();
    }

    @Test (expected = RuntimeException.class)
    public void constructor() throws Exception {
        final CamelResponseHandler responseHandler = new CamelResponseHandler(null, null, _messageComposer);
        responseHandler.handleMessage(null);
    }

    @Test
    public void handleMessageWithOutputType() throws HandlerException {
        final Exchange camelExchange = createMockCamelExchange();
        final ServiceReference serviceReference = createMockServiceRef();
        final org.switchyard.Exchange switchYardExchange = createMockExchangeWithBody(10);
        final CamelResponseHandler responseHandler = new CamelResponseHandler(camelExchange, serviceReference, _messageComposer);

        responseHandler.handleMessage(switchYardExchange);

        assertThat(switchYardExchange.getMessage().getContent(Integer.class), is(equalTo(new Integer(10))));
    }

    private Exchange createMockCamelExchange() {
        final Exchange camelExchange = mock(Exchange.class);
        final org.apache.camel.Message camelMessage = mock(org.apache.camel.Message.class);
        when(camelExchange.getPattern()).thenReturn(ExchangePattern.InOut);
        when(camelExchange.getIn()).thenReturn(camelMessage);
        when(camelExchange.getOut()).thenReturn(camelMessage);
        return camelExchange;
    }

    private ServiceReference createMockServiceRef() {
        final ServiceReference serviceReference = mock(ServiceReference.class);
        when(serviceReference.getInterface()).thenReturn(JavaService.fromClass(MockService.class));
        return serviceReference;
    }

    private org.switchyard.Exchange createMockExchangeWithBody(final Integer payload) {
        final org.switchyard.Exchange switchYardExchange = mock(org.switchyard.Exchange.class);
        final org.switchyard.Context switchYardContext = mock(org.switchyard.Context.class);
        final ExchangeContract exchangeContract = mock(ExchangeContract.class);
        final ServiceOperation serviceOperation = mock(ServiceOperation.class);
        final ServiceOperation referenceOperation = mock(ServiceOperation.class);
        final Service provider = mock(Service.class);

        final Message message = mock(Message.class);
        when(provider.getName()).thenReturn(SERVICE_QNAME);
        when(switchYardExchange.getProvider()).thenReturn(provider);
        when(message.getContent(Integer.class)).thenReturn(payload);
        when(switchYardExchange.getContext()).thenReturn(switchYardContext);
        when(switchYardExchange.getMessage()).thenReturn(message);
        when(referenceOperation.getOutputType()).thenReturn(JAVA_STRING_QNAME);
        when(exchangeContract.getProviderOperation()).thenReturn(serviceOperation);
        when(serviceOperation.getInputType()).thenReturn(JAVA_STRING_QNAME);
        when(serviceOperation.getOutputType()).thenReturn(null);
        when(serviceOperation.getFaultType()).thenReturn(null);
        when(exchangeContract.getConsumerOperation()).thenReturn(referenceOperation);
        when(switchYardExchange.getContract()).thenReturn(exchangeContract);

        return switchYardExchange;
        
    }

    private class MockService {
        @SuppressWarnings("unused")
        public void print(final String msg) {
        }
    }
    

}
