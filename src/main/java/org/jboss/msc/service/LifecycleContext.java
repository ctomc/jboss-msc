/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2010, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.msc.service;

/**
 *
 */
public interface LifecycleContext {

    /**
     * Call within the service lifecycle method to trigger an <em>asynchronous</em> lifecycle action.  This action
     * will not be considered complete until indicated so by calling a method on this interface.
     *
     * @throws IllegalStateException if called outside of the main service lifecycle method
     */
    void asynchronous() throws IllegalStateException;

    /**
     * Call when an <em>asynchronous</em> lifecycle action is complete.
     *
     * @throws IllegalStateException if called before {@link #asynchronous()} is called, or if the action was already
     * completed
     */
    void complete() throws IllegalStateException;

    /**
     * Call to indicate the beginning of a unit of work for an <em>asynchronous</em> lifecycle action.  Should be followed
     * by a {@code try}/{@code finally} block which calls {@link #endTask()} from its {@code finally} section.  Calling
     * this method may interrupt the current thread if the lifecycle action is cancelled.
     *
     * @throws IllegalStateException if called before {@link #asynchronous()} is called, or if the action is already
     * complete
     */
    void beginTask() throws IllegalStateException;

    /**
     * Call to indicate the end of a unit of work for an <em>asynchronous</em> lifecycle action.  This method does not
     * throw any exceptions, and is safe to use in a {@code finally} block.
     */
    void endTask();
}
