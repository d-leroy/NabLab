// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.expression.constant;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.nodes.expression.NablaExpressionNode;

@GeneratedBy(NablaInt4ConstantNode.class)
public final class NablaInt4ConstantNodeGen extends NablaInt4ConstantNode {

    private NablaInt4ConstantNodeGen(NablaExpressionNode value, NablaExpressionNode[] dimensions) {
        super(value, dimensions);
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        return executeNV4Int(frameValue);
    }

    @Override
    public NodeCost getCost() {
        return NodeCost.MONOMORPHIC;
    }

    public static NablaInt4ConstantNode create(NablaExpressionNode value, NablaExpressionNode[] dimensions) {
        return new NablaInt4ConstantNodeGen(value, dimensions);
    }

}
