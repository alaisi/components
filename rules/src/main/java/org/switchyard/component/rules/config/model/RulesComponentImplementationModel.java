/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
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

package org.switchyard.component.rules.config.model;

import java.util.List;

import org.switchyard.component.common.rules.config.model.ComponentImplementationModel;

/**
 * A "rules" ComponentImplementationModel.
 *
 * @author David Ward &lt;<a href="mailto:dward@jboss.org">dward@jboss.org</a>&gt; (C) 2011 Red Hat Inc.
 */
public interface RulesComponentImplementationModel extends ComponentImplementationModel {

    /**
     * The "rules" namespace.
     */
    public static final String DEFAULT_NAMESPACE = "urn:switchyard-component-rules:config:1.0";

    /**
     * The "rules" implementation type.
     */
    public static final String RULES = "rules";

    /**
     * Gets the child rules action models.
     * @return the child rules action models
     */
    public List<RulesActionModel> getRulesActions();

    /**
     * Adds a child rules action model.
     * @param rulesAction the child rules action model
     * @return this RulesComponentImplementationModel (useful for chaining)
     */
    public RulesComponentImplementationModel addRulesAction(RulesActionModel rulesAction);

    /**
     * Gets the child channel models.
     * @return the child channel models
     */
    public List<ChannelModel> getChannels();

    /**
     * Adds a child channel model.
     * @param channel the child channel model
     * @return this RulesComponentImplementationModel (useful for chaining)
     */
    public RulesComponentImplementationModel addChannel(ChannelModel channel);

    /**
     * Gets the child globals model.
     * @return the child globals model
     */
    public GlobalsModel getGlobals();

    /**
     * Sets the child globals model.
     * @param globals the child globals model
     * @return this RulesComponentImplementationModel (useful for chaining)
     */
    public RulesComponentImplementationModel setGlobals(GlobalsModel globals);

    /**
     * Gets the child facts model.
     * @return the child facts model
     */
    public FactsModel getFacts();

    /**
     * Sets the child facts model.
     * @param facts the child facts model
     * @return this RulesComponentImplementationModel (useful for chaining)
     */
    public RulesComponentImplementationModel setFacts(FactsModel facts);

}
