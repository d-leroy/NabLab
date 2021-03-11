// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.expression.constant;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.nodes.expression.NablaExpressionNode;

@GeneratedBy(NablaReal4ConstantNode.class)
public final class NablaReal4ConstantNodeGen extends NablaReal4ConstantNode {

    private NablaReal4ConstantNodeGen(NablaExpressionNode value, NablaExpressionNode[] dimensions) {
        super(value, dimensions);
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        return executeNV4Real(frameValue);
    }

    @Override
    public NodeCost getCost() {
        return NodeCost.MONOMORPHIC;
    }

    public static NablaReal4ConstantNode create(NablaExpressionNode value, NablaExpressionNode[] dimensions) {
        return new NablaReal4ConstantNodeGen(value, dimensions);
    }

}
