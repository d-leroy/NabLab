// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.expression.constant;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.interpreter.nodes.expression.constant.NablaReal3ConstantNode;

@GeneratedBy(NablaReal3ConstantNode.class)
public final class NablaReal3ConstantNodeGen extends NablaReal3ConstantNode {

    private NablaReal3ConstantNodeGen(NablaExpressionNode value, NablaExpressionNode[] dimensions) {
        super(value, dimensions);
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        return executeNV3Real(frameValue);
    }

    @Override
    public NodeCost getCost() {
        return NodeCost.MONOMORPHIC;
    }

    public static NablaReal3ConstantNode create(NablaExpressionNode value, NablaExpressionNode[] dimensions) {
        return new NablaReal3ConstantNodeGen(value, dimensions);
    }

}
