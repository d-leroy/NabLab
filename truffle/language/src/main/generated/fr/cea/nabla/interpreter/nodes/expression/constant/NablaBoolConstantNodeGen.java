// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.expression.constant;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.nodes.expression.constant.NablaBoolConstantNode;

@GeneratedBy(NablaBoolConstantNode.class)
public final class NablaBoolConstantNodeGen extends NablaBoolConstantNode {

    private NablaBoolConstantNodeGen(boolean value) {
        super(value);
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        return executeNV0Bool(frameValue);
    }

    @Override
    public NodeCost getCost() {
        return NodeCost.MONOMORPHIC;
    }

    public static NablaBoolConstantNode create(boolean value) {
        return new NablaBoolConstantNodeGen(value);
    }

}
