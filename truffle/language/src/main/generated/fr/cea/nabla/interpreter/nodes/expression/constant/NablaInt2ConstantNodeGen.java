// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.expression.constant;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.interpreter.nodes.expression.constant.NablaInt2ConstantNode;

@GeneratedBy(NablaInt2ConstantNode.class)
public final class NablaInt2ConstantNodeGen extends NablaInt2ConstantNode {

    private NablaInt2ConstantNodeGen(NablaExpressionNode value, NablaExpressionNode[] dimensions) {
        super(value, dimensions);
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        return executeNV2Int(frameValue);
    }

    @Override
    public NodeCost getCost() {
        return NodeCost.MONOMORPHIC;
    }

    public static NablaInt2ConstantNode create(NablaExpressionNode value, NablaExpressionNode[] dimensions) {
        return new NablaInt2ConstantNodeGen(value, dimensions);
    }

}
