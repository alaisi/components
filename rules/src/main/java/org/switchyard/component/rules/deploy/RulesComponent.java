/* 
 * JBoss, Home of Professional Open Source 
 * Copyright 2011 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved. 
 * See the copyright.txt in the distribution for a 
 * full listing of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use, 
 * modify, copy, or redistribute it subject to the terms and conditions 
 * of the GNU Lesser General Public License, v. 2.1. 
 * This program is distributed in the hope that it will be useful, but WITHOUT A 
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A 
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details. 
 * You should have received a copy of the GNU Lesser General Public License, 
 * v.2.1 along with this distribution; if not, write to the Free Software 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, 
 * MA  02110-1301, USA.
 */
package org.switchyard.component.rules.deploy;

import org.switchyard.ServiceDomain;
import org.switchyard.component.common.rules.util.drools.ResourceChangeService;
import org.switchyard.config.Configuration;
import org.switchyard.deploy.Activator;
import org.switchyard.deploy.BaseComponent;

/**
 * An implementation of Rules component.
 *
 * @author Magesh Kumar B <mageshbk@jboss.com> (C) 2011 Red Hat Inc.
 */
public class RulesComponent extends BaseComponent {

    /**
     * Default constructor.
     */
    public RulesComponent() {
        super(RulesActivator.RULES_TYPE);
        setName("RulesComponent");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(Configuration config) {
        super.init(config);
        ResourceChangeService.start(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
        ResourceChangeService.stop(this);
        super.destroy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Activator createActivator(ServiceDomain domain) {
        RulesActivator activator = new RulesActivator();
        activator.setServiceDomain(domain);
        return activator;
    }

}
