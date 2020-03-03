// CheckStyle: start generated
package fr.cea.nabla.ir.truffle.nodes.expression;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaInt2ConstantNode;

@GeneratedBy(NablaInt2ConstantNode.class)
public final class NablaInt2ConstantNodeGen extends NablaInt2ConstantNode {

    private NablaInt2ConstantNodeGen(NablaExpressionNode[] values, NablaExpressionNode[] dimensions) {
        super(values, dimensions);
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        return executeNV2Int(frameValue);
    }

    @Override
    public NodeCost getCost() {
        return NodeCost.MONOMORPHIC;
    }

    public static NablaInt2ConstantNode create(NablaExpressionNode[] values, NablaExpressionNode[] dimensions) {
        return new NablaInt2ConstantNodeGen(values, dimensions);
    }

}
