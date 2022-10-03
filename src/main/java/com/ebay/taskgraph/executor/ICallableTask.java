/*
 * Copyright 2022 eBay Inc.
 *  Author/Developer: Damian Dolan
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.ebay.taskgraph.executor;

import java.util.concurrent.Callable;

import com.ebay.taskgraph.context.ResponseContext;

public interface ICallableTask<T> extends Callable<T> {

    String TASK_CAL_TYPE = "Task";
    
    /**
     * @return name of the task
     */
    String getName();

    /**
     * @return response context of the class 
     */
    ResponseContext getContext();
    
    /**
     * timeout for this task
     */
    CallableTaskConfig getTaskConfig();

    /**
     * Allows tasks to implement custom wait logic e.g. wait for either
     * one of two dependencies to complete.
     * Default implementation blocks for all dependencies to complete.
     */
    void waitForDependencies();
    
    /**
     * accessor for task dependencies
     */
    ICallableTaskFuture<?>[] getDependencies();
}