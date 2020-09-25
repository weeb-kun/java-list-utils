/*
   Copyright 2020 weebkun

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

package com.weebkun.utils;

import com.weebkun.tree.Node;

/**
 * thrown when a node in a tree is already occupied.
 */
public class NodeOccupiedException extends RuntimeException{

    private Node<?> node;

    /**
     * thrown by specifying the supposedly occupied node.
     * @param node the node
     */
    public NodeOccupiedException(Node<?> node){
        this.node = node;
    }

    /**
     * thrown with a message and the occupied node.
     * @param message the message
     * @param node the node
     */
    public NodeOccupiedException(String message, Node<?> node){
        super(message);
        this.node = node;
    }

    /**
     * thrown with a message, the cause, and the occupied node.
     * @param message the message
     * @param cause the cause of this exception
     * @param node the node
     */
    public NodeOccupiedException(String message, Throwable cause, Node<?> node){
        super(message, cause);
        this.node = node;
    }

    /**
     * returns the supposedly occupied node.
     * @return the node
     */
    public Node<?> getNode(){
        return this.node;
    }
}
