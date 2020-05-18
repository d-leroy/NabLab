// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.expression.constant;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.interpreter.nodes.expression.constant.NablaReal2ConstantNode;

@GeneratedBy(NablaReal2ConstantNode.class)
public final class NablaReal2ConstantNodeGen extends NablaReal2ConstantNode {

    private NablaReal2ConstantNodeGen(NablaExpressionNode value, NablaExpressionNode[] dimensions) {
        super(value, dimensions);
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        return executeNV2Real(frameValue);
    }

    @Override
    public NodeCost getCost() {
        return NodeCost.MONOMORPHIC;
    }

    public static NablaReal2ConstantNode create(NablaExpressionNode value, NablaExpressionNode[] dimensions) {
        return new NablaReal2ConstantNodeGen(value, dimensions);
    }

}
