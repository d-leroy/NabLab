// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.expression.constant;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.nodes.expression.NablaExpressionNode;

@GeneratedBy(NablaBool3ConstantNode.class)
public final class NablaBool3ConstantNodeGen extends NablaBool3ConstantNode {

    private NablaBool3ConstantNodeGen(NablaExpressionNode value, NablaExpressionNode[] dimensions) {
        super(value, dimensions);
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        return executeNV3Bool(frameValue);
    }

    @Override
    public NodeCost getCost() {
        return NodeCost.MONOMORPHIC;
    }

    public static NablaBool3ConstantNode create(NablaExpressionNode value, NablaExpressionNode[] dimensions) {
        return new NablaBool3ConstantNodeGen(value, dimensions);
    }

}
